package com.shubhankar.debita.advice;

import com.shubhankar.debita.exception.AuthException;
import com.shubhankar.debita.util.RootCause;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> onConstraintValidationException(ConstraintViolationException e) {
        List<String> violations = new ArrayList<>();
        for (var violation : e.getConstraintViolations()) {
            violations.add(violation.getPropertyPath() + " " + violation.getMessage());
        }
        return new ResponseEntity<>(StringUtils.join(violations, '\n'), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        for (var fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(StringUtils.join(errors, '\n'), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> onDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable rootCause = RootCause.get(e);
        return new ResponseEntity<>("Invalid data in body", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> onAuthException(AuthException e) {
        return new ResponseEntity<>("Invalid username or password", HttpStatus.FORBIDDEN);
    }
}
