package com.example.springbootdemo.dao;

import com.example.springbootdemo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    List<User> findUserPage(String keyword);

    @Select("SELECT * FROM user WHERE name = #{name}")
    User getUserByName(String name);

    int save(User newUser);

}