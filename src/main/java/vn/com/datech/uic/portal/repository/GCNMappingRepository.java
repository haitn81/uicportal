/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.datech.uic.portal.entity.GCNMapping;

/**
 * The interface Gcn mapping repository.
 */
@Repository
public interface GCNMappingRepository extends JpaRepository<GCNMapping, Long> {
}
