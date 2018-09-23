package com.oa.me.Service;

import com.oa.me.domain.Dict;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/9/20
 */
public interface DictService {

    Map<String, List<Dict>> getAllDict();
}
