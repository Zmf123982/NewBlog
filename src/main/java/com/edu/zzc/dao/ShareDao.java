package com.edu.zzc.dao;

import com.edu.zzc.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShareDao {
    @Insert("insert into cn_share(cn_share_id,cn_share_title,cn_share_body,cn_note_id) values (#{cn_share_id},#{cn_share_title},#{cn_share_body},#{cn_note_id})")
    int save(Share share);
    @Select("select * from cn_share where cn_share_title like #{keyword} limit #{begin},5")
    List<Share> findLikeTitle(@Param("keyword") String keyword,@Param("begin") Integer page);
    @Select("select cn_share_id,cn_share_title,cn_share_body,cn_note_id from cn_share where cn_share_id = #{cn_share_id}")
    Share findShareByID(Share share);
}
