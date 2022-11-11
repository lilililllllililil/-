package com.cdu.ershows.controller;

import com.cdu.ershows.dto.Result;
import com.cdu.ershows.mapper.UserMapper;
import com.cdu.ershows.pojo.User;
import com.cdu.ershows.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
   @Resource
   UserMapper userMapper;
   @Resource
   UserService userService;

   @GetMapping("/index")
   public Result index(){
      return Result.ok(userMapper.selectList(null));
   }

   /**
    * 发送邮箱验证码
    */
   @PostMapping("code")
   public Result sendCode(@RequestParam("email") String email) {
      // TODO 发送短信验证码并保存验证码
      return userService.sendCode(email);
   }

   @PostMapping("/addUser")
   public Result addUser(@RequestBody User user){
      return userService.addUser(user);
   }

   @GetMapping("/login")
   public Result login(@RequestBody User user){
      return userService.login(user);
   }

}
