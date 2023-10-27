package com.javayh.content.hub.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author haiji
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("content_hub_images_config")
public class ContentHubImages {

    private Long id;

    /**
     * 存储桶的类型
     */
    private String bucketType;

    /**
     * 存储桶的名字
     */
    private String bucketName;

    /**
     * 图片的唯一id
     */
    private String objectKey;

    /**
     * 存储后生成的路径
     */
    private String imageUrl;

    /**
     * 图片的描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}