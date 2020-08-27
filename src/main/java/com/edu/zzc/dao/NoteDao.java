package com.edu.zzc.dao;

import com.edu.zzc.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoteDao {
    @Select("select cn_note_id,cn_note_title,cn_note_type_id from cn_note where cn_notebook_id = #{bookId} and cn_note_status_id='1'")
    List<Note> findByBookId(String bookId);
    @Select("select cn_note_id,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_type_id,cn_note_title,cn_note_body,cn_note_create_time,cn_note_create_time from cn_note where cn_note_id = #{noteId}")
    Note findById(String noteId);
//    @Update("update cn_note set cn_note_title=#{cn_note_title},cn_note_body=#{cn_note_body},cn_note_last_modify_time = #{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
//    int updateNote(Note note);
    @Update(" <script> update cn_note\n" +
            "       <set>\n" +
            "           <if test=\"cn_note_id != null\">\n" +
            "               cn_note_id = #{cn_note_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_notebook_id != null\">\n" +
            "               cn_notebook_id = #{cn_notebook_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_user_id != null\">\n" +
            "               cn_user_id = #{cn_user_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_note_status_id != null\">\n" +
            "               cn_note_status_id = #{cn_note_status_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_note_type_id != null\">\n" +
            "               cn_note_type_id = #{cn_note_type_id},\n" +
            "           </if>\n" +
            "           <if test=\"cn_note_title != null\">\n" +
            "               cn_note_title = #{cn_note_title},\n" +
            "           </if>\n" +
            "           <if test=\"cn_note_body != null\">\n" +
            "               cn_note_body = #{cn_note_body},\n" +
            "           </if>\n" +
            "           <if test=\"cn_note_create_time != null\">\n" +
            "               cn_note_create_time = #{cn_note_create_time},\n" +
            "           </if>\n" +
            "           <if test=\"cn_note_last_modify_time != null\">\n" +
            "               cn_note_last_modify_time = #{cn_note_last_modify_time},\n" +
            "           </if>\n" +
            "       </set>\n" +
            "       where cn_note_id = #{cn_note_id}\n </script>")
    int updateNote(Note note);
    @Insert("insert into cn_note(cn_note_id,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_type_id,cn_note_title,cn_note_body,cn_note_create_time,cn_note_last_modify_time) values(#{cn_note_id},#{cn_notebook_id},#{cn_user_id},'1','1',#{cn_note_title},#{cn_note_body},#{cn_note_create_time},#{cn_note_last_modify_time})")
    void  save(Note note);
}
