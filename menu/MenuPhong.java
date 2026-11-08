package menu;

import danhsach.DSPhong;
import doituong.PhongThuong;
import doituong.PhongVIP;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets; // Cần import thêm
import java.util.Scanner; // Cần import thêm

public class MenuPhong {
    private Scanner sc;
    private DSPhong ds;
    public MenuPhong(DSPhong ds) {
        this.sc = doituong.Input.get();
        this.ds = ds;
    }

    public MenuPhong() {
        this.sc = doituong.Input.get();
        this.ds = new DSPhong();

        try {
            this.ds.docFile();
        } catch (FileNotFoundException e) {
            System.out.println("[INFO] File phong trong, khoi tao moi.");
        }
    }

    public void chay() {
        while (true) {
            System.out.println("\n╔═════════════════════════════════════╗");
            System.out.println("║          MENU QUẢN LÝ PHÒNG         ║");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║ 1. Thêm phòng                       ║");
            System.out.println("║ 2. Xoá phòng                        ║");
            System.out.println("║ 3. Hiển thị danh sách               ║");
            System.out.println("║ 4. Sửa thông tin phòng              ║");
            System.out.println("║ 5. Tìm phòng                        ║");
            System.out.println("║ 6. Thống kê theo loại phòng         ║");
            System.out.println("║ 0. Quay lại                         ║");
            System.out.println("╚═════════════════════════════════════╝");
            System.out.print("Chọn: ");

            int chon;
            try {
                chon = Integer.parseInt(this.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                continue;
            }

            switch (chon) {
                case 0:
                    return;
                case 1:
                    System.out.print("Nhap loai phong (1: Thuong | 2: VIP): ");
                    int loai = Integer.parseInt(this.sc.nextLine());
                    if (loai == 1) {
                        PhongThuong pt = new PhongThuong();
                        pt.nhap();
                        this.ds.themPhongThuTS(pt);
                    } else {
                        PhongVIP pv = new PhongVIP();
                        pv.nhap();
                        this.ds.themPhongVipTS(pv);
                    }
                    try {
                        this.ds.ghiFile();
                    } catch (Exception e) {
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }
                    break;
                case 2:
                    this.ds.xoaMaPhong();
                    try {
                        this.ds.ghiFile();
                    } catch (IOException e) {
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }
                    break;
                case 3:
                    if (this.ds.getn() == 0) {
                        System.out.println("Danh sach phong trong!");
                        break;
                    }
                    this.ds.xemDS();
                    break;
                case 4:
                    this.ds.suaPhong();
                    try {
                        this.ds.ghiFile();
                    } catch (Exception e) {
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }
                    break;
                case 5:
                    this.ds.timTheoMaPhong();
                    break;
                case 6:
                    this.ds.thongKeTheoLoai();
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
