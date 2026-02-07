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
  { label: '课程列表', icon: 'Reading',url:'/Course' },
  {label:'修改课程',icon:'Edit'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '类别', prop: 'category', required: true, span: 12},
  {label: '顺序', prop: 'idx', required: true, span: 12,type:'number'},
  {label: '学时', prop: 'hours', required: true, span: 12,type:'number'},
  {label: '介绍', prop: 'info', required: true, span: 24,type:'textarea'},
]
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = ref(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//修改成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到列表页
  setTimeout(()=>router.push('/Course'),1000)
}

// 定义模块名
const args = {module:'course'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="update-card" header="修改课程">
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