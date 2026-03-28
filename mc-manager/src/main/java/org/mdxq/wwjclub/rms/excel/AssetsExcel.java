package org.mdxq.wwjclub.rms.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 类说明：资产报表类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/9 14:17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT,verticalAlignment = VerticalAlignmentEnum.CENTER)//表头，左对齐和居中对齐
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.LEFT,verticalAlignment = VerticalAlignmentEnum.CENTER)//表头，左对齐和居中对齐
public class AssetsExcel {

    @ExcelProperty(value = {"资产数据统计表","资产名称"})
    private String title;

    @ExcelProperty(value = {"资产数据统计表","资产图片"})
    private String picture;

    @ExcelProperty(value = {"资产数据统计表","资产单价"})
    private Double price;

    @ExcelProperty(value = {"资产数据统计表","资产单价单位"})
    private String priceUnit;

    @ExcelProperty(value = {"资产数据统计表","资产单价"})
    private Integer stock;

    @ExcelProperty(value = {"资产数据统计表","剩余库存单位"})
    private String stockUnit;

    @ExcelProperty(value = {"资产数据统计表","总计库存"})
    private Integer total;

    @ExcelProperty(value = {"资产数据统计表","资产描述"})
    private String info;


    @ExcelProperty(value = {"资产数据统计表","首次创建时间"})
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime created;

    @ExcelProperty(value = {"资产数据统计表","最后修改时间"})
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updated;
}
