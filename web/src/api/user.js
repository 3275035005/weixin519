import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/p-user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/p-user/info',
    method: 'get',
    params: { token }
  })
}

export function logout(token) {
  return request({
    url: '/p-user/logout',
    method: 'get',
    params: { token }
  })
}

export function pageQuery(page, limit, data) {
  return request({
    url: `/p-user/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/p-user/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/p-user/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/p-user/update`,
    method: 'put',
    data: data
  })
}
export function getUserAll(userType) {
  return request({
    url: '/p-user/getUserAll?userType='+userType,
    method: 'get'
  })
}
export function getHome() {
  return request({
    url: '/p-user/getHome',
    method: 'get'
  })
}

export function updatePassword(data){
  return request({
    url: `/p-user/updatePassword`,
    method: 'post',
    data: data
  })
}

export function resetPassword(id){
  return request({
    url: `/p-user/resetPassword/`+id,
    method: 'post'
  })
}


