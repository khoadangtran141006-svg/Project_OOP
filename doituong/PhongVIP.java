package doituong;

import java.util.Scanner;

public class PhongVIP extends Phong {
    Scanner sc = doituong.Input.get();
    private String freedv;
    private String bontamcaocap;

    public PhongVIP() {
        super();
        freedv = "Co";
        bontamcaocap = "Co";
    }

    public PhongVIP(String maphong, String tenphong, String day, int songuoitoida, int sogiuong,
                    String loaiphong, double dongia, String trangthai,
                    String freedv, String bontamcaocap) {
        super(maphong, tenphong, day, songuoitoida, sogiuong, loaiphong, dongia, trangthai);
        this.freedv = freedv;
        this.bontamcaocap = bontamcaocap;
    }

    public PhongVIP(PhongVIP pv) {
        super(pv);
        this.freedv = pv.freedv;
        this.bontamcaocap = pv.bontamcaocap;
    }

    public String getfreedv() { return freedv; }
    public String getbontamcaocap() { return bontamcaocap; }
    public void setfreedv(String freedv) { this.freedv = freedv; }
    public void setbontamcaocap(String bontamcaocap) { this.bontamcaocap = bontamcaocap; }

    @Override
    public void nhap() {
        System.out.println("\n--- NHAP THONG TIN PHONG VIP ---");
        super.nhap();
        System.out.println("Phong VIP mac dinh free tat ca dich vu va bon tam cao cap.");
        freedv = "Co";
        bontamcaocap = "Co";
        System.out.println("Da nhap thong tin phong VIP thanh cong!");
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("%-10s %-10s\n", freedv, bontamcaocap);
    }
}
