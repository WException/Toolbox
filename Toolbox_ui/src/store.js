import Vue from "vue";
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		//用户登录状态 true：已登录
		user: {
			islogin: false,
			name: "",
			touxiang: "",
			age:"",
			sex:"",
			tag:"",
			jieshao:"",
		},
		// user:JSON.parse(sessionStorage.getItem("user")),
		//请求地址

		URL:"https://zhihuilvyou.top:9998",
		// URL:"https://localhost:9998",
		//图片地址前缀

		HEADURL:"https://zhihuilvyou.top/Toolbox_img/"
		// HEADURL:"http://localhost:8080/Toolbox_Img/"
	}
})