package com.edu.zzc.dao;

import com.edu.zzc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from cn_user")
    List<User> findAll();
    @Select("select * from cn_user where cn_user_name = #{cn_user_name}")
    User findByName(String cn_user_name);
    @Insert("insert into cn_user(cn_user_id,cn_user_name,cn_user_password,cn_user_nick) values(#{cn_user_id},#{cn_user_name},#{cn_user_password},#{cn_user_nick})")
    void save(User user);
}
