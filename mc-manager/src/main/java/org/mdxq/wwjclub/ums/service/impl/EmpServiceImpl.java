package org.mdxq.wwjclub.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.constant.MC;
import org.mdxq.wwjclub.entity.Emp;
import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.ums.dao.EmpMapper;
import org.mdxq.wwjclub.ums.dto.EmpInsertDTO;
import org.mdxq.wwjclub.ums.dto.EmpPageDTO;
import org.mdxq.wwjclub.ums.dto.EmpUpdateDTO;
import org.mdxq.wwjclub.ums.dto.UpdatePasswordDTO;
import org.mdxq.wwjclub.ums.service.EmpService;
import org.mdxq.wwjclub.util.MinioUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 类说明：员工业务接口实现类
 *
 * @author WuWenJin
 * @date 2026/1/30 10:32
 * @version 1.0
 */
@Service
@CacheConfig(cacheNames = "emp")//配置Redis缓存名
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;


//    @Override
//    @CacheEvict(allEntries = true)
//    public boolean save(EmpInsertDTO dto) {
//        Emp emp = BeanUtil.copyProperties(dto, Emp.class);
//        if (ObjectUtil.isEmpty(emp.getInfo())) {
//            emp.setInfo("暂无员工描述");
//        }
//        //对员工的密码进行加密，使用md5加密算法
//        emp.setPassword(SecureUtil.md5(dto.getPassword()));
//        //设置员工的默认头像
//        emp.setAvatar(MC.Emp.DEFAULT_AVATAR);
//        if (empMapper.insert(emp) == 0) {
//            throw new ServerErrorException("DB: 添加员工失败");
//        }
//        //从身份证号中分别获取：年龄、性别和籍贯省份
//        emp.setAge(IdcardUtil.getAgeByIdCard(dto.getIdcard()));
//        emp.setGender(IdcardUtil.getGenderByIdCard(dto.getIdcard()));
//        emp.setProvince(IdcardUtil.getProvinceByIdCard(dto.getIdcard()));
//        if(empMapper.insert(emp) == 0) {
//            throw new ServerErrorException("DB: 添加员工失败");
//        }
//
//        return true;
//    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(EmpInsertDTO dto) {
        Emp emp = BeanUtil.copyProperties(dto, Emp.class);

        // 先设置默认值
        if (ObjectUtil.isEmpty(emp.getInfo())) {
            emp.setInfo("暂无员工描述");
        }
        // 密码加密
        emp.setPassword(SecureUtil.md5(dto.getPassword()));
        // 设置默认头像
        emp.setAvatar(MC.Emp.DEFAULT_AVATAR);

        // 从身份证解析赋值（放在insert之前），避免null
        emp.setAge(IdcardUtil.getAgeByIdCard(dto.getIdcard()));
        emp.setGender(IdcardUtil.getGenderByIdCard(dto.getIdcard()));
        emp.setProvince(IdcardUtil.getProvinceByIdCard(dto.getIdcard()));

        // 只执行一次insert
        if (empMapper.insert(emp) == 0) {
            throw new ServerErrorException("DB: 添加员工失败");
        }

        return true;
    }



    @Override
    @CacheEvict(allEntries = true)
    public boolean update(EmpUpdateDTO dto) {
        //待更新的员工是否存在
        Emp emp = empMapper.selectById(dto.getId());
        if (emp == null) {
            throw new ServerErrorException("DB: 员工记录不存在或已删除");
        }
        // 身份证号如果被修改了
        if (!StrUtil.equals(dto.getIdcard(), emp.getIdcard())) {
            // 从身份证号码中分别获取：年龄、性别、籍贯省份
            emp.setAge(IdcardUtil.getAgeByIdCard(dto.getIdcard()));
            emp.setGender(IdcardUtil.getGenderByIdCard(dto.getIdcard()));
            emp.setProvince(IdcardUtil.getProvinceByIdCard(dto.getIdcard()));
        }
        // 从dto中覆emp的属性
        BeanUtil.copyProperties(dto, emp);
        if (empMapper.update(emp) == 0) {
            throw new VersionException("DB: 更新员工信息失败");
        }
        return true;
    }

    @Override
    @Cacheable(key = "#p0", condition = "#result != null") // 根据员工id缓存查询结果
    public Emp getById(Long id) {
        Emp emp = empMapper.selectById(id);
        if (emp == null) {
            throw new ServerErrorException("DB: 员工记录不存在或已删除");
        }
        return emp;
    }

    @Override
    @CacheEvict(allEntries = true) // 删除员工后清除所有emp缓存
    public boolean deleteById(Long id) {
        if (empMapper.delete(id) == 0) {
            throw new ServerErrorException("DB: 删除员工失败");
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteBatch(List<Long> ids) {
        if (empMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB: 批量删除员工失败");
        }
        return true;
    }


    @Override
    public PageInfo<Emp> page(EmpPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(empMapper.list(dto));
    }

    @Override
    public List<Emp> listAll() {
        EmpPageDTO dto = new EmpPageDTO();
        dto.setPageNum(1);
        dto.setPageSize(Integer.MAX_VALUE);
        return empMapper.list(dto);
    }

    @Override
    @Transactional
    public String uploadAvatar(MultipartFile newFile, Long id) {
        Emp emp = empMapper.selectById(id);
        if (emp == null) {
            throw new ServerErrorException("DB: 记录不存在");
        }
        // 获取员工的旧头像
        String oldAvatar = emp.getAvatar();
        // 生成新头像
        String newAvatar = MinioUtil.randomFilename(newFile);
        // 更新用户头像
        emp.setAvatar(newAvatar);
        if (empMapper.update(emp) == 0) {
            throw new ServerErrorException("DB: 员工头像更新失败");
        }
        try {
            MinioUtil.upload(newFile, newAvatar, MC.MinIO.AVATAR_DIR, MC.MinIO.BUCKET_NAME);
        } catch (Exception ex) {
            throw new ServerErrorException("Minio操作失败: " + ex.getMessage());
        }
        return newAvatar;
    }

    @Override
    public boolean updatePassword(UpdatePasswordDTO dto) {
        Emp emp = empMapper.selectById(dto.getId());
        if (emp == null) {
            throw new ServerErrorException("DB: 记录不存在或已删除");
        }
        // 先给oldPass加密
        String oldPass = SecureUtil.md5(dto.getOldPass());
        if(!StrUtil.equals(oldPass, emp.getPassword())) {
            throw new ServerErrorException("旧密码不正确");
        }
        // 给新密码加密
        String newPass = SecureUtil.md5(dto.getNewPass());
        // 更新密码
        emp.setPassword(newPass);
        if (empMapper.update(emp) == 0) {
            throw new ServerErrorException("DB: 密码修改失败");
        }
        return true;
    }
}