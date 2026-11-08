package menu;

import danhsach.*;
import java.util.Scanner;

public class MenuChinh {

    private Scanner sc = doituong.Input.get();

    // Các danh sách dùng chung
    private DSPhong dsPhong;
    private DSPhieunhaphang dsPNH;
    private DSCTphieunhaphang dsCTPNHang;
    private DSNhanvien dsNhanvien;
    private DSNhacungcap dsNCCap;
    private DSDichVu dsDichvu;
    private DSCTDichVu dsCTDV;
    private DSHoaDon dsHoadon;
    private DSCTHoaDon dsCTHDon;
    private DSKhachHang dsKhachhang;
    private DSCTThue dsCTThue;
    private DSHDThue dsHDThue;

    // Menu con
    private MenuPhong menuPhong;
    private MenuPhieunhaphang menuPNHang;
    private MenuCTphieunhaphang menuCTPNHang;
    private MenuNhanvien menuNhanvien;
    private MenuNhacungcap menuNCCap;
    private MenuDichVu menuDichvu;
    private MenuCTDichVu menuCTDV;
    private MenuHoaDon menuHoadon;
    private MenuKhachHang menuCTKHang;
    private MenuCTThue menuCTThue;
    private MenuHDThue menuHDThue;
    private MenuThongke menuThongke;

    public MenuChinh() {

        // Khởi tạo danh sách
        dsPhong = new DSPhong();
        dsPNH = new DSPhieunhaphang();
        dsCTPNHang = new DSCTphieunhaphang();
        dsNhanvien = new DSNhanvien();
        dsNCCap = new DSNhacungcap();
        dsDichvu = new DSDichVu();
        dsCTDV = new DSCTDichVu();
        dsHoadon = new DSHoaDon();
        dsCTHDon = new DSCTHoaDon();
        dsKhachhang = new DSKhachHang();
        dsCTThue = new DSCTThue();
        dsHDThue = new DSHDThue();

        // Đọc file dữ liệu
        try {
            dsPhong.docFile();
            dsPNH.docfile();          
            dsCTPNHang.docfile(); 
            dsNhanvien.docfile();
            dsNCCap.docfile();
            dsDichvu.docFile();
            dsCTDV.docFile();
            dsHoadon.docFile();
            dsCTHDon.docFile();
            dsKhachhang.docFile();
            dsCTThue.docFile();
            dsHDThue.docFile();
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file dữ liệu: " + e.getMessage());
        }

        // Gán danh sách cho menu con
        menuPhong = new MenuPhong(dsPhong);
        menuPNHang = new MenuPhieunhaphang(dsPNH);
        menuCTPNHang = new MenuCTphieunhaphang(dsCTPNHang);
        menuNhanvien = new MenuNhanvien(dsNhanvien);
        menuNCCap = new MenuNhacungcap(dsNCCap);
        menuDichvu = new MenuDichVu(dsDichvu);
        menuCTDV = new MenuCTDichVu(dsCTDV, dsDichvu);
        menuHoadon = new MenuHoaDon(dsHoadon, dsCTHDon, dsKhachhang, dsPhong, dsDichvu);
        menuCTKHang = new MenuKhachHang(dsKhachhang);
        menuCTThue = new MenuCTThue(dsCTThue);
        menuHDThue = new MenuHDThue(dsHDThue);
        menuThongke = new MenuThongke(dsKhachhang, dsHoadon);
    }

    public void chay() {
        boolean thoat = false;
        while (!thoat) {
            hienThiMenu();
            System.out.print("Chọn chức năng: ");
            int option;

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại (chỉ số).");
                continue;
            }

            System.out.println();
            switch (option) {
                case 1 ->
                    menuPhong.chay();
                case 2 ->
                    menuPNHang.chay();
                case 3 ->
                    menuCTPNHang.chay();
                case 4 ->
                    menuNhanvien.chay();
                case 5 ->
                    menuNCCap.chay();
                case 6 ->
                    menuDichvu.chay();
                case 7 ->
                    menuCTDV.chay();
                case 8 ->
                    menuHoadon.chay();
                case 9 ->
                    menuCTKHang.chay();
                case 10 ->
                    menuCTThue.chay();
                case 11 ->
                    menuHDThue.chay();
                case 12 ->
                    menuThongke.chay();
                case 0 -> {
                    thoat = true;
                    hienThiLoiTamBiet();
                }
                default ->
                    System.out.println("Lựa chọn không hợp lệ.");
            }

            if (!thoat) {
                System.out.print("Nhấn Enter để quay lại menu chính...");
                sc.nextLine();
            }
        }
    }

    private void hienThiMenu() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              HỆ THỐNG QUẢN LÝ KHÁCH SẠN                ║");
        System.out.println("║                    MENU CHÍNH                          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Quản lý phòng khách sạn                            ║");
        System.out.println("║  2. Quản lý phiếu nhập hàng                            ║");
        System.out.println("║  3. Quản lý chi tiết phiếu nhập                        ║");
        System.out.println("║  4. Quản lý nhân viên khách sạn                        ║");
        System.out.println("║  5. Quản lý nhà cung cấp                               ║");
        System.out.println("║  6. Quản lý dịch vụ                                    ║");
        System.out.println("║  7. Quản lý chi tiết dịch vụ                           ║");
        System.out.println("║  8. Quản lý hóa đơn                                    ║");
        System.out.println("║  9. Quản lý khách hàng                                 ║");
        System.out.println("║  10. Quản lý chi tiết thuê phòng                       ║");
        System.out.println("║  11. Quản lý hợp đồng thuê phòng                       ║");
        System.out.println("║  12. Thống kê doanh thu của khách sạn                  ║");
        System.out.println("║  0. Thoát chương trình                                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    private void hienThiLoiTamBiet() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                     TẠM BIỆT!                          ║");
        System.out.println("║    Cảm ơn bạn đã sử dụng hệ thống quản lý khách sạn    ║");
        System.out.println("║                 Hẹn gặp lại!                            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }

    public void dongScanner() {
        if (sc != null) {
            sc.close();
        }
    }
}
