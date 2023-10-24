package com.javayh.content.hub.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * bucket相关配置
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(value = "content.hub.config")
public class BucketProperties {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * 存储桶的类型
     */
    private BucketType bucketType;

    /**
     * 数据存储桶的类型
     */
    public enum BucketType {

        AWS,

        SOS,

        QINIU,

        ;
    }
}
