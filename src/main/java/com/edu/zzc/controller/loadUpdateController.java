package com.edu.zzc.controller;

import com.edu.zzc.service.NoteService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class loadUpdateController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/update.do")
    public NotResult execute(String noteId,String title, String body){
        return noteService.updateNote(noteId,title,body);
    }
}
