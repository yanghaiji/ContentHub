package com.javayh.content.hub.buket.interfaces;

import com.javayh.content.hub.common.Result;

/**
 * bucket的curd
 *
 * @author haiji
 */
public interface BucketService {
    /**
     * 获取BucketList
     *
     * @return {@link Result} 统一的返回
     */
    Result getBucketList();

    /**
     * 创建 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    Result createBucket(String bucketName);


    /**
     * 删除 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    Result delBucket(String bucketName);


    /**
     * 修改 bucket
     *
     * @param bucketName    old name
     * @param newBucketName new name
     * @return {@link Result} 统一的返回
     */
    Result updateBucket(String bucketName, String newBucketName);

}