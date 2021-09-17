package com.cst.controller.wx;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.cst.config.HttpReq;
import com.cst.controller.BaseController;
import com.cst.entities.User;
import com.cst.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/api/wx")
public class WxController extends BaseController {

    @Value("${wx.appid}")
    public String APPID;
    @Value("${wx.secret}")
    public String SECRET;


    public final WxService wxService;

    public WxController(WxService wxService) {
        this.wxService = wxService;
    }

    @GetMapping("/qrcode/generate")
    public ModelAndView generateQrCode(HttpServletRequest request) {
        User user = getUser();
        String recommenderId = user.getOpenid();
        log.info(request.getServerName());
        QrConfig qrConfig = new QrConfig();
        qrConfig.setHeight(300);
        qrConfig.setWidth(300);
        String jpg = QrCodeUtil.generateAsBase64(request.getScheme()+"://" + request.getServerName() + "/home?recommenderId=" + recommenderId, qrConfig, "jpg");
        Map mp = new HashMap();
        mp.put("imageData", jpg);
        ModelAndView invite = new ModelAndView("invite", mp);
        return invite;
    }


    // 获取推广公众号使用的二维码
//    public String getExtOfficialAccountQrCode() {
//        String token = getAccessToken();
//        String result = HttpReq.sendPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token,
//                "{\"expire_seconds\": 604800, " +
//                        "\"action_name\": \"QR_STR_SCENE\", " +
//                        "\"action_info\": {\"scene\": {\"scene_str\": \"userId\"}}}");
//        return result;
//    }

    //获取公众号的AccessToken
    public String getAccessToken() {
        String str = "https://api.weixin.qq.com/cgi-bin/token" +
                "?grant_type=client_credential" +
                "&appid="+APPID +
                "&secret="+SECRET;
        String s = HttpReq.sendGet(str.split("\\?")[0], str.split("\\?")[1]);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //将接收到的xml文件转换为map
//    public Map parseXml(InputStream is) throws DocumentException {
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(is);
//        Element rootElement = document.getRootElement();
//        List<Element> elements = rootElement.elements();
//        Map<String, String> mp = new HashMap();
//        for (Element element : elements) {
//            mp.put(element.getName(), element.getStringValue());
//
//        }
//        return mp;
//    }


    //公众号接收信息
//    @PostMapping
//    @ResponseBody
//    public String recMsg(HttpServletRequest request) {
//        Map map = null;
//        try {
//            map = parseXml(request.getInputStream());
//            ServletInputStream is = request.getInputStream();
//            System.out.println(map.toString());
//            return "<xml>\n" +
//                    "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>\n" +
//                    "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>\n" +
//                    "<CreateTime>" + 12345678 + "</CreateTime>\n" +
//                    "<MsgType><![CDATA[text]]></MsgType>\n" +
//                    "<Content><![CDATA[你好]]></Content>\n" +
//                    "</xml>";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
