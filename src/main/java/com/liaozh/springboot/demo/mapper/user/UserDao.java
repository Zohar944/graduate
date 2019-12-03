package com.liaozh.springboot.demo.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liaozh.springboot.demo.model.sql.user.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public interface UserDao extends BaseMapper<User> {
    User userLoginQuery(@Param("userId") Integer userId, @Param("password") String password);
    int userRegister(@RequestBody @Valid User user);
    int userEdit(@RequestBody @Valid User user);
}
