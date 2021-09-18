package com.cst.controller.api;


import com.cst.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController {



    public static Map<String, User> map = new HashMap<>();
    static  {
        map.put("1",new User());
        map.put("2",new User());
        map.put("3",new User());
        map.put("4",new User());
    }



    @RequestMapping(value = "/ssePage")
    public String ssePage(String id){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sse";
    }
    @RequestMapping(value = "/sse",produces = "text/event-stream")
    @ResponseBody
    public String sse(String id){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "data:"+Math.random();
    }



}
