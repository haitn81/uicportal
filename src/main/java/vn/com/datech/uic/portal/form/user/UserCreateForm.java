/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.form.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The type User create form.
 */
@Getter
@Setter
@ToString
public class UserCreateForm extends UserForm {
    /**
     * The Password.
     */
    private String password;
    /**
     * The Username.
     */
    private String username;
}
