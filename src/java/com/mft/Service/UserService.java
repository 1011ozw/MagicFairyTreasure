/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mft.Service;

import com.mft.Bean.User;
import com.mft.Dao.Impl.UserImpl;
import com.mft.Dao.UserDao;
import java.util.List;

/**
 *
 * @author 张帆
 */
public class UserService {

    UserDao ud = new UserImpl();

    public int addUser(User u) {
        int r = ud.addUser(u);
        return r;
    }

    public User checkLogin(String name, String pwd) {
        User u = ud.checkLogin(name, pwd);
        return u;
    }

    public boolean isUserByName(String name) {
        return ud.isUserByName(name);
    }

    public User login(String userName, String userPwd, int type) {
        return ud.login(userName, userPwd, type);
    }

    public List<User> userId(int pageIndex, int pageSum) {
        return ud.userId(pageIndex, pageSum);
    }

    public User user(int userId) {
        return ud.user(userId);
    }

    public User getUserbyname(String username) {
        User user = null;
        User u = ud.bynameuser(username);
        if (u != null) {
            user = new User();
            user.setId(u.getId());
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setSex(u.getSex());
            user.setBirthday(u.getBirthday());
            user.setIdentitycard(u.getIdentitycard());
            user.setEmail(u.getEmail());
            user.setMobile(u.getMobile());
            user.setAddress(u.getAddress());
            user.setStatus(u.getStatus());
            user.setImage(u.getImage());
            user.setBalance(u.getBalance());
        }
        return user;
    }

    public int updateusers(User u) {
        User user = null;
        int rows = ud.updateuser(u);
        if (rows > 0) {
            user = new User();
            user.setId(u.getId());
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setSex(u.getSex());
            user.setBirthday(u.getBirthday());
            user.setIdentitycard(u.getIdentitycard());
            user.setEmail(u.getEmail());
            user.setMobile(u.getMobile());
            user.setAddress(u.getAddress());
            user.setStatus(u.getStatus());
            user.setImage(u.getImage());
            user.setBalance(u.getBalance());
        }
        return rows;
    }
}
