package com.edu.zzc.controller;

import com.edu.zzc.service.NoteService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AddNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/add.do")
    public NotResult execute(String title, String bookId, String userId){
        return noteService.addNote(title,bookId,userId);
    }
}
