package com.acme.studenthome;

import com.acme.studenthome.controller.UserAccountSystemController.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class StudentHomeApplicationTests {

    @Autowired
    private UserController userController;
    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

}
