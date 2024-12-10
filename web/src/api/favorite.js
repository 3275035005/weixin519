import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/p-favorite/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/p-favorite/deleteById/${id}`,
    method: 'delete'
  })
}
