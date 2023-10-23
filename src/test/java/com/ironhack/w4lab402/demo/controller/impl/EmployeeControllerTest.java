package com.ironhack.w4lab402.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.w4lab402.demo.controller.dto.EmployeeStatusDTO;
import com.ironhack.w4lab402.demo.model.Employee;
import com.ironhack.w4lab402.demo.model.EmployeeStatus;
import com.ironhack.w4lab402.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ManarAlfarsi
 */
@SpringBootTest
class EmployeeControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

      MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    EmployeeRepository employeeRepository;
Employee employee;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        employee = new Employee(356712, "cardiology", "Alonso Flores", EmployeeStatus.ON_CALL);
}
//    @AfterEach
//    public void tearDown() {
//        employeeRepository.deleteById(356712);
//    }
/* GET */
    @Test
    void getAllEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Alonso Flores"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sam Ortega"));

    }
    @Test  void getAllEmployeeNegativeCase() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/api/employees/wrongPath")) -> Will be a 400 error.
//                .andExpect(status().is(400))
//        .andExpect(status().reason((mvcResult.getResponse().getContentAsString().contains("Bad Request"))));
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("Amal Alansari"));
        //mockMvc.perform(get("/api/employees/wrongPath")).andExpect(status().isNotFound()).andReturn();
        // 400 -> Bad request 404 -> Not Found.
        // Funny 404.
        mockMvc.perform(get("/api/employees/500")).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getEmployeeById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees/356712"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("356712"));
    }

    @Test
    void getEmployeeByStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees/status?status=ON"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(EmployeeStatus.ON.toString()));

    }


    @Test
    void getEmployeeByDepartment() throws Exception  {
        MvcResult mvcResult = mockMvc.perform(get("/api/employees/department?department=cardiology"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("cardiology"));

    }
    /* POST */
    @Test
    void saveEmployee() throws Exception {
        String body = objectMapper.writeValueAsString(employee);
        mockMvc.perform(post("/api/employees").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(employeeRepository.findAll().stream().anyMatch(e -> e.getId().equals(356712)));
    }
    /* PATCH */
    @Test
    void updateEmployeeStatus() throws Exception {
        EmployeeStatusDTO employeeStatusDTO = new EmployeeStatusDTO(EmployeeStatus.ON_CALL);

        String body = objectMapper.writeValueAsString(employeeStatusDTO);

        mockMvc.perform(patch("/api/employees/status/356712").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertTrue(employeeRepository.findAll().toString().contains(EmployeeStatus.ON_CALL.toString()));
//
    }

    @Test
    void updateEmployeeDepartment() throws Exception {
     employee.setDepartment("cardiology");

        String body = objectMapper.writeValueAsString(employee);

        mockMvc.perform(patch("/api/employees/department/356712").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
        assertTrue(employeeRepository.findAll().toString().contains("cardiology"));
//        assertTrue(employeeRepository.findAll().stream().anyMatch(e -> e.getStatus().equals(EmployeeStatus.ON_CALL)));
    }


}

