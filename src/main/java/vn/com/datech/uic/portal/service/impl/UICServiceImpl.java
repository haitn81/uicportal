/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.service.impl;

import com.google.zxing.WriterException;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import sun.util.calendar.Gregorian;
import vn.com.datech.uic.portal.common.jasper.JasperReportUtils;
import vn.com.datech.uic.portal.common.utils.CommonUtils;
import vn.com.datech.uic.portal.common.utils.DateUtils;
import vn.com.datech.uic.portal.constant.UICConstants;
import vn.com.datech.uic.portal.entity.GCN;
import vn.com.datech.uic.portal.entity.GCNMapping;
import vn.com.datech.uic.portal.form.ws.gcn.GCNCreateForm;
import vn.com.datech.uic.portal.form.ws.gcn.GCNDetailCreateForm;
import vn.com.datech.uic.portal.form.ws.gcn.GCNForm;
import vn.com.datech.uic.portal.service.GCNMappingService;
import vn.com.datech.uic.portal.service.GCNService;
import vn.com.datech.uic.portal.service.UICService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Uic service.
 */
@Service
public class UICServiceImpl implements UICService {
    /**
     * The Export folder.
     */
    @Value("${uic-portal.output-pdf-path}")
    private String exportFolder;

    /**
     * The Gcn detail link.
     */
    @Value("${uic-portal.gcn-detail-link}")
    private String gcnDetailLink;
    /**
     * The constant GCN_DETAIL_FOLDER.
     */
    private static final String GCN_DETAIL_FOLDER = "gcn_detail";
    /**
     * The Gcn folder.
     */
    private static final String GCN_FOLDER = "gcn";
    /**
     * The Gcn service.
     */
    private final GCNService gcnService;

    /**
     * The Gcn mapping service.
     */
    private final GCNMappingService gcnMappingService;

    /**
     * Instantiates a new Uic service.
     *
     * @param pGcnService        the p gcn service
     * @param pGCNMappingService the p gcn mapping service
     */
    public UICServiceImpl(final GCNService pGcnService,
                          final GCNMappingService pGCNMappingService) {
        this.gcnService = pGcnService;
        this.gcnMappingService = pGCNMappingService;
    }

    /**
     * Export mox detail.
     *
     * @param form the form
     * @return the gcn detail
     * @throws WriterException the writer exception
     * @throws IOException     the io exception
     * @throws JRException     the jr exception
     */
    @Override
    public GCN exportGCNDetail(final GCNDetailCreateForm form) throws WriterException, IOException, JRException {
        String fileTemplate = ResourceUtils.getURL("classpath:jasper/MOX_VIEW.jrxml").getPath();
        if (UICConstants.TYPE_MOT.equalsIgnoreCase(form.getType())) {
            fileTemplate = ResourceUtils.getURL("classpath:jasper/MOT_VIEW.jrxml").getPath();
        }
        GCN gcnDetail = null;
        if (form.getId() == null) {
            gcnDetail = gcnService.createTemp();
        } else {
            gcnDetail = gcnService.get(form.getId());
        }
        Map<String, Object> params = this.fillGCNForm(form,null);
        this.fillGeneralInfo(params,form.getType());
        String outputFile = exportFolder + "/" + GCN_DETAIL_FOLDER + "/" +this.getSysDateFolder()+ "/" + gcnDetail.getId() + ".pdf";
        params.put("QRCode", CommonUtils.generateQRCode(gcnDetailLink + gcnDetail.getId(), 200, 200));
        JasperReportUtils.export(fileTemplate, params, outputFile, JasperReportUtils.TYPE_PDF);
        CommonUtils.convertPdfToImg(outputFile, exportFolder + "/"  + "/img");
        gcnDetail.setImgFolder(exportFolder + "/img/" + gcnDetail.getId());
        gcnDetail.setFile(outputFile);
        this.fillInfo(gcnDetail, form);
        gcnService.save(gcnDetail);
        return gcnDetail;
    }

    /**
     * Export gcn gcn.
     *
     * @param form the form
     * @return the gcn
     * @throws IOException     the io exception
     * @throws WriterException the writer exception
     * @throws JRException     the jr exception
     */
    @Override
    public GCNMapping exportGCN(final GCNCreateForm form) throws IOException, WriterException, JRException {
        String fileTemplate = ResourceUtils.getURL("classpath:jasper/MOX_PRINT.jrxml").getPath();
        if (UICConstants.TYPE_MOT.equalsIgnoreCase(form.getType())) {
            if(form.getTndshk()){
                fileTemplate = ResourceUtils.getURL("classpath:jasper/MOT_PRINT.jrxml").getPath();
            }else {
                fileTemplate = ResourceUtils.getURL("classpath:jasper/MOT_PRINT_b.jrxml").getPath();
            }
        }
        Long detailId = form.getDetailId();
        if (detailId == null) {
            GCN gcnDetail = gcnService.createTemp();
            detailId = gcnDetail.getId();
        }
        Map<String, Object> params = this.fillGCNForm(form,detailId);
        this.fillGeneralInfo(params,form.getType());
        params.put("QRCode", CommonUtils.generateQRCode(gcnDetailLink + detailId, 100, 100));
        GCN gcn = gcnService.createTemp();
        String outputFile = exportFolder + "/" + GCN_FOLDER + "/"+ this.getSysDateFolder() + "/" + gcn.getId() + ".pdf";
        JasperReportUtils.export(fileTemplate, params, outputFile, JasperReportUtils.TYPE_PDF);
        gcn.setFile(outputFile);
        this.fillInfo(gcn, form);
        gcnService.save(gcn);
        GCNMapping gcnMapping = new GCNMapping();
        gcnMapping.setGcnDetailId(detailId);
        gcnMapping.setGcnId(gcn.getId());
        gcnMappingService.save(gcnMapping);
        return gcnMapping;
    }

    public void exportImgList(final long id) throws IOException{
//        String outputFile = exportFolder + "/" + GCN_DETAIL_FOLDER + "/" + id + ".pdf";
        GCN fileInfo = gcnService.get(id);
        String outputFile = fileInfo.getFile();
        CommonUtils.convertPdfToImg(outputFile, exportFolder + "/"  + "/img");
    }
    /**
     * Fill info.
     *
     * @param gcn  the gcn
     * @param form the form
     */
    private void fillInfo(final GCN gcn, final GCNForm form) {
        gcn.setContractNumber(form.getContractNumber());
        gcn.setPolicyNumber(form.getPolicyNumber());
    }

    /**
     * Gcn detail fill report map.
     *
     * @param form the form
     * @return the map
     * @throws WriterException the writer exception
     * @throws IOException     the io exception
     */
    private Map<String, Object> fillGCNForm(GCNForm form,Long detailId) throws WriterException, IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("SoBaoHiem","Số: " + this.trimText(form.getPolicyNumber()));
        if(form.getEndorNumber()!=null && form.getEndorNumber().trim().length()>0) {
            params.put("SoSdbs", "SĐBS số:"+form.getEndorNumber().trim());
        }
        /**
         * Thong tin chu xe
         * */
        boolean isView = (detailId == null);
        String tenchuxe = form.getVehicleOwner().getOwnerName();
        if(isView){
            params.put("HoTenChuXe","CHỦ XE: " + this.trimAndCut(form.getVehicleOwner().getOwnerName(),90));
            params.put("DiaChiChuXe", "Địa chỉ: " + this.trimAndCut(form.getVehicleOwner().getAddress(),100));
        }else{
            params.put("HoTenChuXe","CHỦ XE: " + this.trimText(form.getVehicleOwner().getOwnerName()));
            params.put("DiaChiChuXe", "Địa chỉ: " + this.trimText(form.getVehicleOwner().getAddress()));
        }
        params.put("DienThoaiChuXe", this.trimText(form.getVehicleOwner().getPhone()));
        params.put("EmailChuXe", this.trimText(form.getVehicleOwner().getEmail()));
        /**
         * Ben mua bao hiem
         * */
        if (UICConstants.TYPE_MOT.equalsIgnoreCase(form.getType())) {
            if(isView){
                params.put("HoTenBenMuaBaoHiem", "BÊN MUA BẢO HIỂM: " + this.trimAndCut(form.getBuyer().getCustomerName(),80));
                params.put("DiaChiBenMuaBaoHiem", "Địa chỉ: " + this.trimAndCut(form.getBuyer().getAddress(),100));
            }else{
                params.put("HoTenBenMuaBaoHiem", "BÊN MUA BẢO HIỂM: " + this.trimText(form.getBuyer().getCustomerName()));
                params.put("DiaChiBenMuaBaoHiem", "Địa chỉ: " + this.trimText(form.getBuyer().getAddress()));
            }

            params.put("DienThoaiBenMuaBaoHiem", this.trimText(form.getBuyer().getPhone()));
            params.put("EmailBenMuaBaoHiem", this.trimText(form.getBuyer().getEmail()));
        }
        /**
         * Thong tin xe
         * */
        params.put("SoBienKiemSoat", this.trimText(form.getVehicleInfo().getLicensePlateNo()));
        params.put("LoaiXe", this.trimText(form.getVehicleInfo().getVehicleType()));
        params.put("NhanHieuXe","Nhãn hiệu xe: " + this.trimText(form.getVehicleInfo().getTradeMark()));
        params.put("SoKhung", "Số khung:" + this.trimText(form.getVehicleInfo().getFrameNo()));
        params.put("SoMay", "Số máy:" + this.trimText(form.getVehicleInfo().getMachineNo()));
        if (UICConstants.TYPE_MOT.equalsIgnoreCase(form.getType())) {
            params.put("SoChoNgoi", "Số chỗ ngồi: " + form.getVehicleInfo().getSeatSlot());
            String capacity = form.getVehicleInfo().getCapacity();
            if("0".equals(capacity)){
                capacity = " - ";
            }else{
                capacity+= " tấn";
            }
            params.put("TrongTai", "Trọng tải: " + capacity);
            params.put("MucDichSuDung", form.getVehicleInfo().getPurposeOfUse());
            params.put("useTNDSHK", form.getTndshk());
        }else{ //MOX
            params.put("DungTichDongCo", this.trimText(form.getVehicleInfo().getEngineDisplacement()));
        }
        /**
         * Thoi han bh
         * */
        Calendar insDateFrom = Calendar.getInstance();
        insDateFrom.setTime(new Date(form.getInsuranceDate().getFrom()));
        String thbhTu="Từ " + this.formatSNumber(insDateFrom.get(Calendar.HOUR_OF_DAY) + "");
        thbhTu+=" giờ " + this.formatSNumber(insDateFrom.get(Calendar.MINUTE) + "");
        thbhTu+=" ngày " + this.formatSNumber(insDateFrom.get(Calendar.DATE) + "");
        thbhTu+=" tháng " + this.formatSNumber((insDateFrom.get(Calendar.MONTH) + 1) + "");
        thbhTu+=" năm " + insDateFrom.get(Calendar.YEAR) + "";
        params.put("THBHTu",thbhTu);
        Calendar insDateTo = Calendar.getInstance();
        insDateTo.setTime(new Date(form.getInsuranceDate().getTo()));
        String thbhDen="Đến " + this.formatSNumber(insDateTo.get(Calendar.HOUR_OF_DAY) + "");
        thbhDen+=" giờ " + this.formatSNumber(insDateTo.get(Calendar.MINUTE) + "");
        thbhDen+=" ngày " + this.formatSNumber(insDateTo.get(Calendar.DATE) + "");
        thbhDen+=" tháng " + this.formatSNumber((insDateTo.get(Calendar.MONTH) + 1) + "");
        thbhDen+=" năm " + insDateTo.get(Calendar.YEAR) + "";
        params.put("THBHDen",thbhDen);

        /**
         * Phi BH
         * */
        params.put("PhiBaoHiem", form.getInsuranceFee().getFee() + " VNĐ");
        params.put("ThueGTGT",  form.getInsuranceFee().getVat() + " VNĐ");
        params.put("TongPhiBaoHiem",  form.getInsuranceFee().getTotalfee() + " VNĐ");
        params.put("ThoiHanThanhToanPhiBaoHiem", DateUtils.dateToString(
                new Date(form.getInsuranceFee().getDueDate()) , "dd/MM/yyyy"));
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.setTime(new Date(form.getReleaseDate()));
        String issueDate = this.formatSNumber(releaseDate.get(Calendar.DATE) + "");
        issueDate+= " tháng " + this.formatSNumber((releaseDate.get(Calendar.MONTH)+1) + "");
        issueDate+= " năm " + releaseDate.get(Calendar.YEAR);
        params.put("NgayCapBaoHiem",issueDate);
        /**
         * Hmtn
         * */
        params.put("HmtnNguoi",form.getInsuranceLimit().getHmtnNguoi() + " đồng/1 người/1 vụ tai nạn");
        params.put("HmtnTaisan",form.getInsuranceLimit().getHmtnTaisan()+ " đồng/ 1 vụ tai nạn");
        if (UICConstants.TYPE_MOT.equalsIgnoreCase(form.getType())) {
            params.put("HmtnHanhKhach", form.getInsuranceLimit().getHmtnHanhKhach() + " đồng/1 người/1 vụ tai nạn");
        }
        return params;
    }

    /**
     * Fill general info.
     *
     * @param params the params
     * @throws IOException the io exception
     */
    private void fillGeneralInfo(final Map<String, Object> params,String product_type) throws IOException {
        BufferedImage logo = ImageIO.read(ResourceUtils.getURL("classpath:images/logo.png"));
        BufferedImage productBarcode = ImageIO.read(ResourceUtils.getURL(
                "classpath:images/bar_mox.jpg"));
        if (UICConstants.TYPE_MOT.equalsIgnoreCase(product_type)) {
            productBarcode = ImageIO.read(ResourceUtils.getURL("classpath:images/bar_mot.jpg"));
        }
        params.put("Logo", logo);
        params.put("Barcode", productBarcode);
    }
    private String formatSNumber(String s){
        if(s==null || s.trim().length()==0){
            return s;
        }
        if(s.length()==1){
            s="0"+s;
        }
        return s;
    }
    private String trimText(String s){
        if(s==null || s.trim().equals("null") || s.trim().length()==0){
            return "";
        }
        return s.trim();
    }
    private String trimAndCut(String s,int length){
        if(s==null || s.trim().length()==0){
            return "";
        }
        if(s.length()>length){
            s=s.substring(0,length);
            s+="...";
        }
        return s.trim();
    }
    private String getSysDateFolder(){
        Calendar sysdate = Calendar.getInstance();
        sysdate.setTime(new Date());
        String retval = sysdate.get(Calendar.YEAR) + "";
        retval+=this.formatSNumber((sysdate.get(Calendar.MONTH) + 1) + "");
        retval+=this.formatSNumber(sysdate.get(Calendar.DATE) + "");
        return retval;
    }
}
