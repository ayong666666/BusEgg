import request from './request'

export const authApi = {
  login: data => request.post('/admin/auth/login', data),
  profile: () => request.get('/admin/auth/profile')
}

