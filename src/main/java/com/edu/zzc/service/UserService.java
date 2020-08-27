package com.edu.zzc.service;



import com.edu.zzc.util.NotResult;


public interface UserService {
    public NotResult checkLogin(String name,String password);
    NotResult addUser(String name,String nick,String password);
}
