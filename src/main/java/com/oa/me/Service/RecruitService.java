package com.oa.me.Service;

import com.oa.me.domain.Recruit;
import com.oa.me.domain.User;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
public interface RecruitService {
    Recruit getRecruitById(int id);

    List<Recruit> getRecruitByOther(Recruit recruit);

    Recruit update(int id, int status, String desc, String depart);

    boolean apply(Recruit recruit);

    List<Recruit> getRecruitByContent(String content, String depart, String campus_0);

    List<Recruit> getRecruitByContentAndCampus(String content, String department, String campus);
}
