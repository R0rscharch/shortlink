package com.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.project.dao.entity.ShortLinkDO;
import com.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.util.List;

public interface ShortLinkService extends IService<ShortLinkDO> {

    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);

    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLink(List<String> requestParam);

}
