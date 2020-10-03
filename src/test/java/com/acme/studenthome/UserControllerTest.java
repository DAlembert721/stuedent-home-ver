package com.acme.studenthome;

import com.acme.studenthome.controller.UserAccountSystemController.UserController;
import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.service.UserAccountSystemService.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserControllerTest.class)
@WithMockUser
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUser() throws Exception{
        String body = "{\"email\":\"doguito@email.com\",\"password\":\"holiwis\"}";

        User result = new User();
        result.setId(1L);
        result.setEmail("doguito@email.com");
        result.setPassword("holiwis");

        User user = new User();
        user.setEmail("doguito@email.com");
        user.setPassword("holiwis");

        when(userService.createUser(anyObject())).thenReturn(result);

        mvc.perform(post("/api/users")
                .accept(MediaType.APPLICATION_JSON).content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
