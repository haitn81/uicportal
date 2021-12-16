/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.com.datech.uic.portal.common.base.controller.BaseController;
import vn.com.datech.uic.portal.service.GCNService;
import vn.com.datech.uic.portal.service.UICService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Gcn public controller.
 */
@Controller
@RequestMapping("/lookup")
@Log4j2
public class LookupController extends BaseController {
    /**
     * The Output path.
     */
    @Value("${uic-portal.output-pdf-path}")
    private String outputPath;

    private final UICService uicService;

    public LookupController(final UICService pUICService){
        this.uicService=pUICService;
    }
    /**
     * view as img pages
     *
     * @param id the id
     * @return the model and view
     */
    @SuppressWarnings("ConstantConditions")
    @RequestMapping(value = "/img/{id}", method = RequestMethod.GET)
    public ModelAndView viewImg(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("view-image");
        int page = 0;
        mav.addObject("fileId", id);
        File file = new File(outputPath  + "/img/" + id);
        if (file.exists() && file.list() != null) {
            page = file.list().length;
        }
        mav.addObject("page", page);
        return mav;
    }
    /**
     * view as img pages
     *
     * @param id the id
     * @return the model and view
     */
    @SuppressWarnings("ConstantConditions")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewList(@PathVariable("id") long id) throws IOException {
//        ModelAndView mav = new ModelAndView("view-slide");
        ModelAndView mav = new ModelAndView("viewslider");
        int page = 0;
        mav.addObject("fileId", id);
        File file = new File(outputPath  + "/img/" + id);
        if (!file.exists() || file.list() ==null) {//Ko co file, goi gen file
            uicService.exportImgList(id);
        }
        if (file.exists() && file.list() != null) {
            page = file.list().length;
        }
        mav.addObject("page", page);
        ArrayList listIdx = new ArrayList();
        int idx =0;
        while (idx<page){
            listIdx.add(++idx);
        }
        mav.addObject("listImg",listIdx);
        return mav;
    }
    /**
     * View full page pdf
     *
     * @param id the id
     * @return the model and view
     */
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public ModelAndView test(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("viewer");
        mav.addObject("fileId", id);
        return mav;
    }
    /**
     * View full page pdf
     *
     * @param id the id
     * @return the model and view
     */
    @RequestMapping(value = "/pdf/{id}", method = RequestMethod.GET)
    public ModelAndView pdfSimple(@PathVariable("id") long id) {
        ModelAndView mav = new ModelAndView("view-pdf");
        mav.addObject("fileId", id);
        return mav;
    }
}
