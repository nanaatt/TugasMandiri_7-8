<script>
export default {
  name: "BackProfile"
};
</script>

<template>
  <div class="backProfile">
    <img id="back" src="/back.png" alt="kembali" />
    <div class="head">
      <h2 class="nama">Careen Emilza</h2>
      <p class="ketPost">0 Postingan</p>
    </div>
  </div>
</template>

<style scoped>
.backProfile {
  background: rgba(255, 255, 255, 0.2);
  backdrop-filter: blur(10px);
  width: 550px;
  height: 50px;
  display: flex;
  align-items: center;
  position: fixed;
  z-index: 11;
  left: 50%;
  transform: translateX(-50%);
  padding: 0 10px;
}

#back {
  width: 25px;
  height: 25px;
  margin-right: 15px;
}

.head {
  display: flex;
  flex-direction: column;
  justify-content: center;
  line-height: 1.2;
}

.nama {
  color: black;
  font-weight: bold;
  font-size: 16px;
  margin: 0;
}

.ketPost {
  color: #d9d9d9;
  font-size: 14px;
  margin: 2px 0 0 0;
}
</style>