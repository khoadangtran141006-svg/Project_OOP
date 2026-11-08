package doituong;

import java.util.Scanner;

public class Nhanvien {

    private String manv;
    private String ho;
    private String ten;
    private String chucvu;
    private String sdt;
    private double luong;
    private double luongcb;

    public Nhanvien() {
    }

    public Nhanvien(String manv, String ho, String ten, String chucvu, String sdt, double luongcb) {
        this.manv = manv;
        this.ho = ho;
        this.ten = ten;
        this.chucvu = chucvu;
        this.sdt = sdt;
        this.luongcb = luongcb;
        this.tinhluong();
    }

    public Nhanvien(Nhanvien nv) {
        this.manv = nv.manv;
        this.ho = nv.ho;
        this.ten = nv.ten;
        this.chucvu = nv.chucvu;
        this.sdt = nv.sdt;
        this.luongcb = nv.luongcb;
        this.luong = nv.luong;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã nhân viên: ");
        manv = sc.nextLine();
        System.out.println("Nhập họ: ");
        ho = sc.nextLine();
        System.out.println("Nhập tên: ");
        ten = sc.nextLine();
        System.out.println("Nhập chức vụ (giám đốc/quản lý/nhân viên): ");
        chucvu = sc.nextLine();
        System.out.println("Nhập số điện thoại: ");
        sdt = sc.nextLine();
        System.out.println("Nhập lương cơ bản: ");
        luongcb = sc.nextDouble();
        tinhluong();
    }

    
    public void xuat() {
        System.out.printf("%-10s | %-15s | %-10s | %-12s | %-15s | %-10.0f | %-10.0f",
                manv, ho, ten, chucvu, sdt, luongcb, luong);
    }

    public double tinhluong() {
        double heso;
        switch (chucvu.toLowerCase()) {
            case "giám đốc":
                heso = 3.0;
                break;
            case "trưởng phòng":
            case "quản lý":
                heso = 2.0;
                break;
            default:
                heso = 1.0;
        }
        this.luong = luongcb * heso;
        return this.luong;
    }


    // Getter
    public String getManv() {
        return manv;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public String getChucvu() {
        return chucvu;
    }

    public String getSdt() {
        return sdt;
    }

    public double getLuongcb() {
        return luongcb;
    }

    public double getLuong() {
        return luong;
    }

    // Setter
    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
        tinhluong();
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setLuongcb(double luongcb) {
        this.luongcb = luongcb;
        tinhluong();
    }
    public void setLuong(double luong){
        this.luong = luong;
        tinhluong();
    }
}
