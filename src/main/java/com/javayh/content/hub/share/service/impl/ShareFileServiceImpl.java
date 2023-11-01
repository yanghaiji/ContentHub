package com.javayh.content.hub.share.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javayh.content.hub.configuration.BucketProperties;
import com.javayh.content.hub.db.entity.ContentHubImages;
import com.javayh.content.hub.db.service.DbFileService;
import com.javayh.content.hub.share.bo.ShareLinkBo;
import com.javayh.content.hub.share.dao.ShareItemsInfoMapper;
import com.javayh.content.hub.share.entity.ShareItemsInfoEntity;
import com.javayh.content.hub.share.service.ShareFileService;
import com.javayh.content.hub.share.vo.ShareLinkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 接口实现
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-31
 */
@Service
public class ShareFileServiceImpl extends ServiceImpl<ShareItemsInfoMapper, ShareItemsInfoEntity> implements ShareFileService {

    @Autowired
    private BucketProperties properties;
    @Autowired
    private DbFileService dbFileService;

    /**
     * 实现share 逻辑
     *
     * @param bo 入参
     * @return {@link ShareLinkVo}
     */
    @Override
    public ShareLinkVo shareItems(ShareLinkBo bo) {
        String dateEn = bo.toString() + UUID.randomUUID().toString();
        String url = DigestUtils.md5DigestAsHex(dateEn.getBytes());
        String shareUrlPrefix = properties.getShareUrlPrefix();
        String ids = bo.getSelectedIds().stream().map(Object::toString).collect(Collectors.joining(", "));
        ShareItemsInfoEntity infoEntity = new ShareItemsInfoEntity();
        Date date = new Date();
        infoEntity.setCreateTime(date);
        infoEntity.setSelectedIds(ids);
        infoEntity.setShareUrlPrefix(shareUrlPrefix);
        infoEntity.setLink(url);
        infoEntity.setEncrypt(bo.getEncrypt());
        infoEntity.setExpiration(bo.getExpiration());
        infoEntity.setExpirationTime(date.getTime());
        // 数据落库
        this.save(infoEntity);
        return ShareLinkVo.builder().link(shareUrlPrefix + infoEntity.getLink()).encrypt(bo.getEncrypt()).build();
    }

    /**
     * 验证字段的可用性
     *
     * @param i 资源编号
     * @param p 验证凭证
     * @return
     */
    @Override
    public Boolean verify(String i, String p) {
        List<ShareItemsInfoEntity> list = this.list(new LambdaQueryWrapper<ShareItemsInfoEntity>()
                .eq(ShareItemsInfoEntity::getLink, i).eq(ShareItemsInfoEntity::getEncrypt, p));
        return CollectionUtil.isNotEmpty(list);
    }

    /**
     * 查询分享的资源
     *
     * @param i 资源编号
     * @param p 验证凭证
     */
    @Override
    public List<ContentHubImages> shareItemsList(String i, String p) {
        List<ShareItemsInfoEntity> list = this.list(new LambdaQueryWrapper<ShareItemsInfoEntity>()
                .eq(ShareItemsInfoEntity::getLink, i).eq(ShareItemsInfoEntity::getEncrypt, p));
        String selectedIds = list.stream().findFirst().get().getSelectedIds();
        List<Long> resultList = Arrays.stream(selectedIds.split(", "))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        return dbFileService.listByIds(resultList);
    }


}
