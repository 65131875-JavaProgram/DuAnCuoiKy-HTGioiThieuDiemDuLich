package yenly.edu.eurotravel.dulieu;
import java.io.Serializable;

public class ChuyenDi implements Serializable {
    private String tenDiaDiem, thoiGian, loaiHinh, moTa;
    private int hinhAnh, giaTien;
    private float diemDanhGia;
    private boolean isFavorite;

    public ChuyenDi(String tenDiaDiem, String thoiGian, String loaiHinh, int hinhAnh, int giaTien, float diemDanhGia, String moTa) {
        this.tenDiaDiem = tenDiaDiem;
        this.thoiGian = thoiGian;
        this.loaiHinh = loaiHinh;
        this.hinhAnh = hinhAnh;
        this.giaTien = giaTien;
        this.diemDanhGia = diemDanhGia;
        this.moTa = moTa;
        this.isFavorite = false;
    }

    public String getTenDiaDiem() { return tenDiaDiem; }
    public String getThoiGian() { return thoiGian; }
    public String getLoaiHinh() { return loaiHinh; }
    public int getHinhAnh() { return hinhAnh; }
    public int getGiaTien() { return giaTien; }
    public float getDiemDanhGia() { return diemDanhGia; }
    public String getMoTa() { return moTa; }
    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setDiemDanhGia(float diemDanhGia) { this.diemDanhGia = diemDanhGia; }
}