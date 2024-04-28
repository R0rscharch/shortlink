package com.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.project.dao.entity.ShortLinkDO;
import com.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import com.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.shortlink.project.dto.resp.ShortLinkPageRespDTO;

public interface RecycleBinService extends IService<ShortLinkDO> {

    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    void recoverShortLink(RecycleBinRecoverReqDTO requestParam);

    void removeShortLink(RecycleBinRemoveReqDTO requestParam);
}
