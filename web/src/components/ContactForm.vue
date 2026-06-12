<template>
  <form class="lead-form" novalidate @submit.prevent="submit">
    <label>
      姓名
      <input ref="nameInput" v-model.trim="form.name" type="text" name="name" placeholder="请输入姓名" />
    </label>
    <label>
      联系电话
      <input ref="phoneInput" v-model.trim="form.phone" type="tel" name="phone" placeholder="请输入联系电话" />
    </label>
    <label>
      合作类型
      <select ref="typeInput" v-model="form.type" name="type">
        <option value="">请选择合作类型</option>
        <option>社区店合作</option>
        <option>鸡蛋商合作</option>
        <option>城市运营合作</option>
        <option>其他咨询</option>
      </select>
    </label>
    <label>
      留言
      <textarea ref="messageInput" v-model.trim="form.message" name="message" rows="4" placeholder="请简单描述您的城市、门店或供货资源"></textarea>
    </label>
    <button class="btn btn-primary" type="submit" :disabled="submitting">
      {{ submitting ? '提交中...' : '提交合作意向' }}
    </button>
    <p class="form-tip">留言非必填，最多 200 字。</p>
  </form>

  <div v-if="dialog.visible" class="form-dialog-mask" role="presentation" @click.self="closeDialog">
    <div class="form-dialog" role="dialog" aria-modal="true" aria-labelledby="lead-dialog-title">
      <h3 id="lead-dialog-title">{{ dialog.title }}</h3>
      <p>{{ dialog.message }}</p>
      <button class="btn btn-primary" type="button" @click="closeDialog">我知道了</button>
    </div>
  </div>
</template>

<script setup>
import { nextTick, reactive, ref } from 'vue'
import { siteApi } from '../api/site'

const form = reactive({
  name: '',
  phone: '',
  type: '',
  message: ''
})

const submitting = ref(false)
const nameInput = ref(null)
const phoneInput = ref(null)
const typeInput = ref(null)
const messageInput = ref(null)
const pendingFocus = ref(null)
const dialog = reactive({
  visible: false,
  title: '提示',
  message: ''
})

const phonePattern = /^1[3-9]\d{9}$/

const resetForm = () => {
  form.name = ''
  form.phone = ''
  form.type = ''
  form.message = ''
}

const showDialog = (message, targetRef = null, title = '提示') => {
  dialog.title = title
  dialog.message = message
  dialog.visible = true
  pendingFocus.value = targetRef
}

const closeDialog = async () => {
  dialog.visible = false
  await nextTick()
  pendingFocus.value?.focus?.()
  pendingFocus.value = null
}

const validateForm = () => {
  if (!form.name) {
    showDialog('请输入姓名。', nameInput.value)
    return false
  }

  if (form.name.length < 2 || form.name.length > 20) {
    showDialog('姓名长度需要为 2 到 20 个字符。', nameInput.value)
    return false
  }

  if (!form.phone) {
    showDialog('请输入联系电话。', phoneInput.value)
    return false
  }

  if (!phonePattern.test(form.phone)) {
    showDialog('请输入正确的手机号。', phoneInput.value)
    return false
  }

  if (!form.type) {
    showDialog('请选择合作类型。', typeInput.value)
    return false
  }

  if (form.message.length > 200) {
    showDialog('留言不能超过 200 字。', messageInput.value)
    return false
  }

  return true
}

const submit = async () => {
  if (!validateForm()) return

  submitting.value = true
  try {
    await siteApi.submitLead({ ...form, source: 'website' })
    resetForm()
    showDialog('提交成功，我们会尽快与您联系。', null, '提交成功')
  } catch (error) {
    showDialog('提交失败，请稍后再试或拨打热线。')
  } finally {
    submitting.value = false
  }
}
</script>
