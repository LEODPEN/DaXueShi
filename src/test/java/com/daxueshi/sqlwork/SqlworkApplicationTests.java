package com.daxueshi.sqlwork;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlworkApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void queryTemplate() throws Exception{
        //String content = "{\"userId\":\"010\",\"password\":\"10175101226\",\"email\":\"969023014@qq.com\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/checkCode")
                .param("email","969023014@qq.com")
                .param("checkCode","363359")
                //.content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("res = " + res);
    }
    @Test
    public void testCompany() throws Exception {
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/companies/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
    @Test
    public void testMajor() throws Exception{
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/dxs/major/info")
                .param("majorName","软件工程")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
    @Test
    public void testGraduate() throws Exception{
        //String content = "{\"userId\":\"13\",\"universityId\":\"360\",\"majorId\":\"37\",\"companyId\":\"0\"," +
        //       "\"position\":\"产品锦鲤\",\"salary\":\"9000\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/dxs/graduate/peers")
                //.content(content)
                .param("majorName","软件工程")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
    @Test
    public void testStudent() throws Exception{
        String content = "{\"scores\":\"800\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/students/009")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
    @Test
    public void testUniversity() throws Exception{
        //String content = "{\"scores\":\"800\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/dxs/university")
                //.content(content)
                .param("city","上海市")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void testUser() throws Exception{
        String content = "{\"email\":\"969023017@qq.com\",\"password\":\"10175101227\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.put("/dxs/user/")
                //.param("email","969023014@qq.com")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void testFollow() throws Exception{
        String res = mockMvc.perform(MockMvcRequestBuilders.delete("/dxs/follow")
                //.content(content)
                .param("followingEmail","969023014@qq.com")
                .param("followedEmail","969023010@qq.com")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void testLogin() throws Exception{
       // String content = "{\"email\":\"969023014@qq.com\",\"password\":\"10175101226\"}";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5rSL6JGxIiwiZW1haWwiOiI5NjkwMjMwMTRAcXEuY29tIiwiaWF0IjoxNTU5Mjc5MDE0LCJleHAiOjE1NTkzNjU0MTR9.ezPRyJ_Iy-z3O7UW7qWXOKENUZ3JapxxBD5JBCy9-Jg";
        String head = "token_" + token;
        String res = mockMvc.perform(MockMvcRequestBuilders.post("/dxs/user/visit")
                //.content(content)
                .header("Login",head)
                .param("followingEmail","969023014@qq.com")
                .param("followedEmail","969023016@qq.com")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("res = " + res);
        //userService.sendCheckcode("969023014@qq.com");
        //506447

    }

    @Test
    public void testDiscussion() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5rSL6JGxIiwiZW1haWwiOiI5NjkwMjMwMTRAcXEuY29tIiwiaWF0IjoxNTU5Mjc5MDE0LCJleHAiOjE1NTkzNjU0MTR9.ezPRyJ_Iy-z3O7UW7qWXOKENUZ3JapxxBD5JBCy9-Jg";
        String head = "token_" + token;
        String content = "{\"discussionId\":\"1559281006732382647\",\"commentId\":\"1559284258991774884\",\"content\":\"你真是复读机吧\",\"email\":\"969023017@qq.com\"}";
        //String id = "1559281130480222875";
        String res = mockMvc.perform(MockMvcRequestBuilders.put("/dxs/discussion/comment")
                .content(content)
                .header("Login",head)
                //.param("majorName","软件工程")
                //.param("id",id)
                //.param("discussionId","1559281006732382647")
                //.param("commentId","1559284258991774884")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("res = " + res);
    }

    @Test
    public void testGetDiscussion() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5rSL6JGxIiwiZW1haWwiOiI5NjkwMjMwMTRAcXEuY29tIiwiaWF0IjoxNTU5Mjc5MDE0LCJleHAiOjE1NTkzNjU0MTR9.ezPRyJ_Iy-z3O7UW7qWXOKENUZ3JapxxBD5JBCy9-Jg";
        String head = "token_" + token;
        String id = "1559281006732382647";
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/dxs/discussion/comment")
                .header("Login",head)
                //.param("majorName","软件工程")
                //.param("email","969023014@qq.com")
                .param("id",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("res = " + res);
    }

    @Test
    public void testDeleteDiscussion() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5rSL6JGxIiwiZW1haWwiOiI5NjkwMjMwMTRAcXEuY29tIiwiaWF0IjoxNTU5Mjc5MDE0LCJleHAiOjE1NTkzNjU0MTR9.ezPRyJ_Iy-z3O7UW7qWXOKENUZ3JapxxBD5JBCy9-Jg";
        String head = "token_" + token;
        String res = mockMvc.perform(MockMvcRequestBuilders.delete("/dxs/discussion/comment")
                .header("Login",head)
                //.param("majorName","软件工程")
                //.param("email","969023014@qq.com")
                .param("discussionId","1559281006732382647")
                .param("commentId","1559283593961555125")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("res = " + res);
    }
    @Test
    public void testThumb() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5rSL6JGxIiwiZW1haWwiOiI5NjkwMjMwMTRAcXEuY29tIiwiaWF0IjoxNTU5Mjc5MDE0LCJleHAiOjE1NTkzNjU0MTR9.ezPRyJ_Iy-z3O7UW7qWXOKENUZ3JapxxBD5JBCy9-Jg";
        String head = "token_" + token;
        String id = "1559281006732382647";
        String res = mockMvc.perform(MockMvcRequestBuilders.put("/dxs/discussion/thumb")
                //.content(content)
                .header("Login",head)
                //.param("majorName","软件工程")
                .param("id",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println("res = " + res);
    }

    @Test
    public void contextLoads() {
    }

}
