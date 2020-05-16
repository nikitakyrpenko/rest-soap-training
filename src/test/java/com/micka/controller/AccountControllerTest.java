package com.micka.controller;

import com.micka.ObjectProvider;
import com.micka.dto.Account;
import com.micka.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest {
    private static final Account DOMAIN = (Account) ObjectProvider.provideObjectByClass(Account.class);

    @Mock
    AccountServiceImpl accountService;

    @InjectMocks
    AccountController accountController;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .build();
    }

    @Test
    void whenFindByIdCorrectThen302() throws Exception {
        when(accountService.findById(1)).thenReturn(DOMAIN);

        mockMvc.perform(get("/accounts/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(302));

        verify(accountService,times(1)).findById(anyInt());
    }

    @Test
    void whenFindAllCorrectThen302() throws Exception {
        when(accountService.findAll()).thenReturn(List.of(DOMAIN,DOMAIN));

        mockMvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(302));

        verify(accountService,times(1)).findAll();
    }

    @Test
    void whenFindAllByUserIdThen302() throws Exception{
        when(accountService.findAllByUserId(anyInt())).thenReturn(List.of(DOMAIN,DOMAIN));

        mockMvc.perform(get("/accounts/user/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(302));

        verify(accountService,times(1)).findAllByUserId(anyInt());
    }

    @Test
    void whenSaveCorrectThen201() throws Exception{
        when(accountService.save(any(Account.class))).thenReturn(1);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{ \"balance\": 1000, \"charge\": 0.3, \"userId\": 2 }")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is(201));

        verify(accountService,times(1)).save(any(Account.class));
    }

    @Test
    void whenUpdateCorrectThen201() throws Exception{
        when(accountService.update(any(Account.class))).thenReturn(1);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{ \"id\":3, \"balance\": 1000, \"charge\": 0.3, \"userId\": 2 }")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is(201));
    }

    @Test
    void whenDeleteThen302() throws Exception {
        doNothing().when(accountService).delete(anyInt());

        mockMvc.perform(delete("/accounts/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));

        verify(accountService,times(1)).delete(anyInt());
    }
}