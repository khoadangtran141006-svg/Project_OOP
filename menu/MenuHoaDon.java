package menu;

import danhsach.DSCTHoaDon;
import danhsach.DSDichVu;
import danhsach.DSHoaDon;
import danhsach.DSKhachHang;
import danhsach.DSPhong;
import doituong.CTHoaDon;
import doituong.HoaDon;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuHoaDon {
    private final Scanner sc = doituong.Input.get();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DSHoaDon dsHD;
    private DSCTHoaDon dsCTHD;
    private DSKhachHang dsKH;
    private DSPhong dsPhong;
    private DSDichVu dsDV;

    // Nhận các danh sách đã có (từ MenuChinh)
    public MenuHoaDon(DSHoaDon dsHDChung, DSCTHoaDon dsCTHDChung, DSKhachHang dsKHChung, DSPhong dsPhongChung, DSDichVu dsDVChung) {
        this.dsHD = dsHDChung;
        this.dsCTHD = dsCTHDChung;
        this.dsKH = dsKHChung;
        this.dsPhong = dsPhongChung;
        this.dsDV = dsDVChung;

        // Liên kết danh sách
        dsCTHD.setDSDichVu(dsDV);
        dsHD.setDSCTHD(dsCTHD);
    }

    public void chay() {
        boolean thoat = false;
        while (!thoat) {
            System.out.println("\n╔═══════════════════════════════════════════╗");
            System.out.println("║              MENU QUẢN LÝ HÓA ĐƠN         ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║ 1. Xem danh sách Hóa đơn                  ║");
            System.out.println("║ 2. Thêm Hóa đơn kèm chi tiết              ║");
            System.out.println("║ 3. Tìm Hóa đơn theo mã                    ║");
            System.out.println("║ 4. Thống kê theo mã khách hàng            ║");
            System.out.println("║ 5. Thống kê theo tổng tiền                ║");
            System.out.println("║ 6. Sửa thông tin Hóa đơn                  ║");
            System.out.println("║ 7. Xóa Hóa đơn                            ║");
            System.out.println("║ 8. Xem danh sách Chi tiết Hóa đơn         ║");
            System.out.println("║ 9. Sửa Chi tiết Hóa đơn                   ║");
            System.out.println("║ 10. Xóa Chi tiết Hóa đơn                  ║");
            System.out.println("║ 0. Quay lại                               ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chọn: ");

            int chon;
            try { chon = Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { System.out.println("Vui lòng nhập số!"); continue; }

            switch (chon) {
                case 1 -> dsHD.xemDS();
                case 2 -> themHoaDonKemChiTiet();
                case 3 -> timHoaDonTheoMa();
                case 4 -> thongKeTheoKH();
                case 5 -> dsHD.thongKeTheoKhoangTien();
                case 6 -> suaHoaDon();
                case 7 -> xoaHoaDon();
                case 8 -> dsCTHD.xemDS();
                case 9 -> suaCTHoaDon();
                case 10 -> xoaCTHoaDon();
                case 0 -> thoat = true;
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // ===================== CÁC CHỨC NĂNG =====================

    private void themHoaDonKemChiTiet() {
    System.out.println("=== Thêm Hóa đơn mới ===");

    HoaDon hd = new HoaDon();

    // Nhập mã hóa đơn
    String mahd;
    while (true) {
        System.out.print("Nhập mã hóa đơn: ");
        mahd = sc.nextLine().trim();
        if (dsHD.tonTaiMaHD(mahd)) {
            System.out.println("Mã hóa đơn đã tồn tại, vui lòng nhập lại!");
        } else {
            hd.setmahoadon(mahd);
            break;
        }
    }

    // Nhập mã khách hàng
    while (true) {
        System.out.print("Nhập mã khách hàng: ");
        String makh = sc.nextLine().trim();
        if (dsKH.timkiem(makh) == null) {
            System.out.println("Mã khách hàng không tồn tại, nhập lại!");
        } else {
            hd.setmakhachhang(makh);
            break;
        }
    }

    // Nhập ngày lập hóa đơn
    while (true) {
        try {
            System.out.print("Nhập ngày lập (dd/MM/yyyy): ");
            String nl = sc.nextLine().trim();
            hd.setngaylap(LocalDate.parse(nl, formatter));
            break;
        } catch (Exception e) {
            System.out.println("Định dạng không hợp lệ!");
        }
    }

    // Thêm chi tiết hóa đơn
    boolean tiepTuc = true;
    while (tiepTuc) {
        CTHoaDon ct = new CTHoaDon();
        ct.setMaHoaDon(hd.getmahoadon());

        // Nhập mã phòng
        while (true) {
            System.out.print("Nhập mã phòng: ");
            String maPhong = sc.nextLine().trim();
            if (dsPhong.timTheoMaPhong(maPhong) == null) {
                System.out.println("Mã phòng không tồn tại!");
            } else {
                ct.setMaPhong(maPhong);
                break;
            }
        }

        // Nhập số ngày thuê
        System.out.print("Nhập số ngày thuê: ");
        ct.setSoNgayThue(Integer.parseInt(sc.nextLine()));

        // Chọn dịch vụ
        while (true) {
            System.out.print("Nhập mã dịch vụ: ");
            String madv = sc.nextLine().trim();
            if (dsDV.timDichVu(madv) == null) {
                System.out.println("Mã dịch vụ không tồn tại!");
            } else {
                ct.setMaDichVu(madv);
                break;
            }
        }

        // Nhập số lượng dịch vụ
        System.out.print("Nhập số lượng dịch vụ: ");
        ct.setSoLuong(Integer.parseInt(sc.nextLine()));

        // Tính thành tiền (phòng + dịch vụ)
        ct.tinhThanhTien(dsPhong, dsDV);

        // Thêm vào DS chi tiết
        dsCTHD.themCT(ct);

        System.out.print("Thêm chi tiết khác? (y/n): ");
        if (!sc.nextLine().equalsIgnoreCase("y")) tiepTuc = false;
    }

  

    // Thêm vào danh sách hóa đơn
    dsHD.themHoaDon(hd);

    // Ghi file
    try {
        dsHD.ghiFile();
        dsCTHD.ghiFile();
        System.out.println("[✔] Đã lưu hóa đơn và chi tiết!");
    } catch (IOException e) {
        System.out.println("Lỗi lưu file: " + e.getMessage());
    }
}


    private void timHoaDonTheoMa() {
        System.out.print("Nhập mã hóa đơn cần tìm: ");
        String mahd = sc.nextLine();
        dsHD.timHoaDon(mahd);
    }

    private void thongKeTheoKH() {
        System.out.print("Nhập mã khách hàng: ");
        String makh = sc.nextLine();
        dsHD.thongKeTheoKH(makh);
    }

    private void suaHoaDon() {
        System.out.print("Nhập mã hóa đơn cần sửa: ");
        String mahd = sc.nextLine();
        dsHD.suaHoaDon(mahd);
        try { dsHD.ghiFile(); } catch (IOException e) { System.out.println("Lỗi lưu file: " + e.getMessage()); }
    }

    private void xoaHoaDon() {
        System.out.print("Nhập mã hóa đơn cần xóa: ");
        String mahd = sc.nextLine();
        dsHD.xoaHoaDon(mahd);
        dsCTHD.xoaTheoMaHD(mahd);
        try {
            dsHD.ghiFile();
            dsCTHD.ghiFile();
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void suaCTHoaDon() {
        dsCTHD.suaCTHoaDon();
        try { dsCTHD.ghiFile(); } catch (IOException e) { System.out.println("Lỗi lưu file: " + e.getMessage()); }
    }

    private void xoaCTHoaDon() {
        System.out.print("Nhập mã hóa đơn: ");
        String mahd = sc.nextLine();
        dsCTHD.xoaTheoMaHD(mahd);
        try { dsCTHD.ghiFile(); } catch (IOException e) { System.out.println("Lỗi lưu file: " + e.getMessage()); }
    }
}
