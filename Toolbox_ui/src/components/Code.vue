<template>
  <div class="code_body">

    <header>
      <el-select v-model="value" @change="g">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <div>
        <el-button type="info" icon="el-icon-delete" plain @click="deleteCode"></el-button>
        <el-button type="success" icon="el-icon-check" plain @click="copyCode"></el-button>
      </div>
    </header>

    <codemirror
        id="ss"
        ref="chartOption"
        :options="cmOptions"
    >
    </codemirror>
  </div>
</template>

<script>

// import { Notification } from 'element-ui' //提示框组件
import {codemirror} from 'vue-codemirror' // 引入组件
import 'codemirror/lib/codemirror.css' //核心样式
import 'codemirror/theme/idea.css' //文本框主题

import 'codemirror/mode/python/python.js'
import 'codemirror/mode/css/css.js'
import 'codemirror/mode/javascript/javascript.js'
import 'codemirror/mode/clike/clike.js'
import 'codemirror/mode/xml/xml.js'

export default {
  name: "Code",
  components: {
    codemirror, // 声明组件
    // Notification
  },
  data() {
    return {
      cmOptions: {
        tabSize: 4, // Tab键空格数
        mode: 'text/x-python', //模式
        theme: 'idea', // 主题
        lineNumbers: true, //是否显示行号
      },
      options: [{
        value: 'text/x-python',
        label: 'Python'
      }, {
        value: 'text/html',
        label: 'HTML'
      }, {
        value: 'text/css',
        label: 'CSS'
      }, {
        value: 'text/javascript',
        label: 'JavaScript'
      }, {
        value: 'text/x-java',
        label: 'Java'
      }],
      value: 'Python'
    }
  },
  methods: {
    // 切换代码样式
    g(e) {
      this.cmOptions.mode = e
    },
    // 删除代码
    deleteCode(){
      // let code = this.$refs.chartOption.codemirror.getValue()
      this.$refs.chartOption.codemirror.setValue('')
      this.$notify.success({
        title: '提示',
        message: "清除成功",
        showClose: false,
        duration: 1500,
      });
    },
    // 复制代码链接
    copyCode(){
      let text = {
        userName : this.$store.state.user.name,
        fileData : this.$refs.chartOption.codemirror.getValue(),
        fileType : this.cmOptions.mode
      }
      this.axios.post(this.$store.state.URL + "/code", text).then((response) => {
        navigator.clipboard.writeText(response.data).then(this.$message({message: '复制成功！', type: 'success'}));
      })
    }
  }
}
</script>

<style scoped>
.code_body {
  box-sizing: border-box;
  width: 100vw;
  height: 80vh;
  padding-top: 10vh;
  padding-left: 20vh;
}

#ss {
  width: 80%;
  height: 100%;
  border: 1px solid black;
}

header {
  width: 80%;
  display: flex;
  /*两端对齐*/
  justify-content: space-between;
}
</style>