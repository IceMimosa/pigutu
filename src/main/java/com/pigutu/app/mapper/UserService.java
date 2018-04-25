package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    UserDao userDao;
    public UserEntity getUser(String name) {
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name",name));
        return userEntity;
    }
}