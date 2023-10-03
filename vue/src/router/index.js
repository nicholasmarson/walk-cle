import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Google from '../views/Google.vue'
import NewMapBoxView from '../views/NewMapBoxView.vue'
import Rewards from '../views/Rewards.vue'
import PlacesList from '../components/PlacesList.vue'
import addlayers from '../views/addlayers.vue'
import AddPointView from '../views/addPointView.vue'
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/google",
      name:"google",
      component: Google,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/newmapbox",
      name: "new-mapbox",
      component: NewMapBoxView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/rewards",
      name: "rewards",
      component: Rewards,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/places",
      name: "places",
      component: PlacesList,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/add",
      name: "layers",
      component: addlayers,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/addPoint",
      name: "AddPoint",
      component: AddPointView,
      meta: {
        requiresAuth: true
      }
    }
    
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
