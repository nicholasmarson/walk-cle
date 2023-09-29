<template>
  <div class="container">
      <h1 id="title">CLE-WARDS</h1>
      <div class="images">
      <img src="../assets/trophies/bar.jpg" id="bar" />
      <img src="../assets/trophies/defender.jpg" id="defender" />
      <img src="../assets/trophies/sport.jpg" id="sport" />
      <img src="../assets/trophies/tree.jpg" id="tree" />
      </div>
      <div class="footer-container">
          <FooterComp />
      </div>
  </div>
</template>
<script>
import FooterComp from '../components/FooterComp.vue'
export default {
    components: {
        FooterComp
    }
}
</script>
<style>
.footer-container {
  grid-area: footer;
}
#title {
    grid-area: title;
    align-self: start;
}
.images img {
    width:200px;
    padding: 250px;
}
.images {
    display: flex;
    grid-area: images;
}
.container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "title"
    "images"
    "footer";
  justify-items: center;
  align-items: center;
  height: 100vh;
}
</style>