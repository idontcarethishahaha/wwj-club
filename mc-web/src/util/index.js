// 封装一些全局的工具函数，方便在项目开发时使用
// 判空函数
export function isNotNull(val) {//val不是空
    return val !== null && val !== undefined;
}
export function isNull(val) {//val是空
    return !isNotNull(val);
}
//可以判断多个参数或数组项中有没有空值
export function hasNull(...values) {
    // 只要任意一项是空，都返回true
    for (let val in values) {
        if (isNull(val)) return true;
    }
    return false;
}
// 判断字符串不为空
export function isNotEmpty(str) {
    return str !== null && str !== undefined && str !== '';
}
// 判断字符串是空的
export function isEmpty(str) {
    return !isNotEmpty(str);
}
// 将月日时分秒都转换成两位的函数
function toDouble(e) {
    // 小于 10 就在前边拼接 0，举例：5 => 05
    return e < 10 ? '0' + e : e;
}
// 日期时间格式化的函数
export function datetimeFormat(dateStr) {
    if (isEmpty(dateStr)) return '';//先排除空串的情况
    let date = new Date(dateStr);
    let year = date.getFullYear();
    let month = toDouble(date.getMonth());
    let day = toDouble(date.getDate());
    let hour = toDouble(date.getHours());
    let minute = toDouble(date.getMinutes());
    let second = toDouble(date.getSeconds());
    // 不是单引号 2026年01月20日 15:46
    return `${year}年${month}月${day}日 ${hour}:${minute}`;
    // 不是单引号 2026-01-20 15:46
    // return `${year}-${month}-${day} ${hour}:${minute}`;
}
export function dateFormat(dateStr) {
    if (isEmpty(dateStr)) return '';//先排除空串的情况
    let date = new Date(dateStr);
    let year = date.getFullYear();
    let month = toDouble(date.getMonth()+1);
    let day = toDouble(date.getDate());

    // 不是单引号 2026年01月20日
    return `${year}年${month}月${day}日`;
    // 不是单引号 2026-01-20
    // return `${year}-${month}-${day}`;
}

/**
 * 性别代码处理：0->'女'，1->'男'，2->'保密'
 *
 * @param genderCode 性别代码
 * @return string 对应的性别字符串，0女孩，1男孩，2保密
 * */
export function genderFormat(genderCode) {
    if (genderCode === '0' || genderCode === 0) return '女孩';
    if (genderCode === '1' || genderCode === 1) return '男孩';
    if (genderCode === '2' || genderCode === 2) return '保密';
    return '性别代码异常';
}

/**
 * 学生状态代码处理：0->'在培'，1->'结课'，2->'已就业'，3->'休学'，4->'其它'
 *
 * @param stateCode 学生状态代码
 * @return string 对应的学生状态字符串，0在培，1结课，2已就业，3休学，4其它
 * */
export function studentStateFormat(stateCode) {
    if (stateCode === '0' || stateCode === 0) return '在培';
    if (stateCode === '1' || stateCode === 1) return '结课';
    if (stateCode === '2' || stateCode === 2) return '已就业';
    if (stateCode === '3' || stateCode === 3) return '休学';
    if (stateCode === '4' || stateCode === 4) return '其它';
    return '学生状态代码异常';
}
