import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/p-result/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/p-result/deleteById/${id}`,
    method: 'delete'
  })
}
export function update(data){
  return request({
    url: `/p-result/update`,
    method: 'put',
    data: data
  })
}
export function getTypeHistogram(){
  return request({
    url: `/p-result/getTypeHistogram`,
    method: 'get'
  })
}

