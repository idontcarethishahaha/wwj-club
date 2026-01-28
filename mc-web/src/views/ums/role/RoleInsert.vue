<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {insertApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {ref} from "vue";

// 定义导航项
const navItems = [
  {label:'用户管理',icon:'List'},
  {label:'角色列表',icon:'User',url:'/Role'},
  {label:'添加新角色',icon:'Plus'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '描述', prop: 'info', required: true, span:24,type: 'textarea'}
]
//表单值
const formValues = ref({capacity:0})
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//添加成功的响应函数
function insertSuccess(){
  ElMessage.success("添加成功！")
  //延迟1000ms后自动跳转到角色列表页
  setTimeout(()=>router.push('/Role'),1000)
}

// 定义模块名
const args = {module:'role'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="insert-card" header="添加新角色">
    <my-form type="insert"
             :params="formValues"
             :api="insertApi"
             :rules="rules"
             :args="args"
             :callback="insertSuccess"
             :items="formItems"></my-form>
  </el-card>
</template>

<style scoped lang="scss">
   .insert-card{
     width:60%;
     margin: 65px auto;
   }
</style>