package com.javayh.content.hub.api;

import com.javayh.content.hub.configuration.BucketProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * @author haiji
 */
@Slf4j
@RestController
public class ImageController {

    private final BucketProperties bucketProperties;

    public ImageController(BucketProperties bucketProperties) {
        this.bucketProperties = bucketProperties;
    }

    @GetMapping("/images")
    public ResponseEntity<Resource> getImage(@RequestParam(value = "bucketName") String bucketName,
                                             @RequestParam(value = "imageName") String imageName) {
        try {
            // 构建类路径资源的路径，这里假定图片存放在static目录下
            String imagePath = bucketProperties.getLocalPath()+ File.separator + bucketName + File.separator + imageName;
            Resource imageResource = new FileSystemResource(imagePath);

            if (imageResource.exists()) {
                // 返回图片资源
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG).body(imageResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("getImage {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }
}
