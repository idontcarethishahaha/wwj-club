<script setup>
import MyNav from '@/components/MyNav.vue'
import MyTable from '@/components/MyTable.vue'
import MyHead from '@/components/MyHead.vue'
import {ref,onMounted,reactive} from "vue";
import {myPage} from "@/request/index.js";
import {deleteApi,deleteBatchApi,pageApi} from "@/api/axios.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

// 导航项列表
const navItems = [
  {label: '用户管理', icon: 'Avatar'},
  {label: '角色列表', icon: 'UserFilled'},
]
// 数据头
const headItems = [
  {type: 'ipt', span: 5, placeholder: '搜索角色名', callback: pageByTitle}
]
// 表格列信息
const tableColumns = [
  {label: '名称', prop: 'title'},
  {label: '描述', prop: 'info',type: 'card',width:420}
]
//表格数据 + 分页信息 + 角色名称
const records = ref();
const pageInfo = reactive({pageNum: 1, pageSize: 5, total: 0, callback: page});
const roleTitle = ref();

// 封装一个分页查询函数,异步
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: {module: 'role'},
    params: {pageNum, pageSize},
    records, pageInfo
  };
  if (roleTitle.value) { //如果用户查询时填写了角色名称，就额外再增加一个查询参数
    config.params.title = roleTitle.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索角色名
function pageByTitle(val) {
  if (val || roleTitle.value) { //仅当输入框有值，或者roleTitle不为空时，发送分页查询（按角色名）
    pageInfo.pageNum = 1; // 重置页码为1，保证搜索从第一页开始
    roleTitle.value = val;
    page();
  }
}

// 删除角色成功时，显示消息提醒
function deleteSuccess() {
  ElMessage.success('删除成功');
  // 删除后重新查询列表，保证数据最新
  page();
}

// 当网页挂载完成，默认执行分页查询
onMounted(() => page());

const buttons = [
  {label:'重设菜单',icon:'Edit',callback:resetRoleMenus}
]

function resetRoleMenus(item){
  // 点击重置菜单按钮后跳转到更新页面，同时携带角色id环绕角色名称
  router.push({
    path:'/RoleUpdateMenus',
    query: {
      roleId: item.id,
      roleTitle: item.title
    }
  })
}
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :buttons="buttons"
            module="role"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"
            insert-page="/RoleInsert"
            update-page="/RoleUpdate"></my-table>
</template>

<style scoped lang="scss">

</style>