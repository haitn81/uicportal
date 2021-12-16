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
 * The type Insurance date.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InsuranceDate {
    /**
     * The From.
     */
    private long from;
    /**
     * The To.
     */
    private long to;

    /**
     * Instantiates a new Insurance date.
     *
     * @param from the from
     * @param to   the to
     */
    public InsuranceDate(final long from, final long to) {
        this.from = from;
        this.to = to;
    }
}
