<script setup>
import MyNav from "@/components/MyNav.vue";
import MyForm from "@/components/MyForm.vue";
import {updateApi,listApi} from "@/api/axios.js";
import {CLUB_TAG_OPTIONS, RULE} from "@/const/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";
import {ref,onMounted,reactive} from "vue";
import {getResponseData} from "@/request/index.js";

// 保存方向列表
const directionOptions = reactive([]);
// 页面加载完成后获取方向列表
onMounted(async () => {
  Object.values(getResponseData(await listApi({module: 'direction'})))
      .forEach(dir => {
        directionOptions.push(
            {label: dir['title'], value: dir['id']}
        )
      });
});

// 定义导航项
const navItems = [
  { label: '班级管理', icon: 'Platform' },
  { label: '班级列表', icon: 'Platform',url:'/Club' },
  {label:'修改班级',icon:'Edit'}
]
// 定义表单项
const formItems = [
  {label: '名称', prop: 'title', required: true, span: 12},
  {label: '方向', prop: 'fkDirectionId', required: true, span: 12,type: 'select',options:directionOptions},
  {label: '开班时间', prop: 'startTime',required: true, span: 12,type:'datetime'},
  {label: '预计结课时间', prop: 'expectedEndTime', required: true, span: 12,type:'datetime'},
  {label: '标签', prop: 'tag',type:'select',options:CLUB_TAG_OPTIONS,multiple:true, required: true, span: 24},
  {label: '介绍', prop: 'info', required: true, span: 24,type:'textarea'},
]
const record = JSON.parse(sessionStorage.getItem('row'))
//表单值
const formValues = ref(record)

//表单项规则：对表单输入值进行格式校验提醒
const rules = {
  title:RULE.TITLE,info:RULE.INFO
}
//修改成功的响应函数
function updateSuccess(){
  ElMessage.success("修改成功！")
  //延迟1000ms后自动跳转到列表页
  setTimeout(()=>router.push('/Club'),1000)
}

// 定义模块名
const args = {module:'club'}
</script>

<template>
  <my-nav :items="navItems"/>
  <el-card class="update-card" header="修改班级">
    <my-form type="update"
             :params="formValues"
             :api="updateApi"
             :rules="rules"
             :args="args"
             :callback="updateSuccess"
             :items="formItems"></my-form>
  </el-card>
</template>

<style scoped lang="scss">
   .update-card{
     width:60%;
     margin: 65px auto;
   }
</style>