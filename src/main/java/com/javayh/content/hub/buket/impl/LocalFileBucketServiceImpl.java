package com.javayh.content.hub.buket.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javayh.content.hub.buket.interfaces.BucketFileService;
import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.configuration.BucketProperties;
import com.javayh.content.hub.common.FileUtils;
import com.javayh.content.hub.db.entity.ContentHubImages;
import com.javayh.content.hub.db.service.DbFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 本地保存
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-26
 */
@Slf4j
@Service
@AllArgsConstructor
public class LocalFileBucketServiceImpl implements BucketFileService {

    private final BucketProperties bucketProperties;

    private final DbFileService dbFileService;

    /**
     * 获取bucket内的所有对象
     *
     * @param type       bucket 类型
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result bucketFileList(String type, String bucketName) {
        return null;
    }

    /**
     * 文件上传
     *
     * @param type       bucket 类型
     * @param file       上传的文件
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileUpload(String type, MultipartFile file, String bucketName) {
        try {
            String fileName = file.getOriginalFilename();
            String path = bucketProperties.getLocalPath() + File.separator + bucketName + File.separator + fileName;

            // Save the uploaded file to the server's file system
            File dest = new File(path);
            dest.mkdirs();
            file.transferTo(dest);

            // Generate the URL for accessing the resource
            String fileUrl = bucketProperties.getImageUrl() + "/images?bucketName=" + bucketName + "&imageName=" + fileName;

            // 存入数据库
            ContentHubImages image = new ContentHubImages();
            image.setBucketName(bucketName);
            image.setBucketType(type);
            image.setObjectKey(fileName);
            image.setImageUrl(fileUrl);
            image.setDescription(fileName);
            dbFileService.save(image);
        } catch (IOException e) {
            log.error("Local file upload failed", e);
            return Result.fail("File upload failed");
        }

        return Result.ok();
    }

    /**
     * 文件上传
     *
     * @param type           bucket 类型
     * @param multipartFiles 上传的文件
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
     * @param type       bucket 类型
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    @Override
    public Result fileDownLoad(String type, String bucketName, String key) {
        return null;
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
        String filePath = bucketProperties.getLocalPath() + File.separator + bucketName;
        FileUtils.deleteSpecificFile(filePath, key);
        LambdaQueryWrapper<ContentHubImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ContentHubImages::getBucketName, bucketName)
                .eq(ContentHubImages::getBucketType, type)
                .eq(ContentHubImages::getObjectKey, key);
        dbFileService.remove(queryWrapper);
        return Result.ok();
    }
}
