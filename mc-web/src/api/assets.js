// 新增一个上传资产图片的函数
import {PROJECT_INFO} from "@/const/index.js"
import {MC_MANAGE_AXIOS} from "@/api/axios.js";

// 对应服务器段上传资产图片的接口路径
export const UPLOAD_ASSETS_URL = PROJECT_INFO.manageHost +
    "/api/v1/assets/uploadPicture";