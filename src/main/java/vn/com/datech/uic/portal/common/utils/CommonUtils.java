/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.javafx.iio.ImageStorage;
import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import vn.com.datech.uic.portal.common.constant.Constants;
import vn.com.datech.uic.portal.common.response.vo.ResponseMessage;
import vn.com.datech.uic.portal.entity.GCN;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The type Common utils.
 */
public final class CommonUtils {
    
    /**
    * Hide constructor.
    */
    private CommonUtils() {
        //Default.
    }

    /**
     * Gets email username.
     *
     * @param email the email
     * @return the email username
     */
    public static String getEmailUsername(final String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    /**
     * Build REST response message.
     *
     * @param success         success flag.
     * @param responseMessage ResponseMessage object.
     * @return ResponseEntity object.
     */
    public static ResponseEntity buildResponseMessage(
            final boolean success,
            final ResponseMessage responseMessage) {
        if (success) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
    }

    /**
     * To json with view string.
     *
     * @param obj  the obj
     * @param view the view
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public static String toJsonWithView(final Object obj, final Class view)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        return mapper.writerWithView(view)
                .writeValueAsString(obj);
    }

    /**
     * Build response with view object.
     *
     * @param obj  the obj
     * @param view the view
     * @return the object
     * @throws JsonProcessingException the json processing exception
     */
    public static Object buildResponseWithView(
            final Object obj, final Class view)
            throws JsonProcessingException {
        Gson gson = new Gson();
        return gson.fromJson(toJsonWithView(obj, view), Object.class);
    }

    /**
     * To list long list.
     *
     * @param objs the objs
     * @return the list
     */
    public static List<Long> toListLong(final Object[] objs) {
        List<Long> result = new ArrayList<>();
        if (!ObjectUtils.isEmpty(objs)) {
            for (Object obj : objs) {
                result.add(new Long(obj+""));
            }
        }
        return result;
    }

    /**
     * Create page pageable.
     *
     * @param pageIndex the page index
     * @param pageSize  the page size
     * @return the pageable
     */
    public static Pageable createPage(final int pageIndex, final int pageSize) {
        int pageNo = pageIndex - 1;
        if (pageNo < 0) {
            pageNo = 0;
        }
        return PageRequest.of(pageNo, pageSize);
    }

    /**
     * Gets date to string converter.
     *
     * @return the date to string converter
     */
    public static Converter<Date, String> dateToStringConverter() {
        return new Converter<Date, String>() {
            @Override
            public String convert(final MappingContext<Date, String> ctx) {
                return ctx.getSource() != null
                        ? DateUtils.dateToString(ctx.getSource(),
                        Constants.DEFAULT_DATE_FORMAT) : "";
            }
        };
    }

    /**
     * String to date converter converter.
     *
     * @return the converter
     */
    public static Converter<String, Date> stringToDateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(final MappingContext<String, Date> ctx) {
                return (ctx.getSource() != null && !ctx.getSource().isEmpty())
                        ? DateUtils.stringToDate(ctx.getSource(),
                        Constants.DEFAULT_DATE_FORMAT) : null;
            }
        };
    }

    /**
     * Create basic authen http header http headers.
     *
     * @param username the username
     * @param password the password
     * @return the http headers
     */
    public static HttpHeaders createBasicAuthenHttpHeader(
            final String username, final String password) {
        return new HttpHeaders() {{
            String auth =  username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }


    /**
     * Gets resource path.
     * s
     *
     * @param file the file
     * @return the resource path
     */
    public static String getResourcePath(final String file) {
        return Thread.currentThread().getContextClassLoader().getResource(file).getPath();
    }

    /**
     * Generate qr code buffered image.
     *
     * @param text   the text
     * @param width  the width
     * @param height the height
     * @return the buffered image
     * @throws WriterException the writer exception
     */
    public static BufferedImage generateQRCode(final String text, final int width, final int height)
            throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * Gets current time stamp.
     *
     * @return the current time stamp
     */
    public static long getCurrentTimeStamp() {
        return new Date().getTime();
    }


    /**
     * Convert pdf to img.
     *
     * @param source     the source
     * @param outputPath the output path
     * @throws IOException the io exception
     */
    @SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored"})
    public static void convertPdfToImg(final String source, final String outputPath) throws IOException {
        File sourceFile = new File(source);
        if(!sourceFile.exists()){ //Ko ton tai file pdf, then exit
            return;
        }
        String formatName = "png";
        File outputDir = new File(outputPath + "/" + sourceFile.getName().replace(".pdf", ""));
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
        PDDocument document = PDDocument.load(sourceFile);

        try {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int count = document.getNumberOfPages();
            String fileName = sourceFile.getName().replace(".pdf", "");
            for (int i = 0; i < count; i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 200, ImageType.RGB);
                String output = outputDir + "/" + fileName +"_" + (i + 1) + "." + formatName;
                ImageIO.write(image, formatName, new File(output));
            }
            document.close();
//            List<PDPage> listPage = document.getDocumentCatalog().getAllPages();
//            String fileName = sourceFile.getName().replace(".pdf", "");
//            int pageNo = 1;
//            for (PDPage page : listPage) {
//                BufferedImage image = page.convertToImage();
//                File outputFile = new File(outputDir + "/" + fileName +"_"+ pageNo +".png");
//                ImageIO.write(image, "png", outputFile);
//                pageNo++;
//            }
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }


    /**
     * Zip file.
     *
     * @param files      the files
     * @param outputFile the output file
     * @throws IOException the io exception
     */
    public static void zipFile(final List<String> files, final String outputFile) throws IOException {
        byte[] buffer = new byte[1024];
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (String file : files) {
                File srcFile = new File(file);
                if(!srcFile.exists()){
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(srcFile)) {
                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                }
            }
        }
    }
    /**
     * Zip file.
     *
     * @param files      the files
     * @param outputFile the output file
     * @throws IOException the io exception
     */
    public static void zipFileWithName(final List<GCN> files, final String outputFile) throws IOException {
        byte[] buffer = new byte[1024];
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (GCN gcn : files) {
                if(gcn.isDeleted()){
                    continue;
                }
                File srcFile = new File(gcn.getFile());
                if(!srcFile.exists()){
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(srcFile)) {
                    String type = "PRINT";
                    if(srcFile.getPath()!=null && srcFile.getPath().indexOf("gcn_detail")>0){
                        type ="VIEW";
                    }
                    zos.putNextEntry(new ZipEntry(type+ "#" +gcn.getPolicyNumber()+"#"+srcFile.getName()));
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                }
            }
        }
    }
}
