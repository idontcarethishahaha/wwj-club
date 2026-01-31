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
  { label: '用户管理', icon: 'Avatar' },
  { label: '员工列表', icon: 'HomeFilled' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索员工名', callback: pageByTitle }
]

// 表格列信息
const tableColumns = [
  { label: '员工账号', prop: 'username' },
  { label: '真实姓名', prop: 'realname' },
  { label: '手机号码', prop: 'phone' },
  { label: '邮箱地址', prop: 'email' },
  { label: '性别', prop: 'gender', type: 'tag', formatter: (val) => {
      const genderMap = { 0: '女', 1: '男', 2: '保密' };
      return genderMap[val] || '未知';
    }
  },
  { label: '年龄', prop: 'age', type: 'tag' },
  { label: '入职时间', prop: 'hiredate', type: 'card' },
  { label: '所属部门', prop: 'fk_dept_id', type: 'card' },
  { label: '现居住地', prop: 'address', type: 'card' },
  { label: '员工描述', prop: 'info', type: 'card' },
];

// 表格数据 + 分页信息 + 员工名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const Title = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'emp' },
    params: { pageNum, pageSize },
    records, pageInfo
  };
  if (Title.value) { //如果用户查询时填写了xx名称，就额外再增加一个查询参数
    config.params.title = Title.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索xx名
function pageByTitle(val) {
  if (val || Title.value) { //仅当输入框有值，或者Title不为空时，发送分页查询（按xx名）
    Title.value = val;
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
</script>

<template>
  <my-nav :items="navItems"></my-nav>
  <my-head :items="headItems"></my-head>
  <my-table module="emp"
            insert-page="/EmpInsert"
            update-page="/EmpUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>