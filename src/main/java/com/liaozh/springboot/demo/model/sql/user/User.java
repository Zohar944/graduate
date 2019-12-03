package com.liaozh.springboot.demo.model.sql.user;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 用户表
 */
@Table
public class User extends Model<User> {
    /**
     * 用户账号
     */
    @TableId
    private int userId;

    /**
     * 手机号码用来关联微信、支付宝等
     */
    private String telNumber;
    /**
     * 用户名
     */
    private String userName;

    private String password;

    /***
     * 头像存取路径
     */
    private String headPortrait;
    /**
     * 性别
     */
    private String gender;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", telNumber='" + telNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
