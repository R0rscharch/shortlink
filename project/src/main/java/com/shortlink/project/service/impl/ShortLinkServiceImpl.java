package com.shortlink.project.service.impl;

import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shortlink.project.common.convention.exception.ServiceException;
import com.shortlink.project.dao.entity.ShortLinkDO;
import com.shortlink.project.dao.mapper.ShortLinkMapper;
import com.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.shortlink.project.service.ShortLinkService;
import com.shortlink.project.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO creatShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String domain = requestParam.getDomain();
        String fullShortUrl = StrBuilder.create(domain)
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(domain)
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .totalPv(0)
                .totalUv(0)
                .totalUip(0)
                .delTime(0L)
                .fullShortUrl(fullShortUrl)
                .build();
        baseMapper.insert(shortLinkDO);
        shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
                .gid(shortLinkDO.getGid())
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkDO.getOriginUrl())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String originUrl = requestParam.getOriginUrl();
        String shortLinkSuffix;
        int generateCount = 10;
        while (true) {
            String salt = String.valueOf(System.currentTimeMillis());
            shortLinkSuffix = HashUtil.hashToBase62(originUrl + salt);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(shortLinkSuffix)) {
                LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                        .eq(ShortLinkDO::getFullShortUrl, requestParam.getDomain() + '/' + shortLinkSuffix);
                ShortLinkDO result = baseMapper.selectOne(queryWrapper);
                if (result == null) {
                    break;
                }
            }
            if (--generateCount == 0) {
                throw new ServiceException("短链接生成失败，请稍后重试");
            }
        }
        return shortLinkSuffix;
    }
}
