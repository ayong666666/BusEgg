import { defineStore } from 'pinia'
import { authApi } from '../api/auth'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('BUSEGG_ADMIN_TOKEN') || '',
    user: null
  }),
  actions: {
    async login(payload) {
      const res = await authApi.login(payload)
      this.token = res.data.token
      this.user = res.data.user
      localStorage.setItem('BUSEGG_ADMIN_TOKEN', this.token)
    },
    logout() {
      this.token = ''
      this.user = null
      localStorage.removeItem('BUSEGG_ADMIN_TOKEN')
    }
  }
})

