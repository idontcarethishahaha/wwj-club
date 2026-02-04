<script setup>
import MyForm from "@/components/MyForm.vue";
import MyNav from "@/components/MyNav.vue";
import MyUpload from "@/components/MyUpload.vue";
import {GENDER_OPTIONS,PROVINCE_OPTIONS,RULE} from "@/const/index.js";
import {getResponseData} from "@/request/index.js";
import {onMounted,reactive,ref} from "vue";
import {listApi,updateApi} from "@/api/axios.js";
import {ElMessage} from "element-plus";
import {updatePasswordApi, UPLOAD_AVATAR_URL} from "@/api/emp.js";
import router from "@/router/index.js";

const loginEmp = JSON.parse(sessionStorage.getItem('loginEmp'))
const deptOptions = reactive([])

const navItems = [
  {label:'系统首页',url:'/Dashboard',icon:'House'},
  {label:'修改个人信息',icon:'Edit'},
]

const formItems = reactive([
  {label:'登录账号',prop:'username',required:true,span:12},
  {label:'姓名',prop:'realname',required:true,span:12},
  {label:'手机号码',prop:'phone',required:true,span:12},
  {label:'微信号码',prop:'wechat',required:true,span:12},
  {label:'电子邮箱',prop:'email',required:true,span:12},
  {label:'身份证号',prop:'idcard',required:true,span:12},
  {label:'所属部门',prop:'fkDeptId',required:true,span:12,type:'select',options: deptOptions,disabled: true},
  {label:'性别',prop:'gender',required:true,span:12,type: 'select',options:GENDER_OPTIONS,disabled:true},
  {label:'年龄',prop:'age',required:true,span:12,disabled: true},
  {label:'户籍省份',prop:'province',required:true,span:12,type: 'select',options:PROVINCE_OPTIONS,disabled: true},
  {label:'居住地',prop:'address',required:true,span:24,type: 'textarea',rows:3},
  {label:'个人简介',prop:'info',required:true,span:24,type:'textarea'},
])

const formValues = reactive(loginEmp)
//表单值的规则
const rules = {
  realname:RULE.REALNAME,
  idcard:RULE.IDCARD,
  phone:RULE.PHONE,
  email:RULE.EMAIL,
  address:RULE.ADDRESS,
  info:RULE.INFO
}

// 定义专门修改密码的表单
const updatePasswordFormItems = [
  {label:'原密码',prop:'oldPass',type:'password',required:true,placeholder:'输入原密码',showPassword:true},
  {label:'新密码',prop:'newPass',type:'password',required:true,placeholder:'输入新密码',showPassword:true},
  {label:'确认密码',prop:'confirmPass',type:'password',required:true,placeholder:'确认新密码',showPassword:true},
]

const updatePasswordFormValues = reactive({id:loginEmp['id']})
const updatePasswordRules = {
  oldPass:RULE.PASSWORD,
  newPass:RULE.PASSWORD,
  confirmPass:{
    validate:(rule,value,callback)=>{
      if(value!==updatePasswordFormValues['newPass']){
        callback('两次密码不一致')
      }else{
        callback();
      }
    },
    trigger:['blur','input']//触发条件，失去焦点时，输入时 校验
  }
}

// 修改成功的函数
function updateSuccess(){
  ElMessage.success('修改成功，请重新登录！')
  setTimeout(()=>{
    router.push('/')
  },1000);
}
// 加载部门列表
onMounted(async()=>{
  Object.values(getResponseData(await listApi({module:'dept'})))
      .forEach(dept=>{
        deptOptions.push(
            {label:dept['title'],value:dept['id']}
        )
      });
});
</script>

<template>
  <my-nav :items="navItems"/>
  <el-row class="personal-update-body" :gutter="20">
    <el-col :span="16">
      <el-card header="修改个人信息">
        <my-form
            :params="formValues"
            :api="updateApi"
            :rules="rules"
            :items="formItems"
            type="update"
            :args="{'module': 'emp'}"
            :callback="updateSuccess">
        </my-form>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card header="上传头像">
        <my-upload
            :url="UPLOAD_AVATAR_URL + '/' + loginEmp['id']"
            name="avatarFile"
            :callback="updateSuccess"
            :auto-upload="true">
        </my-upload>
      </el-card>
      <el-card header="修改登录密码" class="update-password-card">
        <my-form
            :params="updatePasswordFormValues"
            :api="updatePasswordApi"
            :rules="updatePasswordRules"
            :items="updatePasswordFormItems"
            type="update"
            :args="{'module': 'emp'}"
            :callback="updateSuccess">
        </my-form>
      </el-card>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">
.personal-update-body {
  margin-top: 22px; // 上外边距
  height: 620px; // 高度
  overflow-y: scroll; // Y轴溢出滚动

  .update-password-card {
    margin-top: 37px; // 上外边距
  }
}
</style>