//package vn.com.datech.uic.portal;
//
//import com.google.zxing.WriterException;
//import net.sf.jasperreports.engine.JRException;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.ResourceUtils;
//import vn.com.datech.uic.portal.common.jasper.JasperReportUtils;
//import vn.com.datech.uic.portal.common.utils.CommonUtils;
//import vn.com.datech.uic.portal.common.utils.DateUtils;
//import vn.com.datech.uic.portal.form.ws.gcn.GCNDetailCreateForm;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//class UicPortalApplicationTests {
//    @Test
//    void contextLoads() throws JRException, IOException, WriterException {
//        String fileTemplate = ResourceUtils.getURL("classpath:jasper/GCN_DETAIL.jrxml").getPath();
//        String outputFile = "F:\\test\\report.pdf";
//        Map<String, Object> params = new HashMap<>();
//        GCNDetailCreateForm GCNDetailCreateForm = new GCNDetailCreateForm();
//        GCNDetailCreateForm.setId(CommonUtils.getCurrentTimeStamp());
//        GCNDetailCreateForm.setContractNumber("BHLV.2021.03.13.001");
//        GCNDetailCreateForm.setReleaseDate(new Date().getTime());
//        GCNDetailCreateForm.setVehicleOwner(
//                new GCNDetailCreateForm.VehicleOwner("166, Phố Huyện, Quốc Oai, Hà Nội",
//                        "0981511919", "nth0712@gmail.com"));
//        GCNDetailCreateForm.setBuyer(
//                new GCNDetailCreateForm.Buyer("166, Phố Huyện, Quốc Oai, Hà Nội",
//                        "0981511919", "nth0712@gmail.com"));
//        GCNDetailCreateForm.setVehicleInfo(new GCNDetailCreateForm.VehicleInfo(
//                "30G - 93091", "Xe ô tô", "ABC11143",
//                "XYZ114", ""));
//        Calendar insuranceExpiredDate = Calendar.getInstance();
//        insuranceExpiredDate.add(Calendar.YEAR, 1);
//        GCNDetailCreateForm.setInsuranceDate(
//                new GCNDetailCreateForm.InsuranceDate(new Date().getTime(), insuranceExpiredDate.getTime().getTime()));
//        GCNDetailCreateForm.setInsuranceFee(new GCNDetailCreateForm.InsuranceFee(7000000, 700000, new Date().getTime()));
//        params.put("SoBaoHiem", GCNDetailCreateForm.getContractNumber());
//        params.put("DiaChiChuXe", GCNDetailCreateForm.getVehicleOwner().getAddress());
//        params.put("DienThoaiChuXe", GCNDetailCreateForm.getVehicleOwner().getPhone());
//        params.put("EmailChuXe", GCNDetailCreateForm.getVehicleOwner().getEmail());
//        params.put("DiaChiBenMuaBaoHiem", GCNDetailCreateForm.getBuyer().getAddress());
//        params.put("DienThoaiBenMuaBaoHiem", GCNDetailCreateForm.getBuyer().getPhone());
//        params.put("EmailBenMuaBaoHiem", GCNDetailCreateForm.getBuyer().getEmail());
//        params.put("SoBienKiemSoat", GCNDetailCreateForm.getVehicleInfo().getLicensePlateNo());
//        params.put("LoaiXe", GCNDetailCreateForm.getVehicleInfo().getVehicleType());
//        params.put("SoKhung", GCNDetailCreateForm.getVehicleInfo().getFrameNo());
//        params.put("SoMay", GCNDetailCreateForm.getVehicleInfo().getMachineNo());
//        params.put("DungTichDongCo", GCNDetailCreateForm.getVehicleInfo().getEngineDisplacement());
//        Calendar insDateFrom = Calendar.getInstance();
//        insDateFrom.setTime(new Date(GCNDetailCreateForm.getInsuranceDate().getFrom()));
//        params.put("THBHTuGio", insDateFrom.get(Calendar.HOUR_OF_DAY) + "");
//        params.put("THBHTuNgay", insDateFrom.get(Calendar.DATE) + "");
//        params.put("THBHTuPhut", insDateFrom.get(Calendar.MINUTE) + "");
//        params.put("THBHTuThang", insDateFrom.get(Calendar.MONTH) + "");
//        params.put("THBHTuNam", insDateFrom.get(Calendar.YEAR) + "");
//        Calendar insDateTo = Calendar.getInstance();
//        insDateTo.setTime(new Date(GCNDetailCreateForm.getInsuranceDate().getTo()));
//        params.put("THBHDenGio", insDateTo.get(Calendar.HOUR_OF_DAY) + "");
//        params.put("THBHDenPhut", insDateTo.get(Calendar.MINUTE) + "");
//        params.put("THBHDenNgay", insDateTo.get(Calendar.DATE) + "");
//        params.put("THBHDenThang", insDateTo.get(Calendar.MONTH) + "");
//        params.put("THBHDenNam", insDateTo.get(Calendar.YEAR) + "");
//        params.put("PhiBaoHiem", "" + GCNDetailCreateForm.getInsuranceFee().getFee());
//        params.put("ThueGTGT", "" + GCNDetailCreateForm.getInsuranceFee().getVat());
//        params.put("TongPhiBaoHiem", ""
//                + ( GCNDetailCreateForm.getInsuranceFee().getVat() + GCNDetailCreateForm.getInsuranceFee().getFee() ));
//        params.put("ThoiHanThanhToanPhiBaoHiem", DateUtils.dateToString(new Date(GCNDetailCreateForm.getInsuranceFee().getDueDate()) , "dd/MM/yyyy"));
//        Calendar releaseDate = Calendar.getInstance();
//        releaseDate.setTime(new Date(GCNDetailCreateForm.getReleaseDate()));
//        params.put("NgayCapBaoHiem", releaseDate.get(Calendar.DATE) + "");
//        params.put("ThangCapBaoHiem", releaseDate.get(Calendar.MONTH) + "");
//        params.put("NamCapBaoHiem", releaseDate.get(Calendar.YEAR) + "");
//        params.put("QRCode", CommonUtils.generateQRCode("Test", 200, 200));
//        //File file = new File(ResourceUtils.getURL("classpath:images/logo.png").getPath());
//        BufferedImage logo = ImageIO.read(ResourceUtils.getURL("classpath:images/logo.png"));
//        BufferedImage productBarcode = ImageIO.read(ResourceUtils.getURL("classpath:images/product_barcode.png"));
//        params.put("Logo", logo);
//        params.put("Barcode", productBarcode);
//        JasperReportUtils.export(fileTemplate, params, outputFile, JasperReportUtils.TYPE_PDF);
//    }
//
//}
