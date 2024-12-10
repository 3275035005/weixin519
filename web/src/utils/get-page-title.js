import defaultSettings from '@/settings'

const title = defaultSettings.title || '大学生心理咨询管理系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
