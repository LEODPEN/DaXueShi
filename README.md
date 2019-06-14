# DAXUESHI（大学仕）
Connect the universities and companies

> 起源于数据库大作业，第一阶段于6.14完成

+ 此处为DXS之后端仓库，前后完全分离.

+ [前端](https://github.com/Onion12138/dxs_front)主要使用elementui开发.

+ 后端项目结构：
```
.
├── java
│   └── com
│       └── daxueshi
│           └── sqlwork
│               ├── RequestDataForm
│               │   └── RequestForm.java
│               ├── SqlworkApplication.java
│               ├── VO
│               │   └── Result.java
│               ├── config
│               │   ├── CorsConfig.java
│               │   ├── GlobalCorConfig.java
│               │   ├── InterceptorConfig.java
│               │   ├── MybatisConfig.java
│               │   ├── SwaggerConfig.java
│               │   ├── WebSecurityConfig.java
│               │   └── WebSocketConfig.java
│               ├── controller
│               │   ├── CityController.java
│               │   ├── DiscussionController.java
│               │   ├── FollowController.java
│               │   ├── GraduateController.java
│               │   ├── InterviewController.java
│               │   ├── MajorController.java
│               │   ├── MessageController.java
│               │   ├── StudentController.java
│               │   ├── UniversityController.java
│               │   └── UserController.java
│               ├── converter
│               │   └── TotalUserDTOConverter.java
│               ├── dao
│               │   ├── CityDao.java
│               │   ├── CommentDao.java
│               │   ├── CompanyDao.java
│               │   ├── DiscussionDao.java
│               │   ├── FollowDao.java
│               │   ├── GraduateDao.java
│               │   ├── InterviewDao.java
│               │   ├── MajorDao.java
│               │   ├── MessageDao.java
│               │   ├── StudentDao.java
│               │   ├── UniversityDao.java
│               │   └── UserDao.java
│               ├── domain
│               │   ├── City.java
│               │   ├── Comment.java
│               │   ├── Company.java
│               │   ├── Discussion.java
│               │   ├── Follow.java
│               │   ├── Graduate.java
│               │   ├── Interview.java
│               │   ├── Major.java
│               │   ├── Message.java
│               │   ├── Student.java
│               │   ├── University.java
│               │   └── User.java
│               ├── dto
│               │   ├── ChoiceDTO.java
│               │   ├── CommentDTO.java
│               │   ├── DesCityDTO.java
│               │   ├── DesInstitutionDTO.java
│               │   ├── GraduateInfo.java
│               │   └── TotalUserDTO.java
│               ├── enums
│               │   ├── GraduationEnums.java
│               │   ├── OtherErrorEnums.java
│               │   ├── ResultEnums.java
│               │   ├── SalaryEnums.java
│               │   ├── UserEnums.java
│               │   └── UserStatusEnums.java
│               ├── exception
│               │   └── MyException.java
│               ├── handler
│               │   └── MyExceptionHandler.java
│               ├── interceptor
│               │   └── JwtInterceptor.java
│               ├── lock
│               │   └── RedisLock.java
│               ├── provider
│               │   ├── GraduateProvider.java
│               │   ├── StudentProvider.java
│               │   └── UserProvider.java
│               ├── service
│               │   ├── CityService.java
│               │   ├── DataDisplayService.java
│               │   ├── DiscussionService.java
│               │   ├── FollowService.java
│               │   ├── GraduateService.java
│               │   ├── MailService.java
│               │   ├── MajorService.java
│               │   ├── MessageService.java
│               │   ├── StudentService.java
│               │   ├── UniversityService.java
│               │   ├── UserService.java
│               │   └── impl
│               │       ├── CityServiceImpl.java
│               │       ├── DataDisplayServiceImpl.java
│               │       ├── DiscussionServiceImpl.java
│               │       ├── FollowServiceImpl.java
│               │       ├── GraduateServiceImpl.java
│               │       ├── MailServiceImpl.java
│               │       ├── MajorServiceImpl.java
│               │       ├── MessageServiceImpl.java
│               │       ├── StudentServiceImpl.java
│               │       ├── UniversityServiceImpl.java
│               │       └── UserServiceImpl.java
│               ├── socket
│               │   └── MyWebSocket.java
│               └── utils
│                   ├── CheckcodeUtils.java
│                   ├── GraduateJwtUtils.java
│                   ├── JwtUtils.java
│                   ├── KeyUtils.java
│                   ├── ResultUtils.java
│                   └── StudentJwtUtils.java
└── resources
    ├── application.yml
    ├── major_system.sql
    └── static

```

+ 注：
  1. 部分开发记录见 '大学仕开发记录.md'，因期末时间紧，未持续更新。
  2. 后续项目大概率会继续改进，改进方向与方法暂且保留。
  3. 有问题欢迎联系我们。
