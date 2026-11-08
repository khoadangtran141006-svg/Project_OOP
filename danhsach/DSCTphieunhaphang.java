package danhsach;

import doituong.CTphieunhaphang;
import java.io.*;
import java.util.*;

public class DSCTphieunhaphang {

    Scanner sc = doituong.Input.get();
    private int n;
    private CTphieunhaphang[] dsct;
    private boolean isReading = false;

    public DSCTphieunhaphang() {
        this.dsct = new CTphieunhaphang[0];
        this.n = 0;
    }

    public DSCTphieunhaphang(int n, CTphieunhaphang[] dsct) {
        this.n = n;
        this.dsct = new CTphieunhaphang[n];
        for (int i = 0; i < n; i++) {
            this.dsct[i] = new CTphieunhaphang(dsct[i]);
        }
    }

    public DSCTphieunhaphang(DSCTphieunhaphang listct) {
        this.n = listct.n;
        this.dsct = new CTphieunhaphang[listct.n];
        for (int i = 0; i < n; i++) {
            this.dsct[i] = new CTphieunhaphang(listct.dsct[i]);
        }
    }

    public void nhap() {
        System.out.println("Nhập số lượng: ");
        n = sc.nextInt();
        sc.nextLine();
        dsct = new CTphieunhaphang[n];
        for (int i = 0; i < n; i++) {
            dsct[i] = new CTphieunhaphang();
            dsct[i].nhap();
        }
    }

    public static boolean maCTphieunhaphangTrung(String ma, CTphieunhaphang[] danhSach, int n) {
        for (int i = 0; i < n; i++) {
            if (danhSach[i].getMaphieu().equalsIgnoreCase(ma)) {
                return true; // mã đã tồn tại
            }
        }
        return false; // mã chưa tồn tại
    }

    public void ghiFile() {
        try (FileWriter fw = new FileWriter("CTphieunhaphang.txt")) { // sẽ tạo file nếu chưa có
            for (int i = 0; i < n; i++) {
                CTphieunhaphang ct = dsct[i];
                fw.write(ct.getMaphieu() + ","
                        + ct.getMasp() + ","
                        + ct.getDongia() + ","
                        + ct.getSoluong() + ","
                        + ct.getThanhtien() + "\n"
                );
            }
            System.out.println("SUCCESS");
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    public void docfile() throws FileNotFoundException {
        File file = new File("CTphieunhaphang.txt");
        if (!file.exists()) {
            System.out.println("File không tồn tại");
            return;
        }
        isReading = true;
        Scanner scfile = new Scanner(file);
        n = 0;
        dsct = new CTphieunhaphang[0];
        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            // Tách bằng dấu | 
            String[] parth = line.split("\\,");
            if (parth.length == 5) {
                String maphieu = parth[0].trim();
                String masp = parth[1].trim();
                int dongia = Integer.parseInt(parth[2].trim());
                int soluong = Integer.parseInt(parth[3].trim());
                int thanhtien = Integer.parseInt(parth[4].trim());

                CTphieunhaphang ct = new CTphieunhaphang();
                ct.setMaphieu(maphieu);
                ct.setMasp(masp);
                ct.setDongia(n);
                ct.setSoluong(n);
                ct.setThanhtien(n);

                themTS(ct);
            }
        }
        scfile.close();
        isReading = false;
        System.out.println("Đã đọc file thành công");
    }

    public void xem() {
        try {
            docfile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Không tìm thấy file CTphieunhaphang.txt. " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("Danh sách chi tiết phiếu nhập hàng rỗng!");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           DANH SÁCH CHI TIẾT PHIẾU NHẬP                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════╣");

        System.out.printf("║ %-10s | %-10s | %-12s | %-10s | %-12s ║\n",
                "Mã phiếu", "Mã SP", "Đơn giá", "Số lượng", "Thành tiền");
        System.out.println("╠═══════════|════════════|══════════════|════════════|══════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dsct[i].xuat(); // Giả định xuat() in các trường không có xuống dòng
            System.out.println(" ║");
        }

        System.out.println("╚═══════════┴════════════┴══════════════┴════════════┴══════════════╝");
    }

    //tìm không tham số
    public void tim() {
        System.out.println("Nhập mã phiếu cần tìm: ");
        String ma = sc.nextLine();
        if (dsct == null || n == 0) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (dsct[i].getMaphieu().equalsIgnoreCase(ma)) {
                System.out.println("Đã tìm thấy");
                dsct[i].xuat();
                return;
            }
        }
        System.out.println("Không tìm thấy phiếu có mã: " + ma);
    }

    //tìm có tham số
    public CTphieunhaphang timMaphieu(String timma) {
        for (int i = 0; i < n; i++) {
            if (dsct[i].getMaphieu().equalsIgnoreCase(timma)) {
                return dsct[i];
            }
        }
        return null;
    }

    public void xoa() {
        System.out.print("Nhập mã phiếu cần xóa: ");
        String ma = sc.nextLine();

        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dsct[i].getMaphieu().equalsIgnoreCase(ma)) {
                vitri = i;
                break;
            }
        }

        if (vitri == -1) {
            System.out.println("Không tìm thấy phiếu có mã: " + ma);
            return;
        }
        // Dịch phần tử phía sau lên
        for (int i = vitri; i < n - 1; i++) {
            dsct[i] = dsct[i + 1];
        }
        // Rút gọn mảng 1 phần tử
        dsct = Arrays.copyOf(dsct, dsct.length - 1);
        n--;
        System.out.println("Đã xóa phiếu có mã " + ma);
        ghiFile();
    }

    // xóa có tham số
    public void xoaTS(String xoama) {
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dsct[i].getMaphieu().equalsIgnoreCase(xoama)) {
                vitri = i;
                break;
            }
        }
        if (vitri == -1) {
            System.out.println("Không tìm thấy");
            return;
        }
        for (int i = vitri; i < n - 1; i++) {
            dsct[i] = dsct[i + 1];
        }
        dsct = Arrays.copyOf(dsct, dsct.length - 1);
        n--;
        System.out.println("Đã xóa phiếu có mã " + xoama);
        ghiFile();
    }

    public void sua() {
        System.out.print("Nhập mã phiếu cần sửa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (dsct[i].getMaphieu().equalsIgnoreCase(ma)) {
                System.out.println("Tìm thấy mã của nhân viên cần sửa");
                System.out.println("1. Sửa mã phiếu");
                System.out.println("2. Sửa mã sp");
                System.out.println("3. Sửa số lượng");
                System.out.println("4. Sửa đơn giá");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.println("Nhập mã mới: ");
                        dsct[i].setMaphieu(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 2: {
                        System.out.println("Nhập mã sản phẩm mới: ");
                        dsct[i].setMasp(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 3: {
                        System.out.println("Nhập sô lượng mới: ");
                        dsct[i].setSoluong(sc.nextInt());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 4: {
                        System.out.println("Nhập đơn giá mới: ");
                        dsct[i].setDongia(sc.nextInt());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                }
            }
        }
        System.out.println("Không tìm thấy chi tiết phiếu có mã: " + ma);
        return;
    }

    // thêm không tham số
    public void them() {
        System.out.println("\n--- Thêm nhà phiếu mới mới ---");
        CTphieunhaphang ctmoi = new CTphieunhaphang();

        String ma;
        while (true) {
            System.out.print("Nhập mã phiếu: ");
            ma = sc.nextLine();
            if (maCTphieunhaphangTrung(ma, dsct, n)) {
                System.out.println("Mã đã tồn tại, vui lòng nhập lại!");
            } else {
                ctmoi.nhap();
                ctmoi.setMaphieu(ma);
                break;
            }
        }
        // Thêm vào mảng
        dsct = Arrays.copyOf(dsct, n + 1);
        dsct[n] = ctmoi;
        n++;
        System.out.println("Đã thêm thành công!");
        ghiFile();
    }

    //thêm có tham số
    public void themTS(CTphieunhaphang ct) {
        if (maCTphieunhaphangTrung(ct.getMaphieu(), dsct, n)) {
            System.out.println("Mã phiếu nhập hàng '" + ct.getMaphieu() + "' đã tồn tại!");
            return;
        }
        dsct = Arrays.copyOf(dsct, n + 1);
        dsct[n] = new CTphieunhaphang(ct);
        n++;
        System.out.println("Đã thêm thành công");
        ghiFile();
    }

    // Cập nhật tổng tiền cho tất cả phiếu dựa trên danh sách chi tiết
    public void capNhatTongTien(DSCTphieunhaphang dsCT) {
        for (int i = 0; i < n; i++) {
            int tongTien = dsct[i].getDongia() * dsct[i].getSoluong();
            dsct[i].setThanhtien(tongTien);
        }
        ghiFile(); // ghi lại sau khi cập nhật
        System.out.println("Đã cập nhật tổng tiền cho tất cả phiếu nhập hàng!");
    }

    // Getter để truy xuất mảng chi tiết (sử dụng khi cần truyền mảng này cho hàm khác)
    public CTphieunhaphang[] getDsCT() {
        return this.dsct;
    }

}
