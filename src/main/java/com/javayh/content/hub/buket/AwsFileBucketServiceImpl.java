package com.javayh.content.hub.buket;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javayh.content.hub.buket.interfaces.BucketFileService;
import com.javayh.content.hub.common.AwsClientUtil;
import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.configuration.BucketProperties;
import com.javayh.content.hub.db.entity.ContentHubImages;
import com.javayh.content.hub.db.service.DbFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
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
public class AwsFileBucketServiceImpl implements BucketFileService {

    private final DbFileService dbFileService;

    private final BucketProperties bucketProperties;

    /**
     * 获取bucket内的所有对象
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result bucketFileList(String bucketName) {
        ObjectListing objectListing = AwsClientUtil.s3Client().listObjects(bucketName);
        List<String> objectKeys = new ArrayList<>();
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            objectKeys.add(objectSummary.getKey());
        }
        return Result.ok(objectKeys);
    }

    /**
     * 文件上传
     *
     * @param multipartFile 上传的文件
     * @param bucketName    name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileUpload(MultipartFile multipartFile, String bucketName) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream fileInputStream = multipartFile.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, fileInputStream, metadata);
            AwsClientUtil.s3Client().putObject(request);
            // 存入数据库
            ContentHubImages image = new ContentHubImages();
            image.setBucketName(bucketName);
            image.setObjectKey(fileName);
            image.setImageUrl(aggUrl(bucketName, fileName));
            image.setDescription(fileName);
            dbFileService.save(image);
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
     * @param bucketName     name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileUpload(List<MultipartFile> multipartFiles, String bucketName) {
        for (MultipartFile file : multipartFiles) {
            fileUpload(file, bucketName);
        }
        return Result.ok("All files uploaded successfully");
    }

    /**
     * 文件下载
     *
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileDownLoad(String bucketName, String key) {
        try {
            AwsClientUtil.s3Client().getObject(bucketName, key);
            return Result.ok("File download successful");
        } catch (Exception e) {
            return Result.ok("File download failed: " + e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileDelete(String bucketName, String key) {
        try {
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key);
            AwsClientUtil.s3Client().deleteObject(deleteObjectRequest);
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
