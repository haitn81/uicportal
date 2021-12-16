/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.form.ws.gcn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.com.datech.uic.portal.form.ws.general.*;

/**
 * The type Gcn create form.
 */
@Getter
@Setter
@ToString
public class GCNCreateForm extends GCNForm {
    /**
     * The Detail id.
     */
    private Long detailId;
}
