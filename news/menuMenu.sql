-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻菜单', '3', '1', 'menu', 'news/menu/index', 1, 0, 'C', '0', '0', 'news:menu:list', '#', 'admin', sysdate(), '', null, '新闻菜单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻菜单查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'news:menu:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻菜单新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'news:menu:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻菜单修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'news:menu:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻菜单删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'news:menu:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('新闻菜单导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'news:menu:export',       '#', 'admin', sysdate(), '', null, '');
