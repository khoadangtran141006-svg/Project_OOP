package doituong;

import danhsach.DSDichVu;
import danhsach.DSPhong;
import java.util.Scanner;

public class CTHoaDon {
    private Scanner sc = doituong.Input.get();

    private String maCTHoaDon;  // Mã chi tiết hóa đơn
    private String maHoaDon;    // Mã hóa đơn

    // Phòng
    private String maPhong;
    private int soNgayThue;
    private double thanhTienPhong;

    // Dịch vụ
    private String maDichVu;
    private int soLuong;
    private double thanhTienDV;

    // Tổng tiền
    private double thanhTien;

    // ===== Constructor mặc định =====
    public CTHoaDon() {
        maCTHoaDon = "";
        maHoaDon = "";
        maPhong = "";
        soNgayThue = 0;
        thanhTienPhong = 0.0;
        maDichVu = "";
        soLuong = 0;
        thanhTienDV = 0.0;
        thanhTien = 0.0;
    }

    // ===== Constructor đầy đủ =====
    public CTHoaDon(String maCTHoaDon, String maHoaDon,
                    String maPhong, int soNgayThue,
                    String maDichVu, int soLuong) {
        this.maCTHoaDon = maCTHoaDon;
        this.maHoaDon = maHoaDon;
        this.maPhong = maPhong;
        this.soNgayThue = soNgayThue;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.thanhTienPhong = 0.0;
        this.thanhTienDV = 0.0;
        this.thanhTien = 0.0;
    }

    // ===== Copy constructor =====
    public CTHoaDon(CTHoaDon cthd) {
        this.maCTHoaDon = cthd.maCTHoaDon;
        this.maHoaDon = cthd.maHoaDon;
        this.maPhong = cthd.maPhong;
        this.soNgayThue = cthd.soNgayThue;
        this.thanhTienPhong = cthd.thanhTienPhong;
        this.maDichVu = cthd.maDichVu;
        this.soLuong = cthd.soLuong;
        this.thanhTienDV = cthd.thanhTienDV;
        this.thanhTien = cthd.thanhTien;
    }

    // ===== Getter/Setter =====
    public String getMaCTHoaDon() { return maCTHoaDon; }
    public void setMaCTHoaDon(String maCTHoaDon) { this.maCTHoaDon = maCTHoaDon; }

    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }

    public int getSoNgayThue() { return soNgayThue; }
    public void setSoNgayThue(int soNgayThue) { this.soNgayThue = soNgayThue; }

    public String getMaDichVu() { return maDichVu; }
    public void setMaDichVu(String maDichVu) { this.maDichVu = maDichVu; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getThanhTienPhong() { return thanhTienPhong; }
    public double getThanhTienDV() { return thanhTienDV; }
    public double getThanhTien() { return thanhTien; }

    // ===== Nhập thông tin =====
    public void nhap() {
        System.out.println("\n╔════════ NHẬP CHI TIẾT HÓA ĐƠN ════════╗");
        System.out.print("Mã chi tiết hóa đơn: "); maCTHoaDon = sc.nextLine().trim().toUpperCase();
        System.out.print("Mã hóa đơn: "); maHoaDon = sc.nextLine().trim().toUpperCase();
        System.out.print("Mã phòng: "); maPhong = sc.nextLine().trim().toUpperCase();
        System.out.print("Số ngày thuê: "); soNgayThue = sc.nextInt(); sc.nextLine();
        System.out.print("Mã dịch vụ: "); maDichVu = sc.nextLine().trim().toUpperCase();
        System.out.print("Số lượng dịch vụ: "); soLuong = sc.nextInt(); sc.nextLine();
    }

    // ===== Tính thành tiền =====
public void tinhThanhTien(DSPhong dsPhong, DSDichVu dsdv) {
    // Tiền phòng
    thanhTienPhong = 0;
    for (int i = 0; i < dsPhong.getn(); i++) {
        if (maPhong.equalsIgnoreCase(dsPhong.getds()[i].getmaphong())) {
            thanhTienPhong = soNgayThue * dsPhong.getds()[i].getdongia();
            break;
        }
    }

    // Tiền dịch vụ
    thanhTienDV = 0;
    if (dsdv.timDichVu(maDichVu) != null) {
        thanhTienDV = soLuong * dsdv.timDichVu(maDichVu).getDonGia();
    }

    // Tổng
    thanhTien = thanhTienPhong + thanhTienDV;
}


    // ===== Xuất thông tin =====
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-10d %-10s %-10d %-10.2f %-10.2f %-10.2f\n",
                maCTHoaDon, maHoaDon, maPhong, soNgayThue,
                maDichVu, soLuong, thanhTienPhong, thanhTienDV, thanhTien);
    }
}
