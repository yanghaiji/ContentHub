package com.javayh.content.hub.buket;

import com.javayh.content.hub.buket.impl.AwsBucketServiceImpl;
import com.javayh.content.hub.buket.impl.OssBucketServiceImpl;
import com.javayh.content.hub.buket.interfaces.BucketService;
import com.javayh.content.hub.exception.BucketStateException;
import org.springframework.stereotype.Component;

/**
 * <p>
 * bucket的策略
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-25
 */
@Component
public class BucketStrategySelector {

    private final AwsBucketServiceImpl awsBucketService;
    private final OssBucketServiceImpl ossBucketService;

    public BucketStrategySelector(AwsBucketServiceImpl awsBucketService,
                                  OssBucketServiceImpl ossBucketService) {
        this.awsBucketService = awsBucketService;
        this.ossBucketService = ossBucketService;
    }

    /**
     * 数据初始化
     */
    public BucketService getBucketService(String type) {
        BucketService bucketService = null;
        switch (type) {
            case "S3":
                bucketService = awsBucketService;
                break;
            case "OSS":
                bucketService = ossBucketService;
                break;
            default:
                throw new BucketStateException("Unexpected value: " + type);
        }
        return bucketService;
    }

}
