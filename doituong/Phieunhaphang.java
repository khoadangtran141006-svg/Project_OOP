package doituong;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Phieunhaphang {

    private String maphieu;
    private LocalDate ngaynhap;
    private int tongtien;
    private String mancc;
    private String manv;

    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Phieunhaphang() {
    }

    public Phieunhaphang(String maph, LocalDate ngynh, int ttien, String mancc, String manv) {
        this.maphieu = maph;
        this.ngaynhap = ngynh;
        this.tongtien = ttien;
        this.mancc = mancc;
        this.manv = manv;
    }

    public Phieunhaphang(Phieunhaphang pnh) {
        this.maphieu = pnh.maphieu;
        this.ngaynhap = pnh.ngaynhap;
        this.tongtien = pnh.tongtien;
        this.mancc = pnh.mancc;
        this.manv = pnh.manv;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập mã phiếu: ");
        maphieu = sc.nextLine();
        System.out.print("Nhập ngày nhập (dd/MM/yyyy): ");
        String dateStr = sc.nextLine();
        ngaynhap = LocalDate.parse(dateStr, df);
        System.out.println("nhập mã nhà cung cấp: ");
        mancc = sc.nextLine();
        System.out.println("mã nhân viên: ");
        manv = sc.nextLine();
        System.out.println("tổng tiền: ");
        tongtien = sc.nextInt();
    }
    public void xuat() {
        System.out.printf("| %-10s | %-15s | %-12s | %-12s | %-10d |\n",
                manv, maphieu, mancc, df.format(ngaynhap), tongtien);
    }


    public void tinhTongTien(CTphieunhaphang[] dsCT) {
        int tong = 0;
        for (int i = 0; i < dsCT.length; i++) {
            if (dsCT[i] != null && dsCT[i].getMaphieu().equals(this.maphieu)) {
                tong += dsCT[i].getThanhtien();
            }
        }
        this.tongtien = tong;
    }

    public String getMaphieu() {return maphieu;}
    public LocalDate getNgaynhap() {return ngaynhap;}
    public int getTongtien() {return tongtien;}
    public String getMancc() {return mancc;}
    public String getManv() {return manv;}

    public void setMaphieu(String maphieu) {this.maphieu = maphieu;}
    public void setNgaynhap(LocalDate Ngaynhap) {this.ngaynhap = ngaynhap;}
    public void setMancc(String mancc) {this.mancc = mancc;}
    public void setManv(String manv) {this.manv = manv;}
    public void setTongtien(int tongtien) {this.tongtien = tongtien;}
}
