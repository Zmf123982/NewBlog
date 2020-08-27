package com.edu.zzc.service;


import com.edu.zzc.util.NotResult;

public interface BooksService {
    NotResult loadUserBooks(String userId);
    NotResult addBook(String userId,String bookName);
    NotResult updateById(String bookId,String name);
}
