<template>
  <BackProfile />
  <BagAtas />

  <div class="container">
    <IsiKonten />

    <nav id="nav">
      <router-link to="/tweet">Tweets</router-link>
      <router-link to="/about">Tweets & Replies</router-link>
      <router-link to="/media">Media</router-link>
      <router-link to="/suka">Likes</router-link>
    </nav>

    <button @click="goToPostingPage" class="plusButton">+</button>
  </div>

  <router-view />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Cookies from 'js-cookie'

import BackProfile from '@/components/BackProfile.vue'
import BagAtas from '@/components/BagAtas.vue'
import IsiKonten from '@/components/IsiKonten.vue'

// Router
const router = useRouter()
const goToPostingPage = () => {
  router.push('/posting')
}

// Reactive state
const isiPost = ref('')

// Ambil data ketika halaman dimuat
onMounted(() => {
  const storedPost = Cookies.get('isiPost') || localStorage.getItem('isiPost')
  if (storedPost) {
    isiPost.value = storedPost
  }
})
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
  text-decoration: none;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
}

#nav {
  width: 600px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  padding: 5px;
  gap: 50px;
  margin-top: -20px;
}

#nav a {
  text-decoration: none;
  color: grey;
}

#nav a.router-link-active {
  font-weight: bold;
  color: black;
  border-bottom: 5px solid #1d9bf0;
}

.plusButton {
  margin-top: 50px;
  margin-left: 450px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #1d9bf0;
  color: white;
  font-size: 28px;
  font-weight: bold;
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  z-index: 1000;
  transition: transform 0.2s ease-in-out;
}

.posted {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #f0f0f0;
  border-radius: 10px;
  max-width: 600px;
  word-wrap: break-word;
}
</style>