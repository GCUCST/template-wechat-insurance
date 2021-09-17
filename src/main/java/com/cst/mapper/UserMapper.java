package com.cst.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cst.entities.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findById(long id);
    List<User> list(@Param("page") int page, @Param("size")int size);
    List<User> listByOpenId(@Param("recommenderId") String recommenderId,@Param("page") int page, @Param("size")int size);

}

