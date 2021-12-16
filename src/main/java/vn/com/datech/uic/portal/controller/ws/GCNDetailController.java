/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.controller.ws;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import sun.misc.GC;
import vn.com.datech.uic.portal.common.base.controller.BaseController;
import vn.com.datech.uic.portal.common.response.utils.ResponseUtils;
import vn.com.datech.uic.portal.common.response.vo.Message;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;
import vn.com.datech.uic.portal.entity.GCN;
import vn.com.datech.uic.portal.form.ws.gcn.GCNDetailCreateForm;
import vn.com.datech.uic.portal.service.UICService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Gcn detail controller.
 */
@RestController
@RequestMapping("/ws/gcn-detail")
@Log4j2
public class GCNDetailController extends BaseController {
    private final UICService uicService;

    /**
     * Instantiates a new Gcn detail controller.
     *
     * @param pUicService the p uic service
     */
    public GCNDetailController(final UICService pUicService) {
        this.uicService = pUicService;
    }

    /**
     * Create gcn detail response entity.
     *
     * @param form the form
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity createGCNDetail(final @Valid @RequestBody GCNDetailCreateForm form) {
        if (!super.checkWsToken(form.getToken())) {
            return super.forbidden();
        }
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(false);
        List<Message> messages = new ArrayList<>();
        try {
            GCN gcnDetail = uicService.exportGCNDetail(form);
            responseMessage.setData(gcnDetail.getId());
            responseMessage.setSuccess(true);
        } catch (Exception e) {
            messages.add(super.getErrorMessage());
            responseMessage.setSuccess(false);
            log.error(e);
        }
        responseMessage.setMessage(messages);
        return ResponseUtils.buildResponseMessage(responseMessage.isSuccess(), responseMessage);
    }
}
