-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻内容', '3', '1', 'content', 'news/content/index', 1, 0, 'C', '0', '0', 'news:content:list', '#', 'admin', sysdate(), '', null, '新闻内容菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻内容查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'news:content:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻内容新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'news:content:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻内容修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'news:content:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻内容删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'news:content:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻内容导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'news:content:export',       '#', 'admin', sysdate(), '', null, '');

create table content (
    id int not null auto_increment,
    title varchar(400) default 'no title' comment '标题',
    owner int not null  comment '所有者ID',
    category_id int not null  comment '内容组ID',
    update_date datetime not null comment '更新时间',
    create_date datetime not null comment '创建时间',
    resume varchar(800) null default '' comment '摘要',
    primary key (id)
)engine='InnoDB' COMMENT='内容系统';

create table page_content(
    id int not null auto_increment,
    title varchar(400) default 'no title' comment '标题',
    owner int not null  comment '所有者ID',
    category_id int not null  comment '内容组ID',
    update_date datetime not null comment '更新时间',
    create_date datetime not null comment '创建时间',
    resume varchar(800) null default '' comment '摘要',
    body text not null COMMENT '正文',
    primary key (id)
)engine ='innoDB' comment='html页面';


create table content_category(
    id int not null auto_increment,
    name varchar(100) not null comment '分类名',
    handle_id int not null default 0 comment '内容处理策略',
    remark varchar(400) default 'no title' comment '备注',
    primary key (id)
)engine=InnoDB COMMENT='内容分类';







create table content_handle(
    id int not null auto_increment,
    ui_name varchar(100) not null default 'page',
    ui_name varchar(100) not null default 'page',

    remark varchar(400) default 'no title' comment '备注',
    primary key (id)
)engine=InnoDB default charset ='utf-8' COMMENT='内容处理策略';
