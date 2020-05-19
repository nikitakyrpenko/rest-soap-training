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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminControllerTest {

    private static final Account DOMAIN = (Account) ObjectProvider.provideObjectByClass(Account.class);

    @Mock
    AccountServiceImpl accountService;

    @InjectMocks
    AdminController adminController;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(adminController)
                .build();
    }

    @Test
    void whenFindAllCorrectThen302() throws Exception {
        when(accountService.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(DOMAIN,DOMAIN)));

        mockMvc.perform(get("/admin/accounts/")
                .param("page","0")
                .param("size","1")
                .param("sort","id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is(302));

        verify(accountService,times(1)).findAll(any(Pageable.class));
    }

     @Test
    void whenDeleteThen302() throws Exception {
        doNothing().when(accountService).delete(anyInt());

        mockMvc.perform(delete("/admin/accounts/{id}", 1))
                .andDo(print())
                .andExpect(status().is(200));

        verify(accountService,times(1)).delete(anyInt());
    }

}