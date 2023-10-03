<template>
  <div class="container">
    <h1 id="title">CLE-WARDS</h1>
    <RewardsComp id="rewards-comp"/>
    <FooterComp id="footer-comp"/>
  </div>
</template>

<script>
import FooterComp from '../components/FooterComp.vue'
import RewardsComp from '../components/RewardsComp.vue'

export default {
    components: {
        FooterComp,
        RewardsComp
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

#rewards-comp {
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