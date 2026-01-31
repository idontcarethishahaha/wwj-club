<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi, listApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {onMounted, reactive, ref} from "vue";
import {getResponseData} from "@/request/index.js";

// 定义导航项
const navItems = [
  {label:'用户管理',icon:'Avatar'},
  {label:'部门列表',icon:'HomeFilled',url:'/Dept'},
  {label:'修改部门信息',icon:'Edit'}
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
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = reactive(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,address:RULE.ADDRESS,info:RULE.INFO
}
//修改成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到部门列表页
  setTimeout(()=>router.push('/Dept'),1000)
}

// 定义模块名
const args = {module:'dept'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="update-card" header="修改部门信息">
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