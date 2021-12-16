/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.form.ws.general;

import lombok.*;

/**
 * The type Vehicle owner.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehicleOwner {

    private String ownerName;
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
     * Instantiates a new Vehicle owner.
     *
     * @param address the address
     * @param phone   the phone
     * @param email   the email
     */
    public VehicleOwner(final String ownerName, final String address, final String phone, final String email) {
        this.ownerName=ownerName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
