package com.javayh.content.hub.buket;

import com.javayh.content.hub.buket.impl.AwsFileBucketServiceImpl;
import com.javayh.content.hub.buket.impl.LocalFileBucketServiceImpl;
import com.javayh.content.hub.buket.impl.OssFileBucketServiceImpl;
import com.javayh.content.hub.buket.interfaces.BucketFileService;
import com.javayh.content.hub.exception.BucketStateException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class BucketFileStrategySelector {

    private final AwsFileBucketServiceImpl awsFileBucketService;
    private final OssFileBucketServiceImpl ossFileBucketService;
    private final LocalFileBucketServiceImpl localFileBucketService;


    /**
     * 数据初始化
     */
    public BucketFileService getBucketFileService(String type) {
        BucketFileService bucketFileService;
        switch (type) {
            case "S3":
                bucketFileService = awsFileBucketService;
                break;
            case "OSS":
                bucketFileService = ossFileBucketService;
                break;
            case "LOCAL":
                bucketFileService = localFileBucketService;
                break;
            default:
                throw new BucketStateException("Unexpected value: " + type);
        }
        return bucketFileService;
    }

}
