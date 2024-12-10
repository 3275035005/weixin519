import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/p-knowledge/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/p-knowledge/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/p-knowledge/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/p-knowledge/update`,
    method: 'put',
    data: data
  })
}

export function getKnowledgeAll(){
  return request({
    url: `/p-knowledge/getKnowledgeAll`,
    method: 'get'
  })
}
