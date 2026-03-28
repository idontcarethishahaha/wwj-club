<script setup>
import MyNav from "@/components/MyNav.vue";
import { getResponseData } from "@/request/index.js";
import { onMounted, ref } from "vue";
import { listApi, updateApi } from "@/api/axios.js";
import { listByRoleId, updateByRoleId } from "@/api/menu.js";
import { ElMessage } from "element-plus";
import router from "@/router/index.js";

  // 从路由中获取角色ID和名称
const roleId = router.currentRoute.value.query['roleId'];
const roleTitle = router.currentRoute.value.query['roleTitle'];

  // 定义路径导航
const navItems = [
  {label: '用户管理', icon: 'Avatar'},
  {label: '角色列表', icon: 'UserFilled', url: '/Role'},
  {label: '重置菜单', icon: 'Edit'},
];

// 定义保存所有菜单
const allMenus = ref([]);
// 保存当前角色的菜单ID
const menuIds = ref([]);
// 保存菜单的父子关系
const idToPidMap = {};

// 定义函数，构建菜单树
function buildMenuIds(menuIds) {
  let result = [];
  for (let i in menuIds) {
    result.push(menuIds[i]);//先将所有的menuID存到数组里
    let parentMenuId = idToPidMap[menuIds[i]];
    // 再将它的父菜单ID也存到result数组里
    if (result.indexOf(parentMenuId) === -1) {
      result.push(parentMenuId);
    }
  }
  return result;
}

// 调用服务端接口查询所有菜单和当前角色已配置菜单
onMounted(async () => {
  Object.values(getResponseData(await listApi( {module: 'menu'})))
      .forEach(menu => {
        idToPidMap[menu['id']] = menu['pid'];//保存子菜单和父菜单ID的映射
        if (menu['pid'] !== 0) {
          allMenus.value.push({
            label: menu['parentTitle'] + ' / ' + menu['title'],
            key: menu['id']
          })
        }
      });

  Object.values(getResponseData(await listByRoleId(roleId)))
      .forEach(menu => {
        menuIds.value.push(menu['id']);
      });
})

//更新角色对应菜单的函数
async function updateRoleMenus(){
  let data = getResponseData(await updateByRoleId(roleId,buildMenuIds(menuIds.value)))
  if(data){
    ElMessage.success('该角色映射菜单已重置，重启后生效！')
  }
}
</script>

<template>
  <div style="margin-top: 60px;">
    <my-nav :items="navItems" />
    <el-transfer
        filterable
        filter-placeholder="输入关键字"
        v-model="menuIds"
        :data="allMenus"
        :titles="['全部可选菜单', `【${roleTitle}】已选菜单`]"
        :props="{key: 'key', label: 'label'}"
        :button-texts="['移除', '添加']"
        v-if="allMenus.length > 0"
    >
      <template #left-footer>
        <el-text type="info">Tips：请仔细选择该角色的菜单！</el-text>
      </template>
      <template #right-footer>
        <el-button type="primary" @click="updateRoleMenus" size="small">确认修改</el-button>
      </template>
    </el-transfer>
  </div>
</template>

<style scoped lang="scss">
.role-menus-body {
  text-align: center; // 内容居中

  .role-menus-transfer {
    margin-top: 65px; // 上外边距
  }
}

:deep(.el-transfer-panel) {
  width: 300px; // 宽度
}

:deep(.el-transfer-panel__body) {
  height: 400px; // 高度
}

:deep(.el-transfer-panel__footer) {
  text-align: center; // 内容居中
}
</style>