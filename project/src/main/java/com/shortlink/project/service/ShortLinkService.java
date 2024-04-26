package com.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.project.dao.entity.ShortLinkDO;
import com.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.shortlink.project.dto.resp.ShortLinkPageRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {

    ShortLinkCreateRespDTO creatShortLink(ShortLinkCreateReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
