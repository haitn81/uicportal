/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.controller.ws;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import vn.com.datech.uic.portal.common.base.controller.BaseController;
import vn.com.datech.uic.portal.common.response.utils.ResponseUtils;
import vn.com.datech.uic.portal.common.response.vo.Message;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;
import vn.com.datech.uic.portal.entity.GCN;
import vn.com.datech.uic.portal.form.ws.gcn.BaseForm;
import vn.com.datech.uic.portal.service.GCNService;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ws/file")
@Log4j2
public class FileController extends BaseController {
    /**
     * The Uic service.
     */
    private final GCNService gcnService;
    @Value("${uic-portal.output-pdf-path}")
    private String outputPath;

    public FileController(GCNService gcnService) {
        this.gcnService = gcnService;
    }
    /**
     * View gcn.
     *
     * @param form       the form
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity delete(final @Valid @RequestBody BaseForm form) {
        if (!super.checkWsToken(form.getToken())) {
            return super.forbidden();
        }
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setSuccess(false);
        List<Message> messages = new ArrayList<>();
        try {
            GCN gcn = gcnService.get(form.getId());
            if (gcn != null && !gcn.isDeleted()) {
                //Mark Delete file
                File file = ResourceUtils.getFile(gcn.getFile());
                File desFile =new File(file.getParent()+"/"+"DELETED_"+file.getName());
                file.renameTo(desFile);
                //remove folder image
                FileUtils.deleteDirectory(new File(outputPath  + "/img/" + form.getId()));
                //Update status
                gcn.setDeleted(true);
                gcn.setFile(desFile.getPath());
                gcnService.save(gcn);
            }
            responseMessage.setData("deleted");
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
