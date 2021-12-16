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
 * The type Vehicle info.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehicleInfo {
    /**
     * The License plate no.
     */
    private String licensePlateNo;
    /**
     * The Vehicle type.
     */
    private String vehicleType;

    private String tradeMark;
    /**
     * The Frame no.
     */
    private String frameNo;
    /**
     * The Machine no.
     */
    private String machineNo;
    /**
     * The Engine displacement.
     */
    private String engineDisplacement;
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
    /**
     * Instantiates a new Vehicle info.
     *
     * @param licensePlateNo     the license plate no
     * @param vehicleType        the vehicle type
     * @param frameNo            the frame no
     * @param machineNo          the machine no
     * @param engineDisplacement the engine displacement
     * @param seatSlot           the seat slot
     * @param capacity           the capacity
     * @param purposeOfUse       the purpose of use
     */
    public VehicleInfo(final String licensePlateNo,
                       final String vehicleType, final String tradeMark,
                       final String frameNo, final String machineNo,
                       final String engineDisplacement, final String seatSlot,
                       final String capacity, final String purposeOfUse) {
        this.licensePlateNo = licensePlateNo;
        this.vehicleType = vehicleType;
        this.frameNo = frameNo;
        this.machineNo = machineNo;
        this.engineDisplacement = engineDisplacement;
        this.seatSlot = seatSlot;
        this.capacity = capacity;
        this.purposeOfUse = purposeOfUse;
    }
}
