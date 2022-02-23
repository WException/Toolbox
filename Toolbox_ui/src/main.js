import Vue from 'vue'
import 'element-ui/lib/theme-chalk/index.css';

import App from './App.vue'
import ElementUI from 'element-ui';
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueRouter from 'vue-router'
import router from './router' //路由器
import store from './store' //全局数据vuex

Vue.use(VueRouter)
Vue.use(VueAxios, axios)
Vue.use(ElementUI)

Vue.config.productionTip = false
new Vue({
  el:"#app",
  render: h => h(App),
  router:router,
  store:store
})
