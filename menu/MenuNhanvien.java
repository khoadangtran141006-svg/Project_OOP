package menu;

import danhsach.DSNhanvien;
import java.util.Scanner;

public class MenuNhanvien {

    private DSNhanvien dsnv;
    private Scanner sc = doituong.Input.get();

    public MenuNhanvien(DSNhanvien dsNhanvienChung) {
        this.dsnv = dsNhanvienChung;
    }

    public void chay() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║-------QUẢN LÝ NHÂN VIÊN--------║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Xem tất cả nhân viên        ║");
            System.out.println("║ 2. Thêm nhân viên              ║");
            System.out.println("║ 3. Xóa nhân viên               ║");
            System.out.println("║ 4. Sửa thông tin nhân viên     ║");
            System.out.println("║ 5. Tìm thông tin nhân viên     ║");
            System.out.println("║ 6. Thống kê nhân viên          ║");
            System.out.println("║ 0. Quay lai                    ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("CHỌN: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    dsnv.xem();
                    break;
                }
                case 2: {
                    dsnv.them();
                    break;
                }
                case 3: {
                    dsnv.xoa();
                    break;
                }
                case 4: {
                    dsnv.sua();
                    break;
                }
                case 5: {
                    dsnv.tim();
                    break;
                }
                case 0:
                    System.out.println("Kết thúc chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }
}
