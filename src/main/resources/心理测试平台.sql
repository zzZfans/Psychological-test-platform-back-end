-- assess_result: table
CREATE TABLE `assess_result`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`      bigint unsigned                                       DEFAULT NULL COMMENT '关联userid',
    `username`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
    `assess_type`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '测试类型',
    `result_level` int                                                   DEFAULT NULL COMMENT '程度，0，正常；1轻度；2，中度；3，重度',
    `year`         int                                                   DEFAULT NULL COMMENT '测试时间，年',
    `month`        int                                                   DEFAULT NULL COMMENT '月',
    `day`          int                                                   DEFAULT NULL COMMENT '日',
    `is_deleted`   int(1) unsigned zerofill                              DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    `create_time`  datetime                                              DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 59
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- audio_record: table
CREATE TABLE `audio_record`
(
    `id`             bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `test_record_id` bigint unsigned                  NOT NULL COMMENT '测试题记录表 ID',
    `text_id`        bigint unsigned                  NOT NULL COMMENT '文本记录表 ID',
    `audio_emotion`  tinyint unsigned                 NOT NULL COMMENT '音频情绪 0：生气 1：恐惧 2：快乐 3：中性 4：伤心 5：惊喜',
    `audio_url`      varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '音频链接',
    `create_time`    datetime                         NOT NULL COMMENT '创建时间',
    `is_deleted`     tinyint unsigned                 NOT NULL DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='音频记录表';

-- auto_message: table
CREATE TABLE `auto_message`
(
    `id`          bigint                   NOT NULL AUTO_INCREMENT,
    `type`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型：对应测试结果类型',
    `title`       varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL,
    `message`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '消息',
    `create_id`   bigint unsigned                                        DEFAULT NULL COMMENT '创建者id',
    `create_time` datetime                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id`   bigint unsigned                                        DEFAULT NULL COMMENT '更新者id',
    `update_time` datetime                                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  int(2) unsigned zerofill NOT NULL                      DEFAULT '00' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- facial_emotion_record: table
CREATE TABLE `facial_emotion_record`
(
    `id`             bigint unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `test_record_id` bigint unsigned  NOT NULL COMMENT '测试题记录表 ID',
    `facial_emotion` tinyint unsigned NOT NULL COMMENT '人脸情绪 0：中立 1：快乐 2：悲伤 3：生气 4：忧虑 5：厌恶 6：惊喜',
    `sort`           int unsigned     NOT NULL COMMENT '顺序',
    `create_time`    datetime         NOT NULL COMMENT '创建时间',
    `is_deleted`     tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`),
    KEY `idx_test_record_id` (`test_record_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='人脸情绪记录表';

-- No native definition for element: idx_test_record_id (index)

-- notice: table
CREATE TABLE `notice`
(
    `id`             bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `notice_title`   varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公告标题',
    `notice_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '公告内容',
    `create_id`      bigint unsigned                  DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime                         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id`      bigint unsigned                  DEFAULT NULL COMMENT '更新人id',
    `update_time`    datetime                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`     int unsigned                     DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- operation_log: table
CREATE TABLE `operation_log`
(
    `id`          bigint unsigned                 NOT NULL AUTO_INCREMENT COMMENT '操作日志主键',
    `user_id`     bigint unsigned                 NOT NULL COMMENT '用户主键',
    `operation`   varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户操作',
    `link`        text COLLATE utf8mb4_bin        NOT NULL COMMENT '请求地址',
    `exec_time`   int unsigned                    NOT NULL COMMENT '执行时间（毫秒）',
    `params`      text COLLATE utf8mb4_bin        NOT NULL COMMENT '请求参数',
    `ip`          varchar(46) COLLATE utf8mb4_bin NOT NULL COMMENT '请求 ip',
    `create_time` datetime                        NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3779
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='操作日志表';

-- permission: table
CREATE TABLE `permission`
(
    `id`              bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '权限主键',
    `permission_name` varchar(32) COLLATE utf8mb4_bin  NOT NULL COMMENT '权限名',
    `component`       varchar(255) COLLATE utf8mb4_bin                      DEFAULT NULL COMMENT 'VUE 组件',
    `router_name`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路由名（会用作生成 VUE path',
    `redirect`        varchar(255) COLLATE utf8mb4_bin NOT NULL             DEFAULT '' COMMENT 'VUE 重定向路径',
    `parent_id`       bigint unsigned                  NOT NULL             DEFAULT '0' COMMENT '父级权限主键',
    `icon`            varchar(255) COLLATE utf8mb4_bin                      DEFAULT NULL COMMENT '菜单图标',
    `permission`      varchar(32) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '权限',
    `permission_type` tinyint unsigned                 NOT NULL COMMENT '权限类型 0：菜单 1：按钮',
    `status`          tinyint unsigned                 NOT NULL             DEFAULT '1' COMMENT '状态 0：禁用 1：启用',
    `description`     varchar(255) COLLATE utf8mb4_bin                      DEFAULT NULL COMMENT '权限描述',
    `sort`            int unsigned                     NOT NULL             DEFAULT '0' COMMENT '权限排序因子（升序）',
    `creator_id`      bigint unsigned                  NOT NULL COMMENT '创建者 id',
    `updater_id`      bigint unsigned                  NOT NULL COMMENT '更新者 id',
    `create_time`     datetime                         NOT NULL             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                         NOT NULL             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`      tinyint unsigned                 NOT NULL             DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_permission_name` (`permission_name`),
    UNIQUE KEY `uk_router_name` (`router_name`),
    UNIQUE KEY `uk_icon` (`icon`),
    UNIQUE KEY `uk_permission` (`permission`),
    UNIQUE KEY `uk_description` (`description`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 66
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='权限表';

INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (17, '系统监控', 'RouteView', 'systemMonitor', '/systemMonitor/systemInfo', 0, 'step-backward', NULL, 0, 1, '系统监控', 40, 15, 15, '2022-04-18 13:42:43', '2022-04-23 09:25:08', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (18, '系统信息', 'systemMonitor/systemInfo', 'systemInfo', '', 17, 'up-circle', NULL, 0, 1, '系统信息', 20, 15, 15, '2022-04-18 13:43:55', '2022-04-23 09:26:36', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (19, '操作日志', 'systemMonitor/operationLog', 'operationLog', '', 17, 'swap-right', NULL, 0, 1, '操作日志', 10, 15, 15, '2022-04-18 13:44:23', '2022-04-23 09:26:32', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (22, '系统管理', 'RouteView', 'systemManagement', '/systemManagement/permissionManagement', 0, 'arrow-left', NULL, 0, 1, '系统管理', 30, 15, 15, '2022-04-18 13:48:07', '2022-04-23 09:25:05', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (23, '角色管理', 'systemManagement/roleManagement', 'roleManagement', '', 22, 'vertical-right', NULL, 0, 1, '角色管理', 20, 15, 15, '2022-04-18 13:48:39', '2022-04-23 09:26:18', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (24, '权限管理', 'systemManagement/permissionManagement', 'permissionManagement', '', 22, 'border-verticle', NULL, 0, 1, '权限管理', 30, 15, 15, '2022-04-18 13:49:01', '2022-04-23 09:26:22', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (28, '用户管理', 'systemManagement/userManagement', 'userManagement', '', 22, 'user', NULL, 0, 1, '用户管理', 10, 15, 15, '2022-04-20 14:47:07', '2022-04-23 09:26:14', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (29, '仪表盘', 'RouteView', 'dashboard', '/dashboard/info', 0, 'bar-chart', NULL, 0, 1, '仪表盘', 10, 15, 15, '2022-04-23 04:04:05', '2022-06-06 09:38:18', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (30, '心理测试', 'RouteView', 'assess', '/assess/fast', 0, 'file-text', NULL, 0, 1, '心理测试', 20, 15, 15, '2022-04-23 04:20:30', '2022-04-23 15:29:22', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (31, '快速心理测试', 'assess/table/conf', 'fast', '', 30, 'smile', NULL, 0, 1, '快速心理测试', 10, 15, 15, '2022-04-23 04:22:10', '2022-04-23 15:29:59', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (39, '随机心理测试', 'assess/table/tableRandom', 'randomfast', '', 30, 'meh', NULL, 0, 1, '随机心理测试', 20, 15, 15, '2022-04-23 04:23:36', '2022-05-31 02:38:28', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (40, '面板', 'dashboard/Workplace', 'info', '', 29, 'radar-chart', NULL, 0, 1, '面板', 100, 15, 15, '2022-04-23 05:07:17', '2022-04-23 05:21:44', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (41, '语音问答心理测试', 'assess/voiceQAEval', 'voiceQAEval', '', 30, 'thunderbolt', NULL, 0, 1, '语音问答心理测评', 40, 15, 15, '2022-04-23 05:51:09', '2022-04-27 16:59:28', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (43, '个人中心', 'RouteView', 'account', '/account/center', 0, 'home', NULL, 0, 1, '个人中心', 50, 15, 15, '2022-04-23 07:46:09', '2022-04-23 09:25:13', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (47, '主页', 'account/center', 'center', '', 43, 'pie-chart', NULL, 0, 1, '主页', 10, 15, 15, '2022-04-23 07:47:41', '2022-04-23 09:26:53', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (49, '测试记录', 'account/record/index', 'record', '', 43, 'line-chart', NULL, 0, 1, '测试记录', 30, 15, 15, '2022-04-23 07:48:17', '2022-04-23 09:27:01', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (50, '个人设置', 'account/settings/Index', 'settings', '/account/settings/BasicSettings', 43, 'setting', NULL, 0, 1, '个人设置', 20, 15, 15, '2022-04-23 07:48:58', '2022-04-23 09:26:57', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (52, '基本设置', 'account/settings/BasicSetting', 'BasicSettings', '', 50, 'pic-left', NULL, 0, 1, '基本设置', 10, 15, 15, '2022-04-23 07:50:14', '2022-04-23 09:28:02', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (53, '安全设置', 'account/settings/Security', 'SecuritySettings', '', 50, 'fullscreen-exit', NULL, 0, 1, '安全设置', 20, 15, 15, '2022-04-23 07:51:49', '2022-04-23 09:28:06', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (54, '个性化', 'account/settings/Custom', 'CustomSettings', '', 50, 'pic-right', NULL, 0, 1, '个性化', 30, 15, 15, '2022-04-23 07:52:26', '2022-04-23 09:28:10', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (55, '人脸采集', 'account/settings/Binding', 'BindingSettings', '', 50, 'slack', NULL, 0, 1, '人脸采集', 40, 15, 15, '2022-04-23 07:52:59', '2022-06-04 16:09:36', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (56, '新消息通知', 'account/settings/Notification', 'NotificationSettings', '', 50, 'dingding', NULL, 0, 1, '新消息通知', 50, 15, 15, '2022-04-23 07:53:33', '2022-04-23 09:28:18', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (57, '文本问答心理测试', 'assess/textQAEval', 'textQAEval', '', 30, 'form', NULL, 0, 1, '文本问答心理测试', 30, 15, 15, '2022-04-23 08:05:56', '2022-04-23 15:30:31', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (58, '心理关怀', 'RouteView', 'psychologicalCare', '/psychologicalCare/workBench', 0, 'usergroup-add', NULL, 0, 1, '心理关怀', 25, 15, 15, '2022-04-23 09:34:04', '2022-04-23 16:10:13', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (60, '用户测试记录', 'assess/record/userRecord', 'workBench', '', 58, 'rollback', NULL, 0, 1, '用户测试记录', 10, 15, 15, '2022-04-23 09:37:20', '2022-05-12 08:54:21', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (61, '添加权限', NULL, NULL, '', 24, NULL, 'permission.add', 1, 1, '添加权限', 0, 15, 15, '2022-05-03 15:30:14', '2022-05-03 15:30:14', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (62, '编辑权限', NULL, NULL, '', 24, NULL, 'permission.edit', 1, 1, '编辑权限', 0, 15, 15, '2022-05-03 16:12:26', '2022-05-03 16:12:26', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (63, '消息配置', 'psychologicalCare/automessage/index', 'autoMessage', '', 58, 'double-right', NULL, 0, 1, '消息配置', 20, 21, 21, '2022-05-12 08:42:09', '2022-05-12 09:05:11', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (64, '公告管理', 'systemManagement/noticeManagement', 'noticeManagement', '', 22, 'notification', NULL, 0, 1, NULL, 40, 21, 21, '2022-05-20 03:18:43', '2022-05-20 03:18:43', 0);
INSERT INTO `permission`(`id`, `permission_name`, `component`, `router_name`, `redirect`, `parent_id`, `icon`, `permission`, `permission_type`, `status`, `description`, `sort`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (65, '首页面板', NULL, NULL, '', 40, NULL, 'dashboard.view', 1, 1, '首页面板', 0, 15, 15, '2022-05-22 10:38:55', '2022-05-22 10:38:55', 0);


-- push_record: table
CREATE TABLE `push_record`
(
    `id`          bigint           NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pusher_id`   bigint                                                 DEFAULT NULL COMMENT '推送人id',
    `receiver_id` bigint                                                 DEFAULT NULL COMMENT '接收人id',
    `title`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
    `message`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '推送消息',
    `status`      tinyint unsigned NOT NULL COMMENT '消息状态:0,未查看;1,已查看',
    `create_time` datetime         NOT NULL                              DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime         NOT NULL                              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  int unsigned     NOT NULL                              DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 626
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- push_record_copy: table
CREATE TABLE `push_record_copy`
(
    `id`          bigint           NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pusher_id`   bigint                           DEFAULT NULL COMMENT '推送人id',
    `receiver_id` bigint                           DEFAULT NULL COMMENT '接收人id',
    `title`       varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
    `message`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '推送消息',
    `status`      tinyint unsigned NOT NULL COMMENT '消息状态:0,未查看;1,已查看',
    `create_time` datetime         NOT NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime         NOT NULL        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  int unsigned     NOT NULL        DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 609
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- qa_psychological_test_result: table
CREATE TABLE `qa_psychological_test_result`
(
    `id`          bigint unsigned  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint unsigned  NOT NULL COMMENT '用户主键',
    `test_type`   tinyint unsigned NOT NULL COMMENT '测试类型 0：文本 1：音频',
    `test_result` tinyint unsigned NOT NULL COMMENT '测试结果 0：心理健康状况好 1：心理健康状况不错 2：心理健康状况一般 3：心理健康状况差 4：心理健康状况很差',
    `create_time` datetime         NOT NULL COMMENT '创建时间',
    `is_deleted`  tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='问答心理测试结果表';

-- No native definition for element: idx_user_id (index)

-- role: table
CREATE TABLE `role`
(
    `id`          bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '角色主键',
    `role_name`   varchar(32) COLLATE utf8mb4_bin  NOT NULL COMMENT '角色名',
    `status`      tinyint unsigned                 NOT NULL DEFAULT '0' COMMENT '角色状态 0：启用 1：禁用',
    `description` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '角色描述',
    `creator_id`  bigint unsigned                  NOT NULL DEFAULT '0' COMMENT '创建者 id',
    `updater_id`  bigint unsigned                  NOT NULL DEFAULT '0' COMMENT '更新者 id',
    `create_time` datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_name` (`role_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 71
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色表';

INSERT INTO `role`(`id`, `role_name`, `status`, `description`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (62, '游客', 0, '游客', 15, 15, '2022-05-21 08:51:23', '2022-05-22 10:43:34');
INSERT INTO `role`(`id`, `role_name`, `status`, `description`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (68, '普通用户', 0, '普通用户', 15, 15, '2022-05-21 15:35:35', '2022-05-22 10:43:39');
INSERT INTO `role`(`id`, `role_name`, `status`, `description`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (69, '心理健康运营人员', 0, '心理健康运营人员', 15, 15, '2022-05-21 15:36:56', '2022-05-22 10:43:43');
INSERT INTO `role`(`id`, `role_name`, `status`, `description`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (70, '系统管理员', 0, '系统管理员', 15, 15, '2022-05-21 15:37:38', '2022-05-22 10:43:47');


-- role_permission: table
CREATE TABLE `role_permission`
(
    `id`            bigint unsigned  NOT NULL AUTO_INCREMENT COMMENT '角色权限关联主键',
    `role_id`       bigint unsigned  NOT NULL COMMENT '角色主键',
    `permission_id` bigint unsigned  NOT NULL COMMENT '权限主键',
    `creator_id`    bigint unsigned  NOT NULL COMMENT '创建者 id',
    `updater_id`    bigint unsigned  NOT NULL COMMENT '更新者 id',
    `create_time`   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`    tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 213351
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='角色权限关联表';

INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (10, 1, 17, 15, 15, '2022-04-15 09:25:53', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (11, 1, 18, 15, 15, '2022-04-15 09:25:53', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (14, 1, 19, 15, 15, '2022-04-15 09:41:46', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (1111, 1, 24, 15, 15, '2022-04-15 09:41:46', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (22222, 1, 23, 15, 15, '2022-04-15 09:41:46', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213213, 1, 22, 15, 15, '2022-04-15 09:41:46', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213214, 1, 28, 15, 15, '2022-04-20 14:51:58', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213215, 1, 29, 15, 15, '2022-04-23 04:05:34', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213216, 1, 30, 15, 15, '2022-04-23 05:01:52', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213217, 1, 31, 15, 15, '2022-04-23 05:01:52', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213218, 1, 39, 15, 15, '2022-04-23 05:01:52', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213219, 1, 40, 15, 15, '2022-04-23 05:17:37', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213220, 1, 41, 15, 15, '2022-04-23 05:51:24', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213221, 1, 43, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213222, 1, 50, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213223, 1, 56, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213224, 1, 55, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213225, 1, 54, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213226, 1, 53, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213227, 1, 52, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213228, 1, 49, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213229, 1, 47, 15, 15, '2022-04-23 07:53:57', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213230, 1, 57, 15, 15, '2022-04-23 08:06:31', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213231, 1, 58, 15, 15, '2022-04-23 09:38:04', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213232, 1, 60, 15, 15, '2022-04-23 09:38:04', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213233, 1, 61, 15, 15, '2022-04-23 09:38:04', '2022-05-03 16:30:17', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213238, 1, 62, 15, 15, '2022-05-03 16:30:17', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213243, 50, 17, 20, 20, '2022-05-04 00:45:42', '2022-05-21 09:39:33', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213244, 50, 18, 20, 20, '2022-05-04 00:45:42', '2022-05-04 00:59:50', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213245, 50, 19, 20, 20, '2022-05-04 00:59:32', '2022-05-21 09:39:33', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213246, 50, 18, 20, 20, '2022-05-04 01:16:21', '2022-05-21 09:39:33', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213247, 1, 61, 15, 15, '2022-05-07 12:44:25', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213248, 1, 61, 15, 15, '2022-05-07 12:44:25', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213249, 1, 63, 21, 21, '2022-05-12 09:04:20', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213250, 1, 64, 21, 21, '2022-05-20 03:19:03', '2022-05-21 15:40:11', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213251, 56, 29, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213252, 56, 40, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213253, 56, 30, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213254, 56, 31, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213255, 56, 39, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213256, 56, 57, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213257, 56, 41, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213258, 56, 43, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213259, 56, 47, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213260, 56, 50, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213261, 56, 52, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213262, 56, 53, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213263, 56, 54, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213264, 56, 55, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213265, 56, 56, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213266, 56, 49, 35, 35, '2022-05-21 08:47:17', '2022-05-21 08:47:26', 1);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213267, 62, 29, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213268, 62, 40, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213269, 62, 30, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213270, 62, 31, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213271, 62, 39, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213272, 62, 57, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213273, 62, 41, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213274, 62, 43, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213275, 62, 47, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213276, 62, 50, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213277, 62, 52, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213278, 62, 53, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213279, 62, 54, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213280, 62, 55, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213281, 62, 56, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213282, 62, 49, 15, 15, '2022-05-21 08:51:25', '2022-05-21 08:51:25', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213283, 68, 29, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213284, 68, 40, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213285, 68, 30, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213286, 68, 31, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213287, 68, 39, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213288, 68, 57, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213289, 68, 41, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213290, 68, 43, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213291, 68, 47, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213292, 68, 50, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213293, 68, 52, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213294, 68, 53, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213295, 68, 54, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213296, 68, 55, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213297, 68, 56, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213298, 68, 49, 15, 15, '2022-05-21 15:35:37', '2022-05-21 15:35:37', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213299, 69, 49, 15, 15, '2022-05-21 15:36:57', '2022-05-21 15:36:57', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213300, 69, 56, 15, 15, '2022-05-21 15:36:57', '2022-05-21 15:36:57', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213301, 69, 55, 15, 15, '2022-05-21 15:36:57', '2022-05-21 15:36:57', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213302, 69, 54, 15, 15, '2022-05-21 15:36:57', '2022-05-21 15:36:57', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213303, 69, 53, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213304, 69, 52, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213305, 69, 50, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213306, 69, 47, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213307, 69, 43, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213308, 69, 58, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213309, 69, 60, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213310, 69, 63, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213311, 69, 41, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213312, 69, 57, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213313, 69, 39, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213314, 69, 31, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213315, 69, 30, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213316, 69, 40, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213317, 69, 29, 15, 15, '2022-05-21 15:36:58', '2022-05-21 15:36:58', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213318, 70, 29, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213319, 70, 40, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213320, 70, 30, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213321, 70, 31, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213322, 70, 39, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213323, 70, 57, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213324, 70, 41, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213325, 70, 58, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213326, 70, 60, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213327, 70, 63, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213328, 70, 22, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213329, 70, 28, 15, 15, '2022-05-21 15:37:41', '2022-05-21 15:37:41', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213330, 70, 23, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213331, 70, 24, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213332, 70, 62, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213333, 70, 61, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213334, 70, 64, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213335, 70, 17, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213336, 70, 19, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213337, 70, 18, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213338, 70, 43, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213339, 70, 47, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213340, 70, 50, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213341, 70, 52, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213342, 70, 53, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213343, 70, 54, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213344, 70, 55, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213345, 70, 56, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213346, 70, 49, 15, 15, '2022-05-21 15:37:42', '2022-05-21 15:37:42', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213347, 62, 65, 15, 15, '2022-05-22 10:43:34', '2022-05-22 10:43:34', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213348, 68, 65, 15, 15, '2022-05-22 10:43:39', '2022-05-22 10:43:39', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213349, 69, 65, 15, 15, '2022-05-22 10:43:43', '2022-05-22 10:43:43', 0);
INSERT INTO `role_permission`(`id`, `role_id`, `permission_id`, `creator_id`, `updater_id`, `create_time`, `update_time`, `is_deleted`) VALUES (213350, 70, 65, 15, 15, '2022-05-22 10:43:47', '2022-05-22 10:43:47', 0);


-- subject: table
CREATE TABLE `subject`
(
    `id`         bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type`       tinyint                          NOT NULL DEFAULT '1' COMMENT '类型：1文本情绪识别题目',
    `subject`    varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '题目',
    `is_deleted` tinyint unsigned                 NOT NULL DEFAULT '0' COMMENT '逻辑删除: 0 未删，1删',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

INSERT INTO `subject`(`id`, `type`, `subject`, `is_deleted`) VALUES (1, 1, '你今天遇到的开心的事情?', 0);
INSERT INTO `subject`(`id`, `type`, `subject`, `is_deleted`) VALUES (2, 1, '你认为你今天的早餐好吃吗?', 0);
INSERT INTO `subject`(`id`, `type`, `subject`, `is_deleted`) VALUES (3, 1, '昨天你是否感觉收获满满?', 0);
INSERT INTO `subject`(`id`, `type`, `subject`, `is_deleted`) VALUES (4, 1, '现在你是否感到开心?', 0);
INSERT INTO `subject`(`id`, `type`, `subject`, `is_deleted`) VALUES (5, 1, '最近感觉到烦恼的事情？', 0);
INSERT INTO `subject`(`id`, `type`, `subject`, `is_deleted`) VALUES (6, 1, '你是否经常感觉有很大的压力?', 0);


-- test_record: table
CREATE TABLE `test_record`
(
    `id`                              bigint unsigned                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `qa_psychological_test_result_id` bigint unsigned                  NOT NULL COMMENT '问答心理测试结果表 ID',
    `question_num`                    int unsigned                     NOT NULL COMMENT '题号',
    `question`                        varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '问题',
    `create_time`                     datetime                         NOT NULL COMMENT '创建时间',
    `is_deleted`                      tinyint unsigned                 NOT NULL DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`),
    KEY `idx_qa_psychological_test_result_id` (`qa_psychological_test_result_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='测试题记录表';

-- No native definition for element: idx_qa_psychological_test_result_id (index)

-- text_record: table
CREATE TABLE `text_record`
(
    `id`             bigint unsigned          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `test_record_id` bigint unsigned          NOT NULL COMMENT '测试题记录表 ID',
    `text_emotion`   tinyint unsigned         NOT NULL COMMENT '文本情绪 0：无情绪 1：喜好 2：悲伤 3：厌恶 4：愤怒 5：高兴',
    `text`           text COLLATE utf8mb4_bin NOT NULL COMMENT '文本',
    `create_time`    datetime                 NOT NULL COMMENT '创建时间',
    `is_deleted`     tinyint unsigned         NOT NULL DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='文本记录表';

-- user: table
CREATE TABLE `user`
(
    `id`                      bigint                           NOT NULL AUTO_INCREMENT COMMENT '用户主键',
    `username`                varchar(32) COLLATE utf8mb4_bin  NOT NULL COMMENT '用户名',
    `password`                varchar(64) COLLATE utf8mb4_bin  NOT NULL COMMENT '用户密码',
    `salt`                    varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '密码盐',
    `status`                  tinyint unsigned                 NOT NULL              DEFAULT '0' COMMENT '状态 0：正常 1：禁用',
    `phone_number`            varchar(32) COLLATE utf8mb4_bin                        DEFAULT NULL COMMENT '手机号',
    `email_address`           varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '邮箱',
    `sex`                     tinyint unsigned                 NOT NULL              DEFAULT '2' COMMENT '性别 0:女 1：男 2：保密',
    `date_of_birth`           date                                                   DEFAULT NULL COMMENT '出生日期',
    `avatar`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像',
    `face_recognition_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '人脸识别源图',
    `last_login_ip`           varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '最近一次登录 ip',
    `last_login_time`         datetime                                               DEFAULT NULL COMMENT '最近一次登录时间',
    `state_changer_id`        bigint unsigned                                        DEFAULT NULL COMMENT '状态变更者 id',
    `state_change_time`       datetime                                               DEFAULT NULL COMMENT '状态变更时间',
    `create_time`             datetime                         NOT NULL              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`             datetime                         NOT NULL              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`              tinyint unsigned                 NOT NULL              DEFAULT '0' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_phone_number` (`phone_number`),
    UNIQUE KEY `uk_email_address` (`email_address`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户表';

INSERT INTO `user`(`id`, `username`, `password`, `salt`, `status`, `phone_number`, `email_address`, `sex`, `date_of_birth`, `avatar`, `face_recognition_source`, `last_login_ip`, `last_login_time`, `state_changer_id`, `state_change_time`, `create_time`, `update_time`, `is_deleted`) VALUES (35, '游客', 'A7997F3643ED911E597A4798F87942CF', '0a93dc0d-370e-428f-a615-4cd6345009a3', 0, '13322222226', NULL, 2, NULL, '', NULL, '127.0.0.1', '2022-06-05 15:14:20', NULL, NULL, '2022-05-21 08:45:42', '2022-06-06 10:25:51', 0);

-- user_comment: table
CREATE TABLE `user_comment`
(
    `id`              bigint                   NOT NULL AUTO_INCREMENT,
    `content`         varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '评论',
    `user_id`         bigint unsigned                                        DEFAULT NULL COMMENT '用户id',
    `user_name`       varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '用户名称',
    `avatar`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像',
    `top_id`          bigint                                                 DEFAULT NULL COMMENT '评论的根id',
    `parent_id`       bigint                                                 DEFAULT NULL COMMENT '父级id',
    `create_time`     datetime                 NOT NULL                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `is_deleted`      int(2) unsigned zerofill NOT NULL                      DEFAULT '00' COMMENT '逻辑删除（更新）（0：未删除 1：删除）',
    `parent_username` varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- user_role: table
CREATE TABLE `user_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关联主键',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户主键',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色主键',
    `creator_id`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '创建者 id',
    `updater_id`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新者 id',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id_and_role_id` (`user_id`, `role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 61
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户角色关联表';

INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (1, 10, 1, 0, 0, '2022-04-06 17:33:23', '2022-04-06 17:33:23');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (3, 12, 1, 0, 0, '2022-04-09 11:05:05', '2022-04-09 11:05:05');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (4, 13, 1, 0, 0, '2022-04-11 14:54:47', '2022-04-11 14:54:47');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (5, 14, 1, 0, 0, '2022-04-11 15:27:56', '2022-04-11 15:27:56');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (6, 15, 70, 0, 0, '2022-04-11 15:56:14', '2022-05-21 15:38:13');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (8, 17, 1, 0, 0, '2022-04-13 05:21:41', '2022-04-13 05:21:41');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (9, 18, 1, 0, 0, '2022-04-13 07:25:52', '2022-04-13 07:25:52');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (10, 19, 1, 0, 0, '2022-04-13 08:27:00', '2022-04-13 08:27:00');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (11, 20, 1, 0, 0, '2022-04-13 09:07:36', '2022-04-13 09:07:36');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (23, 32, 70, 0, 0, '2022-05-11 12:32:33', '2022-05-22 09:47:19');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (25, 35, 62, 0, 0, '2022-05-21 08:45:42', '2022-05-21 09:10:44');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (39, 22, 68, 0, 0, '2022-05-22 10:03:56', '2022-05-22 10:03:56');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (40, 23, 68, 0, 0, '2022-05-22 10:04:02', '2022-05-22 10:04:02');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (41, 24, 68, 0, 0, '2022-05-22 10:04:08', '2022-05-22 10:04:08');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (43, 31, 68, 0, 0, '2022-05-22 10:04:45', '2022-05-22 10:04:45');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (44, 30, 68, 0, 0, '2022-05-22 10:04:55', '2022-05-22 10:04:55');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (45, 29, 68, 0, 0, '2022-05-22 10:05:02', '2022-05-22 10:05:02');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (46, 28, 68, 0, 0, '2022-05-22 10:05:11', '2022-05-22 10:05:11');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (47, 27, 70, 0, 0, '2022-05-22 10:05:17', '2022-05-22 15:39:15');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (48, 26, 68, 0, 0, '2022-05-22 10:05:21', '2022-05-22 10:05:21');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (49, 25, 68, 0, 0, '2022-05-22 10:05:28', '2022-05-22 10:05:28');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (50, 33, 69, 0, 0, '2022-05-22 10:51:04', '2022-05-22 10:51:04');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (51, 21, 70, 0, 0, '2022-05-22 15:42:09', '2022-05-22 15:42:09');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (52, 36, 70, 0, 0, '2022-05-31 06:34:27', '2022-05-31 06:37:52');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (53, 37, 68, 0, 0, '2022-06-01 15:31:36', '2022-06-01 15:31:36');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (56, 11, 68, 0, 0, '2022-06-03 17:14:31', '2022-06-03 17:14:31');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (57, 38, 68, 0, 0, '2022-06-04 03:53:44', '2022-06-04 03:53:44');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (59, 39, 68, 0, 0, '2022-06-05 07:24:28', '2022-06-05 07:24:28');
INSERT INTO `user_role`(`id`, `user_id`, `role_id`, `creator_id`, `updater_id`, `create_time`, `update_time`) VALUES (60, 39, 70, 0, 0, '2022-06-05 07:24:28', '2022-06-05 07:24:28');


