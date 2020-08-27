package com.edu.zzc.controller;

import com.edu.zzc.service.NoteService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class loadNotesController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadnotes.do")
    public NotResult execute(String bookId){
        return noteService.findByBookId(bookId);
    }
}
