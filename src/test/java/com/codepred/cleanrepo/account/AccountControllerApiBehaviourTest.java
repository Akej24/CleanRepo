package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.command.RegisterCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("without-security")
class AccountControllerApiBehaviourTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountCommandHandler accountCommandHandler;

    @Test
    @DisplayName("Should pass when successfully authenticated valid account")
    void loginUser() throws Exception {
        var json = new RegisterCommand.Json("test@gmail.com",
                "Password123!",
                "Jack",
                "Smith");
        mockMvc.perform(
                post("/api/account")
                        .content(objectMapper.writeValueAsString(json))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}