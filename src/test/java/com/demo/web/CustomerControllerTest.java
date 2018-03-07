package com.demo.web;

import com.demo.model.Customer;
import com.demo.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerServiceImpl service;

    @Test
    public void testShouldGetAllCustomers() throws Exception {
        this.mockMvc.perform(
                get("/customers")
                        .contentType(APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testShouldAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setAge(12);
        customer.setLastname("Test");
        customer.setFirstname("Test");
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(customer);
        this.mockMvc.perform(
                post("/customers")
                        .accept(APPLICATION_JSON_VALUE)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(body)
        )
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
