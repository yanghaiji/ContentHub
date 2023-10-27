package com.javayh.content.hub.buket.interfaces;

import com.javayh.content.hub.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * bucket 上传文件相关处理
 *
 * @author haiji
 */
public interface BucketFileService {

    /**
     * 获取bucket内的所有对象
     *
     * @param type       bucket 类型
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    Result bucketFileList(String type, String bucketName);

    /**
     * 文件上传
     *
     * @param type          bucket 类型
     * @param multipartFile 上传的文件
     * @param bucketName    name
     * @return {@link Result} 统一的返回
     */
    Result fileUpload(String type, MultipartFile multipartFile, String bucketName);


    /**
     * 文件上传
     *
     * @param multipartFiles 上传的文件
     * @param type           bucket 类型
     * @param bucketName     name
     * @return {@link Result} 统一的返回
     */
    Result fileUpload(String type, List<MultipartFile> multipartFiles, String bucketName);

    /**
     * 文件下载
     *
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @param type       bucket 类型
     * @return {@link Result} 统一的返回
     */
    Result fileDownLoad(String type, String bucketName, String key);


    /**
     * 删除文件
     *
     * @param type       bucket 类型
     * @param bucketName name
     * @param key        bucket 的唯一标识
     * @return {@link Result} 统一的返回
     */
    Result fileDelete(String type, String bucketName, String key);
}