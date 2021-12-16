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
public class Buyer {

    private String customerName;
    /**
     * The Address.
     */
    private String address;
    /**
     * The Phone.
     */
    private String phone;
    /**
     * The Email.
     */
    private String email;

    /**
     * Instantiates a new Buyer.
     *
     * @param address the address
     * @param phone   the phone
     * @param email   the email
     */
    public Buyer(final String customerName,final String address, final String phone, final String email) {
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
