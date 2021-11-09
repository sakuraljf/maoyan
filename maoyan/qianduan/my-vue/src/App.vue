<template>
<div id="app">
<el-menu
  :default-active="$route.path"
  class="el-menu-demo"
  mode="horizontal"
	router
	unique-opened
  @select="handleSelect"
  background-color="#545c64"
  text-color="#fff"
  active-text-color="#ffd04b">
	<el-menu-item index="/index"  ><router-link to="/index">首页</router-link></el-menu-item>
  <el-menu-item index="/login"  ><router-link to="/login">登录</router-link></el-menu-item>
	<el-menu-item index="/resiger" ><router-link to="/resiger">注册</router-link></el-menu-item>
  <el-menu-item index="/message" >我的订单</el-menu-item>
</el-menu>
<router-view></router-view>
 </div>
</template>

<script>
import Login from "./components/Login.vue";
import Resiger from "./components/Resiger.vue"
export default {
  name: 'App',
	components:{
		Login,
		Resiger
	},
	data() {
      return {
        activeIndex2: 'index'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
}
</script>

<style>
a{
	text-decoration: none;
}
</style>
