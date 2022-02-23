<template>
	<el-menu :default-active="$route.path" class="el-menu-demo nav" mode="horizontal" :router="true">
		<el-image style="width: 120px; height: 50px" :src="imgUrl"></el-image>
		<el-menu-item index="/home">图床</el-menu-item>
		<el-menu-item index="/code">代码粘贴板</el-menu-item>
		<el-menu-item index="/color">颜色板</el-menu-item>
		<el-submenu index="/4">
			<template slot="title">我的工作台&nbsp;&nbsp;<el-avatar shape="circle" size="small" :src="this.$store.state.user.touxiang" class="elavatar">
				{{ $store.state.user.islogin ? '头像' : '登录' }}
			</el-avatar>
			</template>
			<el-menu-item index="/user">个人资料</el-menu-item>
			<el-menu-item index="/myimg">我的图床</el-menu-item>
			<el-menu-item index="/yijian">意见反馈</el-menu-item>
			<el-menu-item index="/login" v-if="!$store.state.user.islogin">
				<el-tag type="success">登录</el-tag>
			</el-menu-item>
			<el-menu-item v-if="$store.state.user.islogin" @click="logout">
				<el-tag type="danger">退出登录</el-tag>
			</el-menu-item>
		</el-submenu>
		<el-dialog title="提示" :visible.sync="centerDialogVisible" width="30%" center>
			<span>确定要退出登录吗？</span>
			<span slot="footer" class="dialog-footer">
				<el-button @click="centerDialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="tuichu">确 定</el-button></span>
		</el-dialog>
	</el-menu>
</template>

<script>
export default {
	name: "Navigation",
	data() {
		return {
			activeIndex: '/home',
			centerDialogVisible: false,
			imgUrl: require("../assets/logo.png")
		};
	},
	methods: {
		// handleSelect(key, keyPath) {
		// 	console.log("页面跳转到了:"+key, keyPath);
		// },
		logout() {
			//退出登录
			console.log("退出登录")
			this.centerDialogVisible = true
		},
		tuichu() {
			//确认退出登录
			console.log(JSON.parse(sessionStorage.getItem("user")))
			sessionStorage.removeItem("user")

			//更改用户登录状态
			this.$store.state.user.islogin = false
			this.$store.state.user.name = ""
			this.$store.state.user.sex = ""
			this.$store.state.user.age = ""
			this.$store.state.user.jieshao = ""
			this.$store.state.user.touxiang = ""
			this.$store.state.user.tag = ""

			this.centerDialogVisible = false
			this.$router.push({path: '/home'})
		},
	},
}
</script>

<style scoped>
.nav {
    width: 100%;
    font-size: 20px;
    display: flex;
    justify-content: space-between;
    border: none;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
}

.elavatar {
    font-size: 10px;
}
</style>