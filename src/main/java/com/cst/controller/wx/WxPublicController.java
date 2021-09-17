package com.cst.controller.wx;


import com.cst.entities.User;
import com.cst.exception.AesException;
import com.cst.service.WxService;
import com.cst.utils.WxPublicUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/api/wx/callback")
public class WxPublicController {

    public final WxService wxService;

    public WxPublicController(WxService wxService) {
        this.wxService = wxService;
    }

    @GetMapping("/valid")
    @ResponseBody
    public String wxValid(HttpServletRequest request) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WxPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
            log.info("verifyWXToken----------->END");
            return echostr;
        }
        return null;
    }

    @RequestMapping("/userInfo")
    public ModelAndView getUserInfoCallBack(String code, String state, HttpSession session) throws JSONException {
        String openId = wxService.getOpenIdByCode(code);
        //登录成功
        if (StringUtils.hasLength(openId)) {
            ModelAndView mv ;
//            if("/api/wx/qrcode/generate".equals(session.getAttribute("targetUri"))){
//                mv = new ModelAndView("redirect:/api/wx/qrcode/generate");
//            }else{
//                mv = new ModelAndView("redirect:/index");
//            }
            if(session.getAttribute("targetUri")!=null){
                mv = new ModelAndView("redirect:"+session.getAttribute("targetUri").toString());
                session.removeAttribute("targetUri");
            }else{
                mv = new ModelAndView("redirect:/index");
            }

            User user = User.builder().openid(openId).build();
            mv.addObject("user", user);
            session.setAttribute("user", user);
            session.setAttribute("recommenderId", state);
            return mv;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }


}

