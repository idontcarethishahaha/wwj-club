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
  { label: '资源管理', icon: 'List' },
  { label: '房间列表', icon: 'OfficeBuilding' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索房间名', callback: pageByTitle }
]

// 表格列信息
const tableColumns = [
  { label: '名称', prop: 'title' },
  { label: '容量', prop: 'capacity', type: 'tag', suffix: ' 人' },
  { label: '地址', prop: 'address', type: 'card' },
  { label: '描述', prop: 'info', type: 'card' },
]

// 表格数据 + 分页信息 + 房间名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const roomTitle = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'room' },
    params: { pageNum, pageSize },
    records, pageInfo
  };
  if (roomTitle.value) { //如果用户查询时填写了房间名称，就额外再增加一个查询参数
    config.params.title = roomTitle.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索房间名
function pageByTitle(val) {
  if (val || roomTitle.value) { //仅当输入框有值，或者roomTitle不为空时，发送分页查询（按房间名）
    roomTitle.value = val;
    page();
  }
}

// 删除房间成功时，显示消息提醒
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
  <my-table module="room"
            insert-page="/RoomInsert"
            update-page="/RoomUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>