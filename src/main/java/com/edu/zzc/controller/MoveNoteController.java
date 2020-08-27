package com.edu.zzc.controller;

import com.edu.zzc.service.NoteService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MoveNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/move.do")
    public NotResult execute(String noteId,String bookId){
        return noteService.moveNote(noteId, bookId);
    }
}
