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
 * The type User update form.
 */
@Getter
@Setter
@ToString
public class UserUpdateForm extends UserForm {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The Password.
     */
    private String password;
}
