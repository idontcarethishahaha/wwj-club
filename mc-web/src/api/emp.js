// 新增一个上传员工头像的函数
import {PROJECT_INFO} from "@/const/index.js";
import {MC_MANAGE_AXIOS} from "@/api/axios.js";

// 对应服务端上传资产图片的接口路径
export const UPLOAD_AVATAR_URL = PROJECT_INFO.manageHost + "/api/v1/emp/uploadAvatar";

// 添加员工登录请求的api
export function loginByAccountApi(params){
    // 向服务端发起登录请求
    return MC_MANAGE_AXIOS.post('emp/loginByAccount',params)
}