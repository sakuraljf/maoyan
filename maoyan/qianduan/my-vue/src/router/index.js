import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login.vue"
import Resiger from '../components/Resiger.vue'
import index from "../components/index.vue"
import detail from "../components/detail.vue"
import filmLocation from "../components/filmLocation.vue"
import Buy from '../components/Buy.vue'
import Ding from '../components/Ding.vue'
import message from '../components/message.vue'
import ping from '../components/ping.vue'
import allfilm from '../components/allfilm.vue'
Vue.use(Router)

export default new Router({
mode:'history',
routes:[
	{
		path:'',
		//重定向，页面的path为'',的时候重定向
		redirect:'/allfilm'
	},
	{
		path:"/login",
		component:Login
	},{
		path:"/resiger",
		component:Resiger
	},{
		path:'/index',
		component:index
	},{
		path:'/detail',
		component:detail
	},{
		path:'/filmLocation',
		component:filmLocation
	},{
		path:'/Buy',
		component:Buy
	},{
		path:'/Ding',
		component:Ding
	},{
		path:'/message',
		component:message
	},{
		path:'/ping',
		component:ping
	},{
		path:'/allfilm',
		component:allfilm
	}
]
})
