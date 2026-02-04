import {MC_MANAGE_AXIOS} from "@/api/axios.js";

// 查询员工的角色
export function listByEmpId(empId){
    return MC_MANAGE_AXIOS.get(`role/listByEmpId/${empId}`)
}

// 修改员工的角色
export function updateByRoleId(empId,roleIds){
    return MC_MANAGE_AXIOS.put(`'role/updateByEmpId?empId=${empId}&roleIds=${roleIds}`)
}