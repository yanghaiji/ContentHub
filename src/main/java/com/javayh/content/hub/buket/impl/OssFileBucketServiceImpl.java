package com.javayh.content.hub.buket.impl;

import com.amazonaws.SdkClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javayh.content.hub.buket.interfaces.BucketFileService;
import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.configuration.BucketProperties;
import com.javayh.content.hub.db.entity.ContentHubImages;
import com.javayh.content.hub.db.service.DbFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * aws 操作 bucket
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-24
 */
@Slf4j
@Service
@AllArgsConstructor
public class OssFileBucketServiceImpl implements BucketFileService {

    private final DbFileService dbFileService;

    private final BucketProperties bucketProperties;

    /**
     * 获取bucket内的所有对象
     *
     * @param type       bucket 类型
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result bucketFileList(String type, String bucketName) {

        return Result.ok();
    }

    /**
     * 文件上传
     *
     * @param type          bucket 类型
     * @param multipartFile 上传的文件
     * @param bucketName    name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileUpload(String type, MultipartFile multipartFile, String bucketName) {
        try {
            return Result.ok("File uploaded successfully");
        } catch (Exception e) {
            log.error("fileUpload {}", e.getMessage(), e);
            return Result.fail("File upload failed: " + e.getMessage());
        }
    }

    /**
     * 文件上传
     *
     * @param multipartFiles 上传的文件
     * @param type           bucket 类型
     * @param bucketName     name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileUpload(String type, List<MultipartFile> multipartFiles, String bucketName) {
        for (MultipartFile file : multipartFiles) {
            fileUpload(type, file, bucketName);
        }
        return Result.ok("All files uploaded successfully");
    }

    /**
     * 文件下载
     *
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @param type       bucket 类型
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileDownLoad(String type, String bucketName, String key) {
        try {
            return Result.ok("File download successful");
        } catch (Exception e) {
            return Result.ok("File download failed: " + e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param type       bucket 类型
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileDelete(String type, String bucketName, String key) {
        try {
            // 同时删除s3的数据
            LambdaQueryWrapper<ContentHubImages> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ContentHubImages::getBucketName, bucketName).eq(ContentHubImages::getObjectKey, key);
            dbFileService.remove(queryWrapper);
        } catch (SdkClientException e) {
            log.error("fileDelete bucketName = {}, key = {} ,error{}", bucketName, key, e);
            return Result.fail();
        }
        return Result.ok();
    }

    private String aggUrl(String bucketName, String key) {
        return bucketProperties.getImageUrl() + "/" + bucketName + "/" + key;
    }
}
