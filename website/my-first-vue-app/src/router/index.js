import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import PostingPage from '@/components/PostingPage.vue'

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage,
  },
  {
    path: '/posting', 
    name: 'PostingPage',
    component: PostingPage,
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router