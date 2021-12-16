/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.datech.uic.portal.entity.GCN;

import java.util.List;

/**
 * The interface Gcn repository.
 */
@Repository
public interface GCNRepository extends JpaRepository<GCN, Long> {
    /**
     * Find all by contract number list.
     *
     * @param contractNumber the contract number
     * @return the list
     */
    List<GCN> findAllByContractNumber(String contractNumber);
}
