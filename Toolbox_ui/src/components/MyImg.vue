<template name="el-fade-in-linear">
	<div class="myimg_body">

		<el-tabs tab-position="top" type="card" class="mying_nav" addable @tab-remove="removeTab" @tab-add="addTab" @tab-click="clickTab">
			<el-tab-pane label="全部图片"></el-tab-pane>
			<el-tab-pane v-for="(tab,index) in tabs" :key="index" :label="tab.favoritesName" :name="tab.favoritesName" closable
			></el-tab-pane>
		</el-tabs>
		<div class="img_body">
			<el-row>
				<el-col class="col" :span="20" :push="1">
					<el-table
							:data="tableData.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
							style="width: 100%" @cell-mouse-enter="hover" @cell-mouse-leave="hover2"
							@row-click="imgshowFun">

						<el-table-column label="上传时间" prop="time"/>
						<el-table-column label="图片名称" prop="name"/>
						<el-table-column label="图片大小(kb)" prop="size"/>

						<el-table-column align="right">
							<template slot-scope="scope">
								<el-button
										size="mini"
										type="success"
										plain
										@click.stop="handleCopy(scope.$index, scope.row)">复制链接
								</el-button>
								<el-button
										size="mini"
										type="danger"
										plain
										@click.stop="handleDelete(scope.$index, scope.row)">删除
								</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-col>
			</el-row>
			<div>
				<el-pagination
						class="pagination"
						background
						@current-change="handleCurrentChange"
						:current-page="currentPage"
						:page-size="pagesize"
						layout="total,prev,pager,next"
						:total="tableData.length" >
				</el-pagination>
			</div>
		</div>
		<!--显示预览的图片-->
		<transition name="el-zoom-in-top">
			<div id="yv-lan-img" v-if="yvlanimg.is">
				<el-image
						style="width: 100%; height: 100%"
						:src="yvlanimg.url"
						fit="contain"></el-image>
			</div>
		</transition>
		<!--查看详细的图片-->
		<transition name="el-fade-in-linear">
			<el-card v-show="imgshow.is" class="img-box">
				<div slot="header" class="clearfix">
					<span>{{ imgshow.name }}</span>
					<el-button style="float: right; padding: 3px 0" type="text" @click="noImgshowFun">关闭</el-button>
				</div>
				<div>
					<el-row>
						<el-col :span="12"><el-image fit="contain" style="width: 300px; height: 300px" :src="imgshow.src"></el-image></el-col>
						<el-col :span="12">
							<el-descriptions :column="1">
								<el-descriptions-item label="图片名">{{ imgshow.name }}</el-descriptions-item>
								<el-descriptions-item label="大小">{{ imgshow.size }}kb</el-descriptions-item>
								<el-descriptions-item label="上传时间">{{ imgshow.time }}</el-descriptions-item>
								<el-descriptions-item label="所属收藏夹">
									<el-select v-model="optionsValue" placeholder="请选择">
										<el-option
												v-for="item in options"
												:key="item.favoritesName"
												:label="item.favoritesName"
												:value="item.favoritesName">
										</el-option>
									</el-select>
								</el-descriptions-item>
							</el-descriptions>
							<el-button @click="optionsBaocun">保存</el-button>
						</el-col>
					</el-row>
				</div>
			</el-card>
		</transition>

	</div>
</template>

<script>
export default {
	name: "MyImg",
	data() {
		return {
			// 表格数据
			tableData: [],
			// 当前页码
			currentPage: 1,
			// 每页显示的行数
			pagesize: 8,
			search: '',
			// 预览图片
			yvlanimg: {
				is: false,
				url: "",
			},
			imgshow: {
				is: false,
				name: "",
				src: "",
				size: "",
				time: "",
			},
			// 用户的收藏夹
			tabs: [],
			options: [],
			optionsValue: ""
		}
	},
	methods: {
		// 页面切换方法
		handleCurrentChange(val) {
			this.currentPage = val;
		},
		handleCopy(index, row) {
			console.log(index, row);
			navigator.clipboard.writeText(this.$store.state.HEADURL + row.path).then(this.$message({
				message: '复制成功！',
				type: 'success'
			}));
		},
		handleDelete(index, row) {
			this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				let path = this.$store.state.URL + "/deleteimg?imgname=" + row.name + "&username=" + this.$store.state.user.name
				this.axios.get(path,).then((response) => {
					console.log("删除第" + index + "列的图片：" + row.name + response.data)
					// 删除成功后更新数据
					// 生命周期钩子，在此发送请求获取用户图片信息
					this.axios.post(this.$store.state.URL + "/finduserimg",{name:this.$store.state.user.name}).then((response) => {
						let resCode = response.data
						this.tableData = resCode
						console.log(resCode)
					}).catch((error) => {
						this.$message('服务器错误，请联系管理员')
						console.log("服务器错误，请联系管理员" + error);
						this.fullscreenLoading = false;
					})
					this.$message('删除成功')
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				});
			});

		},
		// 鼠标悬浮预览图片的函数
		hover(row) {
			this.yvlanimg.is = true
			this.axios.get(this.$store.state.URL + "/yvlanimg?imgname=" + row.name + "&username=" + this.$store.state.user.name).then((response) => {
				this.yvlanimg.url = response.data
			})
			// console.log("进来了",row.name)
		},
		// 鼠标悬浮离开的函数
		hover2() {
			this.yvlanimg.is = false
			this.yvlanimg.url = ""
		},
		// 鼠标点击打开图片的函数
		imgshowFun(row) {
			this.yvlanimg.is = false
			this.imgshow.is = true
			this.imgshow.name = row.name
			this.imgshow.src = this.$store.state.HEADURL+row.path
			this.imgshow.size = row.size
			this.imgshow.time = row.time
			this.options = this.tabs

			this.axios.get(this.$store.state.URL+"/selectimgfavorites?username="+this.$store.state.user.name+"&imgname="+row.name).then((respo)=>{
				if (respo.data != '该图片暂不属于任何收藏夹') {
					this.optionsValue = respo.data;
				} else {
					this.optionsValue = "";
				}
			})
		},
		// 鼠标点击关闭图片的函数
		noImgshowFun() {
			this.imgshow.is = false
		},
		// 删除标签的回调
		removeTab(e) {
			// 1.发送给后台添加文件夹的请求
			let text = {
				userName: this.$store.state.user.name,
				favoritesName: e
			}
			console.log(e)
			this.axios.get(this.$store.state.URL+"/imgdelectfavorites?username="+this.$store.state.user.name+"&favoritesname="+e).then(()=>{
				this.axios.post(this.$store.state.URL + "/deletefavorites", text).then(() => {
					this.axios.post(this.$store.state.URL + "/selectfavorites", {name: this.$store.state.user.name}).then((response) => {
						this.tabs = response.data
					})
					// 3.弹出提示框
					this.$message({
						type: 'success',
						message: '删除了项目/收藏夹: ' + e
					});
				})
			})

		},
		// 添加标签的回调
		addTab() {
			this.$prompt('请输入项目/收藏夹名称', '添加', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				inputPattern: /\S/,
				inputErrorMessage: '内容不能为空'
			}).then(({value}) => {

				let flag = false
				this.tabs.forEach((e) => {
					if (e.favoritesName == value) {
						flag = true
					}
				})
				if (!flag) {
					// 1.发送给后台添加文件夹的请求
					let text = {
						userName: this.$store.state.user.name,
						favoritesName: value
					}
					this.axios.post(this.$store.state.URL + "/insertfavorites", text).then(() => {
						// 2.将新添加的标签暂时存在数组中
						this.tabs.push({
							favoritesName: value
						})
						// 3.弹出提示框
						this.$message({
							type: 'success',
							message: '新增了项目/收藏夹: ' + value
						});
					})
				} else {
					this.$message({
						type: 'info',
						message: '项目/收藏夹名称重复'
					});
				}
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '取消添加'
				});
			});
		},
		// 用户保存收藏夹分类的按钮
		optionsBaocun(){
			// console.log(this.optionsValue)
			this.axios.get(this.$store.state.URL+"/insertimgfavorites?username="+this.$store.state.user.name+"&imgname="+this.imgshow.name+"&favoritesname="+this.optionsValue).then((respo)=>{
				console.log(respo.data)
				if (respo.data==1){
					this.$message({
						type: 'success',
						message: '修改成功'
					});
				} else {
					this.$message('修改失败');
				}
			})
		},
		// 用户切换标签页的回调函数
		clickTab(e){
			if (e.index!=0){
				// console.log()
				let favoritesName = this.tabs[e.index-1].favoritesName
				this.axios.get(this.$store.state.URL+"/finduserimgbyfavoritesId?username="+this.$store.state.user.name+"&favoritesname="+favoritesName).then((respo)=>{
					// console.log(respo.data)
					this.tableData = respo.data
				})
			} else {
				this.axios.post(this.$store.state.URL + "/finduserimg", {name: this.$store.state.user.name}).then((response) => {
					this.tableData = response.data
				}).catch((error) => {
					this.$message('服务器错误，请联系管理员')
					console.log("服务器错误，请联系管理员" + error);
					this.fullscreenLoading = false;
				})
			}
		},
	},
	// 生命周期钩子，在此发送请求获取用户图片信息
	beforeCreate() {
		if (this.$store.state.user.name != "") {
			this.axios.post(this.$store.state.URL + "/finduserimg", {name: this.$store.state.user.name}).then((response) => {
				this.tableData = response.data
			}).catch((error) => {
				this.$message('服务器错误，请联系管理员')
				console.log("服务器错误，请联系管理员" + error);
				this.fullscreenLoading = false;
			})
			this.axios.post(this.$store.state.URL + "/selectfavorites", {name: this.$store.state.user.name}).then((response) => {
				this.tabs = response.data
			})
		} else {
			this.$message('请先登录')
		}

	}
}
</script>

<style scoped>
.myimg_body {
    width: 90vw;
}

.mying_nav {
    width: 80vw;
    transform: translate(10vw, 20px);
}

.img_body {
    width: 80vw;
    height: 570px;
    transform: translate(10vw, 5px);
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}

#yv-lan-img {
    width: 250px;
    height: 250px;
    position: absolute;
    top: 30%;
    right: 30px;
}

.pagination{
	position: absolute;
	bottom: 10px;
	left: 50%;
	transform: translateX(-80%);
}

.img-box {
    width: 70vw;
    height: 70vh;
    border-radius: 4px;
    background-color: rgb(255, 255, 255);
    box-sizing: border-box;
    position: absolute;
    top: 20vh;
    left: 15vw;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

</style>
