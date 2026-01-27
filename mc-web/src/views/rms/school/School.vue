<script setup>
import MyNav from '@/components/MyNav.vue'
import MyTable from '@/components/MyTable.vue'
import MyHead from '@/components/MyHead.vue'
import {ref,onMounted,reactive} from "vue";
import {myPage} from "@/request/index.js";
import {deleteApi,deleteBatchApi,pageApi} from "@/api/axios.js";
import {ElMessage} from "element-plus";

// 导航项列表
const navItems = [
  {label: '资源管理', icon: 'List'},
  {label: '学校列表', icon: 'OfficeBuilding'},
]
// 数据头
const headItems = [
  {type: 'ipt', span: 5, placeholder: '搜索学校名', callback: pageByTitle}
]
// 表格列信息
const tableColumns = [
  {label: '名称', prop: 'title'},
  {label: '院系', prop: 'college', type: 'card'},
  {label: '专业', prop: 'major', type: 'card'},
  {label: '班级', prop: 'clazz', type: 'card'},
  {label: '地址', prop: 'address', type: 'card'},
  {label: '描述', prop: 'info', type: 'card'},
]
//表格数据 + 分页信息 + 学校名称
const records = ref();
const pageInfo = reactive({pageNum: 1, pageSize: 5, total: 0, callback: page});
const schoolTitle = ref();

// 封装一个分页查询函数,异步
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: {module: 'school'},
    params: {pageNum, pageSize},
    records, pageInfo
  };
  if (schoolTitle.value) { //如果用户查询时填写了学校名称，就额外再增加一个查询参数
    config.params.title = schoolTitle.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索学校名
function pageByTitle(val) {
  if (val || schoolTitle.value) { //仅当输入框有值，或者schoolTitle不为空时，发送分页查询（按学校名）
    pageInfo.pageNum = 1; // 重置页码为1，保证搜索从第一页开始
    schoolTitle.value = val;
    page();
  }
}

// 删除学校成功时，显示消息提醒
function deleteSuccess() {
  ElMessage.success('删除成功');
  // 删除后重新查询列表，保证数据最新
  page();
}

// 当网页挂载完成，默认执行分页查询
onMounted(() => page());
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            module="school"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"
            insert-page="/SchoolInsert"
            update-page="/SchoolUpdate"></my-table>
</template>

<style scoped lang="scss">

</style>