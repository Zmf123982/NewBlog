package com.edu.zzc.service;

import com.edu.zzc.dao.NoteDao;
import com.edu.zzc.dao.ShareDao;
import com.edu.zzc.entity.Note;
import com.edu.zzc.entity.Share;
import com.edu.zzc.util.NotResult;
import com.edu.zzc.util.NoteUtil;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.file.DirectoryNotEmptyException;
import java.util.List;

@Service
@Transactional
public class ShareServiceImpl implements ShareService{
    @Resource
    private ShareDao shareDao;
    @Resource
    private NoteDao noteDao;
    @Override
    public NotResult shareNote(String notId) {
        NotResult notResult = new NotResult();
        Note note1 = noteDao.findById(notId);
        //检查笔记是否分享过
        if ("2".equals(note1.getCn_note_type_id())){
            notResult.setStatus(1);
            notResult.setMsg("笔记已经分享过了");
            return notResult;
        }
        //修改笔记的类型为2
        Note note = new Note();
        note.setCn_note_id(notId);
        note.setCn_note_type_id("2");
        noteDao.updateNote(note);
        //将笔记信息添加到分享笔记中
        Share share = new Share();
        share.setCn_note_id(notId);
        share.setCn_share_id(NoteUtil.createID());
        share.setCn_share_title(note1.getCn_note_title());//分享标题
        share.setCn_share_body(note1.getCn_note_body());//分享内容
        int i = shareDao.save(share);
        if (i>=1){
            notResult.setStatus(0);
            notResult.setMsg("笔记分享成功");
        }
        return notResult;
    }

    @Override
    public NotResult searchShareNote(String keyword, Integer page) {
        NotResult notResult = new NotResult();
        String title = "%";
        if (keyword != null && !"".equals(keyword)){
            title  = "%"+keyword+"%";
        }
        if (page < 1){
            page = 1;
        }
        //begin = (page-1)*5
        int begin = (page - 1) * 5;
        List<Share> shareList = shareDao.findLikeTitle(title, begin);
        notResult.setStatus(0);
        notResult.setMsg("查询完毕");
        notResult.setData(shareList);
        return notResult;
    }

    @Override
    public NotResult findShareByID(String shareId) {
        NotResult notResult = new NotResult();
        Share share = new Share();
        share.setCn_share_id(shareId);
        Share shareByID = shareDao.findShareByID(share);
        notResult.setStatus(0);
        notResult.setMsg("查询分享标题内容成功");
        notResult.setData(shareByID);
        return notResult;
    }
}
