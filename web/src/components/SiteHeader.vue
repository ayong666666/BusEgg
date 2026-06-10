<template>
  <header class="site-header" :class="{ 'is-scrolled': isScrolled }">
    <a class="brand" href="#home" aria-label="蛋蛋巴士首页">
      <span class="brand-mark">d</span>
      <span>
        <strong>蛋蛋巴士</strong>
        <small>百家信数字科技有限公司</small>
      </span>
    </a>

    <button
      class="nav-toggle"
      type="button"
      aria-label="打开导航"
      :aria-expanded="navOpen"
      @click="navOpen = !navOpen"
    >
      <span></span>
      <span></span>
      <span></span>
    </button>

    <nav class="site-nav" :class="{ 'is-open': navOpen }">
      <a
        v-for="item in navItems"
        :key="item.href"
        :class="{ 'is-active': activeId === item.href.slice(1) }"
        :href="item.href"
        @click.prevent="handleNavClick(item)"
      >
        {{ item.label }}
      </a>
    </nav>
  </header>
</template>

<script setup>
import { onMounted, onUnmounted, ref } from 'vue'

const navOpen = ref(false)
const isScrolled = ref(false)
const activeId = ref('home')
const lockedActiveId = ref('')
let unlockTimer = 0
let unlockFrame = 0

const navItems = [
  { label: '首页', href: '#home' },
  { label: '产品介绍', href: '#products' },
  { label: '解决方案', href: '#solution' },
  { label: '招商合作', href: '#partner' },
  { label: '关于我们', href: '#about' },
  { label: '新闻动态', href: '#news' },
  { label: '联系我们', href: '#contact' }
]

const updateHeader = () => {
  isScrolled.value = window.scrollY > 24
}

const updateActiveSection = () => {
  if (lockedActiveId.value) {
    activeId.value = lockedActiveId.value
    return
  }

  const scrollPosition = window.scrollY + 120
  const current = [...navItems]
    .reverse()
    .find(item => {
      const section = document.querySelector(item.href)
      return section && section.offsetTop <= scrollPosition
    })

  if (current) activeId.value = current.href.slice(1)
}

const handleScroll = () => {
  updateHeader()
  updateActiveSection()
}

const handleNavClick = item => {
  const targetId = item.href.slice(1)
  const section = document.querySelector(item.href)
  const headerOffset = 76
  const targetTop = section ? Math.max(section.offsetTop - headerOffset, 0) : 0

  activeId.value = targetId
  lockedActiveId.value = targetId
  navOpen.value = false

  if (section) {
    window.scrollTo({
      top: targetTop,
      behavior: 'smooth'
    })
  }

  window.clearTimeout(unlockTimer)
  window.cancelAnimationFrame(unlockFrame)

  const unlockActive = () => {
    lockedActiveId.value = ''
    activeId.value = targetId
  }

  const waitUntilArrived = () => {
    if (Math.abs(window.scrollY - targetTop) <= 4) {
      unlockActive()
      return
    }
    unlockFrame = window.requestAnimationFrame(waitUntilArrived)
  }

  unlockFrame = window.requestAnimationFrame(waitUntilArrived)
  unlockTimer = window.setTimeout(() => {
    window.cancelAnimationFrame(unlockFrame)
    unlockActive()
  }, 2500)
}

onMounted(() => {
  handleScroll()
  window.addEventListener('scroll', handleScroll, { passive: true })
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
  window.clearTimeout(unlockTimer)
  window.cancelAnimationFrame(unlockFrame)
})
</script>
