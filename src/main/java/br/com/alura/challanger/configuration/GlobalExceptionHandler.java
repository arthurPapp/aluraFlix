package br.com.alura.challanger.configuration;

import br.com.alura.challanger.model.ExceptionDto;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(NotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDto(HttpStatus.NOT_FOUND.value(), e.getMessage())
        );
    }



    @RequestMapping(produces = "application/json")
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionDto> ForbiddenException(ForbiddenException e){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDto(HttpStatus.FORBIDDEN.value(), e.getMessage())
        );
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> BadRequestException(ConstraintViolationException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDto> BadRequestException(BadRequestException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> IllegalArgumentException(IllegalArgumentException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> DataIntegrityViolationException(DataIntegrityViolationException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> DataIntegrityViolationException(MethodArgumentNotValidException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST.value(), e.getMessage())
        );
    }
}
