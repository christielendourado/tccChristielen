package com.christielen.informacoescadastrais.rest.handler;

import com.christielen.informacoescadastrais.exception.*;
import com.christielen.informacoescadastrais.rest.form.exception.ExceptionResponseForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AssociateIsMembershipHealthPlanException.class)
    public ResponseEntity<ExceptionResponseForm> associateIsMembershipHealthPlanException(AssociateIsMembershipHealthPlanException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IntegrationFailedException.class)
    public ResponseEntity<ExceptionResponseForm> integrationFailedException(IntegrationFailedException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FieldValidationException.class)
    public ResponseEntity<ExceptionResponseForm> fieldValidationException(FieldValidationException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAssociateException.class)
    public ResponseEntity<ExceptionResponseForm> notAssociateException(NotAssociateException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotHealthPlanException.class)
    public ResponseEntity<ExceptionResponseForm> notHealthPlanException(NotHealthPlanException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotActiveMembershipHealthPlanException.class)
    public ResponseEntity<ExceptionResponseForm> notActiveMembershipHealthPlanException(NotActiveMembershipHealthPlanException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AssociateIsNotMembershipHealthPlanException.class)
    public ResponseEntity<ExceptionResponseForm> associateIsNotMembershipHealthPlanException(AssociateIsNotMembershipHealthPlanException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AssociateIsNotMembershipActiveHealthPlanException.class)
    public ResponseEntity<ExceptionResponseForm> associateIsNotMembershipActiveHealthPlanException(AssociateIsNotMembershipActiveHealthPlanException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotConvenedException.class)
    public ResponseEntity<ExceptionResponseForm> notConvenedException(NotConvenedException e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseForm> customException(Exception e) {
        ExceptionResponseForm response = new ExceptionResponseForm();
        response.setErrorCode("INTERNAL_SERVER_ERROR");
        response.setErrorMessage("Ocorreu um erro inesperado. Por favor contate o administrador do sistema.");
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        log.error(e.getMessage(), e);
        return new ResponseEntity<ExceptionResponseForm>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}