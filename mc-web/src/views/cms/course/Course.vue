<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import { myPage } from "@/request/index.js";
import { selectApi, deleteApi, deleteBatchApi, pageApi } from "@/api/axios.js";
import { ElMessage } from "element-plus";

// 导航项列表
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '课程列表', icon: 'Reading' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索课程名', callback: pageByTitle },
  { type: 'ipt', span: 5, placeholder: '搜索分类', callback: pageByCategory }
]

// 表格列信息
const tableColumns = [
  { label: '名称', prop: 'title' },
  { label: '分类', prop: 'category' },
  { label: '顺序', prop: 'idx' },
  { label: '学时', prop: 'hours', type: 'tag',suffix: '小时' },
  { label: '介绍', prop: 'info', type: 'card' },
]

// 表格数据 + 分页信息 + 名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const Title = ref();
const category = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'course' },
    params: { pageNum, pageSize },
    records, pageInfo
  };
  if (Title.value) { //如果用户查询时填写了名称，就额外再增加一个查询参数
    config.params.title = Title.value
  }
  if (category.value) {
    config.params.title = category.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索
function pageByTitle(val) {
  if (val || Title.value) { //仅当输入框有值，或者Title不为空时，发送分页查询
    Title.value = val;
    page();
  }
}

function pageByCategory(val) {
  if (val || category.value) {
    category.value = val;
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
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table module="course"
            insert-page="/CourseInsert"
            update-page="/CourseUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>