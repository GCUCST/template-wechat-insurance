package com.cst.controller;

import com.cst.contants.ConstantsValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class PageController {

    @Value("${wx.appid}")
    public String APPID;
    @Value("${wx.secret}")
    public String SECRET;

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request, String recommenderId, HttpSession session) {
        String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + APPID +
                "&redirect_uri=" + request.getScheme()+"://" + request.getServerName() + "/api/wx/callback/userInfo" +
                "&response_type=code" +
                "&state=" + recommenderId +
                "&scope=" + ConstantsValue.LOGIN_WAY +
                "&connect_redirect=1#wechat_redirect";
        model.addAttribute("redirectUrl", redirectUrl);
        if(StringUtils.hasLength(recommenderId)){
            session.setAttribute(recommenderId,recommenderId);
        }
        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/finish")
    public String finish() {
        return "finish";
    }
}
