package com.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shortlink.admin.common.convention.result.Result;
import com.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * URL 回收站接口层
 */
public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
