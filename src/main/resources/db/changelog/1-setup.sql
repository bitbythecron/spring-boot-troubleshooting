--liquibase formatted sql

--changeset troubleshooting:1 dbms:mysql
-- LOOKUPS
CREATE TABLE IF NOT EXISTS metric_range_categories (
    metric_range_category_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    metric_range_category_ref_id VARCHAR(36) NOT NULL,
    metric_range_category_name VARCHAR(250) NOT NULL,
    metric_range_category_label VARCHAR(250) NOT NULL,
    metric_range_category_description VARCHAR(500) NOT NULL,

    CONSTRAINT pk_metric_range_categories PRIMARY KEY (metric_range_category_id),

    INDEX idx_metric_range_categories_metric_range_category_ref_id (metric_range_category_ref_id),
    INDEX idx_metric_range_categories_metric_range_category_label (metric_range_category_label),

    CONSTRAINT uc_metric_range_categories_metric_range_category_ref_id UNIQUE (metric_range_category_ref_id),
    CONSTRAINT uc_metric_range_categories_metric_range_category_name UNIQUE (metric_range_category_name),
    CONSTRAINT uc_metric_range_categories_metric_range_category_label UNIQUE (metric_range_category_label)
);

CREATE TABLE IF NOT EXISTS metric_types (
    metric_type_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    metric_type_ref_id VARCHAR(36) NOT NULL,
    metric_type_name VARCHAR(250) NOT NULL,
    metric_type_label VARCHAR(250) NOT NULL,
    metric_type_description VARCHAR(500) NOT NULL,

    CONSTRAINT pk_metric_types PRIMARY KEY (metric_type_id),

    INDEX idx_metric_types_metric_type_ref_id (metric_type_ref_id),
    INDEX idx_metric_types_metric_type_label (metric_type_label),

    CONSTRAINT uc_metric_types_metric_type_ref_id UNIQUE (metric_type_ref_id),
    CONSTRAINT uc_metric_types_metric_type_name UNIQUE (metric_type_name),
    CONSTRAINT uc_metric_types_metric_type_label UNIQUE (metric_type_label)
);

CREATE TABLE IF NOT EXISTS metric_units (
    metric_unit_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    metric_unit_ref_id VARCHAR(36) NOT NULL,
    metric_unit_name VARCHAR(250) NOT NULL,
    metric_unit_label VARCHAR(250) NOT NULL,
    metric_unit_description VARCHAR(500) NOT NULL,

    CONSTRAINT pk_metric_units PRIMARY KEY (metric_unit_id),

    INDEX idx_metric_units_metric_unit_ref_id (metric_unit_ref_id),
    INDEX idx_metric_units_metric_unit_label (metric_unit_label),

    CONSTRAINT uc_metric_units_metric_unit_ref_id UNIQUE (metric_unit_ref_id),
    CONSTRAINT uc_metric_units_metric_unit_name UNIQUE (metric_unit_name),
    CONSTRAINT uc_metric_units_metric_unit_label UNIQUE (metric_unit_label)
);


-- ENTITIES
CREATE TABLE IF NOT EXISTS metrics (
    metric_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    metric_id_ref_id VARCHAR(36) NOT NULL,
    metric_type_id BIGINT UNSIGNED NOT NULL,
    metric_unit_id BIGINT UNSIGNED NOT NULL,
    metric_moniker VARCHAR(100) NOT NULL,

    CONSTRAINT pk_metrics PRIMARY KEY (metric_id),
    CONSTRAINT fk_metrics_metric_type_id FOREIGN KEY (metric_type_id) REFERENCES metric_types (metric_type_id),
    CONSTRAINT fk_metrics_metric_unit_id FOREIGN KEY (metric_unit_id) REFERENCES metric_units (metric_unit_id),

    INDEX idx_metrics_metric_ref_id (metric_ref_id),

    CONSTRAINT uc_metrics_metric_ref_id UNIQUE (metric_ref_id)
);

CREATE TABLE IF NOT EXISTS metric_ranges (
    metric_range_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    metric_range_ref_id VARCHAR(36) NOT NULL,
    metric_range_category_id BIGINT UNSIGNED NOT NULL,
    metric_range_low NOT NULL DOUBLE,
    metric_range_high NOT NULL DOUBLE,
    metric_range_low_inclusive NOT NULL INTEGER,
    metric_range_high_inclusive NOT NULL INTEGER,

    CONSTRAINT pk_metric_ranges PRIMARY KEY (metric_range_id),
    CONSTRAINT fk_metric_ranges_metric_range_category_id FOREIGN KEY (metric_range_category_id) REFERENCES metric_range_categories (metric_range_category_id),

    INDEX idx_metric_ranges_metric_range_ref_id (metric_range_ref_id),

    CONSTRAINT uc_metric_ranges_metric_range_ref_id UNIQUE (metric_range_ref_id)
);


-- CROSSWALKS
CREATE TABLE IF NOT EXISTS metrics_x_metric_ranges (
    metric_x_metric_range_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    metric_id BIGINT UNSIGNED NOT NULL,
    metric_range_id BIGINT UNSIGNED NOT NULL,

    CONSTRAINT pk_metrics_x_metric_ranges PRIMARY KEY (accounts_x_device_id),
    CONSTRAINT fk_metrics_x_metric_ranges_metric_id FOREIGN KEY (metric_id) REFERENCES metrics (metric_id),
    CONSTRAINT fk_metrics_x_metric_ranges_metric_range_id FOREIGN KEY (metric_range_id) REFERENCES metric_ranges (metric_range_id),

    INDEX idx_metrics_x_metric_ranges_metric_id (metric_id),
    INDEX idx_metrics_x_metric_ranges_metric_range_id (metric_range_id),

    CONSTRAINT uc_metrics_x_metric_ranges_all UNIQUE (metric_id, metric_range_id)
);
