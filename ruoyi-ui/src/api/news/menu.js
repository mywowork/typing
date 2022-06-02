import request from '@/utils/request'

// 查询新闻菜单列表
export function listMenu(query) {
  return request({
    url: '/news/menu/list',
    method: 'get',
    params: query
  })
}

// 查询新闻菜单详细
export function getMenu(newsMenuId) {
  return request({
    url: '/news/menu/' + newsMenuId,
    method: 'get'
  })
}

// 新增新闻菜单
export function addMenu(data) {
  return request({
    url: '/news/menu',
    method: 'post',
    data: data
  })
}

// 修改新闻菜单
export function updateMenu(data) {
  return request({
    url: '/news/menu',
    method: 'put',
    data: data
  })
}

// 删除新闻菜单
export function delMenu(newsMenuId) {
  return request({
    url: '/news/menu/' + newsMenuId,
    method: 'delete'
  })
}
