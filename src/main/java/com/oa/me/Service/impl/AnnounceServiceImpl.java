package com.oa.me.Service.impl;

import com.oa.me.Dao.AnnounceDao;
import com.oa.me.Entity.SysUser;
import com.oa.me.Service.AnnounceService;
import com.oa.me.domain.Announce;
import com.oa.me.domain.AnnounceModel;
import org.apache.ibatis.annotations.Select;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.oa.me.utils.timeUtil.getSecondTimeNow;

/**
 * Created by chenjiehao on 2018/9/18
 */
@Service
public class AnnounceServiceImpl implements AnnounceService {


    @Resource
    private AnnounceDao announceDao;

    @Override
    public List<AnnounceModel> getAnnounceModel() {
        List<Announce> list = new ArrayList<Announce>();
        List<AnnounceModel> list_model = new ArrayList<AnnounceModel>();

        list = announceDao.getAnnounce();
        for (Announce announce : list) {
            AnnounceModel announceModel = new AnnounceModel();
            announceModel.setContent(announce.getContent());
            announceModel.setId(announce.getId());
            announceModel.setTitle(announce.getTitle());
            announceModel.setUid(announce.getUid());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long time = announce.getPublish_time();
            //数据库存储的是注册时的时间戳，单位是s
            Date date = new Date(time * 1000);
            String time_model = simpleDateFormat.format(date);
//            System.out.println(time_model);

            announceModel.setTime(time_model);

            list_model.add(announceModel);
        }


        return list_model;
    }

    @Override
    public boolean setAnnounceModel(AnnounceModel announceModel) {
        Announce announce = new Announce();

        announce.setTitle(announceModel.getTitle());
        if (announce.getContent() == null) {
            announce.setContent("");
        }
        announce.setContent(announceModel.getContent());
        announce.setStatus(1);
//        announce.setUid(announce.getUid());
        announce.setPublish_time(getSecondTimeNow());

        //获取当前用户id
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Integer id = sysuser.getId();

        announce.setUid(id);

        Integer issuccess = announceDao.setAnnounce(announce);

        return issuccess == 1 ? true : false;


    }

    @Override
    public boolean delAnnounce(int id) {


        Integer issuccess = announceDao.delAnnounce(id);

        return issuccess == 1 ? true : false;
    }
}
