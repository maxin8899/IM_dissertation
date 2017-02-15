package com.Rest;

import com.model.ErrorCode;
import com.model.Resp;
import com.model.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by haizhi on 2017/2/7.
 */
@RestController
@RequestMapping("profile")
@Slf4j
public class UserController {
    @ExceptionHandler
    void handleException(Exception e, HttpServletResponse response)throws Exception{
        ErrorCode errorCode = ErrorCode.getByStatus(Integer.parseInt(e.getMessage()));
        response.sendError(errorCode.getHttpStatus().value(),errorCode.getMessage());
    }
    @Autowired
    UserService userService;
    @RequestMapping(path = "register",method = RequestMethod.PUT)
    public Resp register(@RequestBody User user) throws Exception{
        log.info("Get registering User {}",user.getMobile());
        return userService.register(user);
    }

    @RequestMapping(path = "login",method = RequestMethod.POST)
    public User login(@RequestBody User user)throws Exception{
        if(!StringUtils.isEmpty(user.getPassword())&&user.getMobile()!=null){
            log.info("User {} login ",user.getMobile());
            userService.login(user);
        }else {
            throw new IllegalArgumentException(Integer.toString(ErrorCode.WRONG_PARAMETER.getStatus()));
        }
        return null;
    }
    @RequestMapping(path = "complete",method = RequestMethod.POST)
    public Resp complete(@RequestBody User user)throws Exception{
        log.info("User {} complete",user.getMobile());
        return userService.complete(user);
    }
    @RequestMapping(path = "changePass",method = RequestMethod.PUT)
    public Resp changePassword(@RequestBody User user)throws Exception{
        log.info("User {} complete",user.getMobile());
        return userService.changePassword(user);
    }
    @RequestMapping(path = "changeAvatar",method = RequestMethod.POST)
    public Resp changeAvatar(@RequestParam("user")User user, @RequestParam("avatar")MultipartFile avatar)throws Exception{
        log.info("User {} change avatar",user.getMobile());
        return userService.changeAvatar(user,avatar);
    }
}
