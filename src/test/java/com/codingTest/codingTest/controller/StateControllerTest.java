package com.codingTest.codingTest.controller;

import com.codingTest.codingTest.domain.State;
import com.codingTest.codingTest.services.Impl.StateServiceImpl;
import com.codingTest.codingTest.services.StateService;
import com.codingTest.codingTest.util.ConstantUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(StateController.class)
public class StateControllerTest {

    @Autowired
    private StateController stateController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockBean mockBean;

    @Autowired
    private StateServiceImpl stateServiceImpl;

    @Test
    public void addState() throws Exception {
        State state = new State();

        state.setStateName("Processed");
        state.setCreatedAt(new Date());
        state.setCreatedBy(ConstantUtil.SYSTEM);

        mockMvc.perform(MockMvcRequestBuilders.post("/state/add_state").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.STATUS").exists());
    }

    @Test
    public void updateState() throws Exception {
        State state = new State();

        state.setStateName("Processed");
        state.setCreatedAt(new Date());
        state.setCreatedBy(ConstantUtil.SYSTEM);

        mockMvc.perform(MockMvcRequestBuilders.post("/state/update_state").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.STATUS").exists());
    }

}