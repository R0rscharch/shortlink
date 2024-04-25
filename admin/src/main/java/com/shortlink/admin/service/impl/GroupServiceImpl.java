package com.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shortlink.admin.common.biz.user.UserContext;
import com.shortlink.admin.dao.entity.GroupDO;
import com.shortlink.admin.dao.mapper.GroupMapper;
import com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.shortlink.admin.service.GroupService;
import com.shortlink.admin.util.RandomGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 短链接接口实现层
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom(6);
        } while (hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .username(UserContext.getUsername())
                .sortOrder(0)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> list = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(list, ShortLinkGroupRespDTO.class);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO result = baseMapper.selectOne(queryWrapper);
        return result != null;
    }
}
