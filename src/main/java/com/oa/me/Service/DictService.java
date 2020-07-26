package com.oa.me.Service;

import com.oa.me.domain.Dict;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/9/20
 */
public interface DictService {

    /**
     * 用来获取一些在数据库中定义好的字段，诸如部门，校区，工作状态等
     * @return
     */
    Map<String, List<Dict>> getAllDict();
}
