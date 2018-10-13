package com.oa.me.Service;

import com.oa.me.domain.Recruit;
import com.oa.me.domain.User;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
public interface RecruitService {
    /**
     * 通过id获取一条招新信息
     * @param id
     * @return
     */
    Recruit getRecruitById(int id);

    /**
     * 通过部分信息查询相关招新信息
     * @param recruit
     * @return
     */
    List<Recruit> getRecruitByOther(Recruit recruit);

    /**
     * 更新某一条招新信息
     * @param id
     * @param status
     * @param desc
     * @param depart
     * @return
     */
    Recruit update(int id, int status, String desc, String depart);

    /**
     * 上传一条招新信息
     * @param recruit
     * @return
     */
    boolean apply(Recruit recruit);

    /**
     * 以部长的权限获取招生信息
     * @param content
     * @param depart
     * @param campus_0
     * @return
     */
    List<Recruit> getRecruitByContent(String content, String depart, String campus_0);

    /**
     * 以主管的权限获取招生信息
     * @param content
     * @param department
     * @param campus
     * @return
     */
    List<Recruit> getRecruitByContentAndCampus(String content, String department, String campus);
}
