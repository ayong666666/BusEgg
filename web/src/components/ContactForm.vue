<template>
  <form class="lead-form" @submit.prevent="submit">
    <label>
      姓名
      <input v-model.trim="form.name" type="text" name="name" placeholder="请输入姓名" required />
    </label>
    <label>
      联系电话
      <input v-model.trim="form.phone" type="tel" name="phone" placeholder="请输入联系电话" required />
    </label>
    <label>
      合作类型
      <select v-model="form.type" name="type" required>
        <option value="">请选择合作类型</option>
        <option>社区店合作</option>
        <option>鸡蛋商合作</option>
        <option>城市运营合作</option>
        <option>其他咨询</option>
      </select>
    </label>
    <label>
      留言
      <textarea v-model.trim="form.message" name="message" rows="4" placeholder="请简单描述您的城市、门店或供货资源"></textarea>
    </label>
    <button class="btn btn-primary" type="submit" :disabled="submitting">
      {{ submitting ? '提交中...' : '提交合作意向' }}
    </button>
    <p class="form-tip" :class="{ error: tipType === 'error' }">{{ tip }}</p>
  </form>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { siteApi } from '../api/site'

const form = reactive({
  name: '',
  phone: '',
  type: '',
  message: ''
})

const submitting = ref(false)
const tip = ref('')
const tipType = ref('success')

const phonePattern = /^1[3-9]\d{9}$/

const resetForm = () => {
  form.name = ''
  form.phone = ''
  form.type = ''
  form.message = ''
}

const submit = async () => {
  tip.value = ''
  tipType.value = 'success'

  if (!phonePattern.test(form.phone)) {
    tipType.value = 'error'
    tip.value = '请输入正确的手机号。'
    return
  }

  submitting.value = true
  try {
    await siteApi.submitLead({ ...form, source: 'website' })
    resetForm()
    tip.value = '提交成功，我们会尽快与您联系。'
  } catch (error) {
    tipType.value = 'error'
    tip.value = '提交失败，请稍后再试或拨打热线。'
  } finally {
    submitting.value = false
  }
}
</script>

