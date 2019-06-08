package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:14
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;


    @Override
    public List<Student> findByMajorName(String majorName) {
        return studentDao.findByMajorName(majorName);
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public void delete(String email) {
        studentDao.delete(email);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public List<Student> findByUniversityAndMajor(String universityName, String majorName) {
        return studentDao.findByUniversityAndMajor(universityName,majorName);
    }

    @Override
    public PageInfo findByMajorName(String majorName, Integer page, Integer size) {

        PageHelper.startPage(page-1,size);
        return new PageInfo(studentDao.findByMajorName(majorName));
    }

    @Override
    public PageInfo findByUniversityAndMajor(String universityName, String majorName, Integer page, Integer size) {
        PageHelper.startPage(page-1,size);

        return new PageInfo(studentDao.findByUniversityAndMajor(universityName,majorName));
    }
}
