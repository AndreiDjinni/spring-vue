import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap'
import 'babel-polyfill'

import 'es6-promise/auto'

Vue.use(BootstrapVue);

import axios from "axios"

axios.defaults.withCredentials = true

// Setting up Axios on Vue Instance, for use via this.$axios
Vue.prototype.$axios = axios.create({
  baseURL: '/api'
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
