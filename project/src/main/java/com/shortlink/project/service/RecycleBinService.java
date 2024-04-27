package com.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.project.dao.entity.ShortLinkDO;
import com.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.shortlink.project.dto.resp.ShortLinkPageRespDTO;

public interface RecycleBinService extends IService<ShortLinkDO> {

    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
