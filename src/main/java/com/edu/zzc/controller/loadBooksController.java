package com.edu.zzc.controller;

import com.edu.zzc.service.BooksService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class loadBooksController {
    @Resource
    private BooksService booksService;
    @RequestMapping("/book/loadbooks.do")
    public NotResult execute (String userId){
        return booksService.loadUserBooks(userId);
    }
}
