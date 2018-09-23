package com.oa.me.Service;

import com.oa.me.domain.Recruit;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
public interface RecruitService {
    Recruit getRecruitById(int id);

    List<Recruit> getRecruitByOther(Recruit recruit);

    Recruit update(String id, String status, String desc, String depart);

    boolean apply(Recruit recruit);
}
