<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import { myPage } from "@/request/index.js";
import { deleteApi, deleteBatchApi, pageApi } from "@/api/axios.js";
import { ElMessage } from "element-plus";

// 导航项列表
const navItems = [
  { label: '用户管理', icon: 'Avatar' },
  { label: '部门列表', icon: 'HomeFilled' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索部门名', callback: pageByTitle }
]

// 表格列信息
const tableColumns = [
  { label: '名称', prop: 'title' },
  { label: '房间', prop: 'room.title' },
  { label: '描述', prop: 'info', type: 'card',width:520 },
]

// 表格数据 + 分页信息 + 部门名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const Title = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'dept' },
    params: { pageNum, pageSize },
    records, pageInfo
  };
  if (Title.value) { //如果用户查询时填写了xx名称，就额外再增加一个查询参数
    config.params.title = Title.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索部门名
function pageByTitle(val) {
  if (val || Title.value) { //仅当输入框有值，或者Title不为空时，发送分页查询（按xx名）
    Title.value = val;
    page();
  }
}

// 删除部门成功时，显示消息提醒
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
  <my-table module="dept"
            insert-page="/DeptInsert"
            update-page="/DeptUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>