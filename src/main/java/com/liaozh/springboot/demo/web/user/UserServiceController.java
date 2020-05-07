package com.liaozh.springboot.demo.web.user;

import com.liaozh.springboot.demo.model.dto.basedto.ResponseDto;
import com.liaozh.springboot.demo.model.dto.userdto.UserLoginRequestDto;
import com.liaozh.springboot.demo.model.dto.userdto.UserLoginResponseDto;
import com.liaozh.springboot.demo.service.UserService;
import com.liaozh.springboot.demo.model.sql.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User", description = "用户接口")
@RestController
@RequestMapping("userservice")
@CrossOrigin
public class UserServiceController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登陆")
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ResponseDto<UserLoginResponseDto> userLoginService(Integer userId, String password) {
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setUserId(userId);
        userLoginRequestDto.setPassword(password);
            return new ResponseDto("200", "操作成功", userService.userLogin(userLoginRequestDto));
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ResponseDto<UserLoginResponseDto> userRegister(@RequestBody @Valid User userRequestDto) {
        Integer data = userService.userRegister(userRequestDto);
//        if(StringUtils.isEmpty(data)) {
        if(data != null) {
            return new ResponseDto("200","注册成功",data);
        }else {
            return new ResponseDto("200","注册失败","手机号码已注册");
        }
    }

    @ApiOperation(value = "编辑信息")
    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public ResponseDto<User> userEdit(User user) {
        return new ResponseDto("200","修改成功",userService.userEdit(user));
    }

    @ApiOperation(value = "创建消费条数")
    @RequestMapping(value = "/consumptionData", method = RequestMethod.POST)
    public User consumptionData() {
        return null;
    }

    @ApiOperation(value = "查询消费")
    @RequestMapping(value = "/queryConsumption", method = RequestMethod.POST)
    public User queryConsumption() {
        return null;
    }


}
