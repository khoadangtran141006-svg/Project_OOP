package menu;

import danhsach.DSCTphieunhaphang;
import java.util.Scanner;

public class MenuCTphieunhaphang {

    private DSCTphieunhaphang dsct;
    private Scanner sc = new Scanner(System.in);

    public MenuCTphieunhaphang(DSCTphieunhaphang dsCTPNHangChung) {
        this.dsct = dsCTPNHangChung;
    }

    public void chay() {
        int choice;
        do {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║------QUẢN LÝ CHI TIẾT PNH------║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Xem tất cả các chi tiết PNH ║");
            System.out.println("║ 2. Thêm chi tiết PNH           ║");
            System.out.println("║ 3. Xóa chi tiết PNH            ║");
            System.out.println("║ 4. Sửa thông tin chi tiết PNH  ║");
            System.out.println("║ 5. Tìm thông tin chi tiết PNH  ║");
            System.out.println("║ 0. Quay lai                    ║");
            System.out.println("╚════════════════════════════════╝");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: {
                    dsct.xem();
                    break;
                }
                case 2: {
                    dsct.them();
                    break;
                }
                case 3: {
                    dsct.xoa();
                    break;
                }
                case 4: {
                    dsct.sua();
                    break;
                }
                case 5: {
                    dsct.tim();
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
