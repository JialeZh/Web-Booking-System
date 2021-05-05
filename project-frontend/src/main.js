import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import enLocale from 'element-ui/lib/locale/lang/en.js'

import moment from 'moment'
import Vuetify from 'vuetify'

import vuetify from '@/plugins/vuetify'


Vue.use(Vuetify)
Vue.prototype.$moment = moment

import './assets/css/global.css'

import axios from 'axios'
import ElementUI from 'element-ui'
Vue.prototype.$http = axios
axios.defaults.baseURL = "http://localhost:9000/api"

Vue.use(ElementUI, { enLocale })

Vue.config.productionTip = false



new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
