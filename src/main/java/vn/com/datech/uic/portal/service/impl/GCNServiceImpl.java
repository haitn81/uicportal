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
import vn.com.datech.uic.portal.entity.GCN;
import vn.com.datech.uic.portal.repository.GCNRepository;
import vn.com.datech.uic.portal.service.GCNService;

import java.util.List;

/**
 * The type Gcn service.
 */
@Service
public class GCNServiceImpl extends BaseService implements GCNService {
    /**
     * The Gcn repository.
     */
    private final GCNRepository gcnRepository;

    /**
     * Instantiates a new Gcn service.
     *
     * @param pGcnRepository the p gcn repository
     */
    public GCNServiceImpl(final GCNRepository pGcnRepository) {
        this.gcnRepository = pGcnRepository;
    }

    /**
     * Save gcn.
     *
     * @param gcn the gcn
     * @return the gcn
     */
    @Override
    public GCN save(final GCN gcn) {
        super.fillBaseInfo(gcn, UICConstants.SYSTEM, gcn.getId() != null);
        return gcnRepository.save(gcn);
    }

    /**
     * Create temp gcn.
     *
     * @return the gcn
     */
    @Override
    public GCN createTemp() {
        GCN gcn = new GCN();
        return this.save(gcn);
    }

    /**
     * Get gcn.
     *
     * @param id the id
     * @return the gcn
     */
    @Override
    public GCN get(final long id) {
        return gcnRepository.findById(id).orElse(null);
    }

    /**
     * Gets by contract number.
     *
     * @param contractNumber the contract number
     * @return the by contract number
     */
    @Override
    public List<GCN> getByContractNumber(final String contractNumber) {
        return gcnRepository.findAllByContractNumber(contractNumber);
    }
}
