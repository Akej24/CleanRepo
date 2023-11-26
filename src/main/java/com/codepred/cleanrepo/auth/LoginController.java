package com.codepred.cleanrepo.auth;

import com.codepred.cleanrepo.auth.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/login")
@RequiredArgsConstructor
class LoginController {

    private final LoginQueryHandler loginQueryHandler;

    @PostMapping
    ResponseEntity<Void> loginUser(@RequestBody LoginQuery.Json requestJson) {
        var loginQuery = requestJson.toQuery();
        LoginQueryResponse response = loginQueryHandler.handleLoginQuery(loginQuery);
        var responseJson = LoginQueryResponse.Json.fromQuery(response);
        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + responseJson.jwt());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }
}
