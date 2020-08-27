package com.edu.zzc.controller;

import com.edu.zzc.service.BooksService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AddBookController {
    @Resource
    private BooksService booksService;
    @RequestMapping("/book/add.do")
    public NotResult execute(String userId,String name){
        return booksService.addBook(userId,name);
    }
}
