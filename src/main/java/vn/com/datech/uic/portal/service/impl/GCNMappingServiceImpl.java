/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service.impl;

import org.springframework.stereotype.Service;
import vn.com.datech.uic.portal.common.base.service.BaseService;
import vn.com.datech.uic.portal.constant.UICConstants;
import vn.com.datech.uic.portal.entity.GCNMapping;
import vn.com.datech.uic.portal.repository.GCNMappingRepository;
import vn.com.datech.uic.portal.service.GCNMappingService;

/**
 * The type Gcn mapping service.
 */
@Service
public class GCNMappingServiceImpl extends BaseService implements GCNMappingService {

    /**
     * The Gcn mapping repository.
     */
    private final GCNMappingRepository gcnMappingRepository;

    /**
     * Instantiates a new Gcn mapping service.
     *
     * @param pGcnMappingRepository the p gcn mapping repository
     */
    public GCNMappingServiceImpl(final GCNMappingRepository pGcnMappingRepository) {
        this.gcnMappingRepository = pGcnMappingRepository;
    }

    /**
     * Save gcn mapping.
     *
     * @param gcnMapping the gcn mapping
     * @return the gcn mapping
     */
    @Override
    public GCNMapping save(final GCNMapping gcnMapping) {
        super.fillBaseInfo(gcnMapping, UICConstants.SYSTEM, gcnMapping.getId() != null);
        return null;
    }
}
