package com.cdu.ershows;

import com.cdu.ershows.mapper.UserMapper;
import com.cdu.ershows.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ErshowsApplicationTests {

   @Resource
   private UserMapper xxuserMapper;
   @Test
   void contextLoads() {
      //查询所有用户
      List<User> list= xxuserMapper.selectList(null);
      list.forEach(System.out::println);
   }

   @Test
   public void testInsert(){
      User user = new User();
      user.setId("1");
      user.setUsername("苏昊天");
      user.setPassword("6");
      user.setEmail("landon.schumm@gmail.com");
//      user.setCreated();
//      user.setUpdated("2022-11-01 18:49:51");
      int result=xxuserMapper.insert(user);
      System.out.println(result);
   }

   @Test
   public void testDelete(){
//      根据id删除
//      int result=xxuserMapper.deleteById(1);
//      System.out.println(result);

//      多条件删除
//      Map<String,Object> map=new HashMap<>();
//      map.put("Username","张三");
//      map.put("address","遵义");
//      xxuserMapper.deleteByMap(map);

//      根据多个id进行多个删除
//      List<Long> list= Arrays.asList(1L,2L,3L);
//      int result=xxuserMapper.deleteBatchIds(list);
//      System.out.println("result:"+result);
   }

   @Test
   public void update(){
      User user = new User();
      user.setId("4");
      user.setUsername("苏昊天");
      user.setPassword("6");
      user.setEmail("landon.schumm@gmail.com");
//      user.setCreated("2022-08-20 21:55:01");/
//      user.setUpdated("2022-11-01 18:49:51");
      int result=xxuserMapper.updateById(user);
      System.out.println(result);
   }

   @Test
   public void testSELECT(){
      //通过id来查询用户信息
//      User user=xxuserMapper.selectById(4L);
//      System.out.println(user);
//      查询多个id的用户信息
//      List<Long> list=Arrays.asList(4L,5L,6L);
//      List<User> list1=xxuserMapper.selectBatchIds(list);
//      list1.forEach(System.out::println);

//      查询特定条件的
//         Map<String,Object> map=new HashMap<>();
//         map.put("username","严正豪");
//         map.put("password","505725");
//         List<User> list=xxuserMapper.selectByMap(map);
//         list.forEach(System.out::println);
//         查询所有数据
      List<User> list=xxuserMapper.selectList(null);

      list.forEach(System.out::println);
      
   }
   @Test
   public void estSelect(){
      //自定义SQL
      Map<String,Object> map=xxuserMapper.selectMapById(4L);
      System.out.println(map);

   }

   // 用于发送邮件的对象
   @Resource
   private JavaMailSender javaMailSender;

   /**
    * 发送邮件
    * @param code 验证码
    * @param from 发送验证码的邮箱
    * @param to 接收验证码的邮箱
    * @return true 发送成功，否则发送失败
    */
   public boolean sendEmailCode(String code, String from, String to) {
      try {
         // 用来设置邮件信息
         SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
         // 设置邮件标题
         simpleMailMessage.setSubject("登录验证码");
         // 设置邮件内容
         simpleMailMessage.setText("您收到的验证码是：" + code);
         // 设置源邮箱
         simpleMailMessage.setFrom(from);
         // 设置目的邮箱
         simpleMailMessage.setTo(to);
         // 发送
         javaMailSender.send(simpleMailMessage);
         // 没有出现异常，正常发送，返回true
         return true;
      } catch (MailException e) {
         // 发送过程中，发生错误，打印错误信息，返回false
         e.printStackTrace();
         return false;
      }
   }

   @Test
   public void test1() {
      // 设置验证码，可以自己随机生成
      String code = "011635";
      boolean b = sendEmailCode(code, "1782252415@qq.com", "3109599256@qq.com");
   }
}
