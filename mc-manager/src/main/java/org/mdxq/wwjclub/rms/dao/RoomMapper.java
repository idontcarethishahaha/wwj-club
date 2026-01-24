package org.mdxq.wwjclub.rms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mdxq.wwjclub.entity.Room;

import java.util.List;

/**
 * 类说明：房间信息数据访问层
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 11:20
 */
@Mapper//或 @Repository

public interface RoomMapper {
    int insert(Room room);
    int update(Room room);
    int delete(Long id);
    int deleteBatch(List<Long> ids);
    Room selectById(Long id);
    List<Room> list(String title);
}
