// 新增一个上传员工头像的函数
import {PROJECT_INFO} from "@/const/index.js";

// 对应服务端上传资产图片的接口路径
export const UPLOAD_AVATAR_URL = PROJECT_INFO.manageHost + "/api/v1/emp/uploadAvatar";