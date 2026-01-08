package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.UserDao;
import com.example.springbootdemo.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }



    public PageInfo<User> findUserPage(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.findUserPage(keyword);
        return new PageInfo<>(users);
    }

    public User findByName(String name) {
        return userDao.getUserByName(name);
    }

    public User createUser(User newUser) {
        if (this.findByName(newUser.getName()) != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userDao.save(newUser);
        return newUser;
    }

    public boolean validatePassword(User user) {
        User userStorage = this.findByName(user.getName());
        if (userStorage == null) {
            return false;
        }

        // 使用密码编码器验证密码
        return passwordEncoder.matches(user.getPassword(), userStorage.getPassword());
    }

}
