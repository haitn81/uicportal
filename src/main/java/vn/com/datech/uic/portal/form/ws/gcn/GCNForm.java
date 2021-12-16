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
 * The type Gcn form.
 */
@Getter
@Setter
@ToString
public class GCNForm extends WSForm {
    /**
     * The Contract number.
     */
    private String policyNumber;
    /**
     * The Contract number.
     */
    private String contractNumber;
    /**
     * Endorsement number
     * */
    private String endorNumber;
    /**
     * The Release date.
     */
    private Long releaseDate;
    /**
     * The Vehicle owner.
     */
    private VehicleOwner vehicleOwner;
    /**
     * The Buyer.
     */
    private Buyer buyer;
    /**
     * The Vehicle info.
     */
    private VehicleInfo vehicleInfo;
    /**
     * The Insurance date.
     */
    private InsuranceDate insuranceDate;
    /**
     * The Insurance fee.
     */
    private InsuranceFee insuranceFee;
    /**
     * The Type.
     */
    private String type;

    /**
     * The Tndshk.
     */
    private Boolean tndshk;

    private InsuranceLimit insuranceLimit;
}
