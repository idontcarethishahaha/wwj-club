<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import {getResponseData, myPage} from "@/request/index.js";
import {deleteApi, deleteBatchApi, pageApi, listApi, MC_MANAGE_AXIOS} from "@/api/axios.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {isNotNull} from "@/util/index.js";

// 分别定义班级和课程的options
const clubOptions = reactive([])
const courseOptions = reactive([])
onMounted(async()=>{
  Object.values(getResponseData(await listApi({module:'club'})))
      .forEach(club=>{
        clubOptions.push({label:club['title'],value:club['id']})
      });
  Object.values(getResponseData(await listApi({module:'course'})))
      .forEach(course=>{
        courseOptions.push({label:course['title'],value:course['id']})
      });
})

// 导航项列表
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '进度列表', icon: 'More' },
]

// 数据头
const headItems = [
  { type: 'opt', span: 5, placeholder: '按班级查找', callback: pageByClub,options:clubOptions },
  { type: 'opt', span: 5, placeholder: '按课程查找', callback: pageByCourse,options: courseOptions },
]

// 表格列信息
const tableColumns = [
  { label: '班级', prop: 'club.title' ,width:100},
  { label: '课程', prop: 'course.title' ,width:140},
  { label: '教室', prop: 'room.title' ,width:180},
  { label: '状态', prop: 'endTime',type:'tag',format:e => isNotNull(e)?'已结束':'进行中'},
  { label: '主讲老师', prop: 'teacher.realname',width:100},
  { label: '助教老师', prop: 'assistant.realname',width:100},
  { label: '班主任', prop: 'manager.realname',width:100},
  { label: '开始时间', prop: 'startTime',type: 'datetime'},
  { label: '预计结束时间', prop: 'exceptedEndTime',type: 'datetime'},
  { label: '实际结束时间', prop: 'EndTime',type: 'datetime'},
  { label: '序号', prop: 'idx'},
  { label: '进度描述', prop: 'info',type: 'card'},
]

// 表格数据 + 分页信息 + xx名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const fkClubId = ref();//班级ID
const fkCourseId = ref();//课程ID

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'clubProgress' },
    params: { pageNum, pageSize },
    records, pageInfo
  };
  if (fkClubId.value) {
    config.params.fkClubId = fkClubId.value
  }
  if (fkCourseId.value) {
    config.params.fkCourseId = fkCourseId.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索xx名
function pageByClub(val) {
  if (val || fkClubId.value) {
    fkClubId.value = val;
    page();
  }
}

function pageByCourse(val) {
  if (val || fkCourseId.value) {
    fkCourseId.value = val;
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

const buttons = [
  {label: '结束进度', icon: 'Finished', callback: finishProgress}
]

// 定义一个结束课程进度的函数
function finishProgress(item) {
  ElMessageBox.confirm('确定要结束该课程进度吗？', '结束课程进度确认', {
    type: 'primary',
    confirmButtonText: '确定',
    confirmButtonType: 'primary',
    cancelButtonText: '取消',
    cancelButtonType: 'info'
  }).then(() => {
    MC_MANAGE_AXIOS.put(`clubProgress/finish/${item.id}`)
        .then(res => {
          const data = getResponseData(res);
          if (data) {
            ElMessage.success('课程结束');
            page();//执行Page函数刷新页面
          } else {
            ElMessage.error('结束课程失败');
          }
        })
  }).catch(() => {
    ElMessage.info('已取消');
  });
}
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table module="clubProgress"
            :buttons="buttons"
            insert-page="/ClubProgressInsert"
            update-page="/ClubProgressUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>