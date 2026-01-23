-- 新建一个建表的模板：每个table都包含 id,version,deleted
create table wwjclub.模板表(
    id bigint auto_increment comment '主键',
    `version` bigint not null default 0 comment '乐观锁数据版本号',
    deleted tinyint not null default 0 comment '逻辑删除状态 0-正常 1-已删除',
    created datetime not null default current_timestamp comment '创建时间',
    updated datetime not null default current_timestamp comment '修改时间',
    primary key (id)
) comment '注释';

-- 资源管理模块 RMS
-- 房间表（教师和办公室）
create table wwjclub.rms_room (
    id bigint auto_increment comment '主键',
    title varchar(128) not null default '' comment '房间名称',
    address varchar(256) not null default '' comment '房间地址',
    info varchar(512) not null default '' comment '房间描述',
    capacity int not null default 0 comment '房间能够容纳人数',
    `version` bigint not null default 0 comment '乐观锁数据版本号',
    deleted tinyint not null default 0 comment '逻辑删除状态 0-正常 1-已删除',
    created datetime not null default current_timestamp comment '创建时间',
    updated datetime not null default current_timestamp comment '修改时间',
    primary key (id)
) comment '房间表';

-- 插入数据
-- record
insert into wwjclub.rms_room (id, title, address, info, capacity, version, deleted, created, updated)
values  (1, 'JN_OFFICE_101', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '领导办公室', 4, 0, 0, '2024-12-02 15:28:13', '2025-08-13 11:41:45'),
        (2, 'JN_OFFICE_102', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '人力资源办公室', 1, 0, 0, '2024-12-02 15:28:13', '2025-08-13 11:42:03'),
        (3, 'JN_CLASSROOM_101', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '一楼大教室', 28, 0, 0, '2024-12-02 15:28:13', '2025-08-13 11:42:18'),
        (4, 'JN_WAREHOUSE_101', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '小仓库', 0, 0, 0, '2024-12-02 15:28:13', '2025-08-13 11:42:32'),
        (5, 'JN_CLASSROOM_201', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '黄色教室', 20, 0, 0, '2024-12-02 15:28:13', '2025-08-08 16:13:19'),
        (6, 'JN_CLASSROOM_202', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '粉色教室', 20, 0, 0, '2024-12-02 15:28:13', '2025-08-08 16:11:47'),
        (7, 'JN_CLASSROOM_203', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '蓝色教室', 30, 0, 0, '2024-12-02 15:28:13', '2025-08-08 16:15:04'),
        (8, 'JN_OFFICE_201', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '咨询办公室', 10, 0, 0, '2024-12-02 15:28:13', '2025-08-08 16:14:22'),
        (9, 'JN_OFFICE_202', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '教学办公室', 10, 0, 0, '2024-12-02 15:28:13', '2025-08-08 16:12:57'),
        (10, 'JN_WAREHOUSE_201', '黑龙江省哈尔滨市南岗区水木汤城洗浴附近', '大仓库', 0, 0, 0, '2025-07-25 14:50:56', '2025-08-08 16:15:51'),
        (11, 'JB_CLASSROOM_101', '黑龙江省哈尔滨市松北区君沐洗洗浴附近', '教室一', 50, 0, 0, '2025-08-08 16:14:54', '2025-08-08 16:14:54'),
        (12, 'JB_CLASSROOM_102', '黑龙江省哈尔滨市松北区君沐洗洗浴附近', '教室二', 40, 0, 0, '2025-08-08 16:20:11', '2025-08-08 16:20:11'),
        (13, 'JB_CLASSROOM_103', '黑龙江省哈尔滨市松北区君沐洗洗浴附近', '教室二', 35, 0, 0, '2025-08-08 16:20:11', '2025-08-08 16:20:11'),
        (14, 'JB_OFFICE_101', '黑龙江省哈尔滨市松北区君沐洗洗浴附近', '办公室', 10, 0, 0, '2025-08-08 16:20:11', '2025-08-08 16:20:11');

-- 院校表
create table wwjclub.rms_school(
    id bigint auto_increment comment '主键',
    title varchar(128) not null default '' comment '学校名',
    college varchar(128) not null default '' comment '学院名',
    major varchar(128) not null default '' comment '专业名称',
    clazz varchar(128) not null default '' comment '班级名称',
    address varchar(128) not null default '' comment '校址',
    info varchar(512) not null default '' comment '学校描述',
     `version` bigint not null default 0 comment '乐观锁数据版本号',
     deleted tinyint not null default 0 comment '逻辑删除状态 0-正常 1-已删除',
     created datetime not null default current_timestamp comment '创建时间',
    updated datetime not null default current_timestamp comment '修改时间',
    primary key (id)
) comment '学校信息表';

-- 资产表
create table wwjclub.rms_assets(
   id bigint auto_increment comment '主键',
   title varchar(128) not null default '' comment '资产名称',
   picture varchar(256) not null default '' comment '参考图',
   price decimal(8,2) not null default 0.0 comment '资产单价',
   price_unit varchar(128) not null default '' comment '计量单位',
   stock int not null default 0 comment '剩余库存',
   stock_unit varchar(128) not null default 0 comment '剩余库存单位',
   total int not null default 0 not null comment '总计库存',
   info varchar(512) not null default '' comment '资产描述',
   `version` bigint not null default 0 comment '乐观锁数据版本号',
   deleted tinyint not null default 0 comment '逻辑删除状态 0-正常 1-已删除',
   created datetime not null default current_timestamp comment '创建时间',
   updated datetime not null default current_timestamp comment '修改时间',
   primary key (id)
) comment '资产表';

insert into wwjclub.rms_assets (id, title, picture, price, price_unit, stock, stock_unit, total, info, version, deleted, created, updated)
values  (1, '台式电脑', '台式电脑.png', 10000.00, '元', 90, '台', 100, '高配i7组装电脑台式全套华硕主机吃鸡网咖游戏型电竞直播', 0, 0, '2024-08-16 09:13:11', '2025-08-07 15:38:26'),
        (2, '双人课桌', '双人课桌.png', 1000.00, '元', 190, '件', 200, '岩板长条书桌简约现代卧室家用靠墙学习写字桌电脑桌工作台', 0, 0, '2024-08-16 09:13:11', '2025-07-26 18:04:19'),
        (3, '单人座椅', '单人座椅.png', 200.00, '元', 290, '把', 300, '人体工学椅子护腰家用舒适久坐电竞椅子男生可躺办公室座椅电脑椅', 0, 0, '2024-08-16 09:13:11', '2025-07-26 18:04:19'),
        (4, '饮水机', '饮水机.png', 100.00, '元', 390, '件', 400, 'Glani格兰尼茶吧机2024年新款饮水机家用下置水桶办公室自动智能', 0, 0, '2024-08-16 10:14:29', '2024-08-16 10:16:52'),
        (5, '书架', '书架.png', 50.00, '元', 490, '个', 500, '桌面书立大号学生收纳盒书架书桌放书置物架办公室收纳架书本挡板', 0, 0, '2024-08-16 10:16:00', '2024-08-16 10:16:00'),
        (6, 'U盘', 'U盘.png', 30.00, '元', 590, '个', 600, '64g定制u盘定制手机u盘电脑u盘128g车载影视u盘电影', 0, 0, '2024-08-16 10:18:32', '2024-08-16 10:18:32'),
        (7, '插排', '插排.png', 35.00, '元', 690, '个', 700, '公牛插排', 0, 0, '2025-08-07 12:00:00', '2025-08-07 12:00:00');

-- 资产申请表
create table wwjclub.rms_assets_borrow(
   id bigint auto_increment comment '主键',
   fk_assets_id bigint not null comment '资产ID，资产表外键',
   fk_emp_id bigint not null comment '员工ID，员工表外键',
   count int not null default 0 comment '申请数量',

   borrow_time datetime not null default current_timestamp comment '申请时间',
   expected_return_time datetime not null default current_timestamp comment '预计归还时间',
   return_time datetime comment '实际归还时间',

   info varchar(512) not null default '' comment '申请描述',

   `version` bigint not null default 0 comment '乐观锁数据版本号',
   deleted tinyint not null default 0 comment '逻辑删除状态 0-正常 1-已删除',
   created datetime not null default current_timestamp comment '创建时间',
   updated datetime not null default current_timestamp comment '修改时间',
   primary key (`id`)
) comment '资产申请表';

insert into wwjclub.rms_assets_borrow (id, fk_assets_id, fk_emp_id, count, borrow_time, expected_return_time, return_time, info, version, deleted, created, updated)
values  (1, 1, 1, 4, '2020-12-12 00:00:00', '2021-12-12 00:00:00', null, '暂无描述', 0, 0, '2025-07-29 09:24:25', '2025-07-28 17:40:43'),
        (2, 1, 2, 6, '2025-08-10 09:59:18', '2025-08-11 09:59:21', '2025-08-11 06:00:00', '暂无描述', 0, 0, '2025-08-10 09:59:32', '2025-08-10 10:04:00'),
        (3, 2, 3, 10, '2025-08-22 00:00:00', '2025-08-30 00:00:00', null, '暂无描述', 0, 0, '2025-08-10 10:36:31', '2025-08-10 10:36:31'),
        (4, 3, 3, 10, '2025-08-10 10:54:38', '2025-08-10 10:54:38', null, '', 0, 0, '2025-08-10 10:54:38', '2025-08-10 10:54:38'),
        (5, 4, 3, 10, '2025-08-10 10:54:38', '2025-08-10 10:54:38', null, '', 0, 0, '2025-08-10 10:54:38', '2025-08-10 10:54:38'),
        (6, 5, 4, 10, '2025-08-10 10:54:38', '2025-08-10 10:54:38', null, '', 0, 0, '2025-08-10 10:54:38', '2025-08-10 10:54:38'),
        (7, 6, 5, 10, '2025-08-10 10:54:38', '2025-08-10 10:54:38', null, '', 0, 0, '2025-08-10 10:54:38', '2025-08-10 10:54:38'),
        (8, 7, 6, 10, '2025-08-10 10:54:38', '2025-08-10 10:54:38', null, '', 0, 0, '2025-08-10 10:54:38', '2025-08-10 10:54:38');

-- table
create table wwjclub.ums_dept (
 `id`         bigint       auto_increment      comment '主键',
 `title`      varchar(128) not null default '' comment '部门名称',
 `info`       varchar(512) not null default '' comment '部门描述',
 `fk_room_id` bigint                           comment '房间ID，房间表外键',
 `version`    bigint       not null default 0  comment '数据版本',
 `deleted`    tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
 `created`    datetime     not null default current_timestamp comment '创建时间',
 `updated`    datetime     not null default current_timestamp comment '修改时间',
 primary key (`id`)
) comment '部门表';

-- record
insert into wwjclub.ums_dept (id, title, info, fk_room_id, version, deleted, created, updated)
values  (1, '【江南】总部', '最高行政部', 1, 0, 0, '2024-12-02 15:28:41', '2025-07-29 01:37:35'),
        (2, '【江南】人力资源部', 'HR办公室', 2, 0, 0, '2025-08-08 16:55:18', '2025-08-08 16:55:18'),
        (3, '【江南】市场部', '培训机构的市场部门负责制定营销策略，推广培训课程，开展市场调研，拓展客户资源，并与潜在客户建立联系，促成合作。他们通过线上线下渠道进行推广，包括社交媒体、展会、宣传册等，以吸引更多学员报名参加培训课程。市场人员需要具备良好的沟通能力、市场分析能力和团队合作精神，以达成培训机构的市场目标。', 8, 0, 0, '2024-12-02 15:28:41', '2025-07-29 01:37:35'),
        (4, '【江南】咨询部', '培训机构的咨询部门致力于为学员提供专业的咨询服务，包括课程选择建议、学习规划指导、职业发展规划等。他们倾听学员的需求和关注点，提供个性化的解决方案，帮助学员充分发挥潜能，实现学习和职业目标。咨询人员需要具备丰富的行业知识、良好的沟通技巧和耐心细致的服务态度，以确保学员获得满意的咨询体验。', 8, 0, 0, '2024-12-02 15:28:41', '2025-07-29 01:37:35'),
        (5, '【江南】教学部', '培训机构的教学部门是培训课程的核心组成部分，他们负责教授课程内容，指导学员进行学习和实践，提供专业的教学指导和辅导。教学人员需要具备扎实的专业知识和丰富的教学经验，能够根据学员的需求和水平制定合适的教学计划，激发学员的学习兴趣，帮助他们掌握所学知识和技能，达到培训目标。他们还需要关注学员的学习情况，及时调整教学方法，提供个性化的教学服务，确保教学质量和效果。', 9, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (6, '【江南】品保部', '培训机构的品保部门是学员的导师和管理者，负责班级的日常管理和学员的学习指导。他们积极促进班级氛围，组织学习活动和集体讨论，解决学员学习和生活中的问题，关注学员的学习情况和心理健康，及时提供帮助和支持。班主任还与教学人员和家长保持沟通，协调学员的学习计划和班级管理事务，确保班级的正常运转和学员的全面发展。', 9, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (7, '【江南】就业部', '培训机构的就业部门致力于为学员提供就业指导和服务，包括就业辅导、职业规划、就业信息发布和招聘安排等。他们与企业建立合作关系，了解市场需求，帮助学员匹配岗位，促成就业机会。就业部门还组织职业技能培训和就业实习，提升学员的就业竞争力。他们关注就业市场动态，为学员提供行业趋势和职业发展建议，助力学员顺利就业并实现职业目标。', 9, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (8, '【江北】市场部', '培训机构的市场部门负责制定营销策略，推广培训课程，开展市场调研，拓展客户资源，并与潜在客户建立联系，促成合作。他们通过线上线下渠道进行推广，包括社交媒体、展会、宣传册等，以吸引更多学员报名参加培训课程。市场人员需要具备良好的沟通能力、市场分析能力和团队合作精神，以达成培训机构的市场目标。', 8, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (9, '【江北】咨询部', '培训机构的咨询部门致力于为学员提供专业的咨询服务，包括课程选择建议、学习规划指导、职业发展规划等。他们倾听学员的需求和关注点，提供个性化的解决方案，帮助学员充分发挥潜能，实现学习和职业目标。咨询人员需要具备丰富的行业知识、良好的沟通技巧和耐心细致的服务态度，以确保学员获得满意的咨询体验。', 8, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (10, '【江北】教学部', '培训机构的教学部门是培训课程的核心组成部分，他们负责教授课程内容，指导学员进行学习和实践，提供专业的教学指导和辅导。教学人员需要具备扎实的专业知识和丰富的教学经验，能够根据学员的需求和水平制定合适的教学计划，激发学员的学习兴趣，帮助他们掌握所学知识和技能，达到培训目标。他们还需要关注学员的学习情况，及时调整教学方法，提供个性化的教学服务，确保教学质量和效果。', 9, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (11, '【江北】品保部', '培训机构的品保部门是学员的导师和管理者，负责班级的日常管理和学员的学习指导。他们积极促进班级氛围，组织学习活动和集体讨论，解决学员学习和生活中的问题，关注学员的学习情况和心理健康，及时提供帮助和支持。班主任还与教学人员和家长保持沟通，协调学员的学习计划和班级管理事务，确保班级的正常运转和学员的全面发展。', 9, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (12, '【江北】就业部', '培训机构的就业部门致力于为学员提供就业指导和服务，包括就业辅导、职业规划、就业信息发布和招聘安排等。他们与企业建立合作关系，了解市场需求，帮助学员匹配岗位，促成就业机会。就业部门还组织职业技能培训和就业实习，提升学员的就业竞争力。他们关注就业市场动态，为学员提供行业趋势和职业发展建议，助力学员顺利就业并实现职业目标。', 9, 0, 0, '2024-12-02 15:28:41', '2025-08-04 19:41:33');

create table wwjclub.ums_emp  (
 `id`         bigint       auto_increment      comment '主键',
 `username`   varchar(128) not null default '' comment '登录账号',
 `password`   varchar(128) not null default '' comment '登录密码',
 `avatar`     varchar(256) not null default '' comment '头像地址',
 `phone`      char(11)     not null default '' comment '手机号码',
 `wechat`     varchar(128) not null default '' comment '微信号码',
 `email`      varchar(128) not null default '' comment '邮箱地址',
 `gender`     tinyint      not null default 0  comment '用户性别：0女1男2保密',
 `age`        tinyint      not null default 0  comment '用户年龄',
 `province`   varchar(128) not null default '' comment '籍贯省份',
 `address`    varchar(256) not null default '' comment '现居住地',
 `realname`   varchar(128) not null default '' comment '真实姓名',
 `idcard`     char(18)     not null default '' comment '身份证号',
 `fk_dept_id` bigint                           comment '部门表外键，所属部门',
 `info`       varchar(512) not null default '' comment '员工描述',
 `hiredate`   datetime     not null default current_timestamp comment '入职时间',
 `version`    bigint       not null default 0  comment '数据版本',
 `deleted`    tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
 `created`    datetime     not null default current_timestamp comment '创建时间',
 `updated`    datetime     not null default current_timestamp comment '修改时间',
 primary key (`id`)
) comment '员工表';

-- table
create table wwjclub.ums_role (
     `id`      bigint       auto_increment      comment '主键',
     `title`   varchar(128) not null default '' comment '角色标题',
     `info`    varchar(512) not null default '' comment '角色描述',
     `version` bigint       not null default 0  comment '数据版本',
     `deleted` tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
     `created` datetime     not null default current_timestamp comment '创建时间',
     `updated` datetime     not null default current_timestamp comment '修改时间',
     primary key (`id`)
) comment '角色表';

-- record
insert into wwjclub.ums_role (id, title, info, version, deleted, created, updated)
values  (1, '管理员', '拥有至高无上的权限', 0, 0, '2024-12-02 15:28:41', '2025-08-01 10:02:07'),
        (2, '市场老师', '负责市场调研和分析，制定营销计划，推广和宣传，客户关系管理，销售支持，数据分析和报告，与合作伙伴合作，跟进市场动态等', 0, 0, '2024-12-02 15:28:41', '2025-08-01 10:02:07'),
        (3, '咨询老师', '负责学员咨询与沟通，课程推荐与定制，培训方案解释，学员跟进与关怀，客户需求反馈，协助招生工作，行业信息分享等', 0, 0, '2024-12-02 15:28:41', '2025-08-01 10:02:07'),
        (4, '主课老师', '负责课程教学设计，教学实施，学员管理，教学评估，教学资源开发，教学团队合作，专业知识更新，学员反馈处理等', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (5, '助教老师', '负责协助教学，学员管理，教学辅助，学习资源管理，学习环境维护，学员沟通与反馈，教学活动组织，团队合作等', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (6, '班主任', '负责班级管理，学员管理，教学协调，家长沟通，学员关怀，危机处理，活动组织，团队合作等', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (7, '就业老师', '负责就业指导，就业资源管理，职业培训，招聘对接，求职辅导，就业跟踪，就业政策宣传，校友联络等', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (8, '设计老师', '负责公司品宣', 0, 0, '2025-08-10 19:44:20', '2025-08-10 19:44:20'),
        (9, 'HR', '负责人力资源管理', 0, 0, '2025-08-10 19:44:20', '2025-08-10 19:44:20');

-- table
create table wwjclub.ums_menu (
     `id`      bigint       auto_increment      comment '主键',
     `title`   varchar(128) not null default '' comment '菜单标题',
     `url`     varchar(256) not null default '' comment '跳转地址',
     `icon`    varchar(256) not null default '' comment '菜单图标',
     `pid`     bigint       not null default 0  comment '父菜单主键，0视为根节点',
     `info`    varchar(512) not null default '' comment '菜单描述',
     `version` bigint       not null default 0  comment '数据版本',
     `deleted` tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
     `created` datetime     not null default current_timestamp comment '创建时间',
     `updated` datetime     not null default current_timestamp comment '修改时间',
     primary key (`id`)
) comment '菜单表';

-- record
insert into wwjclub.ums_menu (id, title, url, icon, pid, info, version, deleted, created, updated)
values  (1, '资产管理', '/', 'List', 0, '包括房间管理，学校管理，资产管理和资产申请管理等。', 0, 0, '2024-12-02 15:28:41', '2025-08-09 11:20:01'),
        (2, '用户管理', '/', 'Avatar', 0, '包括部门管理，员工管理，角色管理和菜单管理等。', 0, 0, '2024-12-02 15:28:41', '2025-08-09 11:17:22'),
        (3, '班级管理', '/', 'Platform', 0, '包括方向管理，班级管理，课程管理，班级进度管理和学员管理等。', 0, 0, '2024-12-02 15:28:41', '2025-08-09 11:18:18'),
        (4, '活动管理', '/', 'Soccer', 0, '包括市场活动管理和咨询活动管理等。', 0, 0, '2024-12-02 15:28:41', '2025-08-09 11:18:43'),
        (11, '房间信息列表', '/Room', 'OfficeBuilding', 1, '包括房间记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (12, '学校信息列表', '/School', 'School', 1, '包括学校记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (13, '资产信息列表', '/Assets', 'Coin', 1, '包括资产记录的增删改查，报表打印，上传图片等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (14, '资产申请列表', '/AssetsBorrow', 'Coin', 1, '包括资产申请记录的增删改查，报表打印等。', 0, 0, '2025-08-07 14:52:55', '2025-08-07 14:52:55'),
        (21, '部门信息列表', '/Dept', 'HomeFilled', 2, '包括部门记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (22, '员工信息列表', '/Emp', 'User', 2, '包括员工记录的增删改查，报表打印，上传头像，分配角色等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (23, '角色信息列表', '/Role', 'UserFilled', 2, '包括角色记录的增删改查，报表打印，分配菜单等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (24, '菜单信息列表', '/Menu', 'Menu', 2, '包括菜单记录的增删改查，报表打印，查询子菜单等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (31, '方向信息列表', '/Direction', 'Rank', 3, '包括方向记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (32, '班级信息列表', '/Club', 'Platform', 3, '包括班级记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (33, '课程信息列表', '/Course', 'Reading', 3, '包括课程记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (34, '班级进度列表', '/ClubProgress', 'More', 3, '包括班级进度记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (35, '学员信息列表', '/Student', 'Avatar', 3, '包括学员记录的增删改查，报表打印，上传头像等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (41, '市场活动列表', '/Market', 'Bicycle', 4, '包括市场活动记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (42, '咨询活动列表', '/Consultation', 'ColdDrink', 4, '包括咨询活动记录的增删改查，报表打印等。', 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41');


-- table
create table wwjclub.ums_emp_role (
     `id`         bigint   auto_increment     comment '主键',
     `fk_emp_id`  bigint                      comment '员工ID，员工表外键',
     `fk_role_id` bigint                      comment '角色ID，角色表外键',
     `version`    bigint   not null default 0 comment '数据版本',
     `deleted`    tinyint  not null default 0 comment '逻辑删除，0未删除，1已删除',
     `created`    datetime not null default current_timestamp comment '创建时间',
     `updated`    datetime not null default current_timestamp comment '修改时间',
     primary key (`id`)
) comment '员工角色关系表';

-- record
insert into wwjclub.ums_emp_role (id, fk_emp_id, fk_role_id, version, deleted, created, updated)
values  (1, 1, 1, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (2, 2, 1, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (3, 3, 1, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (4, 4, 1, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (5, 5, 4, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (6, 6, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (7, 7, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (8, 8, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (9, 9, 4, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (10, 10, 4, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (11, 11, 5, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (12, 12, 5, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (13, 13, 4, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (14, 14, 4, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (15, 15, 4, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (16, 16, 5, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (17, 17, 9, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (18, 18, 8, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (19, 19, 7, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (20, 20, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (21, 21, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (22, 22, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (23, 23, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10'),
        (24, 24, 3, 0, 0, '2025-08-12 17:06:10', '2025-08-12 17:06:10');

-- table
create table wwjclub.ums_role_menu (
      `id`         bigint   auto_increment     comment '主键',
      `fk_role_id` bigint                      comment '角色ID，角色表外键',
      `fk_menu_id` bigint                      comment '菜单ID，菜单表外键',
      `version`    bigint   not null default 0 comment '数据版本',
      `deleted`    tinyint  not null default 0 comment '逻辑删除，0未删除，1已删除',
      `created`    datetime not null default current_timestamp comment '创建时间',
      `updated`    datetime not null default current_timestamp comment '修改时间',
      primary key (`id`)
) comment '角色菜单关系表';

-- record
insert into wwjclub.ums_role_menu (id, fk_role_id, fk_menu_id, version, deleted, created, updated)
values  (1, 1, 1, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (2, 1, 2, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (3, 1, 3, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (4, 1, 4, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (5, 1, 11, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (6, 1, 12, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (7, 1, 13, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (8, 1, 14, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (9, 1, 21, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (10, 1, 22, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (11, 1, 23, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (12, 1, 24, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (13, 1, 31, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (14, 1, 32, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (15, 1, 33, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (16, 1, 34, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (17, 1, 35, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (18, 1, 36, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (19, 1, 37, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (20, 1, 41, 0, 0, '2024-12-02 15:28:41', '2024-12-02 15:28:41'),
        (21, 1, 42, 0, 0, '2025-08-07 14:54:19', '2025-08-07 14:54:19');

-- table
create table wwjclub.cms_direction (
   `id`        bigint       auto_increment      comment '主键',
   `title`     varchar(128) not null default '' comment '班级方向名称',
   `info`      varchar(512) not null default '' comment '班级方向描述',
   `version`   bigint       not null default 0  comment '数据版本',
   `deleted`   tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
   `created`   datetime     not null default current_timestamp comment '创建时间',
   `updated`   datetime     not null default current_timestamp comment '修改时间',
   primary key (`id`)
)comment '班级方向表';

-- record
insert into wwjclub.cms_direction (`title`, `info`)
values
    ('Java', '软件开发与大数据方向'),
    ('C/C++', '操作系统与嵌入式方向'),
    ('网络安全', '数据防护方向'),
    ('AI', '人工智能与机器人方向');

-- table
create table wwjclub.cms_club (
     `id`                bigint       auto_increment      comment '主键',
     `title`             varchar(128) not null default '' comment '班级名称',
     `tag`               varchar(128) not null default '' comment '班级类型标签',
     `fk_direction_id`   bigint                           comment '方向ID，方向表外键',
     `start_time`        datetime     not null default current_timestamp comment '开班时间',
     `expected_end_time` datetime     not null default current_timestamp comment '预期结班时间',
     `end_time`          datetime                                        comment '实际结班时间',
     `info`              varchar(512) not null default '' comment '班级描述',
     `version`           bigint       not null default 0  comment '数据版本',
     `deleted`           tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
     `created`           datetime     not null default current_timestamp comment '创建时间',
     `updated`           datetime     not null default current_timestamp comment '修改时间',
     primary key (`id`)
)comment '班级表';

-- record
insert into wwjclub.cms_club (id, title, tag, fk_direction_id, start_time, expected_end_time, end_time, info, version, deleted, created, updated)
values  (1, '江南25-1', '江南, 线下, 非全日制', 1, '2025-01-01 09:00:00', '2026-01-01 09:00:00', '2025-08-01 17:00:00', '暂无描述', 0, 0, '2025-07-23 18:53:33', '2025-08-12 16:38:37'),
        (2, '江南25-2', '江南, 线下, 全日制', 1, '2025-02-01 09:00:00', '2026-02-01 09:00:00', '2025-08-02 17:00:00', '暂无描述', 0, 0, '2025-07-23 18:53:33', '2025-08-12 16:39:38'),
        (3, '江南25-3', '江南, 线上, 全日制', 2, '2025-03-01 09:00:00', '2026-03-01 09:00:00', '2025-08-03 17:00:00', '暂无描述', 0, 0, '2025-07-23 18:53:33', '2025-08-12 16:40:47'),
        (4, '江北25-1', '江北, 线上, 全日制', 3, '2025-01-01 09:00:00', '2027-01-01 09:00:00', null, '暂无描述', 0, 0, '2025-07-23 18:53:33', '2025-08-12 16:41:24'),
        (5, '江北25-2', '江北, 线上, 非全日制', 1, '2025-02-01 09:00:00', '2026-02-01 09:00:00', null, '暂无描述', 0, 0, '2025-08-12 15:34:58', '2025-08-12 16:42:01'),
        (6, '江北25-3', '江北, 线下, 全日制', 1, '2025-03-01 09:00:00', '2026-03-01 09:00:00', null, '暂无描述', 0, 0, '2025-08-12 15:48:08', '2025-08-12 16:05:34'),
        (7, '江北25-4', '江北, 线上, 非全日制', 2, '2025-02-01 09:00:00', '2026-04-01 09:00:00', null, '暂无描述', 0, 0, '2025-08-12 15:49:40', '2025-08-12 15:49:40'),
        (8, '江北25-5', '江北, 线上, 非全日制', 1, '2025-05-01 09:00:00', '2026-05-01 09:00:00', null, '暂无描述', 0, 0, '2025-08-12 16:03:04', '2025-08-12 16:03:04');

-- table
create table wwjclub.cms_course
(
    `id`              bigint auto_increment comment '主键',
    `title`           varchar(128) not null default '' comment '课程名称',
    `category`        varchar(128) not null default '' comment '课程类别',
    `idx`             int          not null default 0 comment '课程顺序',
    `hours`           int          not null default 0 comment '课程学时',
    `info`            varchar(512) not null default '' comment '课程描述',
    `version`         bigint       not null default 0 comment '数据版本',
    `deleted`         tinyint      not null default 0 comment '逻辑删除，0未删除，1已删除',
    `created`         datetime     not null default current_timestamp comment '创建时间',
    `updated`         datetime     not null default current_timestamp comment '修改时间',
    primary key (`id`)
)comment '课程表';

-- record
insert into wwjclub.cms_course (`title`, `category`, `idx`, `hours`, `info`)
values ('Java语言基础', 'Java', 1, 100, 'Java第一阶段课程：包括Java基础，面向对象，Java高级等'),
       ('JavaWeb开发', 'Java', 2, 200, 'Java第二阶段课程：包括Linux，Docker，Tomcat，JDBC，Servlet，H5，CS，JS等'),
       ('Java语言框架', 'Java', 3, 300, 'Java第三阶段课程：包括MyBatis，Spring，SpringMVC，SpringBoot，Redis，Vue等'),
       ('Java微服务框架', 'Java', 4, 400, 'Java第四阶段课程：包括SpringCloud，SpringCloudAlibaba等'),
       ('C语言基础', 'C/C++', 1, 100, 'C第一阶段课程：包括C基础，指针，数组，结构体等'),
       ('C语言高级', 'C/C++', 2, 200, 'C第二阶段课程：包括操作系统，多线程，嵌入式应用，C语言应用实战等'),
       ('C++语言基础', 'C/C++', 3, 300, 'C++第一阶段课程：包括C++基础，STL，模板，异常处理等'),
       ('C++语言高级', 'C/C++', 4, 400, 'C++第二阶段课程：包括C++高级，C++应用实战等'),
       ('网络安全基础', '网络安全', 1, 100, '网络安全第一阶段课程：包括网络安全基础，漏洞分析，Penetration Testing等'),
       ('网络安全高级', '网络安全', 2, 200, '网络安全第二阶段课程：包括Web Application Firewall等'),
       ('AI基础', 'AI', 1, 100, 'AI第一阶段课程：包括AI基础，机器学习，深度学习，神经网络等'),
       ('AI高级', 'AI', 2, 200, 'AI第二阶段课程：包括AI实际应用等');

-- table
create table wwjclub.cms_club_progress (
  `id`                bigint       auto_increment      comment '主键',
  `idx`               int          not null default 0  comment '班级进度顺序',
  `fk_club_id`        bigint                           comment '班级ID，班级表外键',
  `fk_room_id`        bigint                           comment '房间ID，房间表外键',
  `fk_manager_id`     bigint                           comment '班主任ID，员工表外键',
  `fk_assistant_id`   bigint                           comment '助理老师ID，员工表外键',
  `fk_teacher_id`     bigint                           comment '主讲老师ID，员工表外键',
  `fk_course_id`      bigint                           comment '课程ID，课程表外键',
  `start_time`        datetime     not null default current_timestamp comment '进度开始时间',
  `expected_end_time` datetime     not null default current_timestamp comment '预计进度结束时间',
  `end_time`          datetime                                        comment '实际进度结束时间',
  `info`              varchar(512) not null default '' comment '班级进度描述',
  `version`           bigint       not null default 0  comment '数据版本',
  `deleted`           tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
  `created`           datetime     not null default current_timestamp comment '创建时间',
  `updated`           datetime     not null default current_timestamp comment '修改时间',
  primary key (`id`)
)comment '班级进度表';

-- record
insert into wwjclub.cms_club_progress (id, idx, fk_club_id, fk_room_id, fk_manager_id, fk_assistant_id, fk_teacher_id, fk_course_id, start_time, expected_end_time, end_time, info, version, deleted, created, updated)
values  (1, 1, 1, 3, 6, 11, 1, 1, '2025-01-01 00:00:00', '2025-04-01 00:00:00', '2025-04-01 00:00:00', '暂无描述', 0, 0, '2025-08-13 11:03:39', '2025-08-13 11:10:37'),
        (2, 2, 1, 3, 6, 11, 1, 2, '2025-04-01 00:00:00', '2025-08-01 00:00:00', '2025-08-01 00:00:00', '暂无描述', 0, 0, '2025-08-13 11:03:39', '2025-08-13 11:11:41'),
        (3, 3, 1, 3, 6, 11, 1, 3, '2025-01-01 00:00:00', '2025-04-01 00:00:00', null, '暂无描述', 0, 0, '2025-08-13 11:04:49', '2025-08-13 11:11:04'),
        (4, 4, 1, 3, 6, 11, 1, 4, '2025-08-01 00:00:00', '2025-10-01 00:00:00', null, '暂无描述', 0, 0, '2025-08-13 11:12:17', '2025-08-13 11:12:17'),
        (5, 1, 2, 5, 7, 12, 10, 5, '2025-10-01 00:00:00', '2026-01-01 00:00:00', null, '暂无描述', 0, 0, '2025-08-13 11:12:53', '2025-08-13 11:12:53');

-- table
create table wwjclub.cms_student
(
    `id`              bigint       auto_increment      comment '主键',
    `number`          varchar(128) not null default '' comment '学生编号',
    `realname`        varchar(128) not null default '' comment '学生姓名',
    `avatar`          varchar(256) not null default '' comment '头像地址',
    `phone`           char(11)     not null default '' comment '手机号码',
    `gender`          tinyint      not null default 0  comment '学生性别，0女1男2保密',
    `wechat`          varchar(256) not null default '' comment '微信号码',
    `age`             tinyint      not null default 0  comment '学生年龄',
    `province`        varchar(128) not null default '' comment '籍贯省份',
    `address`         varchar(256) not null default '' comment '现居住地',
    `fk_school_id`    bigint                           comment '学校ID，学校表外键',
    `fk_salesman_id`  bigint                           comment '咨询ID，员工表外键',
    `fk_club_id`      bigint                           comment '班级ID，班级表外键',
    `idcard`          char(18)     not null default '' comment '身份证号',
    `study_date`      datetime     not null default current_timestamp comment '入学时间',
    `emergency_name`  varchar(128) not null default '' comment '紧急联系人姓名',
    `emergency_phone` char(11)     not null default '' comment '紧急联系人电话',
    `status`          tinyint      not null default 0  comment '学生状态：0在培1结课2已就业3休学4其它',
    `info`            varchar(512) not null default '' comment '学生描述',
    `version`         bigint       not null default 0  comment '数据版本',
    `deleted`         tinyint      not null default 0  comment '逻辑删除，0未删除，1已删除',
    `created`         datetime     not null default current_timestamp comment '创建时间',
    `updated`         datetime     not null default current_timestamp comment '修改时间',
    primary key (`id`)
)
    comment '学生表';

-- record
insert into wwjclub.cms_student (id, number, realname, avatar, phone, gender, wechat, age, province, address, fk_school_id, fk_salesman_id, fk_club_id, idcard, study_date, emergency_name, emergency_phone, status, info, version, deleted, created, updated)
values  (1, 'MC2023001', '王小蒙', '王小蒙.jpg', '13800138001', 0, 'wxm123456', 28, '辽宁省', '铁岭市象牙山村', 1, 6, 1, '211282199503151234', '2025-01-01 00:00:00', '王老七', '13900139001', 0, '豆制品厂老板，积极进取', 0, 0, '2025-07-23 20:03:37', '2025-08-02 15:09:36'),
        (2, 'MC2023002', '谢永强', '谢永强.jpg', '13800138002', 1, 'xyq123456', 30, '辽宁省', '铁岭市象牙山村', 1, 7, 2, '211282199305205678', '2025-02-01 00:00:00', '谢广坤', '13900139002', 0, '果园负责人，踏实肯干', 0, 0, '2025-07-23 20:03:37', '2025-08-02 15:09:36'),
        (3, 'MC2023003', '刘能', '刘能.jpg', '13800138003', 1, 'ln123456', 52, '辽宁省', '铁岭市象牙山村', 2, 8, 3, '211282197108159012', '2025-01-01 00:00:00', '李秀莲', '13900139003', 1, '前村主任，热心肠', 0, 0, '2025-07-23 20:03:37', '2025-08-02 15:09:36'),
        (4, 'MC2023004', '赵四', '赵四.jpg', '13800138004', 1, 'zs123456', 53, '辽宁省', '铁岭市象牙山村', 2, 6, 1, '211282197006203456', '2025-01-01 00:00:00', '王美兰', '13900139004', 0, '爱好跳舞，性格幽默', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37'),
        (5, 'MC2023005', '王云', '王云.jpg', '13800138005', 0, 'wy123456', 48, '辽宁省', '铁岭市象牙山村', 3, 6, 1, '211282197502107890', '2025-01-01 00:00:00', '王长贵', '13900139005', 1, '超市老板，消息灵通', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37'),
        (6, 'MC2023006', '宋晓峰', '宋晓峰.jpg', '13800138006', 1, 'sxf123456', 35, '辽宁省', '铁岭市象牙山村', 1, 6, 1, '211282198811052345', '2025-01-01 00:00:00', '宋富贵', '13900139006', 0, '保安队长，喜欢作诗', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37'),
        (7, 'MC2023007', '刘英', '刘英.jpg', '13800138007', 0, 'ly123456', 29, '辽宁省', '铁岭市象牙山村', 1, 6, 1, '211282199404186789', '2025-01-01 00:00:00', '刘能', '13900139003', 2, '性格温和，勤劳朴实', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37'),
        (8, 'MC2023008', '赵玉田', '赵玉田.jpg', '13800138008', 1, 'zyt123456', 30, '辽宁省', '铁岭市象牙山村', 1, 6, 1, '211282199309253456', '2025-01-01 00:00:00', '赵四', '13900139004', 3, '花圃老板，年轻有为', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37'),
        (9, 'MC2023009', '谢广坤', '谢广坤.jpg', '13800138009', 1, 'xgk123456', 55, '辽宁省', '铁岭市象牙山村', 2, 6, 1, '211282196803127890', '2025-01-01 00:00:00', '赵金凤', '13900139009', 4, '爱面子，比较强势', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37'),
        (10, 'MC2023010', '王老七', '王老七.jpg', '13800138010', 1, 'wlq123456', 56, '辽宁省', '铁岭市象牙山村', 2, 6, 1, '211282196711301234', '2025-01-01 00:00:00', '王小蒙', '13900139010', 1, '性格随和，手艺好', 0, 0, '2025-07-23 20:03:37', '2025-07-23 20:03:37');

