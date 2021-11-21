// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'babel-polyfill'

Vue.config.productionTip = false


//引入jquery
import $ from 'jquery';
Vue.prototype.$ = $;

// MD5加密
import md5 from 'js-md5';
Vue.prototype.$md5 = md5;

//将json数据格式转换为url拼接格式
import Qs from 'qs';
Vue.prototype.$qs = Qs;

// import myCharts from './comm/js/myCharts.js'
// Vue.use(myCharts)
//无缝滚动
import scroll from 'vue-seamless-scroll'
Vue.use(scroll)

//兼容ie
import 'babel-polyfill'

//element
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
//git提交测试
//axios
import axios from '../node_modules/axios';
Vue.prototype.$ajax = axios;
// axios.defaults.baseURL = "http://local.wuliantiandi.com:8888";
axios.defaults.baseURL = "http://hrt.chinagrp.org.cn";
// axios.defaults.baseURL = "/api";

//复制
import VueClipboards from 'vue-clipboard2'
Vue.use(VueClipboards);


//加载loading样式
let loading        //定义loading变量
function startLoading() {    //使用Element loading-start 方法
    loading = ElementUI.Loading.service({
        lock: true,
        text: 'Loading……',
        background: 'rgba(0, 0, 0, 0.7)'
    })
}
function endLoading() {    //使用Element loading-close 方法
    loading.close()
}
//那么 showFullScreenLoading() tryHideFullScreenLoading() 要干的事儿就是将同一时刻的请求合并。
//声明一个变量 needLoadingRequestCount，每次调用showFullScreenLoading方法 needLoadingRequestCount + 1。
//调用tryHideFullScreenLoading()方法，needLoadingRequestCount - 1。needLoadingRequestCount为 0 时，结束 loading。
let needLoadingRequestCount = 0
export function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading()
    }
    needLoadingRequestCount++
}

export function tryHideFullScreenLoading() {
    if (needLoadingRequestCount <= 0) return
    needLoadingRequestCount--
    if (needLoadingRequestCount === 0) {
        endLoading()
    }
}


// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  console.log(config.url)
  if(config.url != '/asset/devicegoods/realTimeData' && config.url != '/api/asset/goods/realTimeData'){
    //开启加载
    showFullScreenLoading()
  }
  var token = sessionStorage.getItem('token');
  //把登陆页接口和验证码接口过滤掉
  if(config.url == '/base/user/login'){
      //如果是登陆页和验证码图片则什么都不操作
  }else{
       if (token) { // 每次发送请求之前判断是否存在token，如果存在，则统一在http请求的header都加上token，不用每次请求都手动添加了
         	config.headers['token'] =  token;
       }
  }
  return config;
}, function (error) {
   console.log("请求错误",error)
  // 对请求错误做些什么
  return Promise.reject(error);
});


// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  tryHideFullScreenLoading()
  if(response.data.message){
    if(response.data.status == '401'){
      ElementUI.Message({message: '登录已失效，请重新登录！', type: 'error'});
      router.replace('/');
      return;
    }else if(response.data.status == '260'){
      ElementUI.Message({message: response.data.message, type: 'success'});
    }else{
      ElementUI.Message({message: response.data.message, type: 'error'});
    }
  }
  return response;
}, function (error) {
    console.log("响应错误",error.response);
  return Promise.reject(error);
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
