package com.javayh.content.hub.common;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.javayh.content.hub.configuration.BucketProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Aws工具类
 */

@Component
public class AwsClientUtil {
    @Resource
    private BucketProperties bucketProperties;

    // 维护一个本类的静态变量
    public static AwsClientUtil awsClientUtil;

    @PostConstruct
    public void init() {
        awsClientUtil = this;
        awsClientUtil.bucketProperties = this.bucketProperties;
    }

    public static AmazonS3 s3Client() {
        try {
            AWSCredentialsProvider credentialsProvider = new AWSCredentialsProvider() {
                @Override
                public AWSCredentials getCredentials() {
                    return new BasicAWSCredentials(awsClientUtil.bucketProperties.getAccessKey(), awsClientUtil.bucketProperties.getSecretKey());
                }

                @Override
                public void refresh() {
                }
            };
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            clientConfiguration.setProtocol(Protocol.HTTP);
            AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                    new AwsClientBuilder.EndpointConfiguration(awsClientUtil.bucketProperties.getEndpoint(), awsClientUtil.bucketProperties.getRegions());
            return AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider)
                    .withEndpointConfiguration(endpointConfiguration).withClientConfiguration(clientConfiguration).build();
        } catch (Exception e) {
            return null;
        }
    }

}