/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.com.datech.uic.portal.common.base.controller.BaseController;
import vn.com.datech.uic.portal.common.utils.CommonUtils;
import vn.com.datech.uic.portal.entity.GCN;
import vn.com.datech.uic.portal.service.GCNService;
import vn.com.datech.uic.portal.service.UICService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Download controller.
 */
@Controller
@RequestMapping("/download")
@Log4j2
public class DownloadController extends BaseController {
    /**
     * The Gcn service.
     */
    private final GCNService gcnService;


    /**
     * The Output path.
     */
    @Value("${uic-portal.output-pdf-path}")
    private String outputPath;

    /**
     * Instantiates a new Download controller.
     *
     * @param pGcnService the p gcn service
     */
    public DownloadController(final GCNService pGcnService) {
        this.gcnService = pGcnService;
    }

    /**
     * View gcn.
     *
     * @param response the response
     * @param id       the id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void viewGCN(final HttpServletResponse response, final @PathVariable("id") long id) {
        try {
            GCN gcn = gcnService.get(id);
            if (gcn != null) {
                File file = ResourceUtils.getFile(gcn.getFile());
                viewFile(response, file);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Download by contract number.
     *
     * @param response the response
     * @param id       the id
     */
    @RequestMapping(value = "/contract/{id:.+}")
    public void downloadByContractNumber(final HttpServletResponse response, final @PathVariable("id") String id) {
        try {
            List<GCN> listGCN = gcnService.getByContractNumber(id);
            if (!ObjectUtils.isEmpty(listGCN)) {
                //String fileTemp = outputPath + "/tmp/" + UUID.randomUUID().toString() + ".zip";
                Date date = new Date();
                String fileTemp = outputPath + "/tmp/" + id + "_" + date.getTime() +".zip";
                CommonUtils.zipFileWithName(listGCN, fileTemp);
                File file = ResourceUtils.getFile(fileTemp);
                viewFile(response, file);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Get image byte [ ].
     *
     * @param id   the id
     * @param page the page
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    @ResponseBody
    @RequestMapping(value = "/image/{id}/{page}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(final @PathVariable("id") long id,
                           final @PathVariable("page") int page) throws IOException {
        String filePath = outputPath + "/img/" + id + "/" + id + "_" + page + ".png";
        File file = new File(filePath);
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            return IOUtils.toByteArray(inputStream);
        }
        return null;
    }
    /**
     * Get image byte [ ].
     *
     * @param id   the id
     * @param filename the page
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    @ResponseBody
    @RequestMapping(value = "/img/{id}/{filename}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getDirectImg(final @PathVariable("id") long id,
                           final @PathVariable("filename") String filename) throws IOException {
        String filePath = outputPath + "/img/" + id + "/" + filename+".png";
        File file = new File(filePath);
        if (file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            return IOUtils.toByteArray(inputStream);
        }
        return null;
    }
    /**
     * View file.
     *
     * @param response the response
     * @param file     the file
     * @throws IOException the io exception
     */
    private void viewFile(final HttpServletResponse response, final File file) throws IOException {
        byte[] data = FileUtils.readFileToByteArray(file);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
        response.setContentLength(data.length);
        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
