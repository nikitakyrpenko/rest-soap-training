package com.micka.controller;

import com.micka.ObjectProvider;
import com.micka.dto.Account;
import com.micka.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    void whenFindAllByUserIdThen302() throws Exception{
        when(accountService.findAllByUserId(anyInt(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(DOMAIN,DOMAIN)));

        mockMvc.perform(get("/users/{id}/accounts",1)
                .param("page","0")
                .param("size","1")
                .param("sort","id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(302));

        verify(accountService,times(1)).findAllByUserId(anyInt(),any(Pageable.class));
    }

    @Test
    void whenSaveCorrectThen201() throws Exception{
        when(accountService.save(any(Account.class))).thenReturn(1);

        mockMvc.perform(post("/users/{id}/accounts",1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{ \"balance\": 1000, \"charge\": 0.3}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is(201));

        verify(accountService,times(1)).save(any(Account.class));
    }

    @Test
    void whenUpdateCorrectThen201() throws Exception{
        when(accountService.update(any(Account.class))).thenReturn(1);

        mockMvc.perform(post("/users/{id}/accounts",1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{ \"id\":3, \"balance\": 1000, \"charge\": 0.3}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .is(201));
    }
}