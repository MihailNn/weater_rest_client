package com.aston.mihail.rest.controller;


import com.aston.mihail.rest.configuration.ApplicationConfig;
import com.aston.mihail.rest.configuration.MyConfig;
import com.aston.mihail.rest.service.TemperatureResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.hamcrest.Matchers;
import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class, MyConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemperatureControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    void givenWac_whenServletContext_thenItProvidesTemperatureController() {
        ServletContext servletContext = wac.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(wac.getBean("temperatureController"));
    }

    @Test
    public void givenCityURIWithPost_whenMockMVC_thenVerifyResponse() throws Exception {
        this.mockMvc.perform(post("/temp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"region\": \"Minsk\"}"))
                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.temp").value("10.86"))
                .andExpect((jsonPath("$.*", hasSize(1))))
                .andExpect(status().isOk());
    }

}