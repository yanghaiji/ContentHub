package com.javayh.content.hub.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.db.entity.ContentHubImages;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-24
 */
public interface DbFileService extends IService<ContentHubImages> {

    /**
     * 查询所有的bucket list
     *
     * @param bucketType bucketType
     * @return {@link Result}
     */
    Result bucketList(String bucketType);

    /**
     * 查询所有的 bucket 类型
     *
     * @return {@link Result}
     */
    Result bucketListInfo();

}
