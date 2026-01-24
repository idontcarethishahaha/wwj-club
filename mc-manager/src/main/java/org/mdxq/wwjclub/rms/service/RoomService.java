package org.mdxq.wwjclub.rms.service;

import com.github.pagehelper.PageInfo;
import org.mdxq.wwjclub.entity.Room;
import org.mdxq.wwjclub.rms.dto.RoomInsertDTO;
import org.mdxq.wwjclub.rms.dto.RoomPageDTO;
import org.mdxq.wwjclub.rms.dto.RoomUpdateDTO;

import java.util.List;

/**
 * 接口说明：房间信息管理业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 14:36
 */

public interface RoomService {
    boolean save(RoomInsertDTO dto);
    boolean update(RoomUpdateDTO dto);
    Room getById(Long id);
    boolean deleteById(Long id);
    boolean deleteBatch(List<Long> ids);
    PageInfo<Room> page(RoomPageDTO dto);
}
