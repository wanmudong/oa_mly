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
    /**
     * 获取个人的历史汇报信息
     * @param uid
     * @return
     */
    List getHistory(int uid);

    /**
     * 获取个人以及汇报的状态信息
     * @param uid
     * @return
     */
    RDate getStatus(int uid);

    /**
     * 以部长权限获取汇报信息
     * @param contact
     * @param depart_0
     * @param campus_0
     * @return
     */
    List getReportByContent(String contact, String depart_0, String campus_0);

    /**
     * 以主管权限获取汇报信息
     * @param contact
     * @param depart
     * @param campus
     * @return
     */
    List getReportByContentAndCampus(String contact, String depart, String campus);

    /**
     * 修改汇报状态
     * @param rDate
     * @return
     */
    boolean setStatus(RDate rDate);

    /**
     * 是否关闭汇报
     * @param report_stat
     * @return
     */
    boolean setStatusClose(String report_stat);

    /**
     * 存储汇报信息
     * @param uid1
     * @param content
     * @param suggestion
     * @return
     */
    boolean setReport(int uid1, String content, String suggestion);

    /**
     * 审核汇报
     * @param id
     * @param comment
     * @param rate
     * @param salary_sug
     * @param salary
     * @return
     */
    boolean setReportByAdmin(int id, String comment, String rate, String salary_sug, String salary);

    /**
     * 获取到本部门的汇报数据
     * @param depart1
     * @param campus_0
     * @param start_date
     * @param end_date
     * @return
     */
    List<RReport> getReportExcel(String depart1, String campus_0, String start_date, String end_date);

    /**
     * 获取到所有的汇报数据
     * @param start_date
     * @param end_date
     * @return
     */
    List<RReport> getReportAllExcel(String start_date, String end_date);
}
