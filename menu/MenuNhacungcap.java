package menu;

import danhsach.DSNhacungcap;
import java.util.Scanner;

public class MenuNhacungcap {

    private DSNhacungcap dsncc;
    private Scanner sc = doituong.Input.get();

    public MenuNhacungcap(DSNhacungcap dsNCCapChung) {
        this.dsncc = dsNCCapChung;
    }

    public void chay() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║------QUẢN LÝ NHÀ CUNG CẤP------║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Xem tất cả nhà cung cấp     ║");
            System.out.println("║ 2. Thêm nhà cung cấp           ║");
            System.out.println("║ 3. Xóa nhà cung cấp            ║");
            System.out.println("║ 4. Sửa thông tin nhà cung cấp  ║");
            System.out.println("║ 5. Tìm thông tin nhà cung cấp  ║");
            System.out.println("║ 0. Quay lai                    ║");
            System.out.println("╚════════════════════════════════╝");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    dsncc.xem();
                    break;
                }
                case 2: {
                    dsncc.them();
                    break;
                }
                case 3: {
                    dsncc.xoa();
                    break;
                }
                case 4: {
                    dsncc.sua();
                    break;
                }
                case 5: {
                    dsncc.tim();
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
