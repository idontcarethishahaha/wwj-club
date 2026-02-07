package org.mdxq.wwjclub.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.cms.vo.DirectionVO;
import org.mdxq.wwjclub.entity.Direction;
import org.mdxq.wwjclub.mapper.BaseMapper;
import java.util.List;

/**
 * 接口说明：方向Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/2/4 14:56
 */
@Mapper
public interface DirectionMapper extends BaseMapper<Direction> {

    List<DirectionVO> listAll();
}

