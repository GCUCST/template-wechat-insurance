package com.cst.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@TableName("t_user")
public class User implements Serializable {

    @TableId(type=IdType.AUTO)
    String id;

    String name;
    String openid;
    String recommenderId;
    String contactPhone;
    String gender;
    String other;
}
