package com.ztp.mettings.user;

import com.ztp.mettings.error.common.ResourceNotFoundProblem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserExceptionTests {

    @Autowired
    UserService userService;

    @Test
    public void whenFindNotExistingUser_thenThrowsResourceNotFoundProblem() {
        Assertions.assertThrows(ResourceNotFoundProblem.class, () -> {
            userService.findByIdOrThrow404("1");
        });
    }
}
