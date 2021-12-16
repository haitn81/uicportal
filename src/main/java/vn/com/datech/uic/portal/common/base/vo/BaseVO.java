/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.com.datech.uic.portal.common.base.ApplicationEvent;

import java.util.Date;

/**
 * The type Base vo.
 */
@Getter
@Setter
@ToString
public class BaseVO {
    /**
     * The Id.
     */
    @JsonView(ApplicationEvent.OnNormalView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * The Created time.
     * */
    @JsonView(ApplicationEvent.OnAdminView.class)
    private Date createdTime;

    /**
     * The Modified time.
     */
    @JsonView(ApplicationEvent.OnAdminView.class)
    private Date modifiedTime;

    /**
     * The Created by.
     */
    @JsonView(ApplicationEvent.OnAdminView.class)
    private String createdBy;

    /**
     * The Modified by.
     */
    @JsonView(ApplicationEvent.OnAdminView.class)
    private String modifiedBy;
}
