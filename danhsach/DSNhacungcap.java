package danhsach;

import doituong.Nhacungcap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSNhacungcap {

    Scanner sc = doituong.Input.get();
    private int n;
    private Nhacungcap[] dsncc;
    private boolean isReading = false;

    public DSNhacungcap() {
        this.dsncc = new Nhacungcap[0];
        this.n = 0;
    }

    public DSNhacungcap(int n, Nhacungcap[] dsncc) {
        this.n = n;
        this.dsncc = new Nhacungcap[n];
        for (int i = 0; i < n; i++) {
            this.dsncc[i] = new Nhacungcap(dsncc[i]); // copy constructor
        }
    }

    public DSNhacungcap(DSNhacungcap listncc) {
        this.n = listncc.n;
        this.dsncc = new Nhacungcap[listncc.n];
        for (int i = 0; i < n; i++) {
            this.dsncc[i] = new Nhacungcap(listncc.dsncc[i]);
        }
    }

    public void nhap() {
        System.out.println("Nhập số lượng nhà cung cấp: ");
        n = sc.nextInt();
        sc.nextLine();
        dsncc = new Nhacungcap[n];
        for (int i = 0; i < n; i++) {
            dsncc[i] = new Nhacungcap();
            dsncc[i].nhap();
        }
    }

    public static boolean maNhacungcapTrung(String ma, Nhacungcap[] danhSach, int n) {
        for (int i = 0; i < n; i++) {
            if (danhSach[i].getMancc().equalsIgnoreCase(ma)) {
                return true; // mã đã tồn tại
            }
        }
        return false; // mã chưa tồn tại
    }

    public void ghifile() {
        try (FileWriter fw = new FileWriter("nhacungcap.txt")) {
            for (int i = 0; i < n; i++) {
                Nhacungcap ncc = dsncc[i];
                fw.write(
                        ncc.getMancc() + "|"
                        + ncc.getTenncc() + "|"
                        + ncc.getSdt() + "|"
                        + ncc.getEmail() + "|"
                        + ncc.getDiachi() + "\n"
                );
            }
            if (!isReading) {
                System.out.println("Đã ghi thành công");
            }
        } catch (IOException e) {
            System.out.println("ERROR ghi file: " + e.getMessage());
        }
    }

    public void docfile() throws FileNotFoundException {
        File file = new File("nhacungcap.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File nhacungcap.txt không tồn tại.");
        }
        isReading = true;
        Scanner scfile = new Scanner(file);
        n = 0;
        dsncc = new Nhacungcap[0];
        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parth = line.split("\\|");
            if (parth.length == 5) {
                try {
                    String mancc = parth[0].trim();
                    String tenncc = parth[1].trim();
                    String sdt = parth[2].trim();
                    String email = parth[3].trim();
                    String diachi = parth[4].trim();

                    Nhacungcap ncc = new Nhacungcap();
                    ncc.setMancc(mancc);
                    ncc.setTenncc(tenncc);
                    ncc.setSdt(sdt);
                    ncc.setEmail(email);
                    ncc.setDiachi(diachi);

                    themTS(ncc);
                } catch (Exception e) {
                    System.out.println("Lỗi định dạng dữ liệu ở dòng: " + line);
                }
            }
        }
        scfile.close();
        isReading = false;
    }

    public void xem() {
        try {
            docfile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Không tìm thấy file nhacungcap.txt. " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("Danh sách nhà cung cấp rỗng!");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                     DANH SÁCH NHÀ CUNG CẤP                                                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        System.out.printf("║ %-10s | %-20s | %-12s | %-30s | %-20s ║\n",
                "Mã NCC", "Tên NCC", "SĐT", "Email", "Địa chỉ");
        System.out.println("╠═══════════|══════════════════════|══════════════|═══════════════════════════════|══════════════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dsncc[i].xuat();
            System.out.println(" ║");
        }

        // Tổng kết
        System.out.println("╚═══════════┴══════════════════════┴══════════════┴═══════════════════════════════┴══════════════════════╝");
    }

    //tìm không tham số
    public void tim() {
        System.out.println("Nhập mã nhân viên cần tìm: ");
        String ma = sc.nextLine();
        if (dsncc == null || n == 0) {
            System.out.println("Danh sách nhà cung cấp rỗng.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMancc().equalsIgnoreCase(ma)) {
                System.out.println("Đã tìm thấy");
                dsncc[i].xuat();
                return;
            }
        }
        System.out.println("Không tìm thấy nhà cung cấp có mã: " + ma);
    }

    //tìm có tham số
    public Nhacungcap timMancc(String timma) {
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMancc().equalsIgnoreCase(timma)) {
                return dsncc[i];
            }
        }
        return null;
    }

    public void xoa() {
        System.out.print("Nhập mã nhà cung cấp cần xóa: ");
        String ma = sc.nextLine();

        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMancc().equalsIgnoreCase(ma)) {
                vitri = i;
                break;
            }
        }

        if (vitri == -1) {
            System.out.println("Không tìm thấy nhà cung cấp có mã: " + ma);
            return;
        }
        // Dịch phần tử phía sau lên
        for (int i = vitri; i < n - 1; i++) {
            dsncc[i] = dsncc[i + 1];
        }
        // Rút gọn mảng 1 phần tử
        dsncc = Arrays.copyOf(dsncc, n - 1);
        n--;

        ghifile();
        System.out.println("Đã xóa nhà cung cấp có mã " + ma);
    }

    // xóa có tham số
    public void xoaTS(String xoama) {
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMancc().equalsIgnoreCase(xoama)) {
                vitri = i;
                break;
            }
        }
        if (vitri == -1) {
            System.out.println("Không tìm thấy");
            return;
        }
        for (int i = vitri; i < n - 1; i++) {
            dsncc[i] = dsncc[i + 1];
        }
        dsncc = Arrays.copyOf(dsncc, dsncc.length - 1);
        n--;

        ghifile();
        System.out.println("Đã xóa nhà cung cấp có mã " + xoama);
    }

    public void sua() {
        System.out.print("Nhập mã nhà cung cấp cần sửa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (dsncc[i].getMancc().equalsIgnoreCase(ma)) {
                System.out.println("Tìm thấy mã nhà cung cấp cần sửa");
                System.out.println("1. Sửa mã nhà cung cấp");
                System.out.println("2. Sửa tên nhà cung cấp");
                System.out.println("3. Sửa số điện thoại");
                System.out.println("4. Sửa email");
                System.out.println("5. Sửa địa chỉ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: {
                        System.out.println("Nhập mã mới: ");
                        dsncc[i].setMancc(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 2: {
                        System.out.println("Nhập tên mới: ");
                        dsncc[i].setTenncc(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 3: {
                        System.out.println("Nhập số điện thoại mới: ");
                        dsncc[i].setSdt(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 4: {
                        System.out.println("Nhập email mới: ");
                        dsncc[i].setEmail(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 5: {
                        System.out.println("Nhập địa chỉ mới: ");
                        dsncc[i].setDiachi(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                    }
                }
            }
        }
        System.out.println("Không tìm thấy nhà cung cấp có mã: " + ma);
        return;
    }

    // thêm không tham số
    public void them() {
        if (dsncc == null) {
            dsncc = new Nhacungcap[0];
            n = 0;
        }
        System.out.println("\n--- Thêm nhà cung cấp mới ---");
        Nhacungcap nccmoi = new Nhacungcap();

        // Nhập mã nhà cung cấp (có kiểm tra trùng)
        String ma;
        while (true) {
            System.out.print("Nhập mã nhà cung cấp: ");
            ma = sc.nextLine();
            if (maNhacungcapTrung(ma, dsncc, n)) {
                System.out.println("Mã đã tồn tại, vui lòng nhập lại!");
            } else {
                nccmoi.nhap();
                nccmoi.setMancc(ma);
                break;
            }
        }

        // Thêm vào mảng
        dsncc = Arrays.copyOf(dsncc, n + 1);
        dsncc[n] = nccmoi;
        n++;
        System.out.println("Đã thêm thành công!");
        ghifile();
    }

    //thêm có tham số
    public void themTS(Nhacungcap ncc) {
        if (maNhacungcapTrung(ncc.getMancc(), dsncc, n)) {
            System.out.println(" Mã nhà cung cấp '" + ncc.getMancc() + "' đã tồn tại");
            return;
        }
        dsncc = Arrays.copyOf(dsncc, n + 1);
        dsncc[n] = new Nhacungcap(ncc);
        n++;
        ghifile();
        System.out.println("Đã thêm thành công");
    }

}
