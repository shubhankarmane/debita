package com.shubhankar.debita.advice;

import com.shubhankar.debita.exception.AuthException;
import com.shubhankar.debita.exception.DuplicateCategoryException;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
//        Throwable rootCause = RootCause.get(e);
        return new ResponseEntity<>("Invalid data in body", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> onAuthException(AuthException e) {
        return new ResponseEntity<>("Invalid username or password", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> onNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<String> onDuplicateCategoryException(DuplicateCategoryException e) {
        return new ResponseEntity<>("Category already exists", HttpStatus.FORBIDDEN);
    }
}
