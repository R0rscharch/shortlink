package com.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shortlink.project.common.convention.result.Result;
import com.shortlink.project.common.convention.result.Results;
import com.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/creat")
    public Result<ShortLinkCreateRespDTO> creatShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.creatShortLink(requestParam));
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(@RequestBody ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
}
