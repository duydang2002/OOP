// Bui Trong Dung 20207594
package model;

public class DonHang {
    private String ten;
    private String diaChi;
    private String ThoiGianThem;
    private Bang bang;

    public DonHang(String ten, String diaChi, Bang bang, String thoiGianThem) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.bang = bang;
        this.ThoiGianThem=thoiGianThem;
    }

    public DonHang() {}
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Bang getBang() {
        return bang;
    }
    public void setBang(Bang bang) {
        this.bang = bang;
    }
    public String getThoiGianThem() {
        return ThoiGianThem;
    }

    public void setThoiGianThem(String thoiGianThem) {
        ThoiGianThem = thoiGianThem;
    }

}
