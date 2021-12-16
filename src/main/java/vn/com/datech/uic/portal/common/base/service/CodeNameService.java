/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.service;

import org.springframework.stereotype.Component;
import vn.com.datech.uic.portal.common.base.entity.CodeNameEntity;

@Component
public class CodeNameService extends BaseService {
    public void fillBaseInfo(final CodeNameEntity entity,
                             final String account, boolean isUpdate) {
        super.fillBaseInfo(entity, account, isUpdate);
        entity.setLowerCode(entity.getCode().toLowerCase());
        entity.setLowerName(entity.getName().toLowerCase());
    }
}
