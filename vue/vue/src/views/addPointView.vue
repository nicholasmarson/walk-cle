<template>
  <div class="container">
      <h1 id="title">Add a New Point</h1>
      <LocationForm id="location-form"/>
      <FooterComp id="footer-comp"/>
    </div>
</template>

<script>
import LocationForm from "../components/locationForm.vue"
import FooterComp from '../components/FooterComp.vue';
export default {
    components: {
        LocationForm,
        FooterComp
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
}

#location-form {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80%;
  width: 100%;
}

#footer-comp {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 10%;
  width: 100%;
}
</style>