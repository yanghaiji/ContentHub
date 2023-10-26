package com.javayh.content.hub.api;

import com.javayh.content.hub.buket.BucketFileStrategySelector;
import com.javayh.content.hub.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-25
 */
@RestController
@RequestMapping(value = "/file")
public class FileBucketApiController {

    @Autowired
    private BucketFileStrategySelector bucketFileService;

    /**
     * 获取bucket内的所有对象
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @GetMapping(value = "/list")
    public Result bucketFileList(String type, String bucketName) {
        return bucketFileService.getBucketFileService(type).bucketFileList(type, bucketName);
    }

    /**
     * 文件上传
     *
     * @param multipartFile 上传的文件
     * @param bucketName    name
     * @return {@link Result} 统一的返回
     */
    @PostMapping(value = "/upload")
    public Result fileUpload(MultipartFile multipartFile, String bucketName, String type) {
        return bucketFileService.getBucketFileService(type).fileUpload(type, multipartFile, bucketName);
    }

    /**
     * 文件上传
     *
     * @param multipartFiles 上传的文件
     * @param bucketName     name
     * @return {@link Result} 统一的返回
     */
    @PostMapping(value = "/uploads")
    public Result fileUpload(List<MultipartFile> multipartFiles, String bucketName, String type) {
        return bucketFileService.getBucketFileService(type).fileUpload(type, multipartFiles, bucketName);
    }

    /**
     * 文件下载
     *
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    @GetMapping(value = "/download")
    public Result fileDownLoad(String bucketName, String key, String type) {
        return bucketFileService.getBucketFileService(type).fileDownLoad(type, bucketName, key);
    }

    /**
     * 删除文件
     *
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    @DeleteMapping(value = "/del")
    public Result fileDelete(String bucketName, String key, String type) {
        return bucketFileService.getBucketFileService(type).fileDelete(type, bucketName, key);
    }
}
