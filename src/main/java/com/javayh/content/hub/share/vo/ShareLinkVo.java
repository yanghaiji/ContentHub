package com.javayh.content.hub.share.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareLinkVo {

    /**
     * share 的url
     */
    private String link;

    /**
     * 过期时间
     */
    private Long expiration;

    /**
     * 验证码
     */
    private String encrypt;

}
