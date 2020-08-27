package com.edu.zzc.service;

import com.edu.zzc.util.NotResult;

public interface ShareService {
    NotResult shareNote(String notId);
    NotResult searchShareNote(String keyword,Integer page);
    NotResult findShareByID(String shareId);
}
