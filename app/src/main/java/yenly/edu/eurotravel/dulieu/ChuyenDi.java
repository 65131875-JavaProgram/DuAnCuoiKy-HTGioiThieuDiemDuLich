package yenly.edu.eurotravel.dulieu;

public class ChuyenDi {
    private String tenDiaDiem;
    private String thoiGian;
    private String loaiHinh;
    private int hinhAnh;

    public ChuyenDi(String tenDiaDiem, String thoiGian, String loaiHinh, int hinhAnh) {
        this.tenDiaDiem = tenDiaDiem;
        this.thoiGian = thoiGian;
        this.loaiHinh = loaiHinh;
        this.hinhAnh = hinhAnh;
    }

    public String getTenDiaDiem() { return tenDiaDiem; }
    public String getThoiGian() { return thoiGian; }
    public String getLoaiHinh() { return loaiHinh; }
    public int getHinhAnh() { return hinhAnh; }
}