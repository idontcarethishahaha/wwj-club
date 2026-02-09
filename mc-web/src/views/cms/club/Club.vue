<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import {getResponseData, myPage} from "@/request/index.js";
import {selectApi, deleteApi, deleteBatchApi, pageApi, listApi, MC_MANAGE_AXIOS} from "@/api/axios.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {isNotNull} from "@/util/index.js";

// 保存方向列表
const directionOptions = reactive([]);
// 页面加载完成后获取方向列表
onMounted(async () => {
  Object.values(getResponseData(await listApi({module: 'direction'})))
      .forEach(dir => {
        directionOptions.push(
            {label: dir['title'], value: dir['id']}
        )
      });
});

// 导航项列表
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '班级列表', icon: 'Platform' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索班级名称', callback: pageByTitle },
  { type: 'opt', span: 5, placeholder: '搜索方向查询', callback: pageByDirection ,options:directionOptions}
]

// 表格列信息
const tableColumns = [
  {label: '名称', prop: 'title', width: 120},
  {label: '状态', prop: 'endTime', type: 'tag', width: 120, format: e => isNotNull(e) ? '已结课' : '学习中'},
  {label: '方向', prop: 'direction.title', width: 120},
  {label: '标签', prop: 'tag', type: 'tag', width: 200},
  {label: '开班时间', prop: 'startTime'},
  {label: '预计结课时间', prop: 'expectedEndTime'},
  {label: '实际结课时间', prop: 'endTime'},
  {label: '介绍', prop: 'info', type: 'card'},
]

// 表格数据 + 分页信息 + 名称
const records = ref();
const pageInfo = reactive({pageNum: 1, pageSize: 5, total: 0, callback: page});
const title = ref();
const fkDirectionId = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: {module: 'club'},
    params: {pageNum, pageSize},
    records,
    pageInfo,
  };
  if (title.value) { //如果用户查询时填写了名称，就额外再增加一个查询参数
    config.params.title = title.value
  }
  if (fkDirectionId.value) {
    config.params.fkDirectionId = fkDirectionId.value;
  }
  // 发送分页查询请求
  await myPage(config);
}
// 搜索名
function pageByTitle(val) {
  if (val || title.value) { //仅当输入框有值，或者title不为空时，发送分页查询（按xx名）
    title.value = val;
    page();
  }
}

function pageByDirection(val) {
  if (val || fkDirectionId.value) {
    fkDirectionId.value = val;
    page();
  }
}

// 删除成功时，显示消息提醒
function deleteSuccess() {
  ElMessage.success('删除成功');
  page();//刷新网页
}

// 当网页挂载完成，默认执行分页查询
onMounted(() => page());

const buttons = [
  {label:'结束学习',icon:'Finished',callback:finishClub}
]

// 定义一个结束课程进度的函数
function finishClub(item) {
  ElMessageBox.confirm('确定该班级已完成所有课程学习吗？', '班级结课确认', {
    type: 'primary',
    confirmButtonText: '确定',
    confirmButtonType: 'primary',
    cancelButtonText: '取消',
    cancelButtonType: 'info'
  }).then(() => {
    MC_MANAGE_AXIOS.put(`club/finish/${item.id}`)
        .then(res => {
          const data = getResponseData(res);
          if (data) {
            ElMessage.success('班级结课操作成功');
            page();//执行Page函数刷新页面
          } else {
            ElMessage.error('班级结课操作失败');
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
  <my-table module="club"
            :buttons="buttons"
            insert-page="/ClubInsert"
            update-page="/ClubUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>