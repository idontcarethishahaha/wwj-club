<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi, listApi} from "@/api/axios.js";
import {RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {onMounted, reactive, ref} from "vue";
import {getResponseData} from "@/request/index.js";

//定义多组下拉框的options
const clubOptions = reactive([])
const courseOptions = reactive([])
const roomOptions = reactive([])
const empOptions = reactive([])

onMounted(async () => {
  Object.values(getResponseData(await listApi({module: 'club'})))
      .forEach(item => {
        clubOptions.push({label: item['title'], value: item['id']});
      });

  Object.values(getResponseData(await listApi({module: 'course'})))
      .forEach(item => {
        courseOptions.push({label: item['title'], value: item['id']});
      });

  Object.values(getResponseData(await listApi({module: 'room'})))
      .forEach(item => {
        roomOptions.push({label: item['title'], value: item['id']});
      });

  Object.values(getResponseData(await listApi({module: 'emp'})))
      .forEach(item => {
        empOptions.push({label: item['realname'], value: item['id']});
      });
});

// 定义导航项
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '进度列表', icon: 'More',url:'/ClubProgress' },
  {label:'修改进度',icon:'Edit'}
]
// 定义表单项
const formItems = [
  {label: ' 进度序号 ', prop: 'idx', required: true, span: 12, type: 'number'},
  {label: ' 班级 ', prop: 'fkClubId', required: true, span: 12, type: 'select', options: clubOptions},
  {label: ' 课程 ', prop: 'fkCourseId', required: true, span: 12, type: 'select', options: courseOptions},
  {label: ' 教室 ', prop: 'fkRoomId', required: true, span: 12, type: 'select', options: roomOptions},
  {label: ' 主讲老师 ', prop: 'fkTeacherId', required: true, span: 12, type: 'select', options: empOptions},
  {label: ' 班主任老师 ', prop: 'fkManagerId', required: true, span: 12, type: 'select', options: empOptions},
  {label: ' 助教老师 ', prop: 'fkAssistantId', required: true, span: 12, type: 'select', options: empOptions},
  {label: '开始时间', prop: 'startTime', required: true, span: 12, type: 'datetime'},
  {label: '预计结束时间', prop: 'expectedEndTime', required: true, span: 12, type: 'datetime'},
  {label: '进度描述', prop: 'info', required: false, span: 24, type: 'textarea'},
]
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = ref(record)
//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//添加成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到xx列表页
  setTimeout(()=>router.push('/ClubProgress'),1000)
}

// 定义模块名
const args = {module:'clubProgress'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="update-card" header="修改进度">
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