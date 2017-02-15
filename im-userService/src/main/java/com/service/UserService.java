package com.service;

import com.model.Resp;
import com.model.User;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by haizhi on 2017/2/7.
 */

public interface UserService {
    public Resp register(User user)throws Exception;
    public User login(User user)throws Exception;
    public Resp complete(User user)throws Exception;
    public Resp changePassword(User user)throws Exception;
    public Resp changeAvatar(User user, MultipartFile avatar)throws Exception;
}
