/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.base.vo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.com.datech.uic.portal.common.base.ApplicationEvent;

@Getter
@Setter
@ToString
public class CodeNameVO extends BaseVO {
    /**
     * The Code.
     */
    @JsonView(ApplicationEvent.OnNormalView.class)
    private String code;
    /**
     * The Lower code.
     */
    @JsonView(ApplicationEvent.OnNormalView.class)
    private String lowerCode;
    /**
     * The Name.
     */
    @JsonView(ApplicationEvent.OnNormalView.class)
    private String name;
    /**
     * The Lower name.
     */
    @JsonView(ApplicationEvent.OnNormalView.class)
    private String lowerName;
}
