package com.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shortlink.admin.dao.entity.GroupDO;
import com.shortlink.admin.dao.mapper.GroupMapper;
import com.shortlink.admin.service.GroupService;
import com.shortlink.admin.util.RandomGenerator;
import org.springframework.stereotype.Service;

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
        GroupDO groupDO = GroupDO.builder().gid(gid)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, null);
        GroupDO result = baseMapper.selectOne(queryWrapper);
        return result != null;
    }
}
