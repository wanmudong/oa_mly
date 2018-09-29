package com.oa.me.Dao;

import com.oa.me.domain.JUser;
import com.oa.me.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/25
 */
@Mapper
public interface DtoConvert {
    @Resource
    DictDao dictDao = null;

    DtoConvert INSTANCE = Mappers.getMapper(DtoConvert.class);
    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "join_time", target = "time")
//            @Mapping(source = "stuid", target = "stuid"),
//            @Mapping(source = "name", target = "name"),
//            @Mapping(target = "depart", expression = "java(com.oa.me.Dao.DictDao.getDepartIdByName(user.getDepart))"),
//            @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
//            @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
//            @Mapping(source = "user.age", target = "age"),
//            @Mapping(target = "email", ignore = true)
    })
    JUser domain2dto(User user);

    List<JUser> domain2dto(List<User> userList);
}
