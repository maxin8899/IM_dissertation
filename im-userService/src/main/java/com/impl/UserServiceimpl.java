package com.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.mapper.UserMapper;
import com.model.ErrorCode;
import com.model.Resp;
import com.model.User;
import com.service.UserService;
import com.util.FileUpload;
import com.util.Identicon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;

/**
 * Created by haizhi on 2017/2/8.
 */
@Service
@Slf4j
public class UserServiceimpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    Identicon identicon;
    @Autowired
    FileUpload fileUpload;
    @Override
    public Resp register(User user) throws Exception{
        if(userMapper.selectByMobile(user.getMobile())!=null){
            log.info("mobile {} has been registered, cancel register and return error",user.getMobile());
            throw new IllegalArgumentException(Integer.toString(ErrorCode.REPLICIA_USER.getStatus()));
        }else {
            //创建默认头像并且将其上传到七牛
            Hasher hasher = Hashing.md5().newHasher();
            hasher.putString(Long.toString(user.getMobile()), Charsets.UTF_8);
            String md5 = hasher.hash().toString();
            BufferedImage image = identicon.create(md5,200);
            File avatar = new File(md5+".png");
            ImageIO.write(image,"PNG",avatar);
            fileUpload.upload(avatar,md5+".png");
            avatar.delete();
            user.setAvatar(md5);
            //用户入库
            userMapper.insert(user);
        }
        return new Resp("用户注册成功!");
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.selectByMobile(user.getMobile());
        if(user1==null){
            log.info("User {} not existed, cancel login and return error hint",user.getMobile());
            throw new IllegalArgumentException(Integer.toString(ErrorCode.NO_USER.getStatus()));
        }else {
            if(!user1.getPassword().equals(user.getPassword())){
                log.warn("User {} has wrong password, stop now and return error");
                throw new IllegalArgumentException(Integer.toString(ErrorCode.WRONG_PASSWORD.getStatus()));
            }else {
                return user1;
            }
        }
    }

    @Override
    public Resp complete(User user) throws Exception {
        User user1 = userMapper.selectByMobile(user.getMobile());
        if(user1==null){
            log.info("User {} not existed, cancel login and return error hint",user.getMobile());
            throw new IllegalArgumentException(Integer.toString(ErrorCode.NO_USER.getStatus()));
        }else {
            userMapper.updateById(user);
            return new Resp("资料完善成功");
        }
    }

    @Override
    public Resp changePassword(User user) throws Exception {
        User user1 = userMapper.selectByMobile(user.getMobile());
        if(user1==null){
            log.info("User {} not existed, cancel password change and return error hint",user.getMobile());
            throw new IllegalArgumentException(Integer.toString(ErrorCode.NO_USER.getStatus()));
        }else {
            if(!user1.getPassword().equals(user.getPassword())){
                log.warn("User {} has wrong password, stop now and return error");
                throw new IllegalArgumentException(Integer.toString(ErrorCode.WRONG_PASSWORD.getStatus()));
            }else {
                userMapper.updateById(user);
                return new Resp("密码修改成功");
            }
        }
    }

    @Override
    public Resp changeAvatar(User user, MultipartFile avatar) throws Exception {
        User user1 = userMapper.selectByMobile(user.getMobile());
        if(user1==null){
            log.info("User {} not existed, cancel avatar change and return error hint",user.getMobile());
            throw new IllegalArgumentException(Integer.toString(ErrorCode.NO_USER.getStatus()));
        }else {
            if(avatar.isEmpty()){
                throw new IllegalArgumentException(Integer.toString(ErrorCode.FILE_UPLOAD_ERROR.getStatus()));
            }else{
                byte[] bytes = avatar.getBytes();
                fileUpload.upload(bytes,user1.getAvatar());
            }
            return new Resp("头像修改成功");
        }
    }
}
