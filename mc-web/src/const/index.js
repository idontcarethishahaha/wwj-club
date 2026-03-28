// 项目环境信息
const DEV_HOST = 'localhost';
// 部署环境：配置虚拟机IP
const PROD_HOST = '192.168.227.128';

// 员工默认密码
export const DEFAULT_PASSWORD = 'MC123456';

// 表单规则
export const RULE = {
    TITLE: [{pattern: /^.{1,42}$/, message: '标题长度必须在1~42之间'}],
    INFO: [{pattern: /^.{1,170}$/, message: '描述长度必须在1~170之间'}],
    ADDRESS: [{pattern: /^.{1,170}$/, message: '地址长度必须在1~170之间'}],
    MENU_URL: [{pattern: /^\/[a-zA-Z]{0,256}$/, message: '跳转地址必须以 / 开头，后续内容仅支持0~256个英文字母'}],
    MENU_ICON: [{pattern: /^[a-zA-Z]{1,256}$/, message: '图标仅支持1~256个英文字母'}],
    USERNAME: [{pattern: /^[a-zA-Z0-9]{4,20}$/, message: '账号必须由4到20个英文字母或数字组成'}],
    PASSWORD: [{pattern: /^[a-zA-Z0-9]{4,20}$/, message: '密码必须由4到20个英文字母或数字组成'}],
    REALNAME: [{pattern: /^[\u4e00-\u9fa5]{2,6}$/, message: '真实姓名必须由2到6个中文组成'}],
    IDCARD: [{pattern: /^[1-9]\d{5}(19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '身份证号格式不正确'}],
    PHONE: [{pattern: /^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/, message: '手机号码格式不正确'}],
    EMAIL: [{pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '电子邮箱格式不正确'}],
    PROVINCE: [{pattern: /^[\u4e00-\u9fa5]{2,20}$/, message: '省份必须由2到20个中文组成'}],
}

// 项目环境信息
export const PROJECT_INFO = {
    title: '《WWJ-CLUB》后台管理子系统',
    author: 'WuWenJin',
    version: 'v1.0.0',
    manageHost: `http://${PROD_HOST}:23101`,
    monitorHost: `http://${DEV_HOST}:23102`,
    webHost: `http://${DEV_HOST}:23103`,
    docHost: `http://${DEV_HOST}:23101/doc.html`,
    minioHost: `http://${PROD_HOST}:9001/myclub`,
    put: 16,
    post: 17,
    get: 54,
    delete: 26,
    info: 'MyClub 管理系统是基于 SpringBoot 开发的，旨在提供一个全面而高效的管理平台，该系统目前仅支持内部员工登录，登陆后可以轻松管理俱乐部的房间，学校，资产，部门，员工，角色，菜单，班级，课程，学员等相关数据。该系统使用前后端分离的模式进行开发，数据库使用 MySQL，后端使用经典的 SSM 架构，前端使用 Vue + ElementPlus 的组合。该系统具有良好的可扩展性和稳定性，为俱乐部的管理和运营提供了可靠的支持。',
};

// 项目技术栈信息
export const PROJECT_SKILLS = [
    {label: '底层操作系统', value: 'Windows', version: '11'},
    {label: '语言开发环境', value: 'JDK', version: '17.0.9'},
    {label: '集成开发工具', value: 'IntelliJ IDEA', version: '2023.3.3.win Ultimate Edition'},
    {label: '项目管理工具', value: 'Maven', version: '3.9.9'},
    {label: '版本控制工具', value: 'Git', version: '2.28.0.windows.1'},
    {label: '代码托管中心', value: 'GitEE', version: 'latest'},
    {label: '前端服务容器', value: 'Node', version: '20.12.0'},
    {label: '前端测试软件', value: 'Edge', version: '120.0.2210.77'},
    {label: '压力测试工具', value: 'JMeter', version: '5.4.1'},
    {label: '虚拟管理工具', value: 'VmWare', version: '17.5.1 build-23298084'},
    {label: '虚拟操作系统', value: 'OpenEuler', version: '24.03-LTS'},
    {label: '容器管理引擎', value: 'Docker', version: '18.09.0'},
    {label: '数据存储仓库', value: 'MySQL', version: '8.0.27'},
    {label: '对象存储仓库', value: 'MinIO', version: 'RELEASE.2023-08-31T15-31-16Z'},
    {label: '数据缓存仓库', value: 'Redis', version: '7.0.5'},
    {label: '反向代理组件', value: 'Nginx', version: '1.25.2'},
    {label: '搜索引擎组件', value: 'ElasticSearch', version: '8.4.0'},
    {label: '搜索引擎界面', value: 'Kibana', version: '8.4.0'},
    {label: '日志收集组件', value: 'Logstash', version: '8.4.0'}
];

// 性别下拉菜单可选项
export const GENDER_OPTIONS = [
    {label: '女孩', value: 0},
    {label: '男孩', value: 1},
    {label: '保密', value: 2}
];

// 省份下拉菜单可选项（包含中国全部省级行政区）
export const PROVINCE_OPTIONS = [
    {label: '北京', value: '北京'},
    {label: '上海', value: '上海'},
    {label: '天津', value: '天津'},
    {label: '重庆', value: '重庆'},
    {label: '河北', value: '河北'},
    {label: '山西', value: '山西'},
    {label: '辽宁', value: '辽宁'},
    {label: '吉林', value: '吉林'},
    {label: '黑龙江', value: '黑龙江'},
    {label: '江苏', value: '江苏'},
    {label: '浙江', value: '浙江'},
    {label: '安徽', value: '安徽'},
    {label: '福建', value: '福建'},
    {label: '江西', value: '江西'},
    {label: '山东', value: '山东'},
    {label: '河南', value: '河南'},
    {label: '湖北', value: '湖北'},
    {label: '湖南', value: '湖南'},
    {label: '广东', value: '广东'},
    {label: '广西', value: '广西'},
    {label: '海南', value: '海南'},
    {label: '四川', value: '四川'},
    {label: '贵州', value: '贵州'},
    {label: '云南', value: '云南'},
    {label: '西藏', value: '西藏'},
    {label: '陕西', value: '陕西'},
    {label: '甘肃', value: '甘肃'},
    {label: '青海', value: '青海'},
    {label: '宁夏', value: '宁夏'},
    {label: '新疆', value: '新疆'},
    {label: '香港', value: '香港'},
    {label: '澳门', value: '澳门'},
    {label: '台湾', value: '台湾'},
    {label: '其他', value: '其他'},
];

// 班级标签下拉菜单可选项
export const CLUB_TAG_OPTIONS = [
    {label: '江北', value: '江北'},
    {label: '江南', value: '江南'},
    {label: '线上', value: '线上'},
    {label: '线下', value: '线下'},
    {label: '全日制', value: '全日制'},
    {label: '非全日制', value: '非全日制'},
];

// 学生状态下拉菜单可选项
export const STUDENT_STATUS_OPTIONS = [
    {label: '在培', value: 0},
    {label: '结课', value: 1},
    {label: '已就业', value: 2},
    {label: '休学', value: 3},
    {label: '其它', value: 4},
];
