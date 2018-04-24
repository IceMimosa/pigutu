package com.pigutu.app.controller;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.SysRole;
import com.pigutu.app.entity.UserEntity;
import com.pigutu.app.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService {
    @Autowired
    UserEntity userEntity;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name",name));
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println("username:"+userEntity.getUsername()+";password:"+userEntity.getPassword());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(SysRole role:userEntity.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
           // logger.info("loadUserByUsername: " + user);
        }
        userEntity.setAuthorities(authorities); //用于登录时 @AuthenticationPrincipal 标签取值
        return userEntity;
    }
}