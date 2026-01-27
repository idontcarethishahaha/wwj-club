import axios from 'axios';
import {PROJECT_INFO} from '../const/index.js';
import {isNotEmpty} from "../util/index.js";
import {STATUS} from "../request/index.js";

/* =============== Axios实例配置 =============== */

// 创建Axios实例: 配置请求前缀和超时时间
export const MC_MANAGE_AXIOS = axios.create({
    baseURL: PROJECT_INFO.manageHost + '/api/v1/',
    timeout: 5000
});

/* =============== 通用Axios请求 =============== */

// POST - 添加一条记录
export function insertApi(params, args) {
    return MC_MANAGE_AXIOS.post(`${args['module']}/insert`, params)
}

// GET - 根据主键查询
export function selectApi(id, args) {
    return MC_MANAGE_AXIOS.get(`${args['module']}/select/${id}`)
}

// GET - 分页查询记录
export function pageApi(params, args) {
    return MC_MANAGE_AXIOS.get(`${args['module']}/page`, {params: params});
}

// GET - 查询全部记录
export function listApi(args) {
    return MC_MANAGE_AXIOS.get(`${args['module']}/list`);
}

// PUT - 根据主键修改
export function updateApi(params, args) {
    return MC_MANAGE_AXIOS.put(`${args['module']}/update`, params)
}

// DELETE - 根据主键删除
export function deleteApi(id, args) {
    return MC_MANAGE_AXIOS.delete(`${args['module']}/delete/${id}`)
}

// DELETE - 根据主键批删
export function deleteBatchApi(ids, args) {
    return MC_MANAGE_AXIOS.delete(`${args['module']}/deleteBatch?ids=${ids}`)
}

// GET - 下载Excel报表
export function excelApi(url, fileName) {
    // 自动拼接后缀
    fileName = fileName.endsWith('.xlsx') ? fileName : fileName + '.xlsx';
    // 发送下载请求：响应类型为blob
    return MC_MANAGE_AXIOS.get(url, {
        responseType: 'blob'
    }).then(res => {
        // 借助超链接标签完成下载功能
        let a = document.createElement('a');
        a.style.display = 'none';
        a.href = URL.createObjectURL(new Blob([res.data]));
        a.setAttribute('download', fileName);
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        a = null;
    });
}

/* =============== 拦截器 =============== */

// 请求拦截器：在发送请求前执行
MC_MANAGE_AXIOS.interceptors.request.use(
    req => {
        // 从 sessionStorage 中获取 Token，加入请求头并放行请求
        const token = sessionStorage.getItem("token");
        if (isNotEmpty(token)) req.headers['token'] = token;
        return req;
    },
    err => Promise.reject(err)
);

// 响应拦截器：在接收到响应之后执行
MC_MANAGE_AXIOS.interceptors.response.use(
    resp => {
        // 若后台响应 “Token即将过期”，则续期 Token 并重新发送请求
        if (resp.data.code === STATUS.TOKEN_EXPIRING_SOON) {
            const newToken = resp.data.data;
            sessionStorage.setItem("token", newToken);
            MC_MANAGE_AXIOS.defaults.headers['token'] = newToken;
            return MC_MANAGE_AXIOS.request(resp.config);
        }
        // 若不需要 Token 续期，则直接放行响应
        return resp;
    },
    err => Promise.reject(err)
);
