package com.oa.me.Service;

import com.oa.me.domain.AnnounceModel;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/18
 */
public interface AnnounceService {
    /**
     * 获取通知列表
     * @return
     */
    public List<AnnounceModel> getAnnounceModel();

    /**
     * 插入一条通知
     * @param announceModel
     * @return
     */
    boolean setAnnounceModel(AnnounceModel announceModel);

    /**
     * 根据id来删除一条通知
     * @param id
     * @return
     */
    boolean delAnnounce(int id);
}
