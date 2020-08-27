package com.edu.zzc.controller;

import com.edu.zzc.service.NoteService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DeleteNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/delete.do")
    public NotResult execute(String noteId){
        return noteService.deleteNote(noteId);
    }
}
