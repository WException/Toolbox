<template>
  <div class="login_body">
    <div class="login">
      <h1>Toolbox</h1>

      <div class="input">
        <div class="input_box1">账户:</div>
        <el-input class="input_box2" v-model="name" placeholder="username"/>
      </div>

      <div class="input">
        <div class="input_box1">密码:</div>
        <el-input type="password" class="input_box2" v-model="password" placeholder="password"/>
      </div>

      <div>
        <el-button class="buttons" type="success" @click="login" v-loading.fullscreen.lock="fullscreenLoading">登录
        </el-button>
        <el-button class="buttons" type="primary" @click="reg">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginFrom",
  data() {
    return {
      name: "",
      password: "",
      fullscreenLoading: false,
    }
  },
  methods: {
    login() {
      let text = {
        name: this.name,
        password: this.password
      }
      this.fullscreenLoading = true;
      this.axios.post(this.$store.state.URL + "/login", text).then((response) => {
        let resCode = response.data
        // console.log(resCode)
        switch (resCode.user) {
          case "用户不存在":
            this.$message('用户不存在')
            this.fullscreenLoading = false;
            break
          case "密码错误":
            this.$message.error('密码错误')
            this.fullscreenLoading = false;
            break
          default:
            this.fullscreenLoading = false;
            this.$message({
              message: '登陆成功！',
              type: 'success'
            });
            console.log(resCode.user.touxiang+"登录成功")
            // 浏览器中的用户状态,向浏览器存入缓存
            var user = null
              if (resCode.user.tag!=null){
                user = {
                  name : resCode.user.name,
                  sex : resCode.user.sex,
                  age : resCode.user.age,
                  jieshao : resCode.user.jieshao,
                  touxiang : this.$store.state.HEADURL+resCode.user.touxiang,
                  tag : resCode.user.tag
                }
                this.$store.state.user.tag = resCode.user.tag

              } else {
                user = {
                  name : resCode.user.name,
                  sex : resCode.user.sex,
                  age : resCode.user.age,
                  jieshao : resCode.user.jieshao,
                  touxiang : this.$store.state.HEADURL+resCode.user.touxiang,
                  tag : ""
                }
                this.$store.state.user.tag = ""
              }

            sessionStorage.setItem("user", JSON.stringify(user))
            //更改用户登录状态
            this.$store.state.user.islogin = true
            this.$store.state.user.name = resCode.user.name
            this.$store.state.user.sex = resCode.user.sex
            this.$store.state.user.age = resCode.user.age
            this.$store.state.user.jieshao = resCode.user.jieshao
            this.$store.state.user.touxiang = this.$store.state.HEADURL+resCode.user.touxiang

              console.log(this.$store.state.user.touxiang )

            this.$router.push({path: '/home'})
        }
      }).catch((error) => {
        this.$message('服务器错误，请联系管理员')
        console.log("服务器错误，请联系管理员" + error);
        this.fullscreenLoading = false;
      })
    },
    reg() {
      if (this.name == "" || this.password == "") {
        this.$message('密码或用户名不能为空')
      } else {
        var text = {
          name: this.name,
          password: this.password
        }
        this.fullscreenLoading = true;
        this.axios.post(this.$store.state.URL + "/reg", text).then((response) => {
          console.log(response.data)
          if (response.data == 1) {
            this.fullscreenLoading = false;
            this.$message({
              message: '注册成功，请登录。',
              type: 'success'
            });
          } else if (response.data == 0) {
            this.fullscreenLoading = false;
            this.$message.error('该用户名已存在请重新输入！')
          }
        }).catch((error) => {
          this.fullscreenLoading = false;
          console.log("服务器错误，请联系管理员" + error);
          this.$message.error('服务器错误，请联系管理员')
        })
      }
    }
  }
}
</script>

<style scoped>
.login_body {
  width: 100vw;
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

h1 {
  margin-bottom: 20px;
}

.login {
  width: 500px;
  height: 300px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
  padding-top: 30px;
  text-align: center;
}

.input {
  width: 60%;
  height: 40px;
  margin: 0 auto;
  margin-bottom: 20px;
}

.input_box1 {
  width: 20%;
  height: 100%;
  float: left;
  display: flex;
  align-items: center;
  justify-content: center;
}

.input_box2 {
  width: 80%;
  float: left;
}

.buttons {
  transform: translateX(120px);
}
</style>