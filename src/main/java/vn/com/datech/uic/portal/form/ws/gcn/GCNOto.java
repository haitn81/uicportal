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

/**
 * The type Gcn oto.
 */
@Getter
@Setter
@ToString
public class GCNOto extends GCNForm {
    /**
     * The Seat slot.
     */
    private String seatSlot;
    /**
     * The Capacity.
     */
    private String capacity;
    /**
     * The Purpose of use.
     */
    private String purposeOfUse;
}
