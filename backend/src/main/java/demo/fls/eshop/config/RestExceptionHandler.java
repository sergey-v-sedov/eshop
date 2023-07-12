package demo.fls.eshop.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> handleException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Ошибка на сервере");
        problemDetail.setType(URI.create("http://localhost:8080/api/v1/errors/internal_server_error"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("traceId", MDC.get("traceId"));
        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        problemDetail.setTitle(e.getMessage());
        problemDetail.setDetail(Arrays.toString(errors.toArray()));
        problemDetail.setType(URI.create("http://localhost:8080/api/v1/errors/unprocessable_entity"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("traceId", MDC.get("traceId"));
        return new ResponseEntity<>(problemDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        problemDetail.setTitle(e.getMessage());
        problemDetail.setDetail(Arrays.toString(errors.toArray()));
        problemDetail.setType(URI.create("http://localhost:8080/api/v1/errors/unprocessable_entity"));
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("traceId", MDC.get("traceId"));
        return new ResponseEntity<>(problemDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}