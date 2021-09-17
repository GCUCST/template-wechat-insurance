package com.cst.contants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantsValue {

    public String WX_TOKEN = "wx";
    public String LOGIN_WAY = "snsapi_base";  //无需用户点击授权，仅可获得openid
//    public String LOGIN_WAY = "snsapi_userinfo";  //需要用户点击授权，可以通过获得的id去获取userInfo


}
