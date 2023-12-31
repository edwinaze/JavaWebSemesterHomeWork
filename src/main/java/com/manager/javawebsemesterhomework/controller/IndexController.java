package com.manager.javawebsemesterhomework.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.manager.javawebsemesterhomework.entity.VO.Response;
import com.manager.javawebsemesterhomework.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.http.HttpResponse;

/**
 * @Author: Aze
 * @Description: TODO
 * @Date: 2023/12/30 21:00
 */

@Controller
@Slf4j
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("isCaptchaFailure", false);
        return "login";
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
        return userService.authenUser(username, password);
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
}
