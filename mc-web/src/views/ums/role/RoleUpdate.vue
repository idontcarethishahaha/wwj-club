<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {reactive} from "vue";

// 定义导航项
const navItems = [
  {label:'用户管理',icon:'List'},
  {label:'角色列表',icon:'UserFilled',url:'/Role'},
  {label:'编辑角色',icon:'Edit'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '描述', prop: 'info', required: true, span:24,type: 'textarea'}
]
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = reactive(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//编辑成功的响应函数
function updateSuccess(){
  ElMessage.success("编辑成功！")
  //延迟1000ms后自动跳转到角色列表页
  setTimeout(()=>router.push('/Role'),1000)
}

// 定义模块名
const args = {module:'role'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="update-card" header="编辑角色">
    <my-form type="update"
             :params="formValues"
             :api="updateApi"
             :rules="rules"
             :args="args"
             :callback="updateSuccess"
             :items="formItems"></my-form>
  </el-card>
</template>

<style scoped lang="scss">
.update-card{
  width:60%;
  margin: 65px auto;
}
</style>