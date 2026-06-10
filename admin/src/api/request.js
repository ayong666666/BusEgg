import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  timeout: 10000
})

request.interceptors.request.use(config => {
  const token = localStorage.getItem('BUSEGG_ADMIN_TOKEN')
  if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})

request.interceptors.response.use(
  response => response.data,
  error => {
    const message = error.response?.data?.message || '请求失败'
    ElMessage.error(message)
    if (error.response?.status === 401) {
      localStorage.removeItem('BUSEGG_ADMIN_TOKEN')
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

export default request

