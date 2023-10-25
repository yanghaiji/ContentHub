package com.javayh.content.hub.api;

import com.javayh.content.hub.buket.interfaces.BucketService;
import com.javayh.content.hub.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BucketService bucketService;

    @GetMapping(value = "/list")
    public Result getBucketList() {
        return bucketService.getBucketList();
    }

    /**
     * 创建 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @PostMapping("/create")
    public Result createBucket(String bucketName) {
        return bucketService.createBucket(bucketName);
    }


    /**
     * 删除 bucket
     *
     * @param bucketName name
     * @return {@link Result} 统一的返回
     */
    @DeleteMapping(value = "/del")
    public Result delBucket(String bucketName) {
        return bucketService.delBucket(bucketName);
    }


    /**
     * 修改 bucket
     *
     * @param bucketName    old name
     * @param newBucketName new name
     * @return {@link Result} 统一的返回
     */
    @DeleteMapping(value = "/update")
    public Result updateBucket(String bucketName, String newBucketName) {
        return bucketService.updateBucket(bucketName, newBucketName);
    }
}
