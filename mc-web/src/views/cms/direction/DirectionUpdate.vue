<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {ref} from "vue";

// 定义导航项
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '方向列表', icon: 'Rank' ,url:'/Direction'},
  {label:'修改方向',icon:'Edit'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '介绍', prop: 'info', required: true, span: 24,type:'textarea'},
]
// 获取的方向信息
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = ref(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,address:RULE.ADDRESS,info:RULE.INFO
}
//添加成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到方向列表页
  setTimeout(()=>router.push('/Direction'),1000)
}

// 定义模块名
const args = {module:'direction'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="update-card" header="修改方向">
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