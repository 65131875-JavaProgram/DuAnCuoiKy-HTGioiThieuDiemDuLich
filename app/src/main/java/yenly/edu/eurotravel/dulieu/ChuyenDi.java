package yenly.edu.eurotravel.dulieu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDi implements Serializable {
    private String tenDiaDiem;
    private String hinhAnhDaiDien;
    private String giaTien;
    private float diemDanhGia;
    private String soNgayDi;
    private String thoiTiet;
    private String ngonNgu;
    private String moTaTongQuan;
    private String thongTinSanBay;
    private String khachSanNoiBat;

    private List<PhotoItem> danhSachAnhTabPhotos;

    public ChuyenDi() {
        danhSachAnhTabPhotos = new ArrayList<>();
    }
    public static class PhotoItem implements Serializable {
        private String hinhAnhUrl;
        private String chuThich;

        public PhotoItem() {}

        public PhotoItem(String hinhAnhUrl, String chuThich) {
            this.hinhAnhUrl = hinhAnhUrl;
            this.chuThich = chuThich;
        }

        public String getHinhAnhUrl() { return hinhAnhUrl; }
        public void setHinhAnhUrl(String hinhAnhUrl) { this.hinhAnhUrl = hinhAnhUrl; }
        public String getChuThich() { return chuThich; }
        public void setChuThich(String chuThich) { this.chuThich = chuThich; }
    }

    public String getTenDiaDiem() { return tenDiaDiem; }
    public void setTenDiaDiem(String tenDiaDiem) { this.tenDiaDiem = tenDiaDiem; }
    public String getHinhAnhDaiDien() { return hinhAnhDaiDien; }
    public void setHinhAnhDaiDien(String hinhAnhDaiDien) { this.hinhAnhDaiDien = hinhAnhDaiDien; }
    public String getGiaTien() { return giaTien; }
    public void setGiaTien(String giaTien) { this.giaTien = giaTien; }
    public float getDiemDanhGia() { return diemDanhGia; }
    public void setDiemDanhGia(float diemDanhGia) { this.diemDanhGia = diemDanhGia; }
    public String getSoNgayDi() { return soNgayDi; }
    public void setSoNgayDi(String soNgayDi) { this.soNgayDi = soNgayDi; }
    public String getThoiTiet() { return thoiTiet; }
    public void setThoiTiet(String thoiTiet) { this.thoiTiet = thoiTiet; }
    public String getNgonNgu() { return ngonNgu; }
    public void setNgonNgu(String ngonNgu) { this.ngonNgu = ngonNgu; }
    public String getMoTaTongQuan() { return moTaTongQuan; }
    public void setMoTaTongQuan(String moTaTongQuan) { this.moTaTongQuan = moTaTongQuan; }
    public String getThongTinSanBay() { return thongTinSanBay; }
    public void setThongTinSanBay(String thongTinSanBay) { this.thongTinSanBay = thongTinSanBay; }
    public String getKhachSanNoiBat() { return khachSanNoiBat; }
    public void setKhachSanNoiBat(String khachSanNoiBat) { this.khachSanNoiBat = khachSanNoiBat; }
    public List<PhotoItem> getDanhSachAnhTabPhotos() { return danhSachAnhTabPhotos; }
    public void setDanhSachAnhTabPhotos(List<PhotoItem> danhSachAnhTabPhotos) { this.danhSachAnhTabPhotos = danhSachAnhTabPhotos; }
}