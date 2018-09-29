package com.oa.me.utils;

import com.oa.me.Dao.DepartDao;
import com.oa.me.Dao.DictDao;
import com.oa.me.Dao.RoleDao;
import com.oa.me.domain.*;

import javax.annotation.Resource;

/**
 * Created by chenjiehao on 2018/9/24
 */
public class mapperUser {

//    @Resource
//    private DepartDao departDao;

    public static JUser mapperJUser(User user, DictDao dictDao){

        if(user == null){
            return null;
        }
        JUser jUser = new JUser();

        jUser.setKey(user.getId());
        jUser.setStuid(user.getStuid());
        jUser.setName(user.getName()==null?"":user.getName());
        jUser.setSex(user.getSex()==null?"":user.getSex());
        jUser.setPwd("");
        jUser.setSalt("");

        jUser.setDepart(dictDao.getDepartNameById(user.getDepart())==null?"":dictDao.getDepartNameById(user.getDepart()));
        jUser.setDebitcard(user.getDebitcard()==null?"":user.getDebitcard());
        jUser.setRole(dictDao.getRoleNameById(user.getRole())==null?"":dictDao.getRoleNameById(user.getRole()));
        jUser.setStatus(dictDao.getStatusNameById(user.getStatus())==null?"":dictDao.getStatusNameById(user.getStatus()));
        jUser.setJoin_time(timeUtil.dateTime(user.getJoin_time(),"yyyy-MM-dd"));
        jUser.setExit_time(timeUtil.dateTime(user.getExit_time(),"yyyy-MM-dd"));
        jUser.setPhone(user.getPhone()==null?"":user.getPhone());
        jUser.setEmail(user.getEmail()==null?"":user.getEmail());
        jUser.setQq(user.getQq()==null?"":user.getQq());
        jUser.setCampus(dictDao.getCampusNameById(user.getCampus())==null?"":dictDao.getCampusNameById(user.getCampus()));
        jUser.setCollege(user.getCollege()==null?"":user.getMajor());
        jUser.setMajor(user.getMajor()==null?"":user.getMajor());
        return jUser;
    }
    public static User mapperMUser(JUser jUser, DictDao dictDao){
        if(jUser == null){
            return null;
        }

        User user = new User();

        user.setId(user.getId());

        user.setStuid(jUser.getStuid());
        user.setName(jUser.getName()==null?"":jUser.getName());
        user.setSex(jUser.getSex()==null?"":jUser.getSex());

//        user.setPwd("");
        user.setPwd(jUser.getPassword());
        user.setSalt("");

        user.setDepart(Integer.parseInt(dictDao.getDepartIdByName(jUser.getDepart())));
        user.setDebitcard(jUser.getDebitcard()==null?"":jUser.getDebitcard());
        user.setRole(dictDao.getRoleIdByName(jUser.getRole()));
        user.setStatus(dictDao.getStatusIdByName(jUser.getStatus()));
        user.setJoin_time(Integer.parseInt(timeUtil.dateTimeStamp(jUser.getJoin_time(),"yyyy-MM-dd")));
        user.setExit_time(Integer.parseInt(timeUtil.dateTimeStamp(jUser.getJoin_time(),"yyyy-MM-dd")));
        user.setPhone(jUser.getPhone()==null?"":jUser.getPhone());
        user.setEmail(jUser.getEmail()==null?"":jUser.getEmail());
        user.setQq(jUser.getQq()==null?"":jUser.getQq());
        user.setCampus(Integer.parseInt(dictDao.getCampusIdByName(jUser.getCampus())));
        user.setCollege(jUser.getCollege()==null?"":jUser.getMajor());
        user.setMajor(jUser.getMajor()==null?"":jUser.getMajor());


        return user;
    }

    public static JUser mapperJUser(Recruit recruit, DictDao dictDao){

        if(recruit == null){
            return null;
        }
        JUser jUser = new JUser();

//        JUser jUser = new JUser();

        jUser.setKey(recruit.getId());
        jUser.setStuid(recruit.getStuid());
        jUser.setName(Format.getFormat(recruit.getName()));
        jUser.setSex(Format.getFormat(recruit.getSex()));
        jUser.setDepart(Format.getFormat(dictDao.getDepartNameById(recruit.getDepart())));
        jUser.setDebitcard(Format.getFormat(""));
        jUser.setRole(Format.getFormat(""));
        jUser.setStatus(Format.getFormat(""));
        jUser.setJoin_time(timeUtil.dateTime(0,"yyyy-MM-dd"));
        jUser.setExit_time(timeUtil.dateTime(0,"yyyy-MM-dd"));
        jUser.setTime(timeUtil.dateTime(Long.parseLong(recruit.getTime()),"yyyy-MM-dd HH:mm:ss"));
        jUser.setPhone(Format.getFormat(recruit.getPhone()));
        jUser.setEmail(Format.getFormat(""));
        jUser.setQq(Format.getFormat(recruit.getQq()));
        jUser.setCampus(Format.getFormat(dictDao.getCampusNameById(recruit.getCampus())));
        jUser.setCollege(Format.getFormat(recruit.getCollege()));
        jUser.setMajor(recruit.getSex()==null?"":recruit.getMajor());


        jUser.setPwd("");
        jUser.setSalt("");

//        jUser.setDepart(dictDao.getDepartNameById(user.getDepart())==null?"":dictDao.getDepartNameById(user.getDepart()));
//        jUser.setDebitcard(user.getDebitcard()==null?"":user.getDebitcard());
//        jUser.setRole(dictDao.getRoleNameById(user.getRole())==null?"":dictDao.getRoleNameById(user.getRole()));
//        jUser.setStatus(dictDao.getStatusNameById(user.getStatus())==null?"":dictDao.getStatusNameById(user.getStatus()));
//        jUser.setJoin_time(timeUtil.dateTime(user.getJoin_time()));
//        jUser.setExit_time(timeUtil.dateTime(user.getExit_time()));
//        jUser.setPhone(user.getPhone()==null?"":user.getPhone());
//        jUser.setEmail(user.getEmail()==null?"":user.getEmail());
//        jUser.setQq(user.getQq()==null?"":user.getQq());
//        jUser.setCampus(dictDao.getCampusNameById(user.getCampus())==null?"":dictDao.getCampusNameById(user.getCampus()));
//        jUser.setCollege(user.getCollege()==null?"":user.getMajor());
//        jUser.setMajor(user.getMajor()==null?"":user.getMajor());
//        return jUser;


        return jUser;
    }

    public static RRecruit mapperRRecruit(Recruit recruit, DictDao dictDao)
    {

        if(recruit == null){
            return null;
        }

        RRecruit rRecruit = new RRecruit();

        rRecruit.setKey(recruit.getId());
        rRecruit.setCampus(dictDao.getCampusNameById(recruit.getCampus())==null?"":dictDao.getCampusNameById(recruit.getCampus()));
        rRecruit.setDepart(dictDao.getDepartNameById(recruit.getDepart())==null?"":dictDao.getDepartNameById(recruit.getDepart()));

        RRecruitStatus rRecruitStatus = new RRecruitStatus();
        RecruitStatus recruitStatus = recruit.getRecruitStatus();
        if (recruitStatus!=null){

        rRecruitStatus.setCurrent(recruitStatus.getCurrent()==0?0:recruitStatus.getStatus());
        rRecruitStatus.setStatus(recruit.getStatus()==1?"normal":"");
        rRecruitStatus.setSteps(recruitStatus.getSteps());
        }
        rRecruit.setStatus(rRecruitStatus);

        rRecruit.setCollege(Format.getFormat(recruit.getCollege()));
        rRecruit.setCurrent(recruit.getCurrent());
        rRecruit.setMajor(Format.getFormat(recruit.getMajor()));
        rRecruit.setName(Format.getFormat(recruit.getName()));
        rRecruit.setPhone(Format.getFormat(recruit.getPhone()));
        rRecruit.setQq(Format.getFormat(recruit.getQq()));
        rRecruit.setSay(Format.getFormat(recruit.getSay()));
        rRecruit.setSex(Format.getFormat(recruit.getSex()));
        rRecruit.setStuid(recruit.getStuid());
        rRecruit.setTime(Format.getFormat(recruit.getTime()));

        return rRecruit;
    }
}
