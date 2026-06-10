<template>
  <div>
    <el-card class="content-card">
      <template #header>
        <div class="card-header">
          <span>留资管理</span>
          <el-button @click="loadData">刷新</el-button>
        </div>
      </template>
      <el-form :inline="true" :model="query" class="search-form">
        <el-form-item label="关键词">
          <el-input v-model="query.keyword" clearable placeholder="姓名或手机号" />
        </el-form-item>
        <el-form-item label="合作类型">
          <el-select v-model="query.type" clearable placeholder="全部类型" style="width: 160px">
            <el-option label="社区店合作" value="社区店合作" />
            <el-option label="鸡蛋商合作" value="鸡蛋商合作" />
            <el-option label="城市运营合作" value="城市运营合作" />
            <el-option label="其他咨询" value="其他咨询" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="query.status" clearable placeholder="全部状态" style="width: 140px">
            <el-option label="未处理" value="未处理" />
            <el-option label="跟进中" value="跟进中" />
            <el-option label="已合作" value="已合作" />
            <el-option label="无效" value="无效" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loadData">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="list" border>
        <el-table-column prop="name" label="姓名" width="110" />
        <el-table-column prop="phone" label="联系电话" width="140" />
        <el-table-column prop="type" label="合作类型" width="140" />
        <el-table-column prop="message" label="留言" min-width="220" show-overflow-tooltip />
        <el-table-column prop="status" label="处理状态" width="120">
          <template #default="{ row }">
            <el-tag :type="statusType(row.status)">{{ row.status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createdAt" label="提交时间" width="180" />
        <el-table-column label="操作" width="190" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" @click="openDialog(row)">跟进</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-wrap">
        <el-pagination
          background
          layout="prev, pager, next, total"
          :total="total"
          :page-size="query.pageSize"
          v-model:current-page="query.page"
          @current-change="loadData"
        />
      </div>
    </el-card>

    <el-dialog v-model="dialogVisible" title="跟进留言" width="520px">
      <el-form v-if="current" label-position="top">
        <el-form-item label="处理状态">
          <el-select v-model="current.status" style="width: 100%">
            <el-option label="未处理" value="未处理" />
            <el-option label="跟进中" value="跟进中" />
            <el-option label="已合作" value="已合作" />
            <el-option label="无效" value="无效" />
          </el-select>
        </el-form-item>
        <el-form-item label="跟进备注">
          <el-input v-model="current.remark" type="textarea" rows="4" placeholder="请输入跟进备注" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveFollow">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { leadApi } from '../api/lead'

const loading = ref(false)
const list = ref([])
const total = ref(0)
const dialogVisible = ref(false)
const current = ref(null)

const query = reactive({
  page: 1,
  pageSize: 10,
  keyword: '',
  type: '',
  status: ''
})

const statusType = status => {
  const map = {
    未处理: 'warning',
    跟进中: 'primary',
    已合作: 'success',
    无效: 'info'
  }
  return map[status] || 'info'
}

const loadData = async () => {
  loading.value = true
  try {
    const res = await leadApi.list(query)
    list.value = res.data.list
    total.value = res.data.total
  } finally {
    loading.value = false
  }
}

const reset = () => {
  query.keyword = ''
  query.type = ''
  query.status = ''
  query.page = 1
  loadData()
}

const openDialog = row => {
  current.value = { ...row }
  dialogVisible.value = true
}

const saveFollow = async () => {
  await leadApi.updateStatus(current.value.id, { status: current.value.status })
  await leadApi.updateRemark(current.value.id, { remark: current.value.remark || '' })
  ElMessage.success('保存成功')
  dialogVisible.value = false
  loadData()
}

onMounted(loadData)
</script>

