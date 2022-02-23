<template name="el-fade-in-linear">
	<div class="home_body">
		<el-upload
			class="upload-demo upimg"

			:before-upload="beforeupload"
			drag
			:data="{name:this.$store.state.user.name}"
			:action="this.$store.state.URL+'/upload'"
			multiple>
			<i class="el-icon-upload"></i>
			<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
		</el-upload>
	</div>
</template>

<script>
export default {
	name: "Home",
	methods: {
		// onsuccess(res, fileList, index) {
		// 	//文件上传成功钩子
		// 	console.log("url", res)
		// 	// alert(res)
		// 	console.log(fileList)
		// 	console.log(index)
		// },
		beforeupload(file) {console.log(file.type)
			if (this.$store.state.user.name != "") {
				return true
			} else {
				this.$message('请先登录后再上传')
				return false
			}

			//文件上传前的钩子(返回false说明取消上传文件)
			//限制用户登陆后才能进行文件上传
			const isJPG = file.type === 'image/jpeg' || 'image/png' || 'image/gif';
			const isLt2M = file.size / 1024 / 1024 < 5;

			if (!isJPG) {
				this.$message.error('上传头像图片只能是 JPG 格式!');
			}
			if (!isLt2M) {
				this.$message.error('上传头像图片大小不能超过 5MB!');
			}
			return isJPG && isLt2M;

		}
	}
}
</script>

<style scoped>
.home_body {
	box-sizing: border-box;
	width: 100vw;
	height: 80vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>