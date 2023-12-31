import App from './App'
import env from './utils/env';
// import router from './router'
import Vue from 'vue'
import router from './router'  
import RouterMount from 'uni-simple-router'
// 引入 uni-simple-router
// 注册路由
// main.js
import Mylink from './node_modules/uni-simple-router/dist/link.vue'     
Vue.component('my-link',Mylink)
Vue.prototype.$onBackPressed = function() {
  // 处理返回按钮逻辑
  // 例如：关闭当前页面或返回上一页
  uni.navigateBack({
    delta: 1
  });
};
// #ifndef VUE3

Vue.use(router)
Vue.prototype.baseUrl = env.baseUrl
Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app,
	router
  }
}
// #endif