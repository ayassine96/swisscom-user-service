package com.bluewin.userservice;

import com.bluewin.userservice.controller.UserController;
import com.bluewin.userservice.service.UserService;
import com.bluewin.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserQuotaTest() throws Exception {
        when(userService.getUserQuota(1L)).thenReturn(10);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/quota")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void updateUserQuotaTest() throws Exception {
        User user = new User();
        user.setId(2L);
        user.setUsername("test_user");
        user.setEmailQuota(10);
        user.setMonthlyCharge(1);

        when(userService.updateUserQuota(2L, 10)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/2/quota")
                .content("{\"emailQuota\": 10}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 2, \"username\": \"test_user\", \"emailQuota\": 10, \"monthlyCharge\": 1}"));
    }

}

