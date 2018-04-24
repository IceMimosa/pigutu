package com.pigutu.app.mapper;

import com.google.common.collect.ImmutableMap;
import com.pigutu.app.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
  * Created by jack on 2017/4/29.
  */
@Service
public class LightSwordUserDetailService implements UserDetailsService {

  @Autowired
  UserDao userDao;
  @Autowired
  RoleDao roleDao;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userDao.selectOne(ImmutableMap.of("name",username));
    if(userEntity == null) throw new UsernameNotFoundException("username = "+username);
    return userEntity;
  }
}