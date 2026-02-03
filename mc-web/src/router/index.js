import {createRouter, createWebHashHistory} from "vue-router";
import Room from '@/views/rms/room/Room.vue'
import RoomInsert from "@/views/rms/room/RoomInsert.vue";
import RoomUpdate from "@/views/rms/room/RoomUpdate.vue";

import School from "@/views/rms/school/School.vue";
import SchoolInsert from "@/views/rms/school/SchoolInsert.vue";
import SchoolUpdate from "@/views/rms/school/SchoolUpdate.vue";

import Assets from "@/views/rms/assets/Assets.vue";
import AssetsInsert from "@/views/rms/assets/AssetsInsert.vue";
import AssetsUpdate from "@/views/rms/assets/AssetsUpdate.vue";

import Role from "@/views/ums/role/Role.vue";
import RoleInsert from "@/views/ums/role/RoleInsert.vue";
import RoleUpdate from "@/views/ums/role/RoleUpdate.vue";

import AssetsBorrow from "@/views/rms/assetsBorrow/AssetsBorrow.vue";
import AssetsBorrowInsert from "@/views/rms/assetsBorrow/AssetsBorrowInsert.vue";
import AssetsBorrowUpdate from "@/views/rms/assetsBorrow/AssetsBorrowUpdate.vue";

import Dept from "@/views/ums/dept/Dept.vue";
import DeptInsert from "@/views/ums/dept/DeptInsert.vue";
import DeptUpdate from "@/views/ums/dept/DeptUpdate.vue";

import Emp from "@/views/ums/emp/Emp.vue";
import EmpInsert from "@/views/ums/emp/EmpInsert.vue";
import EmpUpdate from "@/views/ums/emp/EmpUpdate.vue";

import Menu from "@/views/ums/menu/Menu.vue";
import MenuInsert from "@/views/ums/menu/MenuInsert.vue";
import SubMenu from "@/views/ums/menu/SubMenu.vue";

import Login from "@/views/Login.vue";

import Main from "@/views/Main.vue";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {path:'/Room',name:'Room',component:Room},
        {path:'/RoomInsert',name:'RoomInsert',component:RoomInsert},
        {path:'/RoomUpdate',name:'RoomUpdate',component:RoomUpdate},

        {path:'/School',name:'School',component:School},
        {path:'/SchoolInsert',name:'SchoolInsert',component:SchoolInsert},
        {path:'/SchoolUpdate',name:'SchoolUpdate',component:SchoolUpdate},

        {path:'/Room',name:'Room',component:Room},
        {path:'/RoomInsert',name:'RoomInsert',component:RoomInsert},
        {path:'/RoomUpdate',name:'RoomUpdate',component:RoomUpdate},

        {path:'/Assets',name:'Assets',component:Assets},
        {path:'/AssetsInsert',name:'AssetsInsert',component:AssetsInsert},
        {path:'/AssetsUpdate',name:'AssetsUpdate',component:AssetsUpdate},

        {path:'/AssetsBorrow',name:'AssetsBorrow',component:AssetsBorrow},
        {path:'/AssetsBorrowInsert',name:'AssetsBorrowInsert',component:AssetsBorrowInsert},
        {path:'/AssetsBorrowUpdate',name:'AssetsBorrowUpdate',component:AssetsBorrowUpdate},

        {path:'/Dept',name:'Dept',component:Dept},
        {path:'/DeptInsert',name:'DeptInsert',component:DeptInsert},
        {path:'/DeptUpdate',name:'DeptUpdate',component:DeptUpdate},

        {path:'/Emp',name:'Emp',component:Emp},
        {path:'/EmpInsert',name:'EmpInsert',component:EmpInsert},
        {path:'/EmpUpdate',name:'EmpUpdate',component:EmpUpdate},

        {path:'/Role',name:'Role',component:Role},
        {path:'/RoleInsert',name:'RoleInsert',component:RoleInsert},
        {path:'/RoleUpdate',name:'RoleUpdate',component:RoleUpdate},


        {path:'/Menu',name:'Menu',component:Menu},
        {path:'/MenuInsert',name:'MenuInsert',component:MenuInsert},
        {path:'/SubMenu',name:'SubMenu',component:SubMenu},

        {path:'/Login',name:'Login',component:Login},

        {path:'/Main',name:'Main',component:Main},


    ]
});

/*
 * 路由前置守卫：每次转发路由前执行的函数
 * param to: 来源地址
 * param from: 目标地址
 * next: 放行函数
 */
router.beforeEach((to, from, next) => {
    // console.log(to, from);
    // 放行：支持使用 next('/ABC') 表示放行到指定页面
    next();
});

export default router
