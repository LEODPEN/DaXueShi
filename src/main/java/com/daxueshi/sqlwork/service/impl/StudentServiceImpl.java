package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.service.StudentService;
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
    public List<Student> findByUniversityId(Integer universityId) {
        return studentDao.findByUniversityId(universityId);
    }

    @Override
    public List<Student> findByMajorId(Integer majorId) {
        return studentDao.findByMajorid(majorId);
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public void delete(String userId) {
        studentDao.delete(userId);
    }

    @Override
    public void update(String userId, Student student) {
        studentDao.update(userId,student);
    }
}
