<script setup>
import router from "@/router/index.js";
import MyIcon from "@/components/MyIcon.vue";
import {ElNotification} from "element-plus";
import {PROJECT_INFO, PROJECT_SKILLS} from "@/const/index.js";
import {onBeforeMount, onMounted, ref, shallowRef} from "vue";
import {getResponseData} from "@/request/index.js";
import {listByEmpId} from "@/api/menu.js";
import vuex from "@/vuex/index.js";

// 获取登录员工信息和头像
const loginEmp = JSON.parse(sessionStorage.getItem('loginEmp'))
const avatar = PROJECT_INFO.minioHost + '/avatar/' + loginEmp['avatar']
// 定义系统的logo
const logo = PROJECT_INFO.minioHost+'/logo.png';

//test=================================
console.log("登录员工信息：", loginEmp);
console.log("头像字段：", loginEmp ? loginEmp['avatar'] : "loginEmp 不存在");
//test=====================================

//定义Menu相关对象
let currentMenuIndex = shallowRef(router.currentRoute.value['path'])
const isCollapse = shallowRef(false)//是否折叠菜单
const menus = ref()

// 定义主界面默认展示内容的对象
const projectInfoDrawer = shallowRef()

function openProjectInfoDrawer(){
  projectInfoDrawer.value = true;
}

// 项目技术栈展示抽屉
const projectSkillDrawer = shallowRef()
function openProjectSkillDrawer(){
  projectSkillDrawer.value = true;
}

//项目日历展示抽屉
const calendarDrawer = shallowRef()
let calendarDate = shallowRef()

function openCalendarDrawer(){
  calendarDate = new Date()//获取最新的日历
  calendarDrawer.value = true;
}

// 显示系统消息
function notify(){
  ElNotification.info({
    title:'通知列表',
    message:'暂无通知',
    position:'bottom-right'
  })
}

// 加载菜单
onMounted(() => {
  listByEmpId(loginEmp['id']).then(res => {
    let data = getResponseData(res);
    if (data) {
      // 使用查询到的菜单列表，构建一个菜单树
      menus.value = buildMenuTree(data);
    }
  });
})

// 定义函数构建菜单树
function buildMenuTree(menus) {
  const menuObj = {};
  const parentMenus = []; // 存父菜单
  // 遍历菜单列表，先把父菜单选出来
  menus.forEach(menu => {
    // 缓存每个菜单：ID做key，对象本身做value
    menuObj[menu['id']] = menu;
    if (menu.pid == 0) {
      menu['subMenus'] = []; // 定义子菜单属性，默认为空数组
      parentMenus.push(menu); // 将菜单存入父菜单列表
    }
  });
  // 再遍历一次，找出每个父菜单关联的子菜单
  menus.forEach(menu => {
    if (menu.pid != 0) {
      // 使用pid去缓存中找到父菜单
      const parent = menuObj[menu['pid']];
      if (parent) {
        // 将当前菜单存入他父菜单的子菜单列表
        parent.subMenus.push(menu);
      }
    }
  });
  return parentMenus;
}
//定义一个登出函数
function logout(){
  // 退出系统时抹除掉登录时添加的缓存数据
  vuex.dispatch('setLoginFlag',false);//登录状态为：已登录
  sessionStorage.removeItem('token')
  sessionStorage.removeItem('loginEmp')
  router.push('/')
}
</script>

<template>
  <el-container class="main-body" v-if="menus">
    <!--左边的侧边栏放菜单-->
    <el-aside class="main-body-left" width="collapse" max-width="200px">
      <el-menu class="menus-menu el-menu-vertical-demo" unique-opened router :collapse="isCollapse"
      :default-active="currentMenuIndex">
        <el-image class="logo" :src="logo"></el-image>
        <el-menu-item class="house-item" index="/DashBorder" title="回到后台项目首页">
          <my-icon icon="House" label="DashBoard"/>
        </el-menu-item>
        <el-sub-menu class="menus" v-for="(menu,i) in menus" :key="menu['id']" :index="i.toString()" :title="menu['info']">
          <!--父菜单的图标和标题-->
          <template #title>
            <my-icon :icon="menu['icon']" :label="menu['title']"/>
          </template>
          <el-menu-item class="sub-menus" v-for="subMenu in menu.subMenus"
                        :key="subMenu['id']" :index="subMenu['url']" :title="subMenu['info']">
            <my-icon :icon="subMenu['icon']" :label="subMenu['title']"/>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-container class="main-body-right">
      <el-header class="main-body-right-head">
        <el-row class="is-align-middle">
          <el-col class="fold-expand" :span="2">
            <el-radio-group v-model="isCollapse">
              <el-radio-button :label="!isCollapse">
                <my-icon :icon="isCollapse ? 'Expand' : 'fold'" size="20"></my-icon>
              </el-radio-button>
            </el-radio-group>
          </el-col>
          <el-col :span="7" class="project-title-col">
            <el-popover width="500" :content="PROJECT_INFO.info" placement="bottom-start" trigger="click">
              <template #reference>
                {{PROJECT_INFO.title}}
              </template>
            </el-popover>
          </el-col>
          <el-col :span="7" class="operation-col">
            <el-divider direction="vertical"/>
            <el-tooltip content="全局搜索">
              <el-button icon="Search" size="small" round @click=""></el-button>
            </el-tooltip>
            <el-tooltip content="系统通知">
              <el-button icon="Bell" size="small" round @click="notify"></el-button>
            </el-tooltip>
            <el-tooltip content="项目基本信息">
              <el-button icon="List" size="small" round @click="openProjectInfoDrawer"></el-button>
            </el-tooltip>
            <el-tooltip content="项目技术栈">
              <el-button icon="Management" size="small" round @click="openProjectSkillDrawer"></el-button>
            </el-tooltip>
            <el-tooltip content="日历">
              <el-button icon="calendar" size="small" round @click="openCalenderDrawer"></el-button>
            </el-tooltip>
            <el-divider direction="vertical"/>
          </el-col>
          <el-col :span="2" class="emp-realname-col">
            {{loginEmp['realname']}}
          </el-col>
          <el-col :span="2" class="emp-avatar-col">
            <el-dropdown trigger="click">
        <span class="el-dropdown-link">
          <el-avatar class="emp-avatar" :size="40" :src="avatar"/>
        </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item icon="InfoFilled" @click="router.push('/Personal')">查看个人信息</el-dropdown-item>
                  <el-dropdown-item icon="Edit" @click="router.push('/PersonalUpdate')">修改个人信息</el-dropdown-item>
                  <el-dropdown-item icon="Close" @click="logout">
                    <el-text type="danger">退出系统</el-text>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-header>
      <el-main class="main-body-right-main">
         <router-view/>
      </el-main>
    </el-container>
  </el-container>
  <el-drawer title="项目系统信息" v-model="projectInfoDrawer" size="50%">
    <el-descriptions border column="1">
      <el-descriptions-item v-for="(v,k) in PROJECT_INFO" :ket="k" :label="k">
        {{v}}
      </el-descriptions-item>
    </el-descriptions>
  </el-drawer>
  <el-drawer title="项目技术栈" v-model="projectSkillDrawer" size="40%">
    <el-descriptions border column="1">
      <el-descriptions-item v-for="item in PROJECT_SKILLS" :ket="item['label']" :label="item['label']">
        {{item['value']}}-{{item['version']}}
      </el-descriptions-item>
    </el-descriptions>
  </el-drawer>
  <el-drawer title="日历" v-model="calendarDrawer" size="40%">
    <el-calendar v-model="calendarDate"/>
  </el-drawer>
</template>

<style scoped lang="scss">
// 定义主界面样式
.main-body-left {

  height: 100vh; // 高度
  border-right: 1px solid #cccccc; // 右边框

  .logo {
    padding: 10px; // 内边距
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px; // 宽度
    height: 100vh; // 高度
    letter-spacing: 2px; // 字间距
  }

  .el-icon {
    margin: 0 10px; // 上下外边距 左右外边距
  }
}

.main-body-right-head {

  .project-title-col {
    font-weight: bolder; // 加粗
    font-size: 1.5rem; // 字号倍率
  }

  .emp-realname-col {
    text-align: right; // 右对齐
    height: 50px; // 高度
    display: inline-block; // 内联块
    text-shadow: 2px 2px 2px gray; // 文字阴影
    line-height: 50px; // 行高
  }

  .emp-avatar-col {
    text-align: right; // 右对齐
  }

  .emp-avatar {
    margin: 10px; // 外边距
    outline: 1px solid #854040; // 边框
    border: 1px solid #854040; // 边框
  }
}
</style>