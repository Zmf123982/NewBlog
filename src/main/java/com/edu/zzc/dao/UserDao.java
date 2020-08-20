package com.edu.zzc.dao;

import com.edu.zzc.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from cn_user")
    List<User> findAll();
}
