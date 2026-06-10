<template>
  <div>
    <div class="stat-grid">
      <article v-for="item in stats" :key="item.label" class="stat-card">
        <span>{{ item.label }}</span>
        <strong>{{ item.value }}</strong>
      </article>
    </div>
    <el-card class="content-card">
      <template #header>
        <div class="card-header">
          <span>第一阶段功能</span>
          <el-button type="primary" @click="$router.push('/leads')">查看留资</el-button>
        </div>
      </template>
      <el-timeline>
        <el-timeline-item timestamp="已完成" type="success">后台登录与路由守卫</el-timeline-item>
        <el-timeline-item timestamp="已完成" type="success">工作台统计数据</el-timeline-item>
        <el-timeline-item timestamp="已完成" type="success">留资列表、筛选、状态和备注</el-timeline-item>
        <el-timeline-item timestamp="后续">产品管理、新闻管理、图片上传、官网配置</el-timeline-item>
      </el-timeline>
    </el-card>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { dashboardApi } from '../api/dashboard'

const stats = ref([
  { label: '今日留言', value: 0 },
  { label: '待跟进', value: 0 },
  { label: '已合作', value: 0 },
  { label: '总留言', value: 0 }
])

onMounted(async () => {
  const res = await dashboardApi.stats()
  stats.value = [
    { label: '今日留言', value: res.data.todayCount },
    { label: '待跟进', value: res.data.pendingCount },
    { label: '已合作', value: res.data.cooperatedCount },
    { label: '总留言', value: res.data.totalCount }
  ]
})
</script>

