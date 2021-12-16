/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.com.datech.uic.portal.common.base.entity.BaseEntity;
import vn.com.datech.uic.portal.constant.UserEntityConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type User.
 */
@Getter
@Setter
@Table(name = UserEntityConstants.TABLE_NAME)
@Entity
@ToString
public class User extends BaseEntity {
    /**
     * The Username.
     */
    private String username;
    /**
     * The Password.
     */
    private String password;
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
    /**
     * The Active.
     */
    private boolean active;
}
