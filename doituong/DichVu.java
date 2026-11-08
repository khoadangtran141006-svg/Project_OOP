package doituong;

import java.util.Scanner;

public class DichVu {
    private String maDichVu;
    private String tenDichVu;
    private double donGia;
    private int soLuong;

    public DichVu() {}

    public DichVu(String maDichVu, String tenDichVu, double donGia, int soLuong) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public DichVu(DichVu dv) {
        this.maDichVu = dv.maDichVu;
        this.tenDichVu = dv.tenDichVu;
        this.donGia = dv.donGia;
        this.soLuong = dv.soLuong;
    }

    public String getMaDichVu() { return maDichVu; }
    public String getTenDichVu() { return tenDichVu; }
    public double getDonGia() { return donGia; }
    public int getSoLuong() { return soLuong; }

    public void setMaDichVu(String maDichVu) { this.maDichVu = maDichVu; }
    public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public void nhap() {
        Scanner sc = doituong.Input.get();
        System.out.print("Nhập mã dịch vụ: ");
        maDichVu = sc.nextLine();
        System.out.print("Nhập tên dịch vụ: ");
        tenDichVu = sc.nextLine();
        System.out.print("Nhập đơn giá: ");
        donGia = sc.nextDouble();
        System.out.print("Nhập số lượng: ");
        soLuong = sc.nextInt();
    }

    public void xuat() {
        System.out.printf("%-10s %-20s %-10.2f %-10d\n", maDichVu, tenDichVu, donGia, soLuong);
    }
}
