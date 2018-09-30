package com.oa.me.Service.impl;

import com.oa.me.Dao.DictDao;
import com.oa.me.Dao.ReportDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Service.RecruitService;
import com.oa.me.Service.ReportService;
import com.oa.me.domain.*;
import com.oa.me.utils.timeUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by chenjiehao on 2018/9/27
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportDao reportDao;
    @Resource
    private UserDao userDao;
    @Resource
    private DictDao dictDao;

    @Override
    public List getHistory(int uid) {
        List<Report> list = new ArrayList<Report>();

        list = reportDao.getHistory(uid);
        for (Report report:list)
        {
            report.setEnd_date(timeUtil.dateTime(Long.parseLong(report.getEnd_date()),"yyyy-MM-dd"));
            report.setStart_date(timeUtil.dateTime(Long.parseLong(report.getStart_date()),"yyyy-MM-dd"));
            report.setTime_report(timeUtil.dateTime(Long.parseLong(report.getTime_report()),"yyyy-MM-dd"));
        }


        return list;
    }

    @Override
    public RDate getStatus(int uid) {
        RDate rDate = new RDate();

        String report_start_date = reportDao.getStatusByName("report_start_date");
        String report_end_date = reportDao.getStatusByName("report_end_date");

        rDate.setReport_end_date(reportDao.getStatusByName("report_end_date"));
        rDate.setReport_start_date(reportDao.getStatusByName("report_start_date"));

        String state = reportDao.getStatusByName("report_stat");

        if (state.equals("close"))
        {
            rDate.setReport_stat(state);
        }else {
            int times = reportDao.getStatusByTime(Long.parseLong(timeUtil.dateTimeStamp(report_start_date,"yyyy-MM-dd")),Long.parseLong(timeUtil.dateTimeStamp(report_end_date,"yyyy-MM-dd")),uid);
            if (times>0){
                rDate.setReport_stat("done");
            }else {
                rDate.setReport_stat("open");
            }
        }



        rDate.setReview_end_date(reportDao.getStatusByName("review_end_date"));
        rDate.setReview_start_date(reportDao.getStatusByName("review_start_date"));
        rDate.setWork_end_date(reportDao.getStatusByName("work_end_date"));
        rDate.setWork_start_date(reportDao.getStatusByName("work_start_date"));


        return rDate;
    }

    @Override
    public List getReportByContent(String contact, String depart_0, String campus_0) {
        List<User> list = new  ArrayList<User>();
        list  = userDao.getUserByContentAll(contact,depart_0.equals("0")?0:Integer.parseInt(depart_0),Integer.parseInt(campus_0));
        List<Report> list1 = new  ArrayList<Report>();
        List<RReport> list2 = new  ArrayList<RReport>();
        String work_start_date = reportDao.getStatusByName("work_start_date");
        String work_end_date = reportDao.getStatusByName("work_end_date");
        work_start_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");

        list2 = reportDao.getReportById(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss")),contact,Integer.valueOf(depart_0),Integer.valueOf(campus_0));
        for (RReport rReport:list2)
        {   Salary salary = new Salary();
            salary.setFina(rReport.getSalary0());
            salary.setReview(rReport.getSalary1());
            rReport.setSalary(salary);
//            rReport.setRate((float) reportDao);
            rReport.setEnd_date(timeUtil.dateTime(Long.parseLong(rReport.getEnd_date()),"yyyy-MM-dd"));
            rReport.setStart_date(timeUtil.dateTime(Long.parseLong(rReport.getStart_date()),"yyyy-MM-dd"));

            if (rReport.getComment()==null){
                rReport.setStatus("normal");
            }else {
                rReport.setStatus("expired");
            }
        }
        return list2;
    }

    @Override
    public List getReportByContentAndCampus(String contact, String depart, String campus) {
        List<User> list = new ArrayList<User>();
        List<Report> list1 = new  ArrayList<Report>();
        List<RReport> list2 = new  ArrayList<RReport>();
        String work_start_date = reportDao.getStatusByName("work_start_date");
        String work_end_date = reportDao.getStatusByName("work_end_date");
        work_start_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");


        if (campus==null|| campus.equals("")){

            if (depart==null|| depart.equals("")){
//                list2  = userDao.getUserByContent(contact);
                list2 = reportDao.getReportByContact(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss")),contact);
            }else {
                //list2  = userDao.getUserByContentByDepart(contact, Integer.parseInt(depart));
                list2 = reportDao.getReportByIdAndDepart(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss")),contact,Integer.valueOf(depart));
            }
        }else {
            if (depart==null|| depart.equals("")){
//                list2  = userDao.getUserByContentByCampus(contact,campus);
                list2 = reportDao.getReportByIdAndCampus(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss")),contact,Integer.valueOf(campus));
            }else {

                list2 = reportDao.getReportById(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss")),contact,Integer.valueOf(depart),Integer.valueOf(campus));
//                list2  = userDao.getUserByContentAll(contact,Integer.valueOf(depart),Integer.valueOf(campus));
            }
        }

        for (RReport rReport:list2)
        {   Salary salary = new Salary();
            salary.setFina(rReport.getSalary0());
            salary.setReview(rReport.getSalary1());
            rReport.setSalary(salary);
            rReport.setEnd_date(timeUtil.dateTime(Long.parseLong(rReport.getEnd_date()),"yyyy-MM-dd"));
            rReport.setStart_date(timeUtil.dateTime(Long.parseLong(rReport.getStart_date()),"yyyy-MM-dd"));
            rReport.setStatus(dictDao.getStatusNameById(Integer.parseInt(rReport.getStatus())));
        }
        return list2;
//        List<Report> list1 = new  ArrayList<Report>();
//        String work_start_date = reportDao.getStatusByName("work_start_date");
//        String work_end_date = reportDao.getStatusByName("work_end_date");
//        for (User user:list)
//        {
//            int uid = user.getId();
//            long delete = 1497542400-1497456000;
//
//            work_start_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");
//            Report report = reportDao.getReportByUid(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss")),uid);
//            if (report == null){
//                continue;
//            }
//            report.setStart_date(work_start_date);
//            report.setEnd_date(work_end_date);
//            list1.add(report);
//        }
//        for (User user:list){
//            user.setPwd("");
//            user.setSalt("");
//        }
//        return list1;
    }

    @Override
    public boolean setStatus(RDate rDate) {
//
//        String report_start_date = reportDao.getStatusByName("report_start_date");
//        String report_end_date = reportDao.getStatusByName("report_end_date");
//
//        rDate.setReport_end_date(reportDao.getStatusByName("report_end_date"));
//        rDate.setReport_start_date(reportDao.getStatusByName("report_start_date"));
//
//        String state = reportDao.getStatusByName("report_stat");

        Long success = reportDao.setStatusByName("report_start_date",rDate.getReport_start_date());
        Long success1 = reportDao.setStatusByName("report_end_date",rDate.getReport_end_date());
        Long success2 = reportDao.setStatusByName("report_stat",rDate.getReport_stat());
        Long success3 = reportDao.setStatusByName("review_end_date",rDate.getReview_end_date());
        Long success4 = reportDao.setStatusByName("review_start_date",rDate.getReview_start_date());
        Long success5 = reportDao.setStatusByName("work_end_date",rDate.getWork_end_date());
        Long success6 = reportDao.setStatusByName("work_start_date",rDate.getWork_start_date());


        if (success>0 &&success1>0 &&success2>0 &&success3>0 &&success4>0 &&success5>0 &&success6>0 )
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean setStatusClose(String report_stat) {

        Long success = reportDao.setStatusByName("report_stat",report_stat);
        if (success>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean setReport(int uid1, String content, String suggestion) {
        String work_start_date = reportDao.getStatusByName("work_start_date");
        String work_end_date = reportDao.getStatusByName("work_end_date");
        work_start_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");

        work_start_date =  timeUtil.dateTimeStamp(work_start_date,"yyyy-MM-dd HH:mm:ss");

        work_end_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(work_end_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");

        work_end_date =String.valueOf( Long.parseLong( timeUtil.dateTimeStamp(work_end_date,"yyyy-MM-dd HH:mm:ss"))+86400-1);

        long success = reportDao.setReport(uid1,work_start_date,work_end_date,timeUtil.getSecondTimeNow(),content,suggestion==null?"":suggestion);
        if (success>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean setReportByAdmin(int id, String comment, String rate, String salary_sug, String salary) {

        long success = 0;
        if (salary != null){
            success =  reportDao.setReportSalaryByAdmin(id,salary);
        }
        if (comment !=null){
            success =  reportDao.setReporCommentByAdmin(id,comment);
        }
        if (rate !=null){
            success =  reportDao.setReportRateByAdmin(id,rate);
        }
        if (salary_sug !=null){
            success =  reportDao.setReportSalary_sugByAdmin(id,salary_sug);
        }
        if (success>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public List<RReport> getReportExcel(String depart1, String campus_0, String start_date, String end_date) {
        start_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(start_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");
        start_date =  timeUtil.dateTimeStamp(start_date,"yyyy-MM-dd HH:mm:ss");

        long start_date_long = Long.parseLong(start_date);


        List<RReport>  list = reportDao.getReport(depart1,campus_0,start_date_long);

        return list;
    }

    @Override
    public List<RReport> getReportAllExcel(String start_date, String end_date) {

        start_date = timeUtil.dateTime(Long.parseLong(timeUtil.dateTimeStamp(start_date,"yyyy-MM-dd")),"yyyy-MM-dd HH:mm:ss");
        start_date =  timeUtil.dateTimeStamp(start_date,"yyyy-MM-dd HH:mm:ss");

        long start_date_long = Long.parseLong(start_date);


        List<RReport>  list = reportDao.getAllReport(start_date_long);
        return list;
    }
}
