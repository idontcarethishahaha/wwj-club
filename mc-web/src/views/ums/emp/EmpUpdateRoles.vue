<script setup>
import MyNav from "@/components/MyNav.vue";
import router from "@/router/index.js";
import {getResponseData} from "@/request/index.js";
import {listApi} from "@/api/axios.js";
import {onMounted,ref,reactive} from "vue";
import {ElMessage} from "element-plus";
import {listByEmpId,updateByEmpId} from "@/api/role.js";

//从路由中获取传递的参数：员工的ID和姓名
const empId = router.currentRoute.value.query['empId']
const realname = router.currentRoute.value.query['realname']

//定义导航项
const navItems = [
  { label: '用户管理', icon: 'Avatar' },
  { label: '员工列表', icon: 'HomeFilled',url:'/Emp' },
  {label:'分配角色',icon:'Edit'}
]

//定义两个数组，分别保存：全部的角色信息和当前分配给该员工的角色ID
const allRoles = ref([])
const roleIds = ref([])

onMounted(async()=>{
  Object.values(getResponseData(await listApi({module:'role'})))
      .forEach(item=>{
        allRoles.value.push({label:item['title'],key:item['id']})
      });
  Object.values(getResponseData(await listByEmpId(empId)))
      .forEach(item=>{
        roleIds.value.push(item['id'])
      });
} )

async function updateRoles(){
  let data = getResponseData(await updateByEmpId(empId,roleIds.value))
  if(data){
    ElMessage.success('角色重设成功，重新登录成功即可生效！')
  }
}
</script>

<template>
  <div style="text-align: center">
    <my-nav :items="navItems" />
    <el-transfer
        style="margin-top: 20px;"
        filterable
        filter-placeholder="输入关键字"
        v-if="allRoles.length > 0"
        v-model="roleIds"
        :data="allRoles"
        :titles="['全部可选角色', '【' + realname + '】已选角色']"
        :props="{key: 'key', label: 'label'}"
        :button-texts="['移除', '添加']"
    >
      <template #left-footer>
        <el-text type="info">tips: 请选择员工的角色!</el-text>
      </template>
      <template #right-footer>
        <el-button type="primary" @click="updateRoles">确认修改</el-button>
      </template>
    </el-transfer>
  </div>
</template>

<style scoped lang="scss">

</style>