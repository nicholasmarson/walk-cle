<template>
    <div class="container">
      <h1 id="title">Check-In History</h1>
      <CheckinHistoryComp id="checkin-history-comp"/>
      <FooterComp id="footer-comp"/>
    </div>
  </template>
  
  <script>
  import FooterComp from '../components/FooterComp.vue'
  import CheckinHistoryComp from '../components/CheckinHistoryComp.vue'
  
  export default {
      components: {
          FooterComp,
          CheckinHistoryComp
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
  
  #checkin-history-comp {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80%;
    width: 100%;
    overflow: auto;
    margin-bottom: 35px;
  }
  
  #footer-comp {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 10%;
    width: 100%;
  }
  </style>