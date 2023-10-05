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
</style>
