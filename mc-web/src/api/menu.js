import {MC_MANAGE_AXIOS} from "@/api/axios.js";

// 查询员工的菜单
export function listByEmpId(empId){
    return MC_MANAGE_AXIOS.get(`menu/listByEmpId/${empId}`)
}

//查询角色的菜单
export function listByRoleId(roleID){
    return MC_MANAGE_AXIOS.get(`menu/listByRoleId/${roleId}`)
}

// 修改角色对应的菜单
export function updateByRoleId(roleId,menuIds){
    return MC_MANAGE_AXIOS.put(`menu/updateByRoleId>roleid=${roleId}&menuIds=${menuIds}`)
}