<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {insertApi, listApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {onMounted, reactive, ref} from "vue";
import {getResponseData} from "@/request/index.js";

// 定义导航项
const navItems = [
  {label:'用户管理',icon:'Avatar'},
  {label:'部门列表',icon:'HomeFilled',url:'/Dept'},
  {label:'添加新部门',icon:'Plus'}
]

// 声明一个房间Options
const roomOptions = reactive([])

// 定义表单项
const formItems = [
  {label: '名称', prop: 'title' ,required: true, span: 12},
  {label: '房间', prop: 'fkRoomId', required: true, span: 12, type: 'select',options:roomOptions},
  {label: '介绍', prop: 'info', required: true, span: 24, type: 'textarea'},
]


onMounted(async()=>{
  // 查询全部的房间列表
  Object.values(getResponseData(await listApi({module:'room'})))
      .forEach(room=>{
        roomOptions.push({
          label:room['title'],
          value:room['id']
        })
      })
})
//表单值
const formValues = ref({capacity:0})
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,address:RULE.ADDRESS,info:RULE.INFO
}
//添加成功的响应函数
function insertSuccess(){
  ElMessage.success("添加成功！")
  //延迟1000ms后自动跳转到部门列表页
  setTimeout(()=>router.push('/Dept'),1000)
}

// 定义模块名
const args = {module:'dept'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="insert-card" header="添加新部门">
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