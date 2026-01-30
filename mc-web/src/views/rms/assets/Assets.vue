<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import { myPage } from "@/request/index.js";
import { selectApi, deleteApi, deleteBatchApi, pageApi } from "@/api/axios.js";
import { ElMessage } from "element-plus";
import {PROJECT_INFO} from "@/const/index.js";

// 导航项列表
const navItems = [
  { label: '资源管理', icon: 'List' },
  { label: '资产列表', icon: 'Coin' },
]

// 数据头
const headItems = [
  { type: 'ipt', span: 5, placeholder: '搜索资产', callback: pageByTitle }
]

// 表格列信息
const tableColumns = [
  { label: '名称', prop: 'title' ,width:100},
  { label: '图片', prop: 'picture',type:'img',minio:minio},
  { label: '单价', prop: 'price',width:80},
  { label: '货币单位', prop: 'priceUnit',width:80},
  { label: '库存', prop: 'stock' ,width:80},
  { label: '库存单位', prop: 'stockUnit',width:80},
  { label: '总库存', prop: 'total',width:80},
  { label: '描述', prop: 'info' , type: 'card'}
]
//封装一个访问Minio资源分发的函数
function minio(src){
  return  PROJECT_INFO.minioHost+'/assets/'+src;
}

// 表格数据 + 分页信息 + 资产名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });
const Title = ref();

// 分装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'assets' },
    params: { pageNum, pageSize },
    records, pageInfo
  };
  if (Title.value) { //如果用户查询时填写了资产名称，就额外再增加一个查询参数
    config.params.title = Title.value
  }
  // 发送分页查询请求
  await myPage(config);
}

// 搜索资产名
function pageByTitle(val) {
  if (val || Title.value) { //仅当输入框有值，或者Title不为空时，发送分页查询
    Title.value = val;
    page();
  }
}

// 删除资产成功时，显示消息提醒
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
  <my-table module="assets"
            insert-page="/AssetsInsert"
            update-page="/AssetsUpdate"
            :page-info="pageInfo"
            :columns="tableColumns"
            :records="records"
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"></my-table>
</template>

<style scoped lang="scss">

</style>