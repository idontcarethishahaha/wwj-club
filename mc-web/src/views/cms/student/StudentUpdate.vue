<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import { updateApi, listApi } from "@/api/axios.js";
import {RULE, STUDENT_STATUS_OPTIONS} from "@/const/index.js";
import { ElMessage } from "element-plus";
import router from "@/router/index.js";
import { reactive, ref, onMounted } from "vue";
import { getResponseData } from "@/request/index.js";
import MyUpload from "@/components/MyUpload.vue";
import {UPLOAD_AVATAR_URL} from "@/api/student.js";

// 定义导航项
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '学生列表', icon: 'UserFilled', url: '/Student' },
  { label: '编辑学生', icon: 'Edit' }
];

// 下拉选项
const schoolOptions = reactive([]); // 学校下拉
const salesmanOptions = reactive([]); // 咨询老师（员工）下拉
const clubOptions = reactive([]); // 班级下拉

// 加载下拉列表数据（完全模仿你的deptOptions加载逻辑）
onMounted(async () => {
  // 加载学校列表
  const schoolData = getResponseData(await listApi({ module: 'school' }));
  Object.values(schoolData).forEach(school => {
    schoolOptions.push({
      label: school.title,
      value: school.id
    });
  });

  // 加载咨询老师（员工）列表
  const empData = getResponseData(await listApi({ module: 'emp' }));
  Object.values(empData).forEach(emp => {
    salesmanOptions.push({
      label: emp.realname,
      value: emp.id
    });
  });

  // 加载班级列表
  const clubData = getResponseData(await listApi({ module: 'club' }));
  Object.values(clubData).forEach(club => {
    clubOptions.push({
      label: club.title,
      value: club.id
    });
  });
});

// 定义表单项
const formItems = [
  { label: '学号', prop: 'number', required: true, span: 12 },
  { label: '真实姓名', prop: 'realname', required: true, span: 12 },
  { label: '手机号码', prop: 'phone', required: true, span: 12 },
  { label: '微信号', prop: 'wechat', required: true, span: 12 },
  { label: '身份证号', prop: 'idcard', required: true, span: 12 },
  {label: '状态', prop: 'status', type: 'select', options: STUDENT_STATUS_OPTIONS, required: true, span: 12},
  // { label: '学生状态', prop: 'status', required: true, span: 12, type: 'select',
  //   options: [
  //     { label: '在培', value: 0 },
  //     { label: '结课', value: 1 },
  //     { label: '就业', value: 2 },
  //     { label: '休学', value: 3 },
  //     { label: '其它', value: 4 }
  //   ] },
  { label: '学校', prop: 'fkSchoolId', required: true, span: 12, type: 'select', options: schoolOptions },
  { label: '咨询老师', prop: 'fkSalesmanId', required: true, span: 12, type: 'select', options: salesmanOptions },
  { label: '班级', prop: 'fkClubId', required: true, span: 12, type: 'select', options: clubOptions },
  { label: '入学时间', prop: 'studyDate', required: true, span: 12, type: 'datetime' },
  { label: '紧急联系人姓名', prop: 'emergencyName', required: true, span: 12 },
  { label: '紧急联系人电话', prop: 'emergencyPhone', required: true, span: 12 },
  { label: '现居住地', prop: 'address', required: true, span: 24, type: 'textarea' },
  { label: '学生描述', prop: 'info', required: false, span: 24, type: 'textarea' }
];

const record = JSON.parse(sessionStorage.getItem('row'))
// 表单值
const formValues = ref(record);

// 表单项规则
const rules = {
  emergencyName: RULE.REALNAME,
  realname: RULE.REALNAME,
  phone: RULE.PHONE,
  emergencyPhone: RULE.PHONE,
  idcard: RULE.IDCARD,
  address: RULE.ADDRESS,
  info: RULE.INFO
};

// 修改成功的响应函数
function updateSuccess() {
  ElMessage.success("修改成功！");
  // 延迟1000ms后自动跳转到学生列表页
  setTimeout(() => router.push('/Student'), 1000);
}

// 定义模块名
const args = { module: 'student' };
</script>

<template>
  <my-nav v-bind:items="navItems" />
  <el-row :gutter="5" style="margin-top: 10px;">
    <el-col :span="18">
      <el-card class="update-card" header="编辑学生">
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
      <el-card class="update-card" header="上传学生头像">
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
.update-card {
  width: 90%;
  margin: 65px auto;
}
</style>