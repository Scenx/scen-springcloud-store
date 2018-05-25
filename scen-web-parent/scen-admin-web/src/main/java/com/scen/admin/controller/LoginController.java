package com.scen.admin.controller;

import com.scen.common.utils.Captcha;
import com.scen.pojo.AdminUser;
import com.scen.vo.ScenResult;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 登录控制器
 *
 * @author Mr chen
 */
@RestController("loginController")
public class LoginController {

    @Autowired
    Captcha capture;

    /**
     * 获取图形验证码
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/drawCheckCode")
    public void drawCheckCode(HttpServletResponse response, HttpSession session)
            throws IOException {
        response.setContentType("image/jpg");
        String captcha = capture.generateCheckcode();
        session.setAttribute(Captcha.captchaParam, captcha);
        OutputStream os = response.getOutputStream();
        ImageIO.write(capture.generateCheckImg(captcha), "jpg", os);
    }

    @RequestMapping("/adminLogin")
    public ScenResult adminLogin(HttpSession session, AdminUser adminUser, String captcha) {
        String imgCode = (String) session.getAttribute(Captcha.captchaParam);
        if (!imgCode.equals(captcha)) {
            return ScenResult.build(400, "验证码错误", "验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(adminUser.getUserName(), adminUser.getPassword());
        try {
            subject.login(token);
            return ScenResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(400, "认证失败", "认证失败");
        }
    }
}
