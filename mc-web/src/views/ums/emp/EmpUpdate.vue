<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi,listApi} from "@/api/axios.js";
import {DEFAULT_PASSWORD, RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {reactive, ref,onMounted} from "vue";
import {getResponseData} from "@/request/index.js";
import MyUpload from "@/components/MyUpload.vue";
import {UPLOAD_AVATAR_URL} from "@/api/emp.js";

// 定义导航项
const navItems = [
  { label: '用户管理', icon: 'Avatar' },
  { label: '员工列表', icon: 'HomeFilled',url:'/Emp' },
  {label:'修改员工信息',icon:'Edit'}
]
const deptOptions = reactive([]);

// 加载部门列表
onMounted(async () => {
  const deptData = getResponseData(await listApi({ module: 'dept' }));
  Object.values(deptData).forEach(dept => {
    deptOptions.push({
      label: dept.title,
      value: dept.id
    });
  });
});
// 定义表单项
const formItems = [
  {label: '登录账号', prop: 'username', required: true, span: 12},
  {label: '真实姓名', prop: 'realname', required: true, span: 12},
  {label: '身份证号', prop: 'idcard', required: true, span: 12},
  {label: '手机号码', prop: 'phone', required: true, span: 12},
  {label: '微信', prop: 'wechat', required: true, span: 12},
  {label: '电子邮箱', prop: 'email', required: true, span: 12},
  {label: '入职部门', prop: 'fkDeptId', required: true, span: 12,type:'select',options:deptOptions},
  {label: '入职日期', prop: 'hiredate', required: true, span: 12, type: 'datetime'},
  {label: '现居住地', prop: 'address', required: true, span: 24, type: 'textarea'},
  {label: '简介', prop: 'info', required: false, span: 24, type: 'textarea'},
]
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = reactive(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  address:RULE.ADDRESS,info:RULE.INFO,realname:RULE.REALNAME,phone:RULE.PHONE,idcard:RULE.IDCARD,email:RULE.EMAIL
}
//修改成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到员工列表页
  setTimeout(()=>router.push('/Emp'),1000)
}

// 定义模块名
const args = {module:'emp'}
</script>

<template>
  <my-nav v-bind:items="navItems" />
  <el-row>
    <el-col :span="18">
      <el-card class="update-card" header="编辑员工信息">
        <my-form
            type="update"
            :params="formValues"
            :api="updateApi"
            :rules="rules"
            :args="args"
            :callback="updateSuccess"
            :items="formItems">
        </my-form>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card class="update-card">
        <my-upload
            :url="UPLOAD_AVATAR_URL + '/' + record.id"
            :callback="updateSuccess"
            :auto-upload="true"
            name="avatarFile">
        </my-upload>
      </el-card>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">
   .update-card{
     width:60%;
     margin: 65px auto;
   }
</style>