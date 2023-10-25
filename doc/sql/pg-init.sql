CREATE TABLE content_hub_images_config (
    id SERIAL PRIMARY KEY,
    bucket_name VARCHAR(255) NOT NULL,
    object_key VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    create_time TIMESTAMP
);
