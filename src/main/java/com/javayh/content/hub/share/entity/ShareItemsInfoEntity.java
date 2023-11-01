package com.javayh.content.hub.share.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-31
 */
@Data
@TableName(value = "content_hub_share_items_info")
public class ShareItemsInfoEntity {

    private Long id;

    /**
     * ids
     */
//    @TableField(typeHandler = UniversalLongTypeHandler.class, value = "selected_ids")
    private String selectedIds;

    /**
     * 过期时间
     */
    private Integer expiration;


    /**
     * share url 前缀
     */
    private String shareUrlPrefix;

    /**
     * link
     */
    private String link;

    /**
     * 验证码
     */
    private String encrypt;

    /**
     * 计算后的过期时间
     */
    private Long expirationTime;


    /**
     * 计算后的过期时间
     */
    private Date createTime;

}
