<template>
  <div class="container">
      <NewMapBox id="new-map-box"/>
      <FooterComp id="footer-comp"/>
  </div>
</template>

<script>
import NewMapBox from '../components/NewMapBox.vue';
import FooterComp from '../components/FooterComp.vue';

export default {
  components: {
    NewMapBox,
    FooterComp
  },
};
</script>

<style scoped>


.container {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
}

#new-map-box {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 90%;
  width: 100%;
  background: #92b9c5;
}

#footer-comp {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 10%;
  width: 100%;
}
</style>