package com.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.admin.dao.entity.UserDO;
import com.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    UserRespDTO getUserByUsername(String username);

    Boolean hasUsername(String username);

    void register(UserRegisterReqDTO requestParam);
}
