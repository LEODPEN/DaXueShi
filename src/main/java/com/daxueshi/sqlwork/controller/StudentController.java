package com.daxueshi.sqlwork.controller;

import com.daxueshi.sqlwork.RequestDataForm.RequestForm;
import com.daxueshi.sqlwork.VO.Result;
import com.daxueshi.sqlwork.converter.TotalUserDTOConverter;
import com.daxueshi.sqlwork.dao.GraduateDao;
import com.daxueshi.sqlwork.dao.StudentDao;
import com.daxueshi.sqlwork.dao.UserDao;
import com.daxueshi.sqlwork.domain.Student;
import com.daxueshi.sqlwork.domain.User;
import com.daxueshi.sqlwork.dto.TotalUserDTO;
import com.daxueshi.sqlwork.enums.GraduationEnums;
import com.daxueshi.sqlwork.enums.UserStatusEnums;
import com.daxueshi.sqlwork.service.DataDisplayService;
import com.daxueshi.sqlwork.service.StudentService;
import com.daxueshi.sqlwork.utils.StudentJwtUtils;
import com.daxueshi.sqlwork.utils.JwtUtils;
import com.daxueshi.sqlwork.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onion
 * @date 2019-05-10 -19:08
 */
@RestController
@Api(tags = "学生相关信息查询请求")
@RequestMapping("/dxs")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private DataDisplayService dataDisplayService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GraduateDao graduateDao;

    @ApiOperation("查询本校本专业在校生信息")
    @GetMapping("/student/classmates")
    public Result findClassmates(@RequestParam("token") String token,
                                 @RequestParam(value = "page", defaultValue = "1")Integer page,
                                 @RequestParam(value = "size", defaultValue = "20")Integer size){

        String email = (String) JwtUtils.parseJwt(token).get("email");
        PageInfo results = studentService.findByUniversityAndMajor(email,page,size);
        List<TotalUserDTO> totalUserDTOS = (List<TotalUserDTO>) results.getList();
        //List<TotalUserDTO> newList = new ArrayList<>();
        for (TotalUserDTO t : totalUserDTOS){
            if (graduateDao.findOne(t.getEmail())!=null){
                t.setRole("毕业老狗");
            }
        }
        results.setTotal(results.getTotal());
        results.setList(totalUserDTOS);
        return ResultUtils.success(results);
    }

    @ApiOperation("查询本专业在校生信息")
    @GetMapping("/student/peers")
    public Result findPeers(@RequestParam("token") String token,
                            @RequestParam(value = "page", defaultValue = "1")Integer page,
                            @RequestParam(value = "size", defaultValue = "20")Integer size){

        String email = (String) JwtUtils.parseJwt(token).get("email");

        PageInfo results = studentService.findByMajorName(email,page,size);

        return ResultUtils.success(results);
    }

    @GetMapping("/student/findOneByEmail")
    //能找到全部的student和graduate,无论是不是本专业本学校
    //直接传list
    public Result findOneByEmail(@RequestParam("email") String email){

        User user = userDao.findByMail(email);
        TotalUserDTO result = new TotalUserDTO();
        if (user!=null){
            String nickname = user.getNickname();
            if (user.getStatus().equals(UserStatusEnums.VISITOR.getCode())){
                result = TotalUserDTOConverter.convert(user,nickname);
            }
            else if (user.getStatus().equals(UserStatusEnums.STUDENT.getCode())){
                result = TotalUserDTOConverter.convert(studentDao.findOne(email),nickname);
            }
            else {
                result = TotalUserDTOConverter.convert(graduateDao.findOne(email),nickname);
            }
        }
        return ResultUtils.success(result);
    }

    @GetMapping("/student/findOneByNickname")
    public Result findOneByNickname(@RequestParam("nickname") String nickname ){

        //List<User> users = userDao.findByNickname(nickname);
        return ResultUtils.success(studentService.findByNickname(nickname));
    }

//    @ApiOperation("删除学生信息")
//    @DeleteMapping("/student")
//    public Result delete(@RequestParam  String email){
//        studentService.delete(email);
//        return ResultUtils.success();
//    }
    @ApiOperation("更新学生信息")
    @PutMapping("/student")
    public Result update(@RequestParam String email,@RequestBody Student student){
        student.setEmail(email);
        studentService.update(student);
        return ResultUtils.success();
    }

    @PostMapping("/student")
    public Result saveStudent(@RequestBody Student student,@RequestParam String email){
        student.setEmail(email);
        studentService.save(student);
        return ResultUtils.success();
    }

    /*data display part */
    @PostMapping("/student/choice")
    public Result getChoice(@RequestBody RequestForm requestForm){
        String email = (String) JwtUtils.parseJwt(requestForm.getToken()).get("email");
        log.info("用户{}查找{}年应届生去向分布",email,requestForm.getYear());
        return ResultUtils.success(dataDisplayService.getChoice(requestForm.getYear(),requestForm.getCollege(),requestForm.getMajor()));
    }

    @PostMapping("/student/desCity")
    public Result desCity(@RequestBody RequestForm requestForm){
        String email = (String) JwtUtils.parseJwt(requestForm.getToken()).get("email");
        log.info("用户{}查找{}年应届生城市分布",email,requestForm.getYear());
        return ResultUtils.success(dataDisplayService.getDesCity(requestForm.getYear(),requestForm.getCollege(),requestForm.getMajor()));
    }

    @PostMapping("/student/desCollege")
    public Result desCollege(@RequestBody RequestForm requestForm){
        String email = (String) JwtUtils.parseJwt(requestForm.getToken()).get("email");
        log.info("用户{}查找{}年应届生研究生学校分布",email,requestForm.getYear());
        return ResultUtils.success(dataDisplayService.getInstitution(requestForm.getYear(),requestForm.getCollege(),requestForm.getMajor(), GraduationEnums.STUDY.getCode()));
    }

    @PostMapping("/student/desCompany")
    public Result desCompany(@RequestBody RequestForm requestForm){
//        String email = (String) UserJwtUtils.parseJwt(requestForm.getToken()).get("email");
        //验证是本科还是毕业生，选择不同解析方式
        //考虑加prefix做substring的识别
        String email = (String) StudentJwtUtils.parseJwt(requestForm.getToken()).get("email");
        log.info("用户{}查找{}年应届生研究生公司分布",email,requestForm.getYear());

        return ResultUtils.success(dataDisplayService.getInstitution(requestForm.getYear(),requestForm.getCollege(),requestForm.getMajor(),GraduationEnums.WORK.getCode()));
    }

    @PostMapping("/student/salaryChange")
    public Result fiveYearSalary(@RequestBody RequestForm requestForm){
        String email = (String) JwtUtils.parseJwt(requestForm.getToken()).get("email");
        log.info("用户{}查找{}近5年应届生平均资薪水平走势",email,requestForm.getYear());

        return ResultUtils.success(dataDisplayService.getSalaryTrend(requestForm.getYear(),requestForm.getCollege(),requestForm.getMajor()));


    }

}
