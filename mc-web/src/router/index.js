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

        {path:'/Role',name:'Role',component:Role},
        {path:'/RoleInsert',name:'RoleInsert',component:RoleInsert},
        {path:'/RoleUpdate',name:'RoleUpdate',component:RoleUpdate},
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
