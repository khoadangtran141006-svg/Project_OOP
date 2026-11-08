package menu;

import danhsach.DSPhieunhaphang;
import java.util.Scanner;

public class MenuPhieunhaphang {

    private DSPhieunhaphang dspnh;
    private Scanner sc = doituong.Input.get();

    public MenuPhieunhaphang(DSPhieunhaphang dsPNHangChung) {
        this.dspnh = dsPNHangChung;
    }

    public void chay() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║----QUẢN LÝ PHIẾU NHẬP HÀNG-----║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Xem tất cả phiếu nhập hàng  ║");
            System.out.println("║ 2. Thêm phiếu nhập hàng        ║");
            System.out.println("║ 3. Xóa phiếu nhập hàng         ║");
            System.out.println("║ 4. Sửa thông tin phiếu         ║");
            System.out.println("║ 5. Tìm thông tin phiếu         ║");
            System.out.println("║ 0. Quay lai                    ║");
            System.out.println("╚════════════════════════════════╝");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    dspnh.xem();
                    break;
                }
                case 2: {
                    dspnh.them();
                    break;
                }
                case 3: {
                    dspnh.xoa();
                    break;
                }
                case 4: {
                    dspnh.sua();
                    break;
                }
                case 5: {
                    dspnh.tim();
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
