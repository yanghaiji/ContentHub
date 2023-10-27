package com.javayh.content.hub.db.api;

import com.javayh.content.hub.buket.BucketFileStrategySelector;
import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.db.service.DbFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 数据库查询
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-25
 */
@RestController
@RequestMapping(value = "/db")
public class DbFileApiController {

    @Autowired
    private DbFileService dbFileService;

    @Autowired
    private BucketFileStrategySelector bucketFileService;

    @GetMapping(value = "/bu/info")
    public Result bucketList() {
        return dbFileService.bucketListInfo();
    }


    /**
     * 查询bucket下所有的资源
     *
     * @param bucketType bucket 类型
     */
    @GetMapping(value = "/bu/list")
    public Result bucketList(String bucketType) {
        return dbFileService.bucketList(bucketType);
    }

    /**
     * 查询bucket下所有的资源
     *
     * @param id 数据id
     */
    @DeleteMapping(value = "/bu/del")
    public Result bucketDelById(@RequestParam(value = "id") Long id,
                                @RequestParam (value = "bucketType") String bucketType,
                                @RequestParam (value = "bucketName") String bucketName,
                                @RequestParam (value = "objectKey") String objectKey) {
        return Result.ok(bucketFileService.getBucketFileService(bucketType).fileDelete(bucketType, bucketName, objectKey));
    }

    /**
     * 上传文件
     *
     * @param file       文件列表
     * @param bucketName 上传的bucket 名字
     * @param bucketType bucket 的类型
     */
    @PostMapping(value = "/bu/upload")
    public Result upload(List<MultipartFile> file, String bucketName, String bucketType) {
        return Result.ok(bucketFileService.getBucketFileService(bucketType).fileUpload(bucketType,file,bucketName));
    }


}
