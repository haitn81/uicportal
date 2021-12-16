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

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InsuranceLimit {
    private String hmtnNguoi;
    private String hmtnTaisan;
    private String hmtnHanhKhach;

    public InsuranceLimit(final String hmtnNguoi, final String hmtnTaisan,final String hmtnHanhKhach) {
        this.hmtnNguoi = hmtnNguoi;
        this.hmtnTaisan = hmtnTaisan;
        this.hmtnHanhKhach = hmtnHanhKhach;
    }
}
