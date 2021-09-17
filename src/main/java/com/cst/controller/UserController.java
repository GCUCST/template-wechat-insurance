package com.cst.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cst.entities.User;
import com.cst.mapper.UserMapper;
import com.cst.service.UserService;
import com.cst.utils.ResModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController extends BaseController{

    final UserService userService;

    public UserController(UserService userMapper) {
        this.userService = userMapper;
    }
    @PostMapping
    public ResModel add(@RequestBody User user) {
        boolean save = userService.save(user);
        log.info("插入：{}",save);
        return ResModel.ok(user);
    }

    @GetMapping("/list")
    public ResModel list() {
        List<User> list = userService.list(null);
        log.info("list：{}",list);
        return ResModel.ok(list);
    }

    @GetMapping("/page")
    public ResModel page(@RequestParam(value = "page",defaultValue = "0") Long page,
                         @RequestParam(value = "size",defaultValue = "3") Long size) {
        Page<User> pageObj = new Page(page,size);
        Page<User> result = userService.page(pageObj,null);
        long total = result.getTotal();
        long current = result.getCurrent();
        System.out.println(total+" "+current);
        log.info("list：{}",result.getRecords());
        return ResModel.ok(result);
    }


//    @GetMapping("/wrap")
//    public ResModel wrap() {
//      //  List<User> list = userService.query().eq("name", "陈少桐").list();
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.in("name","陈少桐");
//        queryWrapper.select("openid");
//        String sqlSelect = queryWrapper.getSqlSelect();
//        String sqlSelgetTargetSqlect = queryWrapper.getTargetSql();
//        System.out.println(sqlSelgetTargetSqlect);
//        List list2 = userService.list(queryWrapper);
//
//        System.out.println(list2);
//        return ResModel.ok();
//    }
    @GetMapping("/wrap")
    public ResModel wrap() {
      //  List<User> list = userService.query().eq("name", "陈少桐").list();
        Map<String,String> parmas = new HashMap();
        parmas.put("recommenderId","abc");
        parmas.put("name","abc");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.allEq((k,v)->{

            //把name这个过滤条件去掉
            if(k.toString().equals("name")){
                return false;
            }

            return true;
        },parmas,false);
        queryWrapper.isNotNull("name");

        List<User> list = userService.list(queryWrapper);
        System.out.println(list);
        System.out.println(list.size());


        return ResModel.ok(list);
    }
//    @GetMapping("/listByOpenId")
//    public ResModel listByOpenId(int page,int size) {
//        List<User> list = userMapper.listByOpenId(getUser().getOpenid(),page, size);
//        log.info("listByOpenId：{}",list);
//        return ResModel.ok(list);
//    }


}
