package com.basis.RRM.web.rest;

import com.basis.RRM.RrmApplication;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RrmApplication.class)
public class CargoResourceTest {

    private MockMvc mockMvc;
    private final String API_URL = "/api/cargo/";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initTeste(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test @SneakyThrows
    public void getCargosTest(){
        mockMvc.perform(
            get(API_URL)
        ).andExpect(status().isOk());
    }

}
