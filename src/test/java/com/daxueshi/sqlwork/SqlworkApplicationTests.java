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
    public void contextLoads() {
    }

}
