package doituong;

import java.util.Scanner;

public class CTphieunhaphang {

    private String maphieu;
    private String masp;
    private int soluong;
    private int thanhtien;
    private int dongia;

    public CTphieunhaphang() {
    }

    public CTphieunhaphang(String maphieu, String masp, int soluong, int dongia) {
        this.maphieu = maphieu;
        this.masp = masp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = this.soluong * this.dongia;

    }

    public CTphieunhaphang(CTphieunhaphang ctpnh) {
        this.maphieu = ctpnh.maphieu;
        this.masp = ctpnh.masp;
        this.soluong = ctpnh.soluong;
        this.thanhtien = ctpnh.thanhtien;
        this.dongia = ctpnh.dongia;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập mã phiếu: ");
        maphieu = sc.nextLine();
        System.out.println("nhập mã sản phẩm: ");
        masp = sc.nextLine();
        System.out.println("nhập số lượng: ");
        soluong = sc.nextInt();
        System.out.println("nhập đơn giá: ");
        dongia = sc.nextInt();
        thanhtien = soluong * dongia;
    }

    public void xuat() {
        System.out.printf("| %-10s | %-15s | %-12d | %-12d | %-10d |\n",
                maphieu, masp, soluong, dongia, thanhtien);
    }


    public String getMaphieu() {
        return maphieu;
    }

    public String getMasp() {
        return masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public int getDongia() {
        return dongia;
    }

    public void setMaphieu(String maphieu) {
        this.maphieu = maphieu;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
        this.thanhtien = this.soluong * this.dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
        this.thanhtien = this.soluong * this.dongia;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

}
