package com.edu.zzc.controller;

import com.edu.zzc.service.ShareService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoadShareController {
    @Resource
    private ShareService shareService;
    @RequestMapping("/note/findById.do")
    public NotResult execute(String shareId){
        return shareService.findShareByID(shareId);
    }
}
