<script setup>
import {selectApi} from "@/api/axios.js";
const args = {module: 'room'};

const getRoom = (id) => {
  const res = selectApi(id, args);
  console.log(res);//将返回的结果打印在 开发者工具 - 控制台上
}
</script>

<template>
    <router-view/>
</template>
