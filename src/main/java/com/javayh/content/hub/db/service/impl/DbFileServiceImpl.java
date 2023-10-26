package com.javayh.content.hub.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.db.entity.ContentHubImages;
import com.javayh.content.hub.db.mapper.DbFileMapper;
import com.javayh.content.hub.db.service.DbFileService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-24
 */
@Service
public class DbFileServiceImpl extends ServiceImpl<DbFileMapper, ContentHubImages> implements DbFileService {

    /**
     * 查询所有的bucket list
     *
     * @param bucketType bucketType
     * @return {@link Result}
     */
    @Override
    public Result bucketList(String bucketType) {
        LambdaQueryWrapper<ContentHubImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ContentHubImages::getBucketType, bucketType);
        return Result.ok(this.list(queryWrapper));
    }

    /**
     * 查询所有的 bucket 类型
     *
     * @return {@link Result}
     */
    @Override
    public Result bucketListInfo() {
        return Result.ok(Stream.of("S3", "OSS").collect(Collectors.toList()));
    }
}
