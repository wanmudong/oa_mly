package com.oa.me.Service;

import com.oa.me.domain.AnnounceModel;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/18
 */
public interface AnnounceService {
    public List<AnnounceModel> getAnnounceModel();

    boolean setAnnounceModel(AnnounceModel announceModel);

    boolean delAnnounce(int id);
}
