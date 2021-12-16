/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service;

import vn.com.datech.uic.portal.entity.GCNMapping;

/**
 * The interface Gcn mapping service.
 */
public interface GCNMappingService {
    /**
     * Save gcn mapping.
     *
     * @param gcnMapping the gcn mapping
     * @return the gcn mapping
     */
    GCNMapping save(GCNMapping gcnMapping);
}
