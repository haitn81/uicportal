/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service;

import com.google.zxing.WriterException;
import net.sf.jasperreports.engine.JRException;
import vn.com.datech.uic.portal.entity.GCN;
import vn.com.datech.uic.portal.entity.GCNMapping;
import vn.com.datech.uic.portal.form.ws.gcn.GCNCreateForm;
import vn.com.datech.uic.portal.form.ws.gcn.GCNDetailCreateForm;

import java.io.IOException;

/**
 * The interface Uic service.
 */
public interface UICService {
    /**
     * Export mox detail.
     *
     * @param form the form
     * @return the gcn detail
     * @throws WriterException the writer exception
     * @throws IOException     the io exception
     * @throws JRException     the jr exception
     */
    GCN exportGCNDetail(GCNDetailCreateForm form) throws WriterException, IOException, JRException;

    /**
     * Export gcn gcn mapping.
     *
     * @param form the form
     * @return the gcn mapping
     */
    GCNMapping exportGCN(GCNCreateForm form) throws IOException, WriterException, JRException;

    void exportImgList(final long id) throws IOException;
}
