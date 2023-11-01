CREATE TABLE content_hub_images_config (
    id SERIAL PRIMARY KEY,
    bucket_type VARCHAR(8) NOT NULL,
    bucket_name VARCHAR(255) NOT NULL,
    object_key VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    description TEXT,
    create_time TIMESTAMP
);

CREATE TABLE content_hub_share_items_info (
    id BIGSERIAL PRIMARY KEY,
    selected_ids BIGINT[],
    expiration INT,
    share_url_prefix VARCHAR(1024),
    link VARCHAR(1024),
    encrypt VARCHAR(255),
    expiration_time BIGINT,
    create_time TIMESTAMPTZ
);