<template>
  <div class="container">
    <nav id="footer">
      <ul>
        <li>
          <router-link v-bind:to="{ name: 'home' }">
            <img src="../assets/Footer-Images/home.png" id="home" />
          </router-link>
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
          <div id="menu-link" @click.prevent="toggleDropdown">
            <img src="../assets/Footer-Images/menu-burger.png" id="menu" />
            <ul v-if="isDropdownOpen" class="dropdown-menu">
              <div class="dropdown-option"><a href="#">Profile</a></div>
              <div class="dropdown-option"><a href="#">Settings</a></div>
              <router-link :to="{ name: 'logout' }">
                <div class="dropdown-option">Logout</div>
              </router-link>
            </ul>
          </div>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: "footer",
  data() {
    return {
      isDropdownOpen: false,
    };
  },
  methods: {
    toggleDropdown() {
      this.isDropdownOpen = !this.isDropdownOpen;
    },
  },
};
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
  grid-template-areas: "footer";
  flex-grow: 1;
  position: relative;
}

#footer {
  grid-area: footer;
}

img {
  width: 24px;
  height: 24px;
}

.dropdown-menu {
  display: flex;
  flex-direction: column;
  list-style: none;
  padding: 0;
  margin: 0;
  position: absolute;
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 1;
  top: auto;
  bottom: auto;
  left: 0;
  right: 0;
  margin-top: -259px;
}

.dropdown-option {
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.dropdown-option:hover {
  background-color: #f0f0f0;
}

.dropdown-option a {
  text-decoration: none;
  color: #333;
}

.dropdown-option a:hover {
  color: #555;
}
</style>