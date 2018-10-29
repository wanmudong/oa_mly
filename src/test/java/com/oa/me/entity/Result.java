package com.oa.me.entity;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/26
 */
@Data
public class Result<T>
{
    private boolean success;
    private Message_oa msg;
    private List<T> data;
}
