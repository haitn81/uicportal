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
import vn.com.datech.uic.portal.constant.GCNMappingEntityConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Gcn mapping.
 */
@Table(name = GCNMappingEntityConstants.TABLE_NAME)
@Entity
@Getter
@Setter
@ToString
public class GCNMapping extends BaseEntity {
    /**
     * The Gcn id.
     */
    private Long gcnId;
    /**
     * The Gcn detail id.
     */
    private Long gcnDetailId;
}
