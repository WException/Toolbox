<template>
	<div class="user_body">

		<el-card class="box-card" shadow="hover">
			<div slot="header" class="clearfix">
				<span>个人资料</span>
				<el-button style="float: right; padding: 3px 0" type="text" @click="xiugaiFun">{{
						xiugai ? "取消" : "修改"
					}}
				</el-button>
			</div>

			<main class="card-main">
				<div class="touxiang">
					<el-image
							style="width: 150px; height: 150px;"
							:src="this.$store.state.user.touxiang"
							fit="fit"></el-image>
					<el-upload
							class="upload-demo"
							:action="this.$store.state.URL+'/headimg'"
							:show-file-list="false"
							:data="{name:this.$store.state.user.name}"
							:limit="10"
							:on-success="success">
						<el-button size="small">更换头像</el-button>
					</el-upload>
				</div>

				<template class="margin-top">
					<el-descriptions :column="1">
						<el-descriptions-item label="用户名">
							<span>{{ this.$store.state.user.name }}</span>
						</el-descriptions-item>
						<el-descriptions-item label="性别">
							<el-input v-if="xiugai" v-model="user.sex"/>
							<span v-if="!xiugai">{{ this.$store.state.user.sex }}</span>
						</el-descriptions-item>
						<el-descriptions-item label="年龄">
							<el-input v-if="xiugai" v-model="user.age" :maxlength="2"/>
							<span v-if="!xiugai">{{ this.$store.state.user.age }}</span>
						</el-descriptions-item>
						<el-descriptions-item label="兴趣标签">
							<el-tag
									:key="tag"
									type="success"
									size="medium"
									v-for="tag in user.tag"
									:closable="xiugai"
									:disable-transitions="false"
									@close="handleClose(tag)">
								{{ tag }}
							</el-tag>
							<span v-if="xiugai">
                <el-input class="input-new-tag" v-if="inputVisible" v-model="inputValue" ref="saveTagInput" size="small" @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm">
                </el-input>
                <el-button v-else class="button-new-tag" size="small" @click="showInput">+添加</el-button>
              </span>
						</el-descriptions-item>
						<el-descriptions-item label="个人介绍">
							<el-input v-if="xiugai" v-model="user.jieshao" type="textarea" :rows="2"/>
							<span v-if="!xiugai">{{ this.$store.state.user.jieshao }}</span>
						</el-descriptions-item>
					</el-descriptions>
					<el-button v-if="xiugai" type="success" @click="xiugaiSub">提交</el-button>
				</template>
			</main>

		</el-card>
	</div>
</template>

<script>
export default {
	name: "User",
	data() {
		return {
			xiugai: false,
			user: {
				name: this.$store.state.user.name,
				sex: this.$store.state.user.sex,
				age: this.$store.state.user.age,
				jieshao: this.$store.state.user.jieshao,
				tag: this.$store.state.user.tag.split(","),
			},
			inputVisible: false,
			inputValue: ''
		}
	},
	methods: {
		//上传头像成功事件
		success(event) {
			console.log(event);
			console.log(JSON.parse(sessionStorage.getItem("user")))
			var userstorage = JSON.parse(sessionStorage.getItem("user"))
			userstorage["touxiang"] = event
			console.log(userstorage)
			this.$store.state.user.touxiang = event
			sessionStorage.setItem("user", JSON.stringify(userstorage))
		},
		// 修改个人信息按钮
		xiugaiFun() {
			if (this.$store.state.user.name != "") {
				this.xiugai = !this.xiugai
			} else {
				this.$message('请先登录')
			}

		},
		//修改个人信息，提交按钮
		xiugaiSub() {
			let xinxidata = {
				name: this.user.name,
				sex: this.user.sex,
				age: this.user.age,
				jieshao: this.user.jieshao,
				tag: this.user.tag.toString(),
			}
			this.axios.post(this.$store.state.URL + "/updatauser", xinxidata).then((response) => {
				let resCode = response.data
				// 浏览器中的用户状态,向浏览器存入缓存
				var user = {
					name: resCode.user.name,
					sex: resCode.user.sex,
					age: resCode.user.age,
					jieshao: resCode.user.jieshao,
					touxiang: this.$store.state.HEADURL + resCode.user.touxiang,
					tag: resCode.user.tag
				}
				sessionStorage.setItem("user", JSON.stringify(user))
				//更改用户登录状态
				this.$store.state.user.islogin = true
				this.$store.state.user.name = resCode.user.name
				this.$store.state.user.sex = resCode.user.sex
				this.$store.state.user.age = resCode.user.age
				this.$store.state.user.jieshao = resCode.user.jieshao
				this.$store.state.user.touxiang = this.$store.state.HEADURL + resCode.user.touxiang
				this.$store.state.user.tag = resCode.user.tag
				this.$message({
					message: '修改成功！',
					type: 'success'
				});
				this.xiugai = !this.xiugai
			}).catch((error) => {
				this.$message('服务器错误，请联系管理员')
				console.log("服务器错误，请联系管理员" + error);
				this.fullscreenLoading = false;
			})
		},
		//删除标签
		handleClose(tag) {
			console.log(tag)
			this.user.tag.splice(this.user.tag.indexOf(tag), 1)
		},
		//添加标签按钮（点击后可参加标签）
		showInput() {
			this.inputVisible = true;
			this.$nextTick(() => {
				this.$refs.saveTagInput.$refs.input.focus();
			});
		},
		//添加标签
		handleInputConfirm() {
			let inputValue = this.inputValue;
			if (inputValue) {
				this.user.tag.push(inputValue);
			}
			this.inputVisible = false;
			this.inputValue = '';
		}
	}
}
</script>

<style scoped>
.user_body {
    width: 100vw;
    height: 80vh;
}

.box-card {
    width: 70vw;
    margin: 50px auto;
}

.margin-top {
    width: 50%;
    height: 100%;
}

.card-main {
    display: flex;
}

.touxiang {
    margin-right: 100px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.upload-demo {
    margin-top: 10px;
}

.el-tag + .el-tag {
    margin-left: 10px;
}

.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}

.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}
</style>