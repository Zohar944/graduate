package com.liaozh.springboot.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liaozh.springboot.demo.mapper.user.UserDao;
import com.liaozh.springboot.demo.model.dto.userdto.UserLoginRequestDto;
import com.liaozh.springboot.demo.model.dto.userdto.UserLoginResponseDto;
import com.liaozh.springboot.demo.model.sql.user.User;
import com.liaozh.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 传入登陆账号密码，回应是否准确
     *
     * @param userLoginRequestDto
     * @return
     */
    @Override
    public UserLoginResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {
        int userId = userLoginRequestDto.getUserId();
        String password = userLoginRequestDto.getPassword();
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setStatus("ERROR");
        User user = userDao.userLoginQuery(userId, password);
        if (user != null) {
            if (user.getUserId() == userId && user.getPassword().equals(password)) {
                userLoginResponseDto.setUserName(user.getUserName());
                userLoginResponseDto.setPassword(user.getPassword());
                userLoginResponseDto.setUserId(user.getUserId());
                userLoginResponseDto.setGender(user.getGender());
                userLoginResponseDto.setHeadPortrait(user.getHeadPortrait());
                userLoginResponseDto.setTelNumber(user.getTelNumber());
                userLoginResponseDto.setStatus("SUCCESS");
                return userLoginResponseDto;
            }
        }
        return userLoginResponseDto;
    }

    /**
     * 传入用户注册信息，返回新注册ID与密码
     *
     * @param userRequestDto
     * @return
     */
    @Override
    public Integer userRegister(User userRequestDto) {
        String telNumber = userRequestDto.getTelNumber();
        String userName = userRequestDto.getUserName();
        String password = userRequestDto.getPassword();
        String headPortrait = userRequestDto.getHeadPortrait();
        String gender = userRequestDto.getGender();

        /**
         *查询插入的数据是否存在,不存在则插入
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getTelNumber, telNumber);
        User isnull = userDao.selectOne(queryWrapper);

        if (StringUtils.isEmpty(isnull)) {
            User user = new User();
            user.setTelNumber(telNumber);
            user.setUserName(userName);
            user.setPassword(password);
            user.setHeadPortrait(headPortrait);
            user.setGender(gender);

            if (userDao.userRegister(user) != 1) {
                return null;
            } else {
                /**
                 * 返回新注册的ID
                 */
                QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.lambda().eq(User::getTelNumber, telNumber);
                User query1 = userDao.selectOne(queryWrapper1);

                return query1.getUserId();
            }
        }
        return null;

    }

    @Override
    public User addUser() {
        return null;
    }

    @Override
    public User deleteUser() {
        return null;
    }

    /**
     * 修改传入用户Id，传回修改后的新数据,其中用户ID由前端自动获得数据，不需用户手动输入，其余修改信息不输入则保持不变。
     *
     * @param
     * @return
     */
    @Override
    public String userEdit(User user) {
        Integer userdd = userDao.userEdit(user);
        if (userdd == 1) {
            return "SUCCESS";
        } else {
            return "ERROR";
        }

    }

    @Override
    public User consumptionData() {
        return null;
    }

    @Override
    public User queryConsumption() {
        return null;
    }
}
