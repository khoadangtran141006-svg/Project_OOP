package menu;

import danhsach.DSCTDichVu;
import danhsach.DSDichVu;
import doituong.CTDichVu;
import doituong.DichVu;
import java.io.IOException;
import java.util.Scanner;

public class MenuCTDichVu {
    private final Scanner sc = new Scanner(System.in);
    private final DSCTDichVu dsCT;
    private final DSDichVu dsDV;

    // Nhận tham chiếu đến danh sách chi tiết và danh sách dịch vụ
    public MenuCTDichVu(DSCTDichVu dsCT, DSDichVu dsDV) {
        this.dsCT = dsCT;
        this.dsDV = dsDV;
    }

    public void chay() {
        boolean thoat = false;
        while (!thoat) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║          MENU CHI TIẾT DỊCH VỤ         ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Xem danh sách chi tiết dịch vụ      ║");
            System.out.println("║ 2. Thêm chi tiết dịch vụ               ║");
            System.out.println("║ 3. Sửa chi tiết dịch vụ                ║");
            System.out.println("║ 4. Xóa chi tiết dịch vụ                ║");
            System.out.println("║ 5. Tìm chi tiết theo mã CTDV           ║");
            System.out.println("║ 6. Đọc file chi tiết dịch vụ           ║");
            System.out.println("║ 7. Ghi file chi tiết dịch vụ           ║");
            System.out.println("║ 0. Quay lại                            ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Chọn: ");

            int chon;
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (chon) {
                case 1 -> dsCT.xuat();
                case 2 -> themChiTiet();
                case 3 -> suaChiTiet();
                case 4 -> xoaChiTiet();
                case 5 -> timChiTiet();
                case 6 -> {
                    try {
                        dsCT.docFile();
                    } catch (IOException e) {
                        System.out.println("Lỗi đọc file: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        dsCT.ghiFile();
                    } catch (IOException e) {
                        System.out.println("Lỗi ghi file: " + e.getMessage());
                    }
                }
                case 0 -> thoat = true;
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // Thêm 1 chi tiết (lấy đơn giá tự động từ DSDichVu nếu có)
    private void themChiTiet() {
        System.out.println("=== Thêm chi tiết dịch vụ ===");
        CTDichVu ct = new CTDichVu();

        System.out.print("Nhập mã CTDV (ví dụ CT001): ");
        ct.setMaCTDichVu(sc.nextLine().trim());

        // Nhập mã dịch vụ, kiểm tra tồn tại trong dsDV
        String maDV;
        while (true) {
            System.out.print("Nhập mã dịch vụ: ");
            maDV = sc.nextLine().trim();
            DichVu dv = dsDV.timDichVu(maDV);
            if (dv == null) {
                System.out.println("Mã dịch vụ không tồn tại. Bạn có muốn nhập lại mã dịch vụ? (y/n)");
                String c = sc.nextLine().trim();
                if (!c.equalsIgnoreCase("y")) {
                    // cho phép nhập đơn giá thủ công
                    ct.setMaDichVu(maDV);
                    System.out.print("Nhập đơn giá cho mã dịch vụ này: ");
                    ct.setDonGia(Double.parseDouble(sc.nextLine()));
                    break;
                }
            } else {
                ct.setMaDichVu(maDV);
                ct.setDonGia(dv.getDonGia()); // lấy đơn giá từ danh sách dịch vụ
                break;
            }
        }

        System.out.print("Nhập số lượng: ");
        try {
            ct.setSoLuong(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Số lượng không hợp lệ, đặt về 1.");
            ct.setSoLuong(1);
        }

        // thành tiền tự cập nhật qua setter
        dsCT.them(ct);

        // lưu file luôn
        try {
            dsCT.ghiFile();
            System.out.println("[✔] Đã thêm và lưu chi tiết dịch vụ.");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    // Sửa chi tiết theo mã CTDV
    private void suaChiTiet() {
        System.out.print("Nhập mã CTDV cần sửa: ");
        String ma = sc.nextLine().trim();
        var ct = dsCT.tim(ma);
        if (ct == null) {
            System.out.println("Không tìm thấy CTDV có mã: " + ma);
            return;
        }

        int chon;
        do {
            System.out.println("\n--- CHỈNH SỬA CTDV " + ma + " ---");
            System.out.println("1. Sửa mã CTDV");
            System.out.println("2. Sửa mã dịch vụ");
            System.out.println("3. Sửa số lượng");
            System.out.println("4. Sửa đơn giá");
            System.out.println("0. Hoàn tất");
            System.out.print("Chọn: ");
            try { chon = Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { chon = -1; }

            switch (chon) {
                case 1 -> {
                    System.out.print("Nhập mã CTDV mới: ");
                    ct.setMaCTDichVu(sc.nextLine().trim());
                }
                case 2 -> {
                    System.out.print("Nhập mã dịch vụ mới: ");
                    String newMaDV = sc.nextLine().trim();
                    DichVu dv = dsDV.timDichVu(newMaDV);
                    ct.setMaDichVu(newMaDV);
                    if (dv != null) ct.setDonGia(dv.getDonGia());
                    else {
                        System.out.print("Dịch vụ không tồn tại. Nhập đơn giá thủ công: ");
                        ct.setDonGia(Double.parseDouble(sc.nextLine()));
                    }
                }
                case 3 -> {
                    System.out.print("Nhập số lượng mới: ");
                    ct.setSoLuong(Integer.parseInt(sc.nextLine()));
                }
                case 4 -> {
                    System.out.print("Nhập đơn giá mới: ");
                    ct.setDonGia(Double.parseDouble(sc.nextLine()));
                }
                case 0 -> System.out.println("Hoàn tất sửa.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (chon != 0);

        // lưu file sau sửa
        try {
            dsCT.ghiFile();
            System.out.println("[✔] Đã lưu sau khi sửa.");
        } catch (IOException e) {
            System.out.println("Lỗi lưu file: " + e.getMessage());
        }
    }

    // Xóa theo mã CTDV
    private void xoaChiTiet() {
        System.out.print("Nhập mã CTDV cần xóa: ");
        String ma = sc.nextLine().trim();
        dsCT.xoa(ma);
        try {
            dsCT.ghiFile();
        } catch (IOException e) {
            System.out.println("Lỗi lưu file: " + e.getMessage());
        }
    }

    // Tìm và hiển thị chi tiết
    private void timChiTiet() {
        System.out.print("Nhập mã CTDV cần tìm: ");
        String ma = sc.nextLine().trim();
        var ct = dsCT.tim(ma);
        if (ct == null) {
            System.out.println("Không tìm thấy.");
        } else {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                    "Mã CTDV", "Mã DV", "Số lượng", "Đơn giá", "Thành tiền");
            ct.xuat();
        }
    }
}
