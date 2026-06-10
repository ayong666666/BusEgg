<template>
  <section class="section news-section" id="news">
    <div class="container section-heading">
      <p class="eyebrow">News</p>
      <h2>新闻动态</h2>
      <p>用于后续发布公司动态、产品升级、门店合作与供应链资讯。</p>
    </div>
    <div class="container news-grid">
      <article v-for="item in news" :key="item.title">
        <time :datetime="item.date">{{ item.dateText }}</time>
        <h3>{{ item.title }}</h3>
        <p>{{ item.summary }}</p>
      </article>
    </div>
  </section>
</template>

<script setup>
const news = [
  {
    date: '2026-05-27',
    dateText: '2026.05',
    title: '蛋蛋巴士社区店合作计划持续开放',
    summary: '面向社区便利店、生鲜店、超市等场景，提供小程序引流与蛋品供应支持。'
  },
  {
    date: '2026-05-27',
    dateText: '2026.05',
    title: '商家经营后台能力完善',
    summary: '围绕订单、库存、奖励、资产、员工授权等功能，提升门店日常管理效率。'
  },
  {
    date: '2026-05-27',
    dateText: '2026.05',
    title: '可溯源鲜蛋产品矩阵升级',
    summary: '持续丰富谷饲鲜鸡蛋、柴鸡蛋、虫草蛋等产品，满足不同消费与渠道需求。'
  }
]
</script>

