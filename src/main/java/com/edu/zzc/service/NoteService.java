package com.edu.zzc.service;

import com.edu.zzc.entity.Note;
import com.edu.zzc.util.NotResult;

public interface NoteService {
    NotResult findByBookId(String bookId);
    NotResult loadNote(String noteId);
    NotResult updateNote(String noteId,String title,String body);
    NotResult addNote(String title,String bookId,String userId);
    NotResult deleteNote(String noteId);
    NotResult moveNote(String noteId,String bookId);
}
