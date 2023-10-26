package com.javayh.content.hub.buket.impl;

import com.javayh.content.hub.buket.interfaces.BucketService;
import com.javayh.content.hub.common.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 * aliyun oss
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-25
 */
@Service
public class OssBucketServiceImpl implements BucketService {

    /**
     * 获取BucketList
     *
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result getBucketList() {
        return null;
    }

    /**
     * 创建 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result createBucket(String bucketName) {
        return null;
    }

    /**
     * 删除 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result delBucket(String bucketName) {
        return null;
    }

    /**
     * 修改 bucket
     *
     * @param bucketName    old name
     * @param newBucketName new name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result updateBucket(String bucketName, String newBucketName) {
        return null;
    }
}
