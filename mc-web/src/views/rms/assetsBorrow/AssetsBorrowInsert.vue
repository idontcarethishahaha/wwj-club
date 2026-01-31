<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {insertApi, listApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {onMounted, ref,reactive} from "vue";
import {getResponseData} from "@/request/index.js";
import {ElMessageBox} from "element-plus";
import {returnAssetsApi} from "@/api/assets.js";

// 定义导航项
const navItems = [
  {label:'资源管理',icon:'List'},
  {label:'资产申请列表',icon:'Coin',url:'/AssetsBorrow'},
  {label:'添加新资产申请记录',icon:'Plus'}
]
//保存所有的资产
let assetsOptions = reactive([])
//保存所有的申请人
let empOptions = reactive([])
// 定义表单项
const formItems = [
  {label: '资产名称', prop: 'fkAssetsId', required: true, span: 12,type:'select',options:assetsOptions},
  {label: '申请人', prop: 'fkEmpId', required: true, span: 12, type: 'select',options:empOptions},
  {label: '申请数量', prop: 'count', required: true, span: 12, type: 'number'},
  {label: '申请时间', prop: 'borrowTime', required: true, span: 12, type: 'datetime',placeholder:'请选择申请时间'},
  {label: '预计归还时间', prop: 'expectedReturnTime', required: true, span: 12, type: 'datetime',placeholder:'请选择预计归还时间'},
  {label: '备注', prop: 'info', required: true, span: 24, type: 'textarea'},
]
//表单值
const formValues = ref({capacity:0})
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  info:RULE.INFO
}
//添加成功的响应函数
function insertSuccess(){
  ElMessage.success("添加成功！")
  //延迟1000ms后自动跳转到房间列表页
  setTimeout(()=>router.push('/AssetsBorrow'),1000)
}

// 定义模块名
const args = {module:'assetsBorrow'}

onMounted(async()=>{
  //查询所有资产，封装到assetsOptions变量里
  Object.values(getResponseData(await listApi({module:'assets'})))
      .forEach(assets=>{
        assetsOptions.push({
          label:assets['title'],
          value:assets['id']
        })
      });
  Object.values(getResponseData(await listApi({module: 'emp'})))
      .forEach(emp=>{
        empOptions.push({
          label: emp.realname,
          value: emp.id
        });
      })
  //console.log("员工下拉选项：", empOptions);
})


</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="insert-card" header="填写资产申请">
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