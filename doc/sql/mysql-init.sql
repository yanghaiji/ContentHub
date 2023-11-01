CREATE TABLE content_hub_images_config (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bucket_type VARCHAR(8) NOT NULL,
    bucket_name VARCHAR(255) NOT NULL,
    object_key VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    create_time TIMESTAMP
);




CREATE TABLE content_hub_share_items_info (
    id BIGINT NOT NULL AUTO_INCREMENT,
    selected_ids LONGTEXT,
    expiration INT,
    link VARCHAR(1024),
    share_url_prefix VARCHAR(1024),
    encrypt VARCHAR(255),
    expiration_time BIGINT,
    create_time DATETIME,
    PRIMARY KEY (id)
);
