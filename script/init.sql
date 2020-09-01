-- 创建数据库
CREATE DATABASE `file` CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci';

-- 存储上传文件的表
CREATE TABLE `upload_file`(
  `id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `absolute_path` VARCHAR(260) NOT NULL COMMENT '上传的文件所在磁盘的绝对路径',
  `original_name` VARCHAR(255) NOT NULL COMMENT '上传时的文件名',
  `save_name` VARCHAR(255) NOT NULL COMMENT '保存时的文件名',
  `extension` VARCHAR(32) NOT NULL COMMENT '文件拓展名',
  `size` INT NOT NULL COMMENT '文件大小（单位：字节）',
  `version` BIGINT NOT NULL COMMENT '数据版本号',
  `deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标识位置',
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间',
  `gmt_modified` DATETIME COMMENT '更新时间'
);