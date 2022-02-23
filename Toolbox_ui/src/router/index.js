// 该文件专门用于创建整个应用的路由器
import VueRouter from 'vue-router'
//引入组件
import LoginFrom from '../components/LoginFrom'
import Home from '../components/Home'
import Code from '../components/Code'
import Color from '../components/Color'
import User from '../components/User'
import MyImg from '../components/MyImg'
import Yijian from '../components/Yijian'

//创建并暴露一个路由器
export default new VueRouter({
	routes:[
		{
			//默认打开的路径
			path: '/',
			redirect: '/home'
		},
		{
			path:'/login',
			component:LoginFrom
		},
        {
			path:'/home',
			component:Home
		},
        {
			path:'/code',
			component:Code
		},
        {
			path:'/color',
			component:Color
		},
		{
			path:'/user',
			component:User
		},
		{
			path:'/myimg',
			component:MyImg
		},
		{
			path:'/yijian',
			component:Yijian
		},
	]
})
