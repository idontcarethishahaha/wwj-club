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
  {label:'资源管理',icon:'List'},
  {label:'资产列表',icon:'Coin',url:'/Assets'},
  {label:'添加新资产',icon:'Plus'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '图片', prop: 'picture', required: true, span: 12, type: 'number'},
  {label: '单价', prop: 'price', required: true, span: 24, type: 'textarea'},
  {label: 'xx', prop: 'xx', required: true, span: 24, type: 'textarea'},
]
//表单值
const formValues = ref({capacity:0})
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,address:RULE.ADDRESS,info:RULE.INFO
}
//添加成功的响应函数
function insertSuccess(){
  ElMessage.success("添加成功！")
  //延迟1000ms后自动跳转到房间列表页
  setTimeout(()=>router.push('/xx'),1000)
}

// 定义模块名
const args = {module:'xx'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="insert-card" header="添加新xx">
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