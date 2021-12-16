/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.helper;

import org.springframework.stereotype.Component;
import vn.com.datech.uic.portal.common.base.helper.BaseHelper;
import vn.com.datech.uic.portal.common.base.helper.HelperResult;
import vn.com.datech.uic.portal.common.response.vo.Message;
import vn.com.datech.uic.portal.constant.UserMessageConstants;
import vn.com.datech.uic.portal.entity.User;
import vn.com.datech.uic.portal.form.user.UserCreateForm;
import vn.com.datech.uic.portal.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User helper.
 */
@Component
public class UserHelper extends BaseHelper {
    /**
     * The User service.
     */
    private final UserService userService;

    /**
     * Instantiates a new User helper.
     *
     * @param pUserService the p user service
     */
    public UserHelper(final UserService pUserService) {
        this.userService = pUserService;
    }

    /**
     * Validate create helper result.
     *
     * @param form the form
     * @return the helper result
     */
    public HelperResult validateCreate(final UserCreateForm form) {
        User user = userService.get(form.getUsername());
        List<Message> messages = new ArrayList<>();
        if (user != null) {
            messages.add(
                    new Message(UserMessageConstants.FIELD_USERNAME,
                            super.getMessageUtils().getMessage(UserMessageConstants.USERNAME_IS_EXISTED)));
        }
        return HelperResult.buildResult(messages);
    }
}
