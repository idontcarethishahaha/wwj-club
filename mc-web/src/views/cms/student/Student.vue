<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import {selectApi, deleteApi, deleteBatchApi, pageApi, listApi, excelApi} from "@/api/axios.js";
import { ElMessage } from "element-plus";
import {getResponseData, myPage} from "@/request/index.js";
import {genderFormat, studentStateFormat} from "@/util/index.js";
import {PROJECT_INFO} from "@/const/index.js";

const clubOptions = reactive([])
const schoolOptions = reactive([])
onMounted(async()=>{
  Object.values(getResponseData(await listApi({module:'club'})))
      .forEach(club=>{
        clubOptions.push({label:club['title'],value:club['id']})
      });
  Object.values(getResponseData(await listApi({module:'school'})))
      .forEach(school=>{
        schoolOptions.push({label:school['title'],value:school['id']})
      });
})

// 导航项列表
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '学生列表', icon: 'UserFilled' },
]

function schoolInfoFormat(school){
  if(school)
     return `${school.title}-${school.college}`
  else
    return '';
}

// 数据头
const headItems = [
  {type: 'ipt', span: 5, placeholder: '按学号查找', callback: pageByNumber},
  {type: 'ipt', span: 5, placeholder: '按姓名查找', callback: pageByName},
  {type: 'opt', span: 5, placeholder: '按学校查找', callback: pageBySchool,options:schoolOptions},
  {type: 'opt', span: 5, placeholder: '按班级查找', callback: pageByClub,options:clubOptions},
]

// 查找Minio头像
function minio(src) {
  return PROJECT_INFO.minioHost + '/avatar/' + src;
}

function studentStatusTagTypeFn(statusCode){
  switch(statusCode){
    case 0: return 'primary';
    case 1: return 'success';
    case 2: return 'warning';
    case 3: return 'success';
    default: return 'info';
  }
}

// 表格列信息
const tableColumns = [
  {label: '学号', prop: 'number', width: 100},
  {label: '姓名', prop: 'realname', width: 100},
  { label: '头像', prop: 'avatar',type: 'img',minio:minio },
  {label: '学校', prop: 'school', width: 250,format:schoolInfoFormat},
  {label: '班级', prop: 'club.title', width: 120},
  {label: '状态', prop: 'status', width: 80,type:'tag',format:studentStateFormat,tagTypeFn:studentStatusTagTypeFn},
  {label: '手机号码', prop: 'phone', width: 120},
  {label: '微信号', prop: 'wechat', width: 120},
  {label: '性别', prop: 'gender', type: 'tag', format: genderFormat, tagTypeFn: e => e == 0 ? 'danger' : 'primary' ,width: 80},
  {label: '年龄', prop: 'age', width: 80},
  {label: '省份', prop: 'province', width: 120},
  {label: '咨询老师', prop: 'counselor.realname', width: 100},
  {label: '身份证号码', prop: 'idcard'},
  {label: '入学时间', prop: 'studyDate'},
  {label: '紧急联系人', prop: 'emergencyName', width: 100},
  {label: '紧急联系电话', prop: 'emergencyPhone', width: 120},
  {label: '现居住地', prop: 'address', type: 'card'},
  {label: '描述', prop: 'info', type: 'card'},
]

// 表格数据 + 分页信息 + xx名称
const records = ref();
const pageInfo = reactive({pageNum: 1, pageSize: 5, total: 0, callback: page});
const number = ref();
const realname = ref();
const fkSchoolId = ref();
const fkClubId = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: {module: 'student'},
    params: {pageNum, pageSize},
    records, pageInfo
  };

  if (number.value) {
    config.params['number'] = number.value;
  }
  if (realname.value) {
    config.params['realname'] = realname.value;
  }
  if (fkSchoolId.value) {
    config.params['fkSchoolId'] = fkSchoolId.value;
  }
  if (fkClubId.value) {
    config.params['fkClubId'] = fkClubId.value;
  }

  // 发送分页查询请求
  await myPage(config);
}

// 搜索xx名
function pageByNumber(val) {
  if (val || number.value) { //仅当输入框有值，或者number不为空时，发送分页查询（按xx名）
    number.value = val;
    page();
  }
}

function pageByName(val) {
  if (val || realname.value) { //仅当输入框有值，或者realname不为空时，发送分页查询（按xx名）
    realname.value = val;
    page();
  }
}

function pageBySchool(val) {
  if (val || fkSchoolId.value) { //仅当输入框有值，或者fkSchoolId不为空时，发送分页查询（按xx名）
    fkSchoolId.value = val;
    page();
  }
}

function pageByClub(val) {
  if (val || fkClubId.value) { //仅当输入框有值，或者fkClubId不为空时，发送分页查询（按xx名）
    fkClubId.value = val;
    page();
  }
}

// 删除xx成功时，显示消息提醒
function deleteSuccess() {
  ElMessage.success('删除成功');
  page();//刷新网页
}

// 当网页挂载完成，默认执行分页查询
onMounted(() => page());

function downloadExcel(){
  excelApi('/student/excel','学生数据表');
}
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table module="student"
            insert-page="/StudentInsert"
            update-page="/StudentUpdate"
            :excel-api="downloadExcel"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>