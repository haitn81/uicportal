/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service;

import vn.com.datech.uic.portal.entity.GCN;

import java.util.List;

/**
 * The interface Gcn service.
 */
public interface GCNService {
    /**
     * Save gcn.
     *
     * @param gcn the gcn
     * @return the gcn
     */
    GCN save(GCN gcn);

    /**
     * Create temp gcn.
     *
     * @return the gcn
     */
    GCN createTemp();

    /**
     * Get gcn.
     *
     * @param id the id
     * @return the gcn
     */
    GCN get(long id);

    /**
     * Gets by contract number.
     *
     * @param contractNumber the contract number
     * @return the by contract number
     */
    List<GCN> getByContractNumber(String contractNumber);
}
