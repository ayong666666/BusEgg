<template>
  <section class="section contact-section" id="contact">
    <div class="container contact-layout">
      <div class="contact-copy">
        <p class="eyebrow">Contact</p>
        <h2>欢迎咨询合作</h2>
        <p>留下合作意向，我们会根据门店、供货、城市运营等不同场景，安排对应人员对接。</p>
        <div class="contact-list">
          <span>财富热线：400-992-0108</span>
          <span>品牌：蛋蛋巴士</span>
          <span>公司：百家信数字科技有限公司</span>
        </div>
        <div class="mini-program">
          <div class="qr-like" aria-hidden="true">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
          </div>
          <p>微信搜索：蛋蛋巴士</p>
        </div>
      </div>
      <ContactForm />
    </div>
  </section>
</template>

<script setup>
import ContactForm from './ContactForm.vue'
</script>

