<script setup>
import router from "@/router/index.js";
import vuex from "@/vuex/index.js";
import {onMounted,shallowRef,shallowReactive} from "vue";
import {PROJECT_INFO,RULE} from "@/const/index.js";
import {ElMessage,ElNotification} from "element-plus";
import {loginByAccountApi} from "@/api/emp.js";
import {getResponseData} from "@/request/index.js";

// 员工登录表单
const loginForm = shallowRef();
//const loginFormData = shallowReactive({username:'admin',password:'admin'});
const loginFormData = shallowReactive({username:'liye',password:'admin'});
const loginFormRules = {
  username:RULE.USERNAME,password:RULE.PASSWORD
}
// 编写登录函数
function login(){
  loginForm.value.validate(valid=>{
    if(valid){
      loginByAccountApi(loginFormData).then(res=>{
        //let data = res.data;
        let data = getResponseData(res)
        if(data){
          ElMessage.success('登录成功!');
          // 从登录响应结果中分别获取令牌和员工数据
          let token = data.token;
          let loginEmp = JSON.stringify(data['emp']);
          vuex.dispatch('setLoginFlag',true);//登录状态为：已登录
          sessionStorage.setItem('token',token);
          sessionStorage.setItem('loginEmp',loginEmp);
          router.push('/Main');//登录成功后跳转到主页面
        }
      })
    }
  })
}
</script>

<template>
  <section class="login-body">
    <el-card class="login-card" :header="PROJECT_INFO.title">
      <el-form class="login-form"
               ref="loginForm"
               status-icon
               :model="loginFormData"
               :rules="loginFormRules">
        <el-form-item prop="username" required>
          <el-input v-model="loginFormData['username']" suffix-icon="User" clearable
                    placeholder="输入用户名或手机号"></el-input>
        </el-form-item>
        <el-form-item prop="password" require>
          <el-input v-model="loginFormData['password']" suffix-icon="Lock" clearable
          placeholder="输入密码" show-password></el-input>
        </el-form-item>
        <el-button class="login-btn" type="primary" @click="login">员工登录</el-button>
      </el-form>
    </el-card>
  </section>
</template>

<style scoped lang="scss">
.login-body {
  height: 100vh; // 高度
  background: url("../assets/image/loginBackground.png") no-repeat; // 背景图片（不平铺）
  background-size: 100% 100%; // 上下 左右
  padding-top: 200px; // 上内边距
  box-sizing: border-box; // 忽略内边距影响

  .login-card {
    margin: auto; // 自居中
    width: 50vh; // 宽度
    opacity: 0.95; // 透明度
  }

  .login-btn {
    width: 100%; // 宽度
    margin: 0 auto 10px; // 外边距
    letter-spacing: 2px; // 字母间距
  }
}
</style>