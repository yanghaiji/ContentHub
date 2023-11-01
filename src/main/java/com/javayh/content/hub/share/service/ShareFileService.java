package com.javayh.content.hub.share.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javayh.content.hub.db.entity.ContentHubImages;
import com.javayh.content.hub.share.bo.ShareLinkBo;
import com.javayh.content.hub.share.entity.ShareItemsInfoEntity;
import com.javayh.content.hub.share.vo.ShareLinkVo;

import java.util.List;

/**
 * <p>
 * share 文件接口
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-31
 */
public interface ShareFileService extends IService<ShareItemsInfoEntity> {

    /**
     * 实现share 逻辑
     *
     * @param bo 入参
     * @return {@link ShareLinkVo}
     */
    ShareLinkVo shareItems(ShareLinkBo bo);

    /**
     * 验证字段的可用性
     *
     * @param i 资源编号
     * @param p 验证凭证
     */
    Boolean verify(String i, String p);

    /**
     * 查询分享的资源
     *
     * @param i 资源编号
     * @param p 验证凭证
     */
    List<ContentHubImages> shareItemsList(String i, String p);
}
