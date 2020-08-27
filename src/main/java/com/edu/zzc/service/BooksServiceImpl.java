package com.edu.zzc.service;

import com.edu.zzc.dao.BookDao;
import com.edu.zzc.entity.Book;
import com.edu.zzc.entity.Note;
import com.edu.zzc.util.NotResult;
import com.edu.zzc.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService{
    @Resource
    private BookDao bookDao;
    @Override
    public NotResult loadUserBooks(String userId) {
        NotResult notResult = new NotResult();
        List<Book> books = bookDao.findByUserId(userId);
        //创建返回结果
        notResult.setStatus(0);
        notResult.setMsg("查询成功");
        notResult.setData(books);
        return notResult;
    }

    @Override
    public NotResult addBook(String userId, String name) {
        NotResult notResult = new NotResult();
        Book book = new Book();
        book.setCn_notebook_id(NoteUtil.createID());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        book.setCn_notebook_createtime(timestamp);
        book.setCn_notebook_name(name);
        book.setCn_user_id(userId);
        bookDao.save(book);
        notResult.setStatus(0);
        notResult.setMsg("创建成功");
        notResult.setData(book);
        return notResult;
    }

    @Override
    public NotResult updateById(String bookId,String name) {
        NotResult notResult = new NotResult();
        Book book = new Book();
        book.setCn_notebook_id(bookId);
        book.setCn_notebook_name(name);
        int i = bookDao.updateById(book);
        if (i >= 1){
            notResult.setStatus(0);
            notResult.setMsg("修改笔记本名成功");
        }else {
            notResult.setStatus(1);
            notResult.setMsg("修改笔记名失败");
        }
        return notResult;
    }
}
