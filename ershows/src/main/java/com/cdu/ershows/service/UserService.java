package com.cdu.ershows.service;

import com.cdu.ershows.dto.Result;
import com.cdu.ershows.pojo.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
   Result sendCode(String phone);
   Result addUser(User user);
   Result login(User user);
}
