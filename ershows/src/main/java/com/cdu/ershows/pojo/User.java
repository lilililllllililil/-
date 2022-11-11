package com.cdu.ershows.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * User
 */
@TableName("user")
@Data
public class User implements Serializable {
//   private static final long serialVersionUID = 1L;
   /**
    * 用户ID
    */
   @TableId(type = IdType.AUTO)
   private String id;
   /**
    * 用户名
    */
   private String username;
   /**
    * 密码
    */
   private String password;
   /**
    * 邮箱
    */
   private String email;

   @TableField(exist = false)
   private String code;

   @TableField(exist = false)
   private String token;

}