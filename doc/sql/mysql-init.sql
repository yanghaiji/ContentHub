CREATE TABLE content_hub_images_config (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bucket_type VARCHAR(8) NOT NULL,
    bucket_name VARCHAR(255) NOT NULL,
    object_key VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    create_time TIMESTAMP
);
