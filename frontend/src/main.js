import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//Bootstrap framework
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap'
import 'open-iconic/font/css/open-iconic-bootstrap.css'
import 'babel-polyfill'

import 'es6-promise/auto'

//Font awesome
import 'vue-awesome/icons'
import Icon from 'vue-awesome/components/Icon'

import axios from "axios"

Vue.component('icon', Icon);

Vue.use(BootstrapVue);

axios.defaults.withCredentials = true;

// Setting up Axios on Vue Instance, for use via this.$axios
Vue.prototype.$axios = axios.create({
  baseURL: '/api'
});

axios.interceptors.response.use(response => {
      return Promise.resolve(response)
    },
    error => {
      if (error.response.status === 401) {
        console.log('Unauthorized, logging out ...');
        store.dispatch('signOut')
        router.replace('/sign-in')
        return Promise.reject(error)
      } else {
        return Promise.reject(error.response);
      }
    })

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
