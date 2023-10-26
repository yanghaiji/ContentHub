package com.javayh.content.hub.api;

import com.javayh.content.hub.buket.BucketStrategySelector;
import com.javayh.content.hub.common.Result;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/bucket")
public class BucketApiController {

    private final BucketStrategySelector bucketService;

    public BucketApiController(BucketStrategySelector bucketService) {
        this.bucketService = bucketService;
    }

    /**
     * @param type 存储的类型
     * @return {@link Result} 统一的返回
     */
    @GetMapping(value = "/list")
    public Result getBucketList(@NonNull String type) {
        return bucketService.getBucketService(type).getBucketList();
    }

    /**
     * 创建 bucket
     *
     * @param type       存储的类型
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @PostMapping("/create")
    public Result createBucket(String type, String bucketName) {
        return bucketService.getBucketService(type).createBucket(bucketName);
    }


    /**
     * 删除 bucket
     *
     * @param type       存储的类型
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @DeleteMapping(value = "/del")
    public Result delBucket(String type, String bucketName) {
        return bucketService.getBucketService(type).delBucket(bucketName);
    }


    /**
     * 修改 bucket
     *
     * @param type          存储的类型
     * @param bucketName    old name
     * @param newBucketName new name
     * @return {@link Result} 统一的返回
     */
    @DeleteMapping(value = "/update")
    public Result updateBucket(String type, String bucketName, String newBucketName) {
        return bucketService.getBucketService(type).updateBucket(bucketName, newBucketName);
    }
}
