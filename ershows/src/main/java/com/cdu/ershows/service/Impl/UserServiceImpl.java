package com.cdu.ershows.service.Impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.cdu.ershows.dto.Result;
import com.cdu.ershows.mapper.UserMapper;
import com.cdu.ershows.pojo.User;
import com.cdu.ershows.service.UserService;
import com.cdu.ershows.util.JWTUtil;
import com.cdu.ershows.util.MailSenderUtil;
import com.cdu.ershows.util.RegexUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
   @Resource
   StringRedisTemplate stringRedisTemplate;
   @Resource
   MailSenderUtil mailSenderUtil;
   @Resource
   UserMapper userMapper;
   @Override
   public Result sendCode(String email) {
      if(RegexUtils.isEmailInvalid(email)){
         return Result.fail("邮箱不合法!");
      }
      //生成验证码
      String code= RandomUtil.randomNumbers(6);
      mailSenderUtil.sendEmailCode(code,"1782252415@qq.com",email);
      stringRedisTemplate.opsForValue().set(email,code);
      return Result.ok("发送成功!");
   }

   @Override
   public Result addUser(User user) {
      if(RegexUtils.isEmailInvalid(user.getEmail())){
         return Result.fail("邮箱不合法!");
      }
      String codei= stringRedisTemplate.opsForValue().get(user.getEmail());
      if(StrUtil.isBlank(codei)){
         return Result.fail("验证码错误");
      }
      if(!codei.equals(user.getCode())){
         return Result.fail("验证码错误");
      }
      try {
         userMapper.insert(user);
      } catch (Exception e) {
         e.printStackTrace();
         return Result.fail("该用户已经注册!");
      }
      return Result.ok("用户注册成功!");
   }

   @Override
   public Result login(User user) {
      Map<String ,Object> map=new HashMap<>();
      map.put("email",user.getEmail());
      map.put("password",user.getPassword());
      List<User> users=userMapper.selectByMap(map);
      if(users==null||users.isEmpty()){
         return Result.fail("邮箱或密码错误");
      }
      user=users.get(0);
      user.setToken(JWTUtil.creatToken(users.get(0)));
      return Result.ok(user);
   }
}
