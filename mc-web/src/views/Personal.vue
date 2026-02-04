<script setup>
import {PROJECT_INFO} from "@/const/index.js";
import {dateFormat, genderFormat} from "@/util/index.js";
import MyNav from "@/components/MyNav.vue";

const loginEmp = JSON.parse(sessionStorage.getItem('loginEmp'));
const avatar = PROJECT_INFO.minioHost + '/avatar/' + loginEmp['avatar'];

const navItems = [
  {icon: 'House', label: '系统首页', url: '/Dashboard'},
  {icon: 'View', label: '个人信息'},
]
</script>

<template>
  <my-nav :items="navItems"/>
  <el-divider/>
  <div class="personal-body">
    <el-row>
      <el-col :span="6">
        <el-image :src="avatar"/>
        <el-divider/>
        <el-descriptions column="1">
          <el-descriptions-item label="登录账号">{{ loginEmp.username }}</el-descriptions-item>
          <el-descriptions-item label="真实姓名">{{ loginEmp.realname }}</el-descriptions-item>
          <el-descriptions-item label="入职日期">{{ loginEmp.hiredate }}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ loginEmp.created }}</el-descriptions-item>
          <el-descriptions-item label="修改时间">{{ loginEmp.updated }}</el-descriptions-item>
        </el-descriptions>
      </el-col>
      <el-col :span="18">
        <el-descriptions title="员工详细信息" column="2" border>
          <el-descriptions-item label="所属部门">{{ loginEmp.dept.title }}</el-descriptions-item>
          <el-descriptions-item label="手机号码">{{ loginEmp.phone }}</el-descriptions-item>
          <el-descriptions-item label="微信">{{ loginEmp.wechat }}</el-descriptions-item>
          <el-descriptions-item label="邮箱">{{ loginEmp.email }}</el-descriptions-item>
          <el-descriptions-item label="户籍省份">{{ loginEmp.province }}</el-descriptions-item>
          <el-descriptions-item label="身份证号">{{ loginEmp.idcard }}</el-descriptions-item>
          <el-descriptions-item label="性别">{{ genderFormat(loginEmp.gender) }}</el-descriptions-item>
          <el-descriptions-item label="年龄">{{ loginEmp.age }}</el-descriptions-item>
          <el-descriptions-item label="现居住地" :span="2">
            <el-card>{{ loginEmp.address }}</el-card>
          </el-descriptions-item>
          <el-descriptions-item label="员工介绍" :span="2">
            <el-card>{{ loginEmp.info }}</el-card>
          </el-descriptions-item>
        </el-descriptions>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="scss">

</style>