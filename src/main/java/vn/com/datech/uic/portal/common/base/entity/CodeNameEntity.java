/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.com.datech.uic.portal.common.base.constant.CodeNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The type Code name entity.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public class CodeNameEntity extends BaseEntity {
    /**
     * The Code.
     */
    @Column(name = CodeNameConstants.COLUMN_CODE)
    private String code;
    /**
     * The Lower code.
     */
    @Column(name = CodeNameConstants.COLUMN_LOWER_CODE)
    private String lowerCode;
    /**
     * The Name.
     */
    @Column(name = CodeNameConstants.COLUMN_NAME)
    private String name;
    /**
     * The Lower name.
     */
    @Column(name = CodeNameConstants.COLUMN_LOWER_NAME)
    private String lowerName;
}
