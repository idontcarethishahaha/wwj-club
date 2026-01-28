<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {reactive} from "vue";
import {UPLOAD_ASSETS_URL} from "@/api/assets.js";
import MyUpload from "@/components/MyUpload.vue";

// 定义导航项
const navItems = [
  {label:'资源管理',icon:'List'},
  {label:'资产列表',icon:'Coin',url:'/Assets'},
  {label:'修改资产',icon:'Plus'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '单价', prop: 'price', required: true, span:12,type: 'number'},
  {label: '货币单位', prop: 'priceUnit', required: true, span:12},
  {label: '库存', prop: 'stock', required: true, span:12,type: 'number'},
  {label: '库存单位', prop: 'stockUnit', required: true, span:12},
  {label: '总计库存', prop: 'total', required: true, span:12,type: 'number'},
  {label: '描述', prop: 'info', required: true, span:24,type: 'textarea'}
]
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = reactive(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//修改成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到资产列表页
  setTimeout(()=>router.push('/Assets'),1000)
}

// 定义模块名
const args = {module:'assets'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-row>
    <el-col :span="16">
      <el-card class="update-card" header="编辑资产">
        <my-form type="update"
                 :params="formValues"
                 :api="updateApi"
                 :rules="rules"
                 :args="args"
                 :callback="updateSuccess"
                 :items="formItems"></my-form>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card class="update-card" header="上传资产图片资产">
        <my-upload :url="UPLOAD_ASSETS_URL+'/'+formValues['id']"
                   :callback="updateSuccess"
                   :auto-upload="true"
                   name="pictureFile"></my-upload>
      </el-card>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">
.update-card{
  width:85%;
  margin: 65px auto;
}
</style>