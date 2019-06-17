# DAXUESHI（大学仕）
Connect the universities and companies

> 起源于数据库大作业，第一阶段于6.14完成。大学仕致力于将在校生、毕业生紧密结合。最核心部分将有：1. 利用数据可视化使学生对本专业历年毕业生走向和专业前景有更清晰认知。2. 设置专业讨论区、点赞、评论、关注等一定的社交功能，不局限于本校本专业，使得相关专业各学校学生联系更紧密。3. 提供专业相关热门公司数据以及在职毕业生的经验分享与评分。产品的目标是一定程度解决大学生对未来的发展的迷茫现象。

+ 此处为DXS之后端仓库，前后完全分离.

+ [前端](https://github.com/Onion12138/dxs_front)主要使用elementui开发.

+ 部署相关:
```
1. 需要项多且时间匆忙，半自动化部署，参见下方说明和要求

2. 完整pull此项目，更改yml文件内容(例如密码)

3. 利用我们提供的sql文件建表和获取mysql中测试数据

4. 关于前端，你可以选择：
    a. 本地拉取前端，使用npm run dev 进行查看
    b. 拉取前端，修改相关配置（例如端口）,build后使用nginx代理，实现浏览器访问
```

+ 注意事项
`为保证顺利运行整个项目，希望以下项保持一致`
   
   1. java版本`11.0.1`及以上
   2. mysql版本`8`以上
   3. redis版本`4.2`以上
   4. mogodb版本`3.10`以上 

可从[仓库](https://hub.docker.com/u/leodpen)中找到latest版本或适合版本下载.或直接使用命令：
```
 docker pull leodpen/redis
 docker pull leodpen/mongo
 
 (nginx直接拉取 官方最新即可，注意前后的端口号)
```
+ 更多相关：
    
    1. 项目演示: [V1.0](http://118.24.96.45:8082).
    2. 部分开发记录见 '大学仕开发记录.md'，因期末时间紧，未持续更新.
    3. 后续项目大概率会继续改进，改进方向与方法暂且保留.
    4. 有问题欢迎联系我们。

#####  以下是后端项目结构：
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

