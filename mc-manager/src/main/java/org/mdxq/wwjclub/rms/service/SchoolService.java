package org.mdxq.wwjclub.rms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Room;
import org.mdxq.wwjclub.entity.School;
import org.mdxq.wwjclub.rms.dto.*;

import java.util.List;

/**
 * 接口说明：学校信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 17:22
 */
public interface SchoolService {
    boolean save(SchoolInsertDTO dto);
    boolean update(SchoolUpdateDTO dto);
    School getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<School> page(SchoolPageDTO dto);
}
