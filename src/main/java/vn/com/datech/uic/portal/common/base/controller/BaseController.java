/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import vn.com.datech.uic.portal.common.constant.CommonMessageConstants;
import vn.com.datech.uic.portal.common.constant.Constants;
import vn.com.datech.uic.portal.common.response.utils.ResponseUtils;
import vn.com.datech.uic.portal.common.response.vo.Message;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;
import vn.com.datech.uic.portal.common.utils.JwtTokenUtils;
import vn.com.datech.uic.portal.common.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Base controller.
 */
@Component
public class BaseController {
    /**
     * JWT utils.
     */
    private JwtTokenUtils jwtTokenUtils;
    /**
     * The Message utils.
     */
    private MessageUtils messageUtils;
    /**
     * The Validator.
     */
    private Validator validator;
    /**
     * The Model mapper.
     */
    private ModelMapper modelMapper;

    @Value("${uic-portal.ws-token-key}")
    private String wsToken;

    /**
     * Forbidden.
     *
     * @return the response entity
     */
    public ResponseEntity forbidden() {
        List<Message> messages = new ArrayList<>();
        ResponseMessage responseMessage = new ResponseMessage();
        String msgKey = "common.forbidden";
        Message msg = new Message();
        msg.setCode("error");
        msg.setMsg(
                getMessageUtils().getMessage(msgKey)
        );
        messages.add(msg);
        responseMessage.setSuccess(false);
        responseMessage.setMessage(messages);
        return ResponseUtils.buildResponseMessage(false,
                responseMessage, HttpStatus.FORBIDDEN);
    }

    /**
     * Check ws token boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean checkWsToken(final String token) {
        return wsToken.equals(token);
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public Message getErrorMessage() {
        return new Message(
                Constants.ERROR,
                messageUtils.getMessage(CommonMessageConstants.ERROR)
        );
    }

    /**
     * Gets error message.
     *
     * @param e the e
     * @return the error message
     */
    public Message getErrorMessage(final Exception e) {
        return new Message(
                Constants.ERROR,
                e.getMessage()
        );
    }

    /**
     * Gets logged in username.
     *
     * @return the logged in username
     */
    public String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Sets jwt token utils.
     *
     * @param pJwtTokenUtils the p jwt token utils
     */
    @Autowired
    public void setJwtTokenUtils(final JwtTokenUtils pJwtTokenUtils) {
        jwtTokenUtils = pJwtTokenUtils;
    }

    /**
     * Gets jwt token utils.
     *
     * @return the jwt token utils
     */
    public JwtTokenUtils getJwtTokenUtils() {
        return jwtTokenUtils;
    }

    /**
     * Gets message utils.
     *
     * @return the message utils
     */
    public MessageUtils getMessageUtils() {
        return messageUtils;
    }

    /**
     * Sets message utils.
     *
     * @param pMessageUtils the p message utils
     */
    @Autowired
    public void setMessageUtils(final MessageUtils pMessageUtils) {
        messageUtils = pMessageUtils;
    }

    /**
     * Gets validator.
     *
     * @return the validator
     */
    public Validator getValidator() {
        return validator;
    }

    /**
     * Sets validator.
     *
     * @param pValidator the p validator
     */
    @Autowired
    @Qualifier("AppValidator")
    public void setValidator(final Validator pValidator) {
        validator = pValidator;
    }

    /**
     * Sets model mapper.
     *
     * @param pModelMapper the p model mapper
     */
    @Autowired
    public void setModelMapper(final ModelMapper pModelMapper) {
        modelMapper = pModelMapper;
    }

    /**
     * Gets model mapper.
     *
     * @return the model mapper
     */
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
