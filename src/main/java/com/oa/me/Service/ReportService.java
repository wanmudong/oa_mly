package com.oa.me.Service;

import com.oa.me.domain.RDate;
import com.oa.me.domain.RReport;
import com.oa.me.domain.Report;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/9/27
 */
public interface ReportService {
    List getHistory(int uid);

    RDate getStatus(int uid);

    List getReportByContent(String contact, String depart_0, String campus_0);

    List getReportByContentAndCampus(String contact, String depart, String campus);

    boolean setStatus(RDate rDate);

    boolean setStatusClose(String report_stat);

    boolean setReport(int uid1, String content, String suggestion);

    boolean setReportByAdmin(int id, String comment, String rate, String salary_sug, String salary);

    List<RReport> getReportExcel(String depart1, String campus_0, String start_date, String end_date);

    List<RReport> getReportAllExcel(String start_date, String end_date);
}
