package com.cst.controller;


import com.cst.entities.Dog;
import com.cst.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class PageController {


//    @GetMapping("/test")
//    public String intestdex(Model model, HttpSession session){
//        Dog dog = Dog.builder().name("小旺财").build();
//        User user = User.builder().name("小旺财").build();
//        User userSession = User.builder().name("小旺财session").build();
//        session.setAttribute("userSession",userSession);
//        model.addAttribute(dog);
//        model.addAttribute(user);
//        return "test";
//    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session){

        return "index";
    }




}
