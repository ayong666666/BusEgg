<template>
  <main class="login-page">
    <section class="login-hero">
      <p>蛋蛋巴士后台</p>
      <h1>管理官网留资与内容运营</h1>
      <span>第一阶段：登录、工作台、留资管理</span>
    </section>
    <section class="login-card">
      <h2>管理员登录</h2>
      <p>请输入管理员账号和密码</p>
      <el-form :model="form" label-position="top" @submit.prevent>
        <el-form-item label="账号">
          <el-input v-model="form.username" placeholder="请输入账号" size="large" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码" size="large" show-password />
        </el-form-item>
        <el-button type="primary" size="large" :loading="loading" @click="submit">登录</el-button>
      </el-form>
    </section>
  </main>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useAuthStore } from '../stores/auth'

const router = useRouter()
const authStore = useAuthStore()
const loading = ref(false)

const form = reactive({
  username: 'bugegg',
  password: 'bugegg'
})

const submit = async () => {
  if (!form.username || !form.password) {
    ElMessage.warning('请输入账号和密码')
    return
  }
  loading.value = true
  try {
    await authStore.login(form)
    ElMessage.success('登录成功')
    router.push('/dashboard')
  } finally {
    loading.value = false
  }
}
</script>

