package com.liaozh.springboot.demo.web.user;

import com.liaozh.springboot.demo.model.sql.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "userBehavior ",description = "用户行为管理")
@RestController
@RequestMapping("userFriend")
public class UserFriendController {

    @ApiOperation(value = "添加好友")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser() {

        return null;
    }

    @ApiOperation(value = "删除好友")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public User deleteUser() {
        return null;
    }
}
