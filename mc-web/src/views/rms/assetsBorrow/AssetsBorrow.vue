<script setup>
import MyNav from "@/components/MyNav.vue";
import MyTable from "@/components/MyTable.vue";
import MyHead from "@/components/MyHead.vue";
import { onMounted, ref, reactive } from "vue";
import {getResponseData, myPage} from "@/request/index.js";
import { selectApi, deleteApi, deleteBatchApi, pageApi } from "@/api/axios.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {PROJECT_INFO} from "@/const/index.js";
import {isNotNull} from "@/util/index.js";
import {returnAssetsApi} from "@/api/assets.js";

// 导航项列表
const navItems = [
  { label: '资源管理', icon: 'List' },
  { label: '资产申请列表', icon: 'Coin' },
]
//资产
const assetsOptions = reactive([])
//申请人
const empOptions = reactive([])

// 数据头
// 过后改成下拉框选择
const headItems = [
  { type: 'opt',options:empOptions, span: 5, placeholder: '请输入申请人ID', callback: pageByEmpId },
  { type: 'opt',options:assetsOptions, span: 5, placeholder: '请输入资产ID', callback: pageByAssetsId }
]

//封装一个访问Minio资源分发的函数
function minio(src){
  return  PROJECT_INFO.minioHost+'/assets/'+src;
}

// 表格列信息
const tableColumns = [
  {label: '资产名称', prop: 'assets.title'},
  // 根据归还时间是否为空判断状态，如果归还时间不为空则状态为 '已归还'
  {label: '状态', prop: 'returnTime', format: e => isNotNull(e) ? '已归还' : ''},
  {label: '图片', prop: 'assets.picture', type: 'img', minio: minio},
  {label: '申请数量', prop: 'count'},
  {label: '资产单位', prop: 'assets.stockUnit'},
  {label: '申请人', prop: 'emp.realname'},
  {label: '申请时间', prop: 'borrowTime'},
  {label: '预计归还时间', prop: 'expectedReturnTime'},
  {label: '实际归还时间', prop: 'returnTime'},
  {label: '备注', prop: 'info'},
]

// 表格数据 + 分页信息 + 资产申请名称
const records = ref();
const pageInfo = reactive({ pageNum: 1, pageSize: 5, total: 0, callback: page });

// 保存资产ID和员工ID
const fkAssetsId = ref();
const fkEmpId = ref();

// 封装一个分页查询函数
async function page(pageNum = pageInfo['pageNum'], pageSize = pageInfo['pageSize']) {
  let config = {
    api: pageApi,
    args: { module: 'assetsBorrow' },
    params: { pageNum, pageSize },
    records, pageInfo
  };

  if (fkAssetsId.value) { //如果用户查询时填写了资产ID，就额外再增加一个查询参数
    config.params.fkAssetsId = fkAssetsId.value
  }

  if (fkEmpId.value) { //如果用户查询时填写了员工ID，就额外再增加一个查询参数
    config.params.fkEmpId = fkEmpId.value
  }

  // 发送分页查询请求
  await myPage(config);
}

// 搜索
function pageByEmpId(val) {
  if (val || fkEmpId.value) {
    fkEmpId.value = val;
    page();
  }
}

function pageByAssetsId(val) {
  if (val || fkAssetsId.value) {
    fkAssetsId.value = val;
    page();
  }
}

// 删除成功时，显示消息提醒
function deleteSuccess() {
  ElMessage.success('删除成功');
  page(); //刷新网页
}

// 当网页挂载完成，默认执行分页查询
onMounted(async() => {
  await page();
});

const buttons = [
  {label: '归还资产', callback: returnAssets}
]

function returnAssets(row) {
  if (isNotNull(row['returnTime'])) {
    ElMessage.warning('该资产申请已归还，不要重复操作！');
    return;
  }

  ElMessageBox.confirm('确认要归还该资产吗？')
      .then(() => {
        returnAssetsApi(row['id'])
            .then(res => {
              let data = getResponseData(res);
              if (data) {
                ElMessage.success('归还成功');
                page();
              } else {
                ElMessage.error(data.coderMessage);
              }
            })
      })
      .catch(() => {
        ElMessage.info('已取消');
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
            :delete-api="deleteApi"
            :delete-batch-api="deleteBatchApi"
            :delete-callback="deleteSuccess"
            insert-page="/AssetsBorrowInsert"
            update-page="/AssetsBorrowUpdate"
            module="assetsBorrow"></my-table>
</template>

<style scoped lang="scss">

</style>