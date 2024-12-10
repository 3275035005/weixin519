import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/p-reply/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(data) {
  return request({
    url: `/p-reply/deleteById`,
    method: 'delete',
    data:data
  })
}
export function sendReply(data) {
  return request({
    url: `/p-reply/sendReply`,
    method: 'post',
    data: data
  })
}

export function getReply(userId, counselorId, flag) {
  return request({
    url: `/p-reply/getReply?userId=${userId}&counselorId=${counselorId}&flag=${flag}`,
    method: 'get'
  })
}
