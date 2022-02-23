<template>
	<main>
		<el-form ref="form" :model="form" label-width="80px">
			<el-form-item label="您的称呼">
				<el-input v-model="form.name"></el-input>
			</el-form-item>
			<el-form-item label="联系方式">
				<el-input placeholder="邮箱地址" v-model="form.email">
					<template slot="append">.com</template>
				</el-input>
			</el-form-item>
			<el-form-item label="满意程度">
				<el-rate
						style="transform: translateY(10px)"
						v-model="form.num"
						show-text
						:colors="['#99A9BF', '#F7BA2A', '#FF9900']">
				</el-rate>
			</el-form-item>
			<el-form-item label="您的建议">
				<el-input placeholder="感谢您的反馈和使用" type="textarea" v-model="form.descc"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="onSubmit">发送</el-button>
			</el-form-item>
		</el-form>
	</main>
</template>

<script>
	export default {
		name: "Yijian",
		data() {
			return {
				form: {
					name: '',
					email: '',
					num: null,
					descc: ''
				},
			}
		},
		methods: {
			onSubmit() {
				// console.log(this.form)
				this.axios.post(this.$store.state.URL + "/sumbitsuggestions", this.form).then((response) => {
					if (response.data == 1) {
						this.$message({
							message: '发送成功，感谢您的反馈。',
							type: 'success'
						});
						this.form = {
							name: '',
							email: '',
							num: null,
							descc: ''
						}
					}
				}).catch((error) => {
					this.$message('服务器错误，请联系管理员')
					console.log("服务器错误，请联系管理员" + error);
					this.fullscreenLoading = false;
				})
			}
		}
	}
</script>

<style scoped>
	main{
		width: 50vw;
		margin: 0 auto;
		margin-top: 50px;
	}
</style>