package org.mdxq.wwjclub.cms.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.mdxq.wwjclub.cms.dao.StudentMapper;
import org.mdxq.wwjclub.cms.dto.StudentPageDTO;
import org.mdxq.wwjclub.cms.dto.StudentInsertDTO;
import org.mdxq.wwjclub.cms.dto.StudentUpdateDTO;
import org.mdxq.wwjclub.cms.service.StudentService;
import org.mdxq.wwjclub.constant.MC;
import org.mdxq.wwjclub.entity.Student;

import org.mdxq.wwjclub.exception.ServerErrorException;
import org.mdxq.wwjclub.exception.VersionException;
import org.mdxq.wwjclub.util.MinioUtil;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 类说明：方向业务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/1/26 15:18
 */
@Service
@CacheConfig(cacheNames = "student")//配置Redis缓存名
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean save(StudentInsertDTO dto) {
        Student student = BeanUtil.copyProperties(dto, Student.class);

        // 设置描述默认值
        if (ObjectUtil.isEmpty(dto.getInfo())) {
            student.setInfo("暂无介绍");
        }

        // 从身份证号解析年龄、性别、省份
        student.setAge(IdcardUtil.getAgeByIdCard(dto.getIdcard()));
        student.setGender(IdcardUtil.getGenderByIdCard(dto.getIdcard()));
        student.setProvince(IdcardUtil.getProvinceByIdCard(dto.getIdcard()));

        // 执行插入（解析后再插入，避免gender等字段为空）
        if (studentMapper.insert(student) == 0) {
            throw new ServerErrorException("DB: 学生插入失败");
        }
        return true;
    }

    @Override
    @Retryable(retryFor = VersionException.class)
    public boolean update(StudentUpdateDTO dto) {
        Student student = studentMapper.selectById(dto.getId());
        if (student == null) {
            throw new ServerErrorException("DB: 记录不存在或以删除");
        }

        // 如果身份证号有修改，重新解析年龄、性别、省份
        if (!StrUtil.equals(dto.getIdcard(), student.getIdcard())) {
            student.setAge(IdcardUtil.getAgeByIdCard(dto.getIdcard()));
            student.setGender(IdcardUtil.getGenderByIdCard(dto.getIdcard()));
            student.setProvince(IdcardUtil.getProvinceByIdCard(dto.getIdcard()));
        }

        // 覆盖其他更新字段
        BeanUtil.copyProperties(dto, student);

        if (studentMapper.update(student) == 0) {
            throw new VersionException("学生更新失败");
        }
        return true;
    }

    @Override
    public Student getById(Long id) {
        Student student = studentMapper.selectById(id);
        if (student == null) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return student;
    }

    @Override
    public boolean deleteById(Long id) {
        if (studentMapper.delete(id) == 0) {
            throw new ServerErrorException("DB：记录不存在或已删除");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (studentMapper.deleteBatch(ids) < ids.size()) {
            throw new ServerErrorException("DB：批量删除失败");
        }
        return true;
    }

    @Override
    public PageInfo<Student> page(StudentPageDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return new PageInfo<>(studentMapper.list(dto));
    }

//    @Override
//    public String uploadAvatar(MultipartFile avatarFile, Long id) {
//        Student student = studentMapper.selectById(id);
//        if (student == null) {
//            throw new ServerErrorException("DB: 学生记录不存在或已删除");
//        }
//        //生成唯一的文件名
//        String newFileName = MinioUtil.randomFilename(avatarFile);
//        MinioUtil.upload(avatarFile,newFileName, MC.MinIO.AVATAR_DIR,MC.MinIO.BUCKET_NAME);
//        student.setAvatar(newFileName);
//        if(studentMapper.update(student)==0){
//            throw new ServerErrorException("DB: 修改学生头像失败");
//        }
//        return newFileName;
//    }
@Override
@CacheEvict(allEntries = true) // 上传头像后清除缓存，和员工模块保持一致
public String uploadAvatar(MultipartFile avatarFile, Long id) {
    Student student = studentMapper.selectById(id);
    if (student == null) {
        throw new ServerErrorException("DB: 学生记录不存在或已删除");
    }
    //生成唯一的文件名
    String newFileName = MinioUtil.randomFilename(avatarFile);
    MinioUtil.upload(avatarFile, newFileName, MC.MinIO.AVATAR_DIR, MC.MinIO.BUCKET_NAME);
    student.setAvatar(newFileName);
    if (studentMapper.update(student) == 0) {
        throw new ServerErrorException("DB: 修改学生头像失败");
    }
    return newFileName;
}
}