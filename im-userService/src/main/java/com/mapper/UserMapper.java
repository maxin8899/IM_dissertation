package com.mapper;

import com.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by haizhi on 2017/2/7.
 */
@Mapper
public interface UserMapper {
    int insert(User user);
    User selectByMobile(Long mobile);
    int updateByMobile(User user);
    int deleteByMobile(Long mobile);
    int updateById(User user);


}
