package com.bluewin.userservice;

import com.bluewin.userservice.model.User;
import com.bluewin.userservice.repository.UserRepository;
import com.bluewin.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// This class tests the basic userService functionalities we implemented.
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void getUserQuotaTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test_user");
        user.setEmailQuota(10);
        user.setMonthlyCharge(10);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        int quota = userService.getUserQuota(1L);

        assertThat(quota).isEqualTo(10);
    }

    @Test
    public void updateUserQuotaTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test_user");
        user.setEmailQuota(10);

        User SecondUser = new User();
        SecondUser.setId(2L);
        SecondUser.setUsername("test_user");
        SecondUser.setEmailQuota(1);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(SecondUser));

        User resultUser = userService.updateUserQuota(1L, 1);
        User secondResultUser = userService.updateUserQuota(2L, 10);

        assertThat(resultUser.getEmailQuota()).isEqualTo(1);
        assertThat(resultUser.getMonthlyCharge()).isEqualTo(0);

        assertThat(secondResultUser.getEmailQuota()).isEqualTo(10);
        assertThat(secondResultUser.getMonthlyCharge()).isEqualTo(1);
    }

}
