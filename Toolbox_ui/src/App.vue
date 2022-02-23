<template>
	<div id="app">
		<Navigation/>
		<router-view></router-view>
	</div>
</template>

<script>
// import LoginFrom from './components/LoginFrom.vue'
import Navigation from './components/Navigation.vue'

export default {
	name: 'App',
	components: {
		// LoginFrom,
		Navigation,
	},
	mounted() {
		setTimeout(() => {
			window.L2Dwidget.init({
				pluginRootPath: 'live2dw/',
				pluginJsPath: 'lib/',
				pluginModelPath: 'live2d-widget-model-z16/assets/',
				tagMode: false,
				debug: false,
				model: { jsonPath: '../live2dw/live2d-widget-model-shizuku/assets/shizuku.model.json' },
				// model: {jsonPath: '../live2dw/live2d-widget-model-haru_2/assets/haru02.model.json'},
				display: {position: 'left', width: 200, height: 300},
				mobile: {show: true},
				log: false
			})
		}, 1000)
	},
	beforeCreate() {
		console.log("%cToolbox工具箱，启动!", "color:#00b894;font-size:30px")
		// console.log("生命周期"+this.$store.state.user.tag.split(","))
		if (sessionStorage.getItem("user") != null) {
			var user = JSON.parse(sessionStorage.getItem("user"))
			if (user.name != "") {
				user["islogin"] = true
				this.$store.state.user = user
			}
		}
	}
}
</script>

<style>
* {
    margin: 0;
    padding: 0;
}
</style>
