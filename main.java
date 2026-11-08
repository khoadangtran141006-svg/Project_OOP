
import menu.MenuChinh;

public class main {

    public static void main(String[] args) {
        MenuChinh menuChinh = new MenuChinh();
        try {
            menuChinh.chay();
        } finally {
            // Đảm bảo đóng Scanner khi chương trình kết thúc
            menuChinh.dongScanner();
        }
    }
}
