package com.cst.service;

import com.cst.config.HttpReq;
import com.cst.contants.ConstantsValue;
import com.cst.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WxService {

    @Value("${wx.appid}")
    public String APPID;
    @Value("${wx.secret}")
    public String SECRET;

    public User getUserInfo(String token, String openid) {
        String str = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=ACCESS_TOKEN" +
                "&openid=OPENID&lang=zh_CN";
        str = str.replace("ACCESS_TOKEN", token)
                .replace("OPENID", openid);
        String result = HttpReq.sendGet(str.split("\\?")[0], str.split("\\?")[1]);
        System.err.println("userInfo" + result);
        return User.builder().openid(openid).build();
    }


    public String getOpenIdByCode(String code) {
        String str = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=APPID" +
                "&secret=SECRET" +
                "&code=CODE" +
                "&grant_type=authorization_code";
        str = str.replace("APPID", APPID)
                .replace("SECRET", SECRET)
                .replace("CODE", code);
        String s = HttpReq.sendGet(str.split("\\?")[0], str.split("\\?")[1]);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
            String openid = jsonObject.getString("openid");
            return openid;
//            String access_token = jsonObject.getString("access_token");
//            String scope = jsonObject.getString("scope");
//            if(ConstantsValue.LOGIN_WAY.equals(scope)){
//                User user = getUserInfo(access_token, openid);
//                return user.getOpenid();
//            }else{
//                return openid;
//            }
        } catch (JSONException e) {
            log.error("获取的用户信息解析异常:{}", e);
            return null;
        }
    }
}
