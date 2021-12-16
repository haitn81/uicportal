/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.datech.uic.portal.common.base.controller.BaseController;
import vn.com.datech.uic.portal.common.base.helper.HelperResult;
import vn.com.datech.uic.portal.common.constant.Constants;
import vn.com.datech.uic.portal.common.response.utils.ResponseUtils;
import vn.com.datech.uic.portal.common.response.vo.Message;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;
import vn.com.datech.uic.portal.constant.UserMessageConstants;
import vn.com.datech.uic.portal.entity.User;
import vn.com.datech.uic.portal.form.user.UserCreateForm;
import vn.com.datech.uic.portal.helper.UserHelper;
import vn.com.datech.uic.portal.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/admin/user")
@Log4j2
public class UserController extends BaseController {
    /**
     * The User service.
     */
    private final UserService userService;
    /**
     * The User helper.
     */
    private final UserHelper userHelper;

    /**
     * Instantiates a new User controller.
     *
     * @param pUserService the p user service
     * @param pUserHelper  the p user helper
     */
    public UserController(final UserService pUserService,
                          final UserHelper pUserHelper) {
        this.userService = pUserService;
        this.userHelper = pUserHelper;
    }

    /**
     * Create response entity.
     *
     * @param form the form
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity create(final @Valid @RequestBody UserCreateForm form) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(false);
        List<Message> messages = new ArrayList<>();
        try {
            HelperResult helperResult = userHelper.validateCreate(form);
            if (helperResult.isSuccess()) {
                userService.create(form);
                responseMessage.setSuccess(true);
                messages.add(new Message(Constants.SUCCESS,
                        super.getMessageUtils().getMessage(UserMessageConstants.UPDATE_USER_SUCCESS)));
            } else {
                messages.addAll(helperResult.getMessage());
            }
        } catch (Exception e) {
            messages.add(super.getErrorMessage());
            responseMessage.setSuccess(false);
            log.error(e);
        }
        responseMessage.setMessage(messages);
        return ResponseUtils.buildResponseMessage(responseMessage.isSuccess(), responseMessage);
    }
}
