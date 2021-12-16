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
import vn.com.datech.uic.portal.common.base.constant.BaseEntityConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The type Base entity.
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    /**
     * The Id.
     */
    @Id
    @Column(name = BaseEntityConstants.COLUMN_ID)
    private Long id;

    /**
     * The Created time.
     */
    @Column(name = BaseEntityConstants.COLUMN_CREATED_TIME, nullable = false)
    private Date createdTime;

    /**
     * The Modified time.
     */
    @Column(name = BaseEntityConstants.COLUMN_MODIFIED_TIME, nullable = false)
    private Date modifiedTime;

    /**
     * The Created by.
     */
    @Column(name = BaseEntityConstants.COLUMN_CREATED_BY, nullable = false)
    private String createdBy;

    /**
     * The Modified by.
     */
    @Column(name = BaseEntityConstants.COLUMN_MODIFIED_BY, nullable = false)
    private String modifiedBy;

    /**
     * The Deleted.
     */
    @Column(name = BaseEntityConstants.COLUMN_DELETED, nullable = false)
    private boolean deleted;
}
