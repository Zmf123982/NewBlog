package com.edu.zzc.dao;

import com.edu.zzc.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {
    @Select("select * from cn_notebook where cn_user_id = #{userId}")
    List<Book> findByUserId(String userId);
    @Insert("insert into cn_notebook(cn_notebook_id,cn_user_id,cn_notebook_type_id,cn_notebook_name,cn_notebook_createtime) values (#{cn_notebook_id},#{cn_user_id},'5',#{cn_notebook_name},#{cn_notebook_createtime})")
    void save(Book book);
    @Update(" <script> update cn_notebook\n" +
            "       <set>\n" +
            "           <if test=\"cn_notebook_id != null\">\n" +
            "               cn_notebook_id = #{cn_notebook_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_user_id != null\">\n" +
            "               cn_user_id = #{cn_user_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_notebook_type_id != null\">\n" +
            "               cn_notebook_type_id = #{cn_notebook_type_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_notebook_name != null\">\n" +
            "               cn_notebook_name = #{cn_notebook_name},\n" +
            "           </if>\n" +
            "           <if test=\"cn_notebook_desc != null\">\n" +
            "               cn_notebook_desc = #{cn_notebook_desc},\n" +
            "           </if>\n" +
            "           <if test=\"cn_notebook_createtime != null\">\n" +
            "               cn_notebook_createtime = #{cn_notebook_createtime},\n" +
            "           </if>\n" +
            "       </set>\n" +
            "       where cn_notebook_id = #{cn_notebook_id}\n </script>")
    int updateById(Book book);
}
