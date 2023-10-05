<template>
    <div class="container">
      <h1 id="title">Locations</h1>
      <LocationsList id="locations-list"/>
      <FooterComp id="footer-comp"/>
    </div>
  </template>
  
  <script>
  import FooterComp from '../components/FooterComp.vue'
  import LocationsList from '../components/LocationsList.vue'
  
  export default {
      components: {
          FooterComp,
          LocationsList
      }
  }
  </script>
  
  
  <style scoped>
  .container {
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100%;
  }
  
  #title {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 10%;
    width: 100%;
    margin-bottom: 0;
    margin-top: 30px;
    text-decoration: underline;
   
  }
  
  #locations-list {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80%;
    width: 100%;
    margin-bottom: 10%;
    overflow: auto;
  }
  
  #footer-comp {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 10%;
    width: 100%;
  }
  </style>