package org.mdxq.wwjclub.constant;


import java.util.Objects;

/**
 * 类说明：基础常量类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/23 11:13
 */
public class MC {
    public interface MinIO{
        String BUCKET_NAME = "myclub";//MinIO数据桶名称
        String AVATAR_DIR = "avatar";//用户头像的上传目录
        String ASSETS_DIR = "assets";//资产图片的上传目录
    }
    public interface Assets{
        String DEFAULT_ASSETS_PIC = "default-assets.png";
    }

    public interface Student {
        Integer FEMALE = 0, MALE = 1;//性别常量
        /*学生状态：0-学习中，1-结课，2-已就业，3-休学，4-其他情况*/
        Integer STUDYING = 0,STUDY_OVER = 1,WORKING = 2,STUDY_PAUSE=3,OTHER=4;
        String DEFAULT_AVATAR = "default-avatar.png";//学生的默认头像

        static String genderFormat(int genderCode){
            if(Objects.equals(genderCode,FEMALE)) return "女生";
            if(Objects.equals(genderCode,MALE)) return "男生";
            return "未知";
        }

        static String statusFormat(int statusCode){
            if(Objects.equals(statusCode,STUDYING)) return "在读";
            if(Objects.equals(statusCode,STUDY_OVER)) return "结课";
            if(Objects.equals(statusCode,WORKING)) return "已就业";
            if(Objects.equals(statusCode,STUDY_PAUSE)) return "休学";
            if(Objects.equals(statusCode,OTHER)) return "其他";
            return "异常";
        }
    }
    public interface Emp {
        Integer FEMALE = 0, MALE = 1;//性别常量
        String DEFAULT_AVATAR = "default-avatar.png";//员工的默认头像

        // 将性别编码转换成字符串
        static String genderFormat(int genderCode) {
            if (Objects.equals(genderCode, FEMALE)) return "女生";
            if (Objects.equals(genderCode, MALE)) return "男生";
            return "未知";
        }
    }

    public interface Menu {
        Long ROOT_ID = 0L;// 根菜单ID
    }

    public interface Regex {
        String TITLE_RE = "^.{1,42}$";
        String TITLE_RE_MSG = "标题长度必须在1~42之间";
        String INFO_RE = "^.{1,170}$";
        String INFO_RE_MSG = "描述长度必须在1~170之间";
        String ADDRESS_RE = "^.{1,170}$";
        String ADDRESS_RE_MSG = "地址长度必须在1~170之间";
        String MENU_URL_RE = "^/[a-zA-Z]{0,256}$";
        String MENU_URL_RE_MSG = "跳转地址必须以 / 开头，后续内容仅支持0~256个英文字母";
        String MENU_ICON_RE = "^[a-zA-Z]{1,256}$";
        String MENU_ICON_RE_MSG = "图标仅支持1~256个英文字母";
        String USERNAME_RE = "^[a-zA-Z0-9]{4,20}$";
        String USERNAME_RE_MSG = "账号必须由4到20个英文字母或数字组成";
        String PASSWORD_RE = "^[a-zA-Z0-9]{4,20}$";
        String PASSWORD_RE_MSG = "密码必须由4到20个英文字母或数字组成";
        String REALNAME_RE = "^[\\u4e00-\\u9fa5]{2,6}$";
        String REALNAME_RE_MSG = "真实姓名必须由2到6个中文组成";
        String ID_CARD_RE = "^[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        String ID_CARD_RE_MSG = "身份证号格式不正确";
        String PHONE_RE = "^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$";
        String PHONE_RE_MSG = "手机号码格式不正确";
        String EMAIL_RE = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        String EMAIL_RE_MSG = "电子邮箱格式不正确";
        String PROVINCE_RE = "^[\\u4e00-\\u9fa5]{2,20}$";
        String PROVINCE_RE_MSG = "省份必须由2到20个中文组成";
    }
}
