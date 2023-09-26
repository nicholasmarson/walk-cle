<template>
  <div>
    <map-with-search />
  </div>
</template>

<script>
import MapWithSearch from '../components/MapWithSearch.vue';

export default {
  components: {
    MapWithSearch
  },
};
</script>
<style>
.map-container {
  width: 100%;
  height: 100vh;
}

#map {
  width: 100%;
  height: 90%;
}
</style>