<template>
  <div class="container">
    <nav id="footer">
      <ul>
        <li>
          <router-link v-bind:to="{ name: 'home' }" >
            <img src="../assets/Footer-Images/home.png" id="home"/>
          </router-link>
        </li>
        <li>
          <a href="#" id="search-link">
            <img src="../assets/Footer-Images/search.png" id="search" />
          </a>
        </li>
        <li>
          <a href="#" id="marker-link">
            <img src="../assets/Footer-Images/marker.png" id="marker" />
          </a>
        </li>
        <li>
          <a href="#" id="trophy-link">
            <img src="../assets/Footer-Images/trophy.png" id="trophy" />
          </a>
        </li>
        <li>
          <a href="#" id="menu-link">
            <img src="../assets/Footer-Images/menu-burger.png" id="menu" />
          </a>
        </li>
      </ul>
    </nav>
    

    <!-- Home
    Search
    Submit
    Rewards
    Profile

    Exact active view class
    Be on every one of these views
    Stick in the same spot no matter where you're scrolled -->

  </div>
</template>

<script>
export default {
  name: "footer"

}
</script>

<style scoped>
nav ul {
    margin: 0;
    padding: 20px 0 0 0;
    display: flex;
    justify-content: space-between;
}

nav ul li {
    list-style: none;
    width: 20%;
    margin: 0 5px;
    border-radius: 5px;
    text-align: center;
    line-height: 50px;
    text-decoration: none;
}

.container {
    display: grid;
    grid-template-columns: 1fr;
    gap: 1%;
    grid-template-areas:
        "footer";
    flex-grow: 1;
}

#footer {
    grid-area: footer;
}

img {
  width: 24px;
  height: 24px;
}
</style>