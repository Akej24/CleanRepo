package com.codepred.cleanrepo.test;

import com.codepred.cleanrepo.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/test")
@PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
@RequiredArgsConstructor
class TestEndpoint {

    private final AuthenticationFacade authenticationFacade;

    @GetMapping
    ResponseEntity<String> test() {
        int accountId = authenticationFacade.getAccountId();
        System.out.println("accountId:" + accountId);
        return ResponseEntity.status(HttpStatus.OK).body("test");
    }
}

