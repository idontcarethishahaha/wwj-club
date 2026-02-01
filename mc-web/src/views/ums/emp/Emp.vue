<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import {getResponseData, myPage} from "@/request/index.js";
import {selectApi, deleteApi, deleteBatchApi, pageApi, listApi} from "@/api/axios.js";
import { ElMessage } from "element-plus";
import {PROJECT_INFO} from "@/const/index.js";
import {genderFormat} from "@/util/index.js";

// 导航项列表
const navItems = [
  { label: '用户管理', icon: 'Avatar' },
  { label: '员工列表', icon: 'User' },
]

const deptOptions = reactive([])

//加载部门列表
onMounted(async()=>{
  Object.values(getResponseData(await listApi({module:'dept'})))
      .forEach(dept=>{
        deptOptions.push({
          label:dept.title,
          value:dept.id
        })
      })
})

// 数据头
const headItems = [
  {type: 'ipt', span: 5, placeholder: '输入账号搜索', callback: pageByUsername},
  {type: 'ipt', span: 5, placeholder: '输入手机号码搜索', callback: pageByPhone},
  {type: 'ipt', span: 5, placeholder: '输入姓名搜索', callback: pageByRealname},
  {type: 'opt', span: 5, placeholder: '选择部门搜索', callback: pageByDeptId,options:deptOptions},
]

const tableColumns = [
  { label: '头像', prop: 'avatar',type: 'img',minio:minio },
  { label: '姓名', prop: 'realname',width:80 },
  { label: '部门', prop: 'dept.title' },
  { label: '性别', prop: 'gender', type: 'tag', format: genderFormat, tagTypeFn: e => e == 0 ? 'primary' : 'success' },
  { label: '介绍', prop: 'info', type: 'card' },
  { label: '登录账号', prop: 'username' },
  { label: '手机号码', prop: 'phone' },
  { label: '身份证号', prop: 'idcard' },
  { label: '微信', prop: 'wechat' },
  { label: '邮箱', prop: 'email' },
  { label: '年龄', prop: 'age' },
  { label: '籍贯', prop: 'province' },
  { label: '现居住地', prop: 'address', type: 'card' },
  { label: '入职时间', prop: 'hiredate' },
];

// 查找Minio头像
function minio(src) {
  return PROJECT_INFO.minioHost + '/avatar/' + src;
}

// 表格数据 + 分页信息 + 名称
const records = ref();
const pageInfo = reactive({pageNum: 1, pageSize: 5, total: 0, callback: page});
const username = ref();
const realname = ref();
const phone = ref();
const deptId = ref();

function pageByUsername(val) {
  if (val || username.value) {
    username.value = val;
    page();
  }
}

function pageByPhone(val) {
  if (val || phone.value) {
    phone.value = val;
    page();
  }
}

function pageByRealname(val) {
  if (val || realname.value) {
    realname.value = val;
    page();
  }
}

function pageByDeptId(val) {
  if (val || deptId.value) {
    deptId.value = val;
    page();
  }
}


// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: {module: 'emp'},
    params: {pageNum, pageSize},
    records, pageInfo
  };
  if (username.value) {
    config.params['username'] = username.value;
  }
  if (realname.value) {
    config.params['realname'] = realname.value;
  }
  if (phone.value) {
    config.params['phone'] = phone.value;
  }
  if (deptId.value) {
    config.params['fkDeptId'] = deptId.value;
  }
  // 发送分页查询请求
  await myPage(config);
}

// 删除员工成功时，显示消息提醒
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