/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.config;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.com.datech.uic.portal.common.response.vo.Message;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom global exception handler.
 */
@RestControllerAdvice
public class CustomGlobalExceptionHandler
        extends ResponseEntityExceptionHandler {
    /**
     * Handler for @Valid.
     * @param ex MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(false);
        //Get all errors
        String[] messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toArray(String[]::new);
        List<Message> listMsg = new ArrayList<>();
        for (String msg : messages) {
            if (msg.contains(";")) {
                String[] msgPart = msg.split(";");
                listMsg.add(new Message(msgPart[0], msgPart[1]));
            } else {
                listMsg.add(new Message("error", msg));
            }
        }
        responseMessage.setMessage(listMsg);
        return new ResponseEntity<>(responseMessage, headers, status);
    }
}
