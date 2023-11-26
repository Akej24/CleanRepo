package com.codepred.cleanrepo.exception_handler;

import com.codepred.cleanrepo.account.exception.GenericAccountException;
import com.codepred.cleanrepo.auth.exception.GenericAuthenticationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
class ErrorResponseController {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception){
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

        List<ErrorDto> errors = violations.stream()
                .map(violation -> new ErrorDto(getConstraintViolationField(violation), violation.getMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder().errors(errors).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            GenericAuthenticationException.class,
            GenericAccountException.class
    })
    ResponseEntity<?> handleBadRequestException(RuntimeException exception){
        List<ErrorDto> errors = List.of(ErrorDto.builder()
                .message(exception.getMessage())
                .build());
        ErrorResponse errorResponse = ErrorResponse.builder().errors(errors).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getConstraintViolationField(ConstraintViolation<?> violation) {
        String[] propertyPath = violation.getPropertyPath().toString().split("\\.");
        return propertyPath.length > 0 ? propertyPath[propertyPath.length - 1] : "unknown";
    }
}