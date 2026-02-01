<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import { myPage } from "@/request/index.js";
import { selectApi, deleteApi, deleteBatchApi, pageApi } from "@/api/axios.js";
import { ElMessage } from "element-plus";
import router from "@/router/index.js";

//获取会话存储中的父菜单ID和父菜单title
const pid = sessionStorage.getItem('pid');
const parentTitle = sessionStorage.getItem('parentTitle');

// 导航项列表
const navItems = [
  { label: '用户管理', icon: 'Avatar' },
  { label: parentTitle, icon: 'Menu' },
  { label: '子菜单列表', icon: 'Menu' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索子菜单', callback: pageByTitle }
]

// 表格列信息
const tableColumns = [
  { label: '名称', prop: 'title' },
  { label: '图标', prop: 'icon' ,width: 120,type: 'icon'},
  { label: 'URL', prop: 'url', type: 'card',width: 200 },
  { label: '介绍', prop: 'info', type: 'card' ,width:400},
]

// 表格数据 + 分页信息 + 名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const Title = ref();


// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'menu' },
    params: { pageNum, pageSize,pid:pid},
    records, pageInfo
  };
  if (Title.value) { //如果用户查询时填写了名称，就额外再增加一个查询参数
    config.params.title = Title.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索xx名
function pageByTitle(val) {
  if (val || Title.value) { //仅当输入框有值，或者Title不为空时，发送分页查询
    Title.value = val;
    page();
  }
}

// 删除菜单成功时，显示消息提醒
function deleteSuccess() {
  ElMessage.success('删除成功');
  page();//刷新网页
}

// 当网页挂载完成，默认执行分页查询
onMounted(() => page());

const buttons = [
  {label:'查看子菜单',icon:'Menu',callback:toSubMenu}
]
// 跳转到子菜单列表
function toSubMenu(row){
  // 将当前行的菜单id和菜单名称保存到会话存储，去子菜单的时候再取出
  sessionStorage.setItem('pid',row['id']);
  sessionStorage.setItem('parentTitle',row['title']);
  router.push('/SubMenu');
}
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table module="menu"
            insert-page="/MenuInsert"
            update-page="/MenuUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :buttons="buttons"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>