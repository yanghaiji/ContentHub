package com.javayh.content.hub.db.api;

import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.db.service.DbFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @DeleteMapping(value = "/bu/del/{id}")
    public Result bucketDelById(@PathVariable(value = "id") Long id) {
        dbFileService.removeById(id);
        return Result.ok();
    }

    @PostMapping(value = "/bu/upload")
    public Result upload(MultipartFile file, String bucketName, String bucketType) {
        System.out.println(bucketName);
        System.out.println(bucketType);
        return Result.ok();
    }


}
