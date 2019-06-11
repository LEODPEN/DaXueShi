package com.daxueshi.sqlwork.service.impl;

import com.daxueshi.sqlwork.converter.TotalUserDTOConverter;
import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.Graduate;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.dto.TotalUserDTO;
import com.daxueshi.sqlwork.enums.OtherErrorEnums;
import com.daxueshi.sqlwork.enums.UserStatusEnums;
import com.daxueshi.sqlwork.exception.MyException;
import com.daxueshi.sqlwork.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.list.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private UserDao userDao;


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

        List<TotalUserDTO> totalUserDTOS = new ArrayList<>();
        String order = "grade desc";
        PageHelper.startPage(page-1,size,order);
        Student student = studentDao.findOne(email);

//        Graduate graduate = graduateDao.findOne(email);

        if (student==null){
            throw new MyException(OtherErrorEnums.NO_RIGHT);
        }

        //同专业
        List<Student> students = studentDao.findByMajorName(student.getMajorName());
        PageInfo pageInfo = new PageInfo(students);

        for (Student s : students){
            totalUserDTOS.add(TotalUserDTOConverter.convert(s,userDao.findByMail(s.getEmail()).getNickname()));
        }
        pageInfo.setList(totalUserDTOS);

        return pageInfo;
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
        List<TotalUserDTO> totalUserDTOS = new ArrayList<>();

        List<Student> students = studentDao.findByUniversityAndMajor(student.getUniversityName(),student.getMajorName());
        PageInfo pageInfo = new PageInfo(students);

        for (Student s : students){
            totalUserDTOS.add(TotalUserDTOConverter.convert(s,userDao.findByMail(s.getEmail()).getNickname()));
        }
        pageInfo.setList(totalUserDTOS);

        return pageInfo;
    }

    @Override
    //查得到所有的用户,故不对学校or专业排序,且直接传list
    public List<TotalUserDTO> findByNickname(String nickname) {
        List<User> users = userDao.findByNickname(nickname);

        List<TotalUserDTO> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(users)){
            return result;
        }
        for (User u:users){
            String nn = u.getNickname();
            if (u.getStatus().equals(UserStatusEnums.VISITOR.getCode())){
                result.add(TotalUserDTOConverter.convert(u,nn));
            }
            else if (u.getStatus().equals(UserStatusEnums.STUDENT.getCode())){
                result.add(TotalUserDTOConverter.convert(studentDao.findOne(u.getEmail()),nn));
            }else {
                result.add(TotalUserDTOConverter.convert(graduateDao.findOne(u.getEmail()),nn));
            }
        }
        return result;

    }
}
