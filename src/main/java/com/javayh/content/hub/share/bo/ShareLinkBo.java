package com.javayh.content.hub.share.bo;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-30
 */
@Data
public class ShareLinkBo {

    /**
     * ids
     */
    private List<Long> selectedIds;

    /**
     * 过期时间
     */
    private Integer expiration;

    /**
     * 验证码
     */
    private String encrypt;


}
