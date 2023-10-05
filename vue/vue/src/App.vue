<template>
  <div id="app">
    <div id="nav"> 
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"></router-link>
      <router-link v-bind:to="{ name: 'home' }"></router-link>
     
    </div>
    <router-view />
  </div>
</template>

<script>

export default {
  name: 'App',
};
</script>

<style>

@import url("https://fonts.googleapis.com/css2?family=Urbanist:wght@300;400&display=swap");

#app {
  font-family: "Urbanist", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
  /* DELETED MAX-WIDTH, WAS MESSING WITH FOOTER */
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}

.btn {
  font-family: "Urbanist", sans-serif;
  display: inline-block;
  background: #407F7F;
  color: #fff;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 5px;
  cursor: pointer;
  text-decoration: none;
  font-size: 15px;
  width: 80%;
}

body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.mapbox-directions-route-summary {
  font-family: "Urbanist", sans-serif;
}

li.mapbox-directions-step {
  font-family: "Urbanist", sans-serif;
}

.mapbox-directions-profile {
  font-family: "Urbanist", sans-serif;
}

div.mapboxgl-control-container {
  font-family: "Urbanist", sans-serif;
}

.mapboxgl-ctrl-directions {
  border-radius: 5px; /* Add rounded corners */
  /* Add other custom styles as needed */
  min-width: 81% !important; 
  min-height: 50px !important; 
  margin-bottom: 0 !important;
  margin-left: 5px !important;
  margin-top: 5px !important;
}

#mapbox-directions-origin-input > div:nth-child(1) > input:nth-child(2) {
  font-family: "Urbanist", sans-serif;
}

#mapbox-directions-destination-input > div:nth-child(1) > input:nth-child(2) {
  font-family: "Urbanist", sans-serif;
}

.mapboxgl-ctrl-logo {
  width: 1px !important;
}

.mapbox-directions-component {
  min-height: 1px !important;
}
</style>
