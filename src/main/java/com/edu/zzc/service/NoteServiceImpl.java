package com.edu.zzc.service;

import com.edu.zzc.dao.NoteDao;
import com.edu.zzc.entity.Note;
import com.edu.zzc.util.NotResult;
import com.edu.zzc.util.NoteUtil;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{
    @Resource
    private NoteDao noteDao;
    @Override
    public NotResult findByBookId(String bookId) {
        NotResult notResult = new NotResult();
        List<Note> notes = noteDao.findByBookId(bookId);
        notResult.setStatus(0);
        notResult.setMsg("查询成功");
        notResult.setData(notes);
        return notResult;
    }
    @Override
    public NotResult loadNote(String noteId) {
        NotResult notResult = new NotResult();
        Note note = noteDao.findById(noteId);
        notResult.setStatus(0);
        notResult.setMsg("加载笔记成功");
        notResult.setData(note);
        return notResult;
    }

    @Override
    public NotResult updateNote(String noteId,String title, String body) {
        NotResult notResult = new NotResult();
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_body(body);
        note.setCn_note_title(title);
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        int i = noteDao.updateNote(note);
        if (i == 1) {
            notResult.setStatus(0);
            notResult.setMsg("保存笔记成功");
        }else {
            notResult.setStatus(1);
            notResult.setMsg("保存笔记失败");
        }
        return notResult;
    }

    @Override
    public NotResult addNote(String title, String bookId, String userId) {
        NotResult notResult = new NotResult();
        Note note = new Note();
        String noteId = NoteUtil.createID();
        note.setCn_note_id(noteId);
        note.setCn_user_id(userId);
        note.setCn_notebook_id(bookId);
        note.setCn_note_title(title);
        note.setCn_note_create_time(System.currentTimeMillis());
        note.setCn_note_last_modify_time(System.currentTimeMillis());
        noteDao.save(note);
        notResult.setStatus(0);
        notResult.setData(noteId);
        notResult.setMsg("添加笔记成功");
        return notResult;
    }

    @Override
    public NotResult deleteNote(String noteId) {
        NotResult notResult = new NotResult();
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_note_status_id("2");
        int i = noteDao.updateNote(note);
        if (i >= 1){
            //成功
            notResult.setStatus(0);
            notResult.setMsg("笔记删除成功");
        }else {
            notResult.setStatus(1);
            notResult.setMsg("删除失败");
        }
        return notResult;
    }

    @Override
    public NotResult moveNote(String noteId,String bookId) {
        NotResult notResult = new NotResult();
        Note note = new Note();
        note.setCn_note_id(noteId);
        note.setCn_notebook_id(bookId);
        int i = noteDao.updateNote(note);
        if (i >= 1){
            notResult.setStatus(0);
            notResult.setMsg("移动笔记成功");
        }
        return notResult;
    }
}
