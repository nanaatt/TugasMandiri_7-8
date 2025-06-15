<template>
  <div class="posting">
    <!-- Notifikasi melayang -->
    <div v-if="showNotif" class="notif">
      Postingan berhasil diunggah!
    </div>

    <div class="topBar">
      <span class="close" @click="kembali">✕</span>
      <button class="postButton" @click="simpanPost">Post</button>
    </div>

    <div class="inputText">
      <textarea
        v-model="isiPost"
        placeholder="Apa yang sedang terjadi?"
        class="input"
      ></textarea>
    </div>

    <div class="icon1">
      <img src="/icmage.png" alt="galeri" class="icon" />
      <img src="/icgif.png" alt="gif" class="icon" />
      <img src="/icpoll.png" alt="polling" class="icon" />
      <img src="/icloc.png" alt="location" class="icon" />
    </div>

    <div class="icon2">
      <img src="/plus.png" alt="plus" class="iconn" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import Cookies from 'js-cookie'

const router = useRouter()
const kembali = () => router.push('/')

const isiPost = ref('')
const showNotif = ref(false) // kontrol visibilitas notifikasi

const simpanPost = () => {
  if (isiPost.value.trim()) {
    const expiredAt = new Date(Date.now() + 24 * 60 * 60 * 1000) // 1 hari
    localStorage.setItem('isiPost', isiPost.value)
    Cookies.set('isiPost', isiPost.value, { expires: expiredAt })

    // Tampilkan notifikasi
    showNotif.value = true

    // Sembunyikan dan kembali ke halaman utama
    setTimeout(() => {
      showNotif.value = false
      kembali()
    }, 2000)
  } else {
    alert('Isi postingan tidak boleh kosong!')
  }
}
</script>

<style scoped>
.posting {
  background-color: #fff;
  color: #000;
  padding: 20px;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  min-height: 100vh;
  max-width: 600px;
  margin: 0 auto;
  position: relative;
  box-sizing: border-box;
}

.topBar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.close {
  font-size: 24px;
  cursor: pointer;
  color: black;
}

.postButton {
  background-color: #1d9bf0;
  color: white;
  border: none;
  border-radius: 20px;
  padding: 6px 16px;
  font-weight: bold;
  cursor: pointer;
}

.inputText {
  display: flex;
  justify-content: center;
  margin-top: 150px;
}

.input {
  width: 100%;
  background-color: transparent;
  border: none;
  color: black;
  font-size: 18px;
  resize: none;
  outline: none;
  text-align: center;
}

.icon1 {
  display: flex;
  gap: 15px;
  margin-top: 200px;
  padding-left: 5px;
}

.icon {
  width: 26px;
  height: 26px;
  object-fit: contain;
  cursor: pointer;
}

.iconn {
  width: 30px;
  height: 30px;
  object-fit: contain;
  cursor: pointer;
  margin-left: 500px;
  position: absolute;
  display: flex;
  margin-top: -20px;
}

/* Notifikasi melayang */
.notif {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #1d9bf0;
  color: white;
  padding: 15px 25px;
  border-radius: 10px;
  font-weight: bold;
  box-shadow: 0 5px 20px rgba(0, 0, 0, 0.2);
  z-index: 9999;
  animation: fade 0.4s ease-in-out;
}

@keyframes fade {
  from {
    opacity: 0;
    transform: translate(-50%, -40%);
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%);
  }
}
</style>