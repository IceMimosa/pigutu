package com.pigutu.app.mapper;

import com.pigutu.app.entity.UserEntity;
import com.pigutu.app.mapper.mybatis.BaseDao;
import com.pigutu.app.mapper.mybatis.DBMeta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository // 不加也可以
@DBMeta(table = RoleDao.TABLE)
public interface RoleDao extends BaseDao<UserEntity> {
    String TABLE = "role";
}
