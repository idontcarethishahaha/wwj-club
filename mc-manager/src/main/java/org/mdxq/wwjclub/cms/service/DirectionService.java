package org.mdxq.wwjclub.cms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.cms.dto.DirectionUpdateDTO;
import org.mdxq.wwjclub.entity.Direction;
import org.mdxq.wwjclub.cms.dto.DirectionInsertDTO;
import org.mdxq.wwjclub.cms.vo.DirectionVO;
import org.mdxq.wwjclub.cms.dto.DirectionPageDTO;


import java.util.List;

/**
 * 接口说明：方向业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:10
 */
public interface DirectionService {
    boolean save(DirectionInsertDTO dto);
    boolean update(DirectionUpdateDTO dto);
    Direction getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Direction> page(DirectionPageDTO dto);
    List<DirectionVO> listAll();
}
