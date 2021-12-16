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
 * The type User form.
 */
@Getter
@Setter
@ToString
public class UserForm {
    /**
     * The Full name.
     */
    private String fullName;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Phone.
     */
    private String phone;
    /**
     * The Address.
     */
    private String address;
}
