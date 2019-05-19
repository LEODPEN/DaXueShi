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
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/majors/jobInfo/37")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
    @Test
    public void testGraduate() throws Exception{
        String content = "{\"userId\":\"13\",\"universityId\":\"360\",\"majorId\":\"37\",\"companyId\":\"0\"," +
               "\"position\":\"产品锦鲤\",\"salary\":\"9000\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/graduates")
                .content(content)
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
        String res = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/universities/city/北京市")
                //.content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void testUser() throws Exception{
        String content = "{\"userId\":\"009\",\"email\":\"969023015@qq.com\",\"password\":\"10175101225\"}";
        String res = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/")
                //.param("email","969023014@qq.com")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
    @Test
    public void contextLoads() {
    }

}
