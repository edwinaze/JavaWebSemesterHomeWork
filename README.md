
# 项目选型

- SpringBoot
- Hibernate JPA
- Thymeleaf
- Lombok 
- Kaptcha

JDK：21 GraaLvm 
如果本机已有其他版本JDK且配过环境，若无法部署运行的花，请使用本目录下的 压缩包 `graalvm-jdk-21_windows-x64_bin`，然后 cmd 进入到安装目录/bin，执行 `java -jar [21164295JavaWeb.jar的绝对路径]` 即可。或者重新配一下PATH。


执行方法：
1. 进入cmd命令行窗口，执行 `java -jar 211164295JavaWeb.jar` 即可一键运行，无需配置tomcat以及项目环境，只需要对应版本的JDK即可
2. 手动配置则需要先导入项目后，在Maven选项卡中更新Maven插件以及下载依赖
![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/6bcfac4d-e7a8-40ed-98bb-56d162929ed9)


执行完成后进入浏览器访问：`http://localhost:8080/` 即可。
# 页面介绍

登录页：![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/bd2affd6-afe6-4c3e-b68b-80153991190e)


左侧3D模型可以交互，初次进入需要一些时间加载，右侧表单支持实时校验，本项目支持全页面登录拦截，如果没有登录访问其他页面会自动重定向到登录页面。

注册页：
![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/22c3d184-bdfc-4219-9fae-f4f0e74d55b9)

同登录页一致，当用户名有重复时会提醒。

首页：
![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/85deef28-41bd-4d6e-b5f6-8aa218644b01)

左侧为导航栏，右上角为修改密码和退出登录的入口。

零件信息维护：
![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/0aba14f5-0bdb-44f2-9f99-9b89caf6da98)

- “新增” ： 直接跳转到添加零件信息页面
- “批量删除”：需要先勾选表格中第一行的多选框，然后会弹出一个确认窗口，确认后进行批量删除操作
- 分页表格：当数据量到达10条以上时，会进行分页
- 搜索框：左侧的下拉框可以选择对应的搜索字段，输入关键词后按回车/右侧搜索按钮即可搜索，如果关键字为空则默认为全部查询
- 每行信息右侧的两个按钮：
	- 修改：弹出模态框修改信息
	- 删除：删除改行

用户信息操作：
![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/ea5622b1-2da4-458d-892b-b2a7713c66d2)

任意页面点击右上角即可显示

修改密码：
![图片](https://github.com/edwinaze/JavaWebSemesterHomeWork/assets/53467361/5e7edbcf-0e52-4ffe-96a6-0bc471096ae9)

支持动态实时检测

