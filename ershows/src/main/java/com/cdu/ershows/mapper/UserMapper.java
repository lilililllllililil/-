package com.cdu.ershows.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.ershows.pojo.User;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Repeatable;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User>{
   //根据id查询用户信息为map集合
   Map<String,Object> selectMapById(Long id);
}
