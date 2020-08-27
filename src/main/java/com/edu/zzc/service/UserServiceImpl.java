package com.edu.zzc.service;


import com.edu.zzc.dao.UserDao;
import com.edu.zzc.entity.User;
import com.edu.zzc.util.NotResult;
import com.edu.zzc.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public NotResult checkLogin(String name, String password) {
        NotResult notResult = new NotResult();
        //判断用户名
        User user = userDao.findByName(name);
        if (user == null){
            notResult.setStatus(1);
            notResult.setMsg("用户名不存在");
            return notResult;
        }
        //判断密码
        try {
            String md5_pwd = NoteUtil.md5(password);
            if (!user.getCn_user_password().equals(md5_pwd)){
                notResult.setStatus(2);
                notResult.setMsg("密码错误");
                return notResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //登录成功
        notResult.setStatus(0);
        notResult.setMsg("登录成功");
        //把密码信息屏蔽掉
        user.setCn_user_password("");
        notResult.setData(user);
        return notResult;
    }

    @Override
    public NotResult addUser(String name, String nick, String password) {
        NotResult notResult = new NotResult();
        try {
            //判断用户名是否存在
            User user = userDao.findByName(name);
            if (user != null){
                notResult.setStatus(1)  ;
                notResult.setMsg("用户名已被占用");
                return notResult;
            }
            //执行注册
            User user1 = new User();
            user1.setCn_user_id(NoteUtil.createID());
            user1.setCn_user_password(NoteUtil.md5(password));
            user1.setCn_user_name(name);
            user1.setCn_user_nick(nick);
            System.out.println(user1);
            userDao.save(user1);
            //创建返回值
            notResult.setMsg("注册成功");
            notResult.setStatus(0);
            return notResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
