<template>
  <div class="container">
    <div class="map-container">
      <google-api/>
    </div>
    <div class="footer-container">
      <FooterComp />
    </div>
  </div>
</template>

<script>
import googleApi from '../components/googleApi.vue';
import FooterComp from '../components/FooterComp.vue';

export default {
  components: {
    googleApi,
    FooterComp
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100%; /* Ensure the container takes at least the full viewport height */
  width: 100%;
}

.map-container {
  display: flex;
  width: 100%;
}

.footer-container {
  background-color: #92b9c5;
  /* padding: 0 200px; Add padding as needed */
  text-align: center; /* Center the content horizontally */
  width: 100%;
  height: 10%;
}
</style>