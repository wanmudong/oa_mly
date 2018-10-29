package com.oa.me.modules.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by chenjiehao on 2018/10/27
 */
@Slf4j
public class JsonMapper {

    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * 将json转化为对象
     * @author yangwenkui
     * @time 2017年3月16日 下午2:56:26
     * @param json json对象
     * @param clazz 待转化的对象类型
     * @return 当转化发生异常时返回null
     */
    public static <T> T fromJson(String json,Class<T> clazz){
        if(json == null){
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(String.format("json=[%s]", json), e);
        }
        return null;
    }

    /**
     * 将对象转化为json
     * @author yangwenkui
     * @time 2017年3月16日 下午2:55:10
     * @param obj 待转化的对象
     * @return 当转化发生异常时返回null
     */
    public static String toJson(Object obj){
        if(obj == null){
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error(String.format("obj=[%s]", obj.toString()), e);
        }
        return null;
    }


}
