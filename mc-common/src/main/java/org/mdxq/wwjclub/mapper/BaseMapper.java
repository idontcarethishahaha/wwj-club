package org.mdxq.wwjclub.mapper;

import org.mdxq.wwjclub.entity.Room;

import java.util.List;

/**
 * 接口说明：业务Mapper的抽象，公共父接口
 * 类似于 mybatis-plus 中的 BaseMapper
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/24 11:29
 */

public interface BaseMapper<T> {
    int insert(T t);
    int update(T t);
    int delete(Long id);
    int deleteBatch(List<Long> ids);
    T selectById(Long id);
    List<T> list(String title);
}

