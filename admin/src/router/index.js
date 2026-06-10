import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', name: 'login', component: () => import('../views/Login.vue') },
    {
      path: '/',
      component: () => import('../components/AppLayout.vue'),
      redirect: '/dashboard',
      children: [
        { path: 'dashboard', name: 'dashboard', component: () => import('../views/Dashboard.vue'), meta: { title: '工作台' } },
        { path: 'leads', name: 'leads', component: () => import('../views/LeadManage.vue'), meta: { title: '留资管理' } }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('BUSEGG_ADMIN_TOKEN')
  if (to.name !== 'login' && !token) next('/login')
  else if (to.name === 'login' && token) next('/dashboard')
  else next()
})

export default router

