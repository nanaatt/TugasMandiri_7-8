<template>
    <div id="profile">
      <img src="/latar.jpg" alt="gambar" id="latarProf" />
      <section class="setProfile">
        <img src="/profille.jpg" alt="profile" id="profile1" />
        <button id="sett" @click="cobaTombol">Edit Profile</button>
      </section>
    </div>
  </template>
  
  <script>
  export default {
    name: "BagAtas",
    methods: {
      cobaTombol() {
        alert("ini mencobanya");
      }
    }
  };
  </script>
  
  <style scoped>
  #profile {
    height: 330px;
  }
  
  #latarProf {
    width: 550px;
    height: 200px;
    margin-top: 50px;
  }
  
  #profile1 {
    width: 100px;
    height: 100px;
    margin-left: 15px;
    margin-top: -55px;
    border-radius: 80px;
    outline: 5px solid #adbabf;
  }
  
  .setProfile {
    display: flex;
    justify-content: center;
  }
  
  #sett {
    width: 120px;
    height: 20px;
    margin-left: 280px;
    margin-top: 8px;
    border-radius: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 16px;
    background-color: white;
    border: 1px solid #adbabf;
    color: black;
    cursor: pointer;
    transition: all 0.3s ease;
  }
  
  #sett:hover {
    font-size: 20px;
    color: black;
  }
  </style>  