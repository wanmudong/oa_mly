package com.oa.me.Service.impl;

import com.oa.me.Dao.DictDao;
import com.oa.me.Service.DictService;
import com.oa.me.domain.Dict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/9/20
 */
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictDao dictDao;

    @Override
    public Map<String, List<Dict>> getAllDict() {
        List<String> list_table_name = dictDao.getDictTableNames();
        Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
        for (String table_name:list_table_name)
        {
                String dict_name  = dictDao.getDictMemo(table_name);
                List<Dict> list = dictDao.getDictByTableName(table_name);
                map.put(dict_name,list);
        }
        System.out.println(map);
        return map;
    }
}
