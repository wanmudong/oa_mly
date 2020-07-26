package com.oa.me.Service.impl;

import com.oa.me.Dao.*;
import com.oa.me.Service.RecruitService;
import com.oa.me.domain.*;
import com.oa.me.utils.oa_md5;
import com.oa.me.utils.timeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.oa.me.utils.Format;

import static org.apache.poi.poifs.crypt.CipherAlgorithm.des;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Service
@Transactional
public class RecruitImpl implements RecruitService {
    @Resource
    private RecruitDao recruitDao;
    @Resource
    private UserDao userDao;

    @Resource
    private DepartDao departDao;
    @Resource
    private CampusDao campusDao;
    @Resource
    private DictDao dictDao;

    @Override
    public Recruit getRecruitById(int id) {

        Recruit recruit = recruitDao.getRecruitById(id);

        RecruitStatus  rs = new RecruitStatus();
        Steps steps = dictDao.getSteps(id);

        Step step0 = new Step(0,"待处理",steps.getStep0()!=null?steps.getStep0():"");
        Step step1 = new Step(1,"一面",steps.getStep1()!=null?steps.getStep1():"");
        Step step2 = new Step(2,"二面",steps.getStep2()!=null?steps.getStep2():"");
        Step step3 = new Step(3,"三面",steps.getStep3()!=null?steps.getStep3():"");
        Step step4 = new Step(4,"四面",steps.getStep4()!=null?steps.getStep4():"");

        List list = new ArrayList();
        list.add(step0);
        list.add(step1);
        list.add(step2);
        list.add(step3);
        list.add(step4);
        rs.setSteps(list);
        rs.setCurrent(recruit.getCurrent());
//        rs.setStatus(recruit.getStatus());

        recruit.setRecruitStatus(rs);
        recruit.setTime(timeUtil.dateTime(Long.parseLong(recruit.getTime()),"yyyy-MM-dd"));
//        recruit.setDepart(depart);
//        recruit.setCampus(campus);

        return recruit;
    }

    @Override
    public List<Recruit> getRecruitByOther(Recruit recruit) {

        List<Recruit> list = new ArrayList<>();

        String start_time = timeUtil.dateTimeStamp(recruit.getStart_date(),"yyyy-MM-dd");
        String end_time = timeUtil.dateTimeStamp(recruit.getEnd_date(),"yyyy-MM-dd");
        String depart = departDao.getIdByName(recruit);
        String campus = dictDao.getIdByCampusName(recruit);

        recruit.setStart_date(start_time);
        recruit.setEnd_date(end_time);
//        recruit.setCampus(campus);
//        recruit.setDepart(depart);

        list = recruitDao.getRecruitAll(recruit);

        for (Recruit re:list)
        {
            depart = departDao.getNameById(Integer.valueOf(re.getDepart()));
            campus = campusDao.getCampusName(re.getCampus());
//            re.setDepart(depart);
//            re.setCampus(campus);
//            re.setRecruitStatus(new RecruitStatus());
            re.setStart_date("");
            re.setEnd_date("");

        }
        return list;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Recruit update(int id, int  status, String desc, String depart) {
/**
 * 0 拒绝 status 为0
 * 123 修改current 为123
 * 4 修改current为4 status 为2 通过考核 插入一条新数据进入oa_member
 * 5 转部门 读 depart 通过重置current为0
 */

        desc = Format.getFormat(desc);
        Recruit recruit = recruitDao.getRecruitById(id);
        int depart1;
        long success;
        long success0;
        long success1;
        long success2;
        long success3;
        long success4;
        switch(status)
        {   //
            case 0:
                //拒绝
                success0 =  recruitDao.updateStatus(id,0);
                if (success0==0)
                {
                    return null;
                }
                break;
            case 1:
                //转为二面
                success =  recruitDao.update(id,status);
                success1= recruitDao.updateLog(id,status,desc);
                if (success==0||success1==0)
                {
                    return null;
                }
                break;
            case 2:
                //转为三面
                success =  recruitDao.update(id,status);
                success1= recruitDao.updateLog(id,status,desc);
                if (success==0||success1==0)
                {
                    return null;
                }
                break;
            case 3:
                //转为四面
                success = recruitDao.update(id,status);
                success1= recruitDao.updateLog(id,status,desc);
                if (success==0||success1==0)
                {
                    return null;
                }
                break;
            case 4:
                //正式入职

                success0 =  recruitDao.updateStatus(id,2);
                success = recruitDao.update(id,status);
                success1= recruitDao.updateLog(id,status,desc);
                 recruit = recruitDao.getRecruitById(id);
                //初始密码为online666
                String password = oa_md5.md5("online666");
                String salt = oa_md5.radomString();
                String pwd = oa_md5.md5_salt(password,salt);
                int time = timeUtil.getSecondTimeNow();
                success2 = userDao.InsertNewMember(recruit,pwd,salt,time);


                if (success0==0||success1==0||success==0||success2==0)
                {
                    return null;
                }
                break;
            case 5:
                //转部门
                //success = recruitDao.update(id,1);
                depart1 = Integer.parseInt(dictDao.getDepartIdByName(depart));
                success3 = recruitDao.updateDepart(id, depart1,0);
                success4 = recruitDao.clearRecruitLog(id,desc);
                if (success3==0||success4==0)
                {
                    return null;
                }
                break;
            default:
                //操作失效
                return null;
        }



//        Integer current = recruitDao.getCurrent(String.valueOf(id));
//
//
//        if (current==4){
//            return null;
//        }
//        Long success =  recruitDao.update(id,status,depart,current+1);
//
//        Long successlog = recruitDao.updateLog(id,String.valueOf(current+1),desc);

         recruit = getRecruitById(id);
        return recruit;
    }

    @Override
    public boolean apply(Recruit recruit) {
//        recruit.setDepart(departDao.getIdByName(recruit));
//        recruit.setCampus(dictDao.getIdByCampusName(recruit));
        recruit.setStatus(2);
        recruit.setTime(String.valueOf(timeUtil.getSecondTimeNow()));

        long success = recruitDao.apply(recruit);
        return success>0?true:false;
    }

    @Override
    public List<Recruit> getRecruitByContent(String content, String depart, String campus_0) {
        /**
         * 部长权限
         */
        List<Recruit> list = new  ArrayList<Recruit>();
        list  = recruitDao.getRecruitByContentAll(content,depart.equals("0")?0:Integer.parseInt(depart),Integer.parseInt(campus_0));
//        for (Recruit user:list){
//            user.setPwd("");
//            user.setSalt("");
//        }
        return list;
    }

    @Override
    public List<Recruit> getRecruitByContentAndCampus(String content, String department, String campus) {
        /**
         * 主管或行政人员权限
         * circle 先不去管
         *
         */
        List<Recruit> list = new ArrayList<Recruit>();
        System.out.println(department);
        System.out.println(campus);



        if (campus == null || campus.equals("")){

            if (department==null|| department.equals("")){
                list  = recruitDao.getRecruitByContent(content);
            }else {
//
                list = recruitDao.getRecruitByContentByDepart(content,Integer.parseInt(department));
            }
        }else {
            if (department==null|| department.equals("")){
                list  = recruitDao.getRecruitByContentByCampus(content, Integer.parseInt(campus));
            }else {
                list  = recruitDao.getRecruitByContentAll(content,department.equals("0")?0:Integer.parseInt(department),Integer.parseInt(campus));
            }
        }
//        for (User user:list){
//            user.setPwd("");
//            user.setSalt("");
//        }
        return list;
    }

}
