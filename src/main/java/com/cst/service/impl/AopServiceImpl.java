package com.cst.service.impl;

import com.cst.service.AopService;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {

    @Override
    public int div(int x, int y) {
        System.out.println("方法被调用：" + x + "" + y);
        return x / y;
    }
}
