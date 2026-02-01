<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {insertApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {ref,reactive} from "vue";

// 定义导航项
const navItems = [
  {label:'用户管理',icon:'Avatar'},
  {label:'菜单列表',icon:'Menu',url:'/Menu'},
  {label:'添加新菜单',icon:'Plus'}
]
let pid  = sessionStorage.getItem('pid')
let parentTitle  = sessionStorage.getItem('parentTitle')
// 如果是父菜单 pid将是null或undefined
if(!pid){
  pid=0;
  parentTitle='无'
}

const menuOptions = reactive([
  {label:parentTitle,value:pid}
])
// 定义表单项
const formItems = [
  {label: '上级菜单', prop: 'pid', required: true, span: 24,type:'select',options:menuOptions},
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: 'URL', prop: 'url', required: true, span: 12},
  {label: '图标', prop: 'icon', required: true, span:24,type:'icon' },
  {label: '描述', prop: 'info', required: true, span: 24, type: 'textarea'},
]
//表单值
const formValues = ref({pid:pid})
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//添加成功的响应函数
function insertSuccess(){
  ElMessage.success("添加成功！")
  //延迟1000ms后自动跳转到列表页
  setTimeout(()=>router.push(pid == 0 ?'/Menu':'SubMenu'),1000)
}

// 定义模块名
const args = {module:'menu'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="insert-card" header="添加新菜单">
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