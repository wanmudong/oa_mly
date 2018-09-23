package com.oa.me.Service.impl;

import com.oa.me.Dao.CampusDao;
import com.oa.me.Dao.DepartDao;
import com.oa.me.Dao.DictDao;
import com.oa.me.Dao.RecruitDao;
import com.oa.me.Service.RecruitService;
import com.oa.me.domain.Recruit;
import com.oa.me.domain.RecruitStatus;
import com.oa.me.domain.Step;
import com.oa.me.domain.Steps;
import com.oa.me.utils.timeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.poi.poifs.crypt.CipherAlgorithm.des;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Service
public class RecruitImpl implements RecruitService {
    @Resource
    private RecruitDao recruitDao;

    @Resource
    private DepartDao departDao;
    @Resource
    private CampusDao campusDao;
    @Resource
    private DictDao dictDao;

    @Override
    public Recruit getRecruitById(int id) {
        Recruit recruit = recruitDao.getRecruitById(id);

        String depart = departDao.getNameById(Integer.valueOf(recruit.getDepart()));
        String campus = campusDao.getCampusName(recruit.getCampus());
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
        rs.setSteplist(list);
        rs.setCurrent(recruit.getCurrent());
        rs.setStatus(recruit.getStatus());
        recruit.setRecruitStatus(rs);
        recruit.setDepart(depart);
        recruit.setCampus(campus);

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
        recruit.setCampus(campus);
        recruit.setDepart(depart);

        list = recruitDao.getRecruitAll(recruit);

        for (Recruit re:list)
        {
            depart = departDao.getNameById(Integer.valueOf(re.getDepart()));
            campus = campusDao.getCampusName(re.getCampus());
            re.setDepart(depart);
            re.setCampus(campus);
//            re.setRecruitStatus(new RecruitStatus());
            re.setStart_date("");
            re.setEnd_date("");

        }
        return list;
    }

    @Override
    public Recruit update(String id, String status, String desc, String depart) {
         depart = departDao.getIdByName(depart);
        Integer current = recruitDao.getCurrent(id);
        if (current==4){
            return null;
        }
        Long success =  recruitDao.update(id,status,depart,current+1);

        Long successlog = recruitDao.updateLog(id,String.valueOf(current+1),desc);

        Recruit recruit = getRecruitById(Integer.valueOf(id));
        return recruit;
    }

    @Override
    public boolean apply(Recruit recruit) {
        recruit.setDepart(departDao.getIdByName(recruit));
        recruit.setCampus(dictDao.getIdByCampusName(recruit));
        recruit.setStatus(2);
        recruit.setTime(String.valueOf(timeUtil.getSecondTimeNow()));

        long success = recruitDao.apply(recruit);
        return success>0?true:false;
    }

}
