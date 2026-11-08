package menu;

import danhsach.DSHoaDon;
import danhsach.DSKhachHang;
import java.util.Scanner;

public class MenuThongke {

    private Scanner sc = new Scanner(System.in);
    private DSKhachHang dskh;
    private DSHoaDon dshd;

    public MenuThongke(DSKhachHang dskhChung, DSHoaDon dshdChung) {
        this.dshd = dshdChung; //Sử dụng instance được truyền vào
        this.dskh = dskhChung;
    }

    public MenuThongke() {
        this.dshd = new DSHoaDon();
        this.dskh = new DSKhachHang();
        try {
            dshd.docFile();
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        try {
            dskh.docFile();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public void chay() {
        while (true) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║          MENU THONG KE         ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Tổng số khách hàng          ║");
            System.out.println("║ 2. Tổng doanh thu              ║");
            System.out.println("║ 0. Quay lại                    ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 ->
                    System.out.println("Tổng số khách hàng: " + dskh.getN());
                case 2 -> {
                    double tong = 0;
                    for (int i = 0; i < dshd.getn(); i++) {
                        tong += dshd.getDSHD()[i].gettongcong();
                    }
                    System.out.println("Tổng doanh thu: " + tong + "VNĐ");
                }
                case 0 -> {
                    return;
                }
            }
        }
    }
}
