package org.mdxq.wwjclub.cms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mdxq.wwjclub.entity.Club;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.entity.School;

import java.time.LocalDateTime;

/**
 * 类说明：学生报表类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/9 14:53
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT,verticalAlignment = VerticalAlignmentEnum.CENTER)//表头，左对齐和居中对齐
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT,verticalAlignment = VerticalAlignmentEnum.CENTER)//表头，左对齐和居中对齐
public class StudentExcel {

    @ExcelProperty(value = {"学生数据统计表","学号"})
    private String number;
    @ExcelProperty(value = {"学生数据统计表","姓名"})
    private String realname;
    @ExcelProperty(value = {"学生数据统计表","手机号"})
    private String phone;
    @ExcelProperty(value = {"学生数据统计表","性别"})
    private Integer gender;
    @ExcelProperty(value = {"学生数据统计表","微信"})
    private String wechat;
    @ExcelProperty(value = {"学生数据统计表","年龄"})
    private Integer age;
    @ExcelProperty(value = {"学生数据统计表","籍贯"})
    private String province;
    @ExcelProperty(value = {"学生数据统计表","现居住地"})
    private String address;
    @ExcelProperty(value = {"学生数据统计表","身份证号"})
    private String idcard;
    @ExcelProperty(value = {"学生数据统计表","入学时间"})
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime studyDate;
    @ExcelProperty(value = {"学生数据统计表","紧急联系人姓名"})
    private String emergencyName;
    @ExcelProperty(value = {"学生数据统计表","紧急联系人电话"})
    private String emergencyPhone;
    @ExcelProperty(value = {"学生数据统计表","学生状态"})
    private Integer status;
    @ExcelProperty(value = {"学生数据统计表","学生描述"})
    private String info;
    @ExcelProperty(value = {"学生数据统计表","首次创建时间"})
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime created;
    @ExcelProperty(value = {"学生数据统计表","最后修改时间"})
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updated;
}
