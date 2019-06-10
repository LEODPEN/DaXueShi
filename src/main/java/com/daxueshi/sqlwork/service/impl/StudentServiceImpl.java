package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.enums.OtherErrorEnums;
import com.daxueshi.sqlwork.exception.MyException;
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

    @Autowired
    private GraduateDao graduateDao;


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
    public PageInfo findByMajorName(String email, Integer page, Integer size) {

        String order = "grade desc";
        PageHelper.startPage(page-1,size,order);
        Student student = studentDao.findOne(email);

//        Graduate graduate = graduateDao.findOne(email);

        if (student==null){
            throw new MyException(OtherErrorEnums.NO_RIGHT);
        }
        return new PageInfo(studentDao.findByMajorName(student.getMajorName()));
    }

    @Override
    public PageInfo findByUniversityAndMajor(String email, Integer page, Integer size) {

        String order = "university_name desc";
        PageHelper.startPage(page-1,size,order);

        Student student = studentDao.findOne(email);
        //目前设定
        //如果是往届的学生，从未毕业到毕业状态的话，是可以查到的
//        Graduate graduate = graduateDao.findOne(email);

        if (student==null){
            throw new MyException(OtherErrorEnums.NO_RIGHT);
        }


        return new PageInfo(studentDao.findByUniversityAndMajor(student.getUniversityName(),student.getMajorName()));
    }
}
