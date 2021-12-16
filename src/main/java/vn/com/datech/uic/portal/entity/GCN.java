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
import vn.com.datech.uic.portal.constant.GCNEntityConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Gcn.
 */
@Table(name = GCNEntityConstants.TABLE_NAME)
@Entity
@Getter
@Setter
@ToString
public class GCN extends BaseEntity {
    /**
     * The File.
     */
    @Column(name = GCNEntityConstants.COL_FILE, length = 3000)
    private String file;
    /**
     * The Img folder.
     */
    private String imgFolder;
    /**
     * The Contract number.
     */
    private String contractNumber;
    /**
     * The Policy number.
     */
    private String policyNumber;
}
