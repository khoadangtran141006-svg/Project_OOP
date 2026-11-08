package menu;

import danhsach.DSDichVu;
import doituong.DichVu;
import java.io.IOException;
import java.util.Scanner;

public class MenuDichVu {
    private final Scanner sc = new Scanner(System.in);
    private DSDichVu dsDV;

    // Nhận danh sách dịch vụ dùng chung (từ MenuChinh)
    public MenuDichVu(DSDichVu dsDVChung) {
        this.dsDV = dsDVChung;
    }

    public void chay() {
        boolean thoat = false;
        while (!thoat) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║             MENU QUẢN LÝ DỊCH VỤ           ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ 1. Xem danh sách Dịch vụ                  ║");
            System.out.println("║ 2. Thêm Dịch vụ mới                       ║");
            System.out.println("║ 3. Sửa thông tin Dịch vụ                  ║");
            System.out.println("║ 4. Xóa Dịch vụ                            ║");
            System.out.println("║ 5. Tìm Dịch vụ theo mã                    ║");
            System.out.println("║ 6. Tìm Dịch vụ theo tên                   ║");
            System.out.println("║ 7. Thống kê theo đơn giá                  ║");
            System.out.println("║ 0. Quay lại                               ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Chọn: ");

            int chon;
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (chon) {
                case 1 -> dsDV.xemDS();
                case 2 -> themDichVu();
                case 3 -> suaDichVu();
                case 4 -> xoaDichVu();
                case 5 -> timTheoMa();
                case 6 -> timTheoTen();
                case 7 -> thongKeTheoDonGia();
                case 0 -> thoat = true;
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // ===================== CHỨC NĂNG =====================

    private void themDichVu() {
        System.out.println("=== Thêm Dịch vụ mới ===");
        DichVu dv = new DichVu();

        // Nhập mã dịch vụ
        while (true) {
            System.out.print("Nhập mã dịch vụ: ");
            String madv = sc.nextLine().trim();
            if (dsDV.timDichVu(madv) != null) {
                System.out.println("Mã dịch vụ đã tồn tại, vui lòng nhập lại!");
            } else {
                dv.setMaDichVu(madv);
                break;
            }
        }

        System.out.print("Nhập tên dịch vụ: ");
        dv.setTenDichVu(sc.nextLine().trim());

        System.out.print("Nhập đơn giá: ");
        dv.setDonGia(Double.parseDouble(sc.nextLine()));

        System.out.print("Nhập số lượng: ");
        dv.setSoLuong(Integer.parseInt(sc.nextLine()));

        dsDV.them(dv);

        try {
            dsDV.ghiFile();
            System.out.println("[✔] Đã thêm dịch vụ và lưu file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void suaDichVu() {
        System.out.print("Nhập mã dịch vụ cần sửa: ");
        String madv = sc.nextLine();
        dsDV.sua(madv);
        try {
            dsDV.ghiFile();
            System.out.println("[✔] Đã cập nhật thông tin dịch vụ!");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void xoaDichVu() {
        System.out.print("Nhập mã dịch vụ cần xóa: ");
        String madv = sc.nextLine();
        dsDV.xoa(madv);
        try {
            dsDV.ghiFile();
            System.out.println("[✔] Đã xóa dịch vụ!");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void timTheoMa() {
        System.out.print("Nhập mã dịch vụ cần tìm: ");
        String madv = sc.nextLine();
        dsDV.timTheoMa(madv);
    }

    private void timTheoTen() {
        System.out.print("Nhập tên dịch vụ cần tìm: ");
        String ten = sc.nextLine();
        dsDV.timTheoTen(ten);
    }

    private void thongKeTheoDonGia() {
        System.out.print("Nhập khoảng giá thấp nhất: ");
        double min = Double.parseDouble(sc.nextLine());
        System.out.print("Nhập khoảng giá cao nhất: ");
        double max = Double.parseDouble(sc.nextLine());
        dsDV.thongKeTheoKhoangGia(min, max);
    }
}
