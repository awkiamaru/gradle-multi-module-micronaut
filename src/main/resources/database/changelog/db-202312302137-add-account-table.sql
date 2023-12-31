-- liquibase formatted sql
-- changeset luis.fernando:202312302137 splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS `account` (
                          `id` VARCHAR(36) NOT NULL,
                          `name` VARCHAR(255) NOT NULL,
                          `created_at` DATETIME(0) NOT NULL,

                          PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
