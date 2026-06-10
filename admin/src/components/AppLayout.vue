<template>
  <el-container class="admin-shell">
    <el-aside width="232px" class="admin-aside">
      <div class="admin-logo">
        <span>d</span>
        <strong>蛋蛋巴士后台</strong>
      </div>
      <el-menu router :default-active="$route.path" class="admin-menu">
        <el-menu-item index="/dashboard">
          <span>工作台</span>
        </el-menu-item>
        <el-menu-item index="/leads">
          <span>留资管理</span>
        </el-menu-item>
        <el-menu-item disabled index="/products">
          <span>产品管理</span>
        </el-menu-item>
        <el-menu-item disabled index="/news">
          <span>新闻管理</span>
        </el-menu-item>
        <el-menu-item disabled index="/site-config">
          <span>官网配置</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="admin-header">
        <div>
          <p>当前位置</p>
          <h1>{{ $route.meta.title || '后台管理' }}</h1>
        </div>
        <div class="header-actions">
          <span>管理员</span>
          <el-button type="primary" plain @click="logout">退出登录</el-button>
        </div>
      </el-header>
      <el-main class="admin-main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()

const logout = () => {
  authStore.logout()
  router.push('/login')
}
</script>

