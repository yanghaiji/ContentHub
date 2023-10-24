package com.javayh.content.hub.buket;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.javayh.content.hub.buket.interfaces.BucketService;
import com.javayh.content.hub.common.AwsClientUtil;
import com.javayh.content.hub.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-24
 */
@Slf4j
@Service
public class AwsBucketServiceImpl implements BucketService {

    /**
     * 获取BucketList
     *
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result getBucketList() {
        AmazonS3 s3 = AwsClientUtil.s3Client();
        if (s3 != null) {
            List<Bucket> bucketList = s3.listBuckets();
            return Result.ok(bucketList);
        }
        return Result.fail("连接异常");
    }

    /**
     * 创建 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result createBucket(String bucketName) {
        if (StringUtils.isNotBlank(bucketName)) {
            AmazonS3 s3 = AwsClientUtil.s3Client();
            if (s3 != null) {
                List<Bucket> bucketList = s3.listBuckets();
                for (Bucket bucket : bucketList) {
                    if (bucket.getName().equals(bucketName)) {
                        return Result.fail(bucketName + "已存在");
                    }
                }
                Bucket bucket;
                try {
                    CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                    bucket = s3.createBucket(createBucketRequest);
                } catch (SdkClientException e) {
                    log.error("SdkClientException {}", e.getMessage(), e);
                    return Result.fail("bucket创建异常");
                }
                return Result.ok(bucket);

            }
            return Result.fail("连接异常");
        }
        return Result.fail("参数异常");
    }

    /**
     * 删除 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result delBucket(String bucketName) {
        if (StringUtils.isNotBlank(bucketName)) {
            AmazonS3 s3 = AwsClientUtil.s3Client();
            if (s3 != null) {
                List<Bucket> bucketList = s3.listBuckets();
                for (Bucket bucket : bucketList) {
                    if (bucket.getName().equals(bucketName)) {
                        return Result.fail(bucketName + "已存在");
                    }
                }
                try {
                    DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucketName);
                    s3.deleteBucket(deleteBucketRequest);
                } catch (SdkClientException e) {
                    log.error("SdkClientException {}", e.getMessage(), e);
                    return Result.fail("bucket创建异常");
                }
                return Result.ok();

            }
            return Result.fail("连接异常");
        }
        return Result.fail("参数异常");
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
        try {
            // 创建新的存储桶
            createBucket(newBucketName);
            AmazonS3 s3 = AwsClientUtil.s3Client();
            // 复制旧存储桶中的数据到新存储桶
            List<S3ObjectSummary> objects = listObjectsInBucket(bucketName, s3);
            for (S3ObjectSummary objectSummary : objects) {
                String objectKey = objectSummary.getKey();
                copyObject(bucketName, objectKey, newBucketName, objectKey, s3);
            }

            // 删除旧存储桶
            delBucket(bucketName);
        } catch (Exception e) {
            log.error("updateBucket {}", e.getMessage(), e);
            return Result.fail("修改bucket失败");
        }
        return Result.ok();
    }

    // 辅助方法：列出存储桶中的对象
    private List<S3ObjectSummary> listObjectsInBucket(String bucketName, AmazonS3 s3Client) {
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    // 辅助方法：复制对象
    private void copyObject(String sourceBucketName, String sourceObjectKey,
                            String destinationBucketName, String destinationObjectKey, AmazonS3 s3Client) {
        s3Client.copyObject(sourceBucketName, sourceObjectKey, destinationBucketName, destinationObjectKey);
    }


}
