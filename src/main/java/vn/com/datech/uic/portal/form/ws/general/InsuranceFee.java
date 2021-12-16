/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.form.ws.general;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Insurance fee.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InsuranceFee {
    /**
     * The Fee.
     */
    private String fee;
    /**
     * The Vat.
     */
    private String vat;
    /**
     * The Totalfee
     * */
    private String totalfee;
    /**
     * The Due date.
     */
    private long dueDate;

    /**
     * Instantiates a new Insurance fee.
     *
     * @param fee     the fee
     * @param vat     the vat
     * @param dueDate the due date
     */
    public InsuranceFee(final String fee, final String vat,final String totalfee, final long dueDate) {
        this.fee = fee;
        this.vat = vat;
        this.totalfee = totalfee;
        this.dueDate = dueDate;
    }
}
