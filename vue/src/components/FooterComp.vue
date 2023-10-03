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
          <router-link v-bind:to="{name: 'places'}">
            <img src="../assets/Footer-Images/marker.png" id="marker" />
          </router-link>
        </li>
        <li>
          <img src="../assets/logo/small.png" id="logo" />
        </li>
        <li>
          <router-link v-bind:to="{ name: 'rewards' }">
            <img src="../assets/Footer-Images/trophy.png" id="trophy" />
          </router-link>
        </li>
        <li>
          <a id="menu-link" @click.prevent="toggleDropdown">
            <img src="../assets/Footer-Images/menu-burger.png" id="menu" />
            <ul v-if="isDropdownOpen" class="dropdown-menu">
              <a href="#" class="dropdown-option">Profile</a>
              <a href="#" class="dropdown-option">Settings</a>
              <router-link :to="{ name: 'google' }" class="dropdown-option">
                <a href="#" class="dropdown-option">Google</a>
              </router-link>
              <router-link :to="{ name: 'AddPoint' }" class="dropdown-option">
                <a href="#" class="dropdown-option">Add a Point</a>
              </router-link>
              <router-link :to="{ name: 'logout' }" class="dropdown-option">
                <a href="#" class="dropdown-option">Logout</a>
              </router-link>
            </ul>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: "footer-comp",
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
img {
  width: 30px;
  height: auto;
}

.container {
  width: 100vw;
  background-color: #92b9c5;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
}

nav ul {
  list-style: none;
  margin: 5;
  padding: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100vw;
}

nav ul li {
  font-size: 16px;
  text-align: center;
  flex: 1;
  margin: 0, 10px;
}

.dropdown-menu {
  display: flex;
  flex-direction: column;
  list-style: none;
  padding: 0;
  margin: 0;
  position: absolute;
  background-color: rgba(146, 185, 197, 0.75);
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  left: 0;
  right: 0;
  margin-top: -260px;
}

.dropdown-option {
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.dropdown-option:hover {
  background-color: #f0f0f0;
  width: 100%;
}

.dropdown-option a {
  text-decoration: none;
  color: #333;
  font-weight: normal;
}

.dropdown-option a:hover {
  color: #555;
  background-color: #f0f0f0;
}
</style>