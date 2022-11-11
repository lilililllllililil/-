package com.cdu.ershows;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cdu.ershows.mapper")//扫描指定mapper接口
public class ErshowsApplication {
   public static void main(String[] args) {
      SpringApplication.run(ErshowsApplication.class, args);
   }

}
