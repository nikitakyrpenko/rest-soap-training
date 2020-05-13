package com.micka.controller;

import com.micka.ObjectProvider;
import com.micka.dto.User;
import com.micka.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private static final User DOMAIN = (User) ObjectProvider.provideObjectByClass(User.class);

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    void whenFindByIdCorrectThen302() throws Exception {
        when(userService.findById(1)).thenReturn(DOMAIN);

        mockMvc.perform(get("/users/{id}", 1))
                .andDo(print())
                .andExpect(status().is(302));

        verify(userService,times(1)).findById(anyInt());
    }

    @Test
    void whenFindByIdEmptyThen302() throws Exception {
        when(userService.findById(anyInt())).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 1))
                .andDo(print())
                .andExpect(status().is(302));
    }

    @Test
    void whenSaveCorrectThen201() throws Exception{
        when(userService.save(any(User.class))).thenReturn(1);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{ \"firstName\": \"foo\", \"lastName\": \"bar\", \"email\": \"foo@bar\", \"password\": \"123\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is(201));

        verify(userService,times(1)).save(any(User.class));
    }

    @Test
    void whenUpdateCorrectThen201() throws Exception{
        when(userService.update(any(User.class))).thenReturn(1);

        mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{ \"id\":1, \"firstName\": \"foo\", \"lastName\": \"bar\", \"email\": \"foo@bar\", \"password\": \"123\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is(200));

        verify(userService,times(1)).update(any(User.class));
    }

    @Test
    void whenDeleteThen302() throws Exception {
        doNothing().when(userService).delete(anyInt());

        mockMvc.perform(delete("/users/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));

        verify(userService,times(1)).delete(anyInt());
    }
}