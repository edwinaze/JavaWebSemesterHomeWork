package com.manager.javawebsemesterhomework.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.manager.javawebsemesterhomework.entity.VO.Response;
import com.manager.javawebsemesterhomework.service.CustomerService;
import com.manager.javawebsemesterhomework.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/30 21:00
 */

@Controller
@Slf4j
public class AuthController {

    @Resource
    CustomerService customerService;
    @GetMapping({"/", "/index"})
    public String index(HttpSession httpSession, Model model) {
        Integer count = customerService.findAllCount();
        String username = (String) httpSession.getAttribute("username");
        log.info("username: " + username);
        model.addAttribute("username", username);
        model.addAttribute("count", count);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Response register(String username, String password, String captcha,HttpSession session, Model model) {
        log.info("username: " + username + ", password: " + password + ", captcha: " + captcha);
        String sessionCaptcha = (String) session.getAttribute("captcha");
        log.info("sessionCaptcha: " + sessionCaptcha);
        if (captcha == null || !captcha.equals(sessionCaptcha)) {
            model.addAttribute("isCaptchaFailure", true);
            return Response.failure("验证码错误");
        }
        Response response = userService.registerUser(username, password);
        if (response.isSuccess()) {
            session.setAttribute("username", username);
        }
        return response;
    }

    @Resource
    UserService userService;


    @PostMapping(value = "/checkLogin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response login(String username) {
        // 检测用户名是否存在
        log.info("username: " + username);
        return userService.checkUser(username);
    }

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response login(String username,String password, String captcha, HttpSession httpSession, Model model) {
        log.info("username: " + username + ", password: " + password + ", captcha: " + captcha);
        String sessionCaptcha = (String) httpSession.getAttribute("captcha");
        log.info("sessionCaptcha: " + sessionCaptcha);
        if (captcha == null || !captcha.equals(sessionCaptcha)) {
            model.addAttribute("isCaptchaFailure", true);
            return Response.failure("验证码错误");
        }
        Response response = userService.authenUser(username, password);
        if (response.isSuccess()) {
            httpSession.setAttribute("username", username);
        }
        return response;
    }

    @Resource
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/captcha")
    @ResponseBody
    public void generateCaptcha(HttpServletResponse response, HttpSession session) throws Exception {
        // 生成验证码字符串
        String code = defaultKaptcha.createText();
        // 将验证码字符串放到session中
        session.setAttribute("captcha", code);
        log.info("captcha: " + session.getAttribute("captcha"));

        // 生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", jpegOutputStream);

        // 设置响应类型
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getOutputStream().write(jpegOutputStream.toByteArray());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        return "redirect:/login";
    }

    @GetMapping("/user/modify")
    public String modify(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        model.addAttribute("username", username);
        return "modify";
    }

    @PostMapping(value = "/user/modify", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response modify(String password, HttpSession session) {
        log.info("newPassword: " + password);
        String username = (String) session.getAttribute("username");
        Response response = userService.modifyUser(username, password);
        if(response.isSuccess()) {
            session.removeAttribute("username");
        }
        return response;
    }

    @PostMapping(value = "/user/checkPassword", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response checkPassword(String password, HttpSession session) {
        String username = (String) session.getAttribute("username");
        log.info("username: " + username + ", password: " + password);
        Response response = userService.authenUser(username, password);
        if (response.isSuccess()) {
            return Response.success("密码正确");
        }
        return Response.failure("旧密码错误");
    }

}
