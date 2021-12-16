/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.util.ResourceUtils;
import vn.com.datech.uic.portal.common.utils.CommonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * The type Jasper report utils.
 */
public final class JasperReportUtils {
    /**
     * The constant TYPE_PDF.
     */
    public static final String TYPE_PDF = "pdf";
    /**
     * Instantiates a new Jasper report utils.
     */
    private JasperReportUtils() {
        //Default.
    }

    /**
     * Export.
     *
     * @param filePath       the file path
     * @param params         the params
     * @param outputFileName the output file name
     * @param type           the type
     * @throws JRException           the jr exception
     * @throws FileNotFoundException the file not found exception
     */
    public static void export(final String filePath,
                              final Map<String, Object> params,
                              final String outputFileName,
                              final String type)
            throws JRException, FileNotFoundException {
        File file = new File(filePath);
        String fileName = file.getName();
        String[] fileArr = fileName.split("\\.");
        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
        JRSaver.saveObject(jasperReport, fileArr[0] + ".jasper");
        String fontPath = ResourceUtils.getURL("classpath:fonts/times.ttf").getPath();
        JRDesignStyle jrDesignStyle = new JRDesignStyle();
        jrDesignStyle.setPdfFontName(fontPath);
        jrDesignStyle.setPdfEncoding("Identity-H");
        jrDesignStyle.setPdfEmbedded(true);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        jasperPrint.setDefaultStyle(jrDesignStyle);
        if (TYPE_PDF.equalsIgnoreCase(type)) {
            exportPdf(jasperPrint, outputFileName);
        }
    }

    /**
     * Export pdf.
     *
     * @param jasperPrint    the jasper print
     * @param outputFileName the output file name
     */
    private static void exportPdf(final JasperPrint jasperPrint, final String outputFileName) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        //check exist folder date
        String folder = outputFileName.substring(0,outputFileName.lastIndexOf("/"));
        File directory = new File(folder);
        if(!directory.exists()){
            directory.mkdir();
        }
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFileName));
        SimplePdfReportConfiguration reportConfig  = new SimplePdfReportConfiguration();
        reportConfig.setSizePageToContent(true);
        reportConfig.setForceLineBreakPolicy(false);
        SimplePdfExporterConfiguration exportConfig  = new SimplePdfExporterConfiguration();
        exportConfig.setAllowedPermissionsHint("PRINTING");
        exporter.setConfiguration(reportConfig);
        exporter.setConfiguration(exportConfig);
        
        exporter.exportReport();
    }
}
