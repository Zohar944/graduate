package com.liaozh.springboot.demo.service;

import com.liaozh.springboot.demo.model.dto.userdto.UserLoginRequestDto;
import com.liaozh.springboot.demo.model.dto.userdto.UserLoginResponseDto;
import com.liaozh.springboot.demo.model.sql.user.User;

public interface UserService {
    UserLoginResponseDto userLogin(UserLoginRequestDto userLoginRequestDto);

    Integer userRegister(User userRequestDto);

    User addUser();

    User deleteUser();

    String userEdit(User user);

    User consumptionData();

    User queryConsumption();

}
