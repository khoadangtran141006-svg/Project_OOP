package doituong;

import java.util.Scanner;

public class CTDichVu {
    private String maCTDichVu;  // Mã chi tiết dịch vụ
    private String maHoaDon;    // ✅ Mã hóa đơn (liên kết với bảng hóa đơn)
    private String maDichVu;    // Mã dịch vụ
    private int soLuong;
    private double donGia;
    private double thanhTien;

    Scanner sc = doituong.Input.get();

    // ======= Constructor mặc định =======
    public CTDichVu() {
        maCTDichVu = "";
        maHoaDon = "";
        maDichVu = "";
        soLuong = 0;
        donGia = 0;
        thanhTien = 0;
    }

    // ======= Constructor đầy đủ (có mã hóa đơn) =======
    public CTDichVu(String maCTDichVu, String maHoaDon, String maDichVu,
                    int soLuong, double donGia, double thanhTien) {
        this.maCTDichVu = maCTDichVu;
        this.maHoaDon = maHoaDon;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    // ======= Constructor không có mã hóa đơn (cũ, vẫn giữ để tương thích) =======
    public CTDichVu(String maCTDichVu, String maDichVu, int soLuong, double donGia, double thanhTien) {
        this(maCTDichVu, "", maDichVu, soLuong, donGia, thanhTien);
    }

    // ======= Constructor sao chép =======
    public CTDichVu(CTDichVu ct) {
        this.maCTDichVu = ct.maCTDichVu;
        this.maHoaDon = ct.maHoaDon;
        this.maDichVu = ct.maDichVu;
        this.soLuong = ct.soLuong;
        this.donGia = ct.donGia;
        this.thanhTien = ct.thanhTien;
    }

    // ======= Getter/Setter =======
    public String getMaCTDichVu() { return maCTDichVu; }
    public String getMaHoaDon() { return maHoaDon; }
    public String getMaDichVu() { return maDichVu; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }
    public double getThanhTien() { return thanhTien; }

    public void setMaCTDichVu(String maCTDichVu) { this.maCTDichVu = maCTDichVu; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; } // ✅ thêm setter cho mã hóa đơn
    public void setMaDichVu(String maDichVu) { this.maDichVu = maDichVu; }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = this.soLuong * this.donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
        this.thanhTien = this.soLuong * this.donGia;
    }

    // ======= Nhập dữ liệu =======
    public void nhap() {
        System.out.print("Nhập mã chi tiết DV: ");
        maCTDichVu = sc.nextLine().trim();

        // ✅ Không nhập mã hóa đơn ở đây, vì nó được gán từ DSCTDichVu
        System.out.print("Nhập mã dịch vụ: ");
        maDichVu = sc.nextLine().trim();

        System.out.print("Nhập số lượng: ");
        soLuong = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Nhập đơn giá: ");
        donGia = Double.parseDouble(sc.nextLine().trim());

        thanhTien = soLuong * donGia;
    }

    // ======= Xuất dữ liệu =======
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-10d %-10.2f %-10.2f\n",
                maCTDichVu, maHoaDon, maDichVu, soLuong, donGia, thanhTien);
    }
}
