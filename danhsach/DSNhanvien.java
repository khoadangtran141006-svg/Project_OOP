package danhsach;

import doituong.Nhanvien;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSNhanvien {

    Scanner sc = doituong.Input.get();
    private int n;
    private Nhanvien[] dsnv;
    private boolean isReading = false;

    public DSNhanvien() {
        this.dsnv = new Nhanvien[0];
        this.n = 0;
        try {
            docfile();
        } catch (FileNotFoundException e) {
            System.out.println("File nhanvien.txt không tồn tại");
        }
    }

    public DSNhanvien(int n, Nhanvien[] dsnv) {
        this.dsnv = new Nhanvien[n];
        for (int i = 0; i < n; i++) {
            this.dsnv[i] = new Nhanvien(dsnv[i]);
        }
    }

    public DSNhanvien(DSNhanvien listnv) {
        this.n = listnv.n;
        this.dsnv = new Nhanvien[listnv.dsnv.length];
        for (int i = 0; i < n; i++) {
            this.dsnv[i] = new Nhanvien(listnv.dsnv[i]);
        }
    }

    public void nhap() {
        System.out.println("Nhập số lượng nhân viên: ");
        n = sc.nextInt();
        sc.nextLine();
        dsnv = new Nhanvien[n];
        for (int i = 0; i < n; i++) {
            dsnv[i] = new Nhanvien();
            dsnv[i].nhap();
        }
    }

    public static boolean maNhanVienTrung(String ma, Nhanvien[] danhSach, int n) {
        for (int i = 0; i < n; i++) {
            if (danhSach[i].getManv().equalsIgnoreCase(ma)) {
                return true; // mã đã tồn tại
            }
        }
        return false; // mã chưa tồn tại
    }

    public void ghiFile() {
        try (FileWriter fw = new FileWriter("nhanvien.txt")) {
            for (int i = 0; i < n; i++) {
                Nhanvien nv = dsnv[i];
                fw.write(nv.getManv() + "|"
                        + nv.getHo() + "|"
                        + nv.getTen() + "|"
                        + nv.getChucvu() + "|"
                        + nv.getSdt() + "|"
                        + nv.getLuongcb() + "\n");
            }
            System.out.println("SUCCESS");
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    public void docfile() throws FileNotFoundException {
        File file = new File("nhanvien.txt");
        if (!file.exists()) {
            System.out.println("File không tồn tại");
            return;
        }
        isReading = true;
        Scanner scfile = new Scanner(file);
        n = 0;
        dsnv = new Nhanvien[0];
        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parth = line.split("\\|");
            if (parth.length == 6) {
                String manv = parth[0].trim();
                String ho = parth[1].trim();
                String ten = parth[2].trim();
                String chucvu = parth[3].trim();
                String sdt = parth[4].trim();
                double luongcb = Double.parseDouble(parth[5].trim());

                Nhanvien nv = new Nhanvien();
                nv.setManv(manv);
                nv.setHo(ho);
                nv.setTen(ten);
                nv.setChucvu(chucvu);
                nv.setSdt(sdt);
                nv.setLuongcb(luongcb);

                themTS(nv);
            }
        }
        scfile.close();
        isReading = false;
        System.out.println("Đã đọc file thành công");
    }

    public void xem() {
        try {
            docfile(); // Tải dữ liệu từ file trước khi xem
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Không tìm thấy file nhanvien.txt. " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("Danh sách nhân viên rỗng!");
            return;
        }

        // Tiêu đề chính
        System.out.println(
                "\n╔═══════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                   DANH SÁCH NHÂN VIÊN                                             ║");
        System.out.println(
                "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột
        System.out.printf("║ %-10s | %-15s | %-10s | %-12s | %-15s | %-10s | %-10s ║\n",
                "Mã NV", "Họ", "Tên", "Chức vụ", "SĐT", "Lương CB", "Lương");
        System.out.println(
                "╠═══════════|═════════════════|════════════|══════════════|═════════════════|════════════|═════════════╣");

        // In từng nhân viên
        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dsnv[i].xuat();
            System.out.println(" ║");
        }

        // Tổng kết
        System.out.println(
                "╚═══════════┴═════════════════┴════════════┴══════════════┴═════════════════┴════════════┴═════════════╝");
    }

    // tìm không tham số
    public void tim() {
        System.out.println("Nhập mã nhân viên cần tìm: ");
        String ma = sc.nextLine();
        if (dsnv == null || n == 0) {
            System.out.println("Danh sách nhân viên rỗng.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getManv().equalsIgnoreCase(ma)) {
                System.out.println("Đã tìm thấy");
                dsnv[i].xuat();
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên có mã: " + ma);
    }

    // tìm có tham số
    public Nhanvien timManv(String timma) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getManv().equalsIgnoreCase(timma)) {
                return dsnv[i];
            }
        }
        return null;
    }

    public void xoa() {
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String ma = sc.nextLine();

        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getManv().equalsIgnoreCase(ma)) {
                vitri = i;
                break;
            }
        }

        if (vitri == -1) {
            System.out.println("Không tìm thấy nhân viên có mã: " + ma);
            return;
        }
        // Dịch phần tử phía sau lên
        for (int i = vitri; i < n - 1; i++) {
            dsnv[i] = dsnv[i + 1];
        }
        // Rút gọn mảng 1 phần tử
        dsnv = Arrays.copyOf(dsnv, dsnv.length - 1);
        n--;
        System.out.println("Đã xóa nhân viên có mã " + ma);
    }

    // xóa có tham số
    public void xoaTS(String xoama) {
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getManv().equalsIgnoreCase(xoama)) {
                vitri = i;
                break;
            }
        }
        if (vitri == -1) {
            System.out.println("Không tìm thấy");
            return;
        }
        for (int i = vitri; i < n - 1; i++) {
            dsnv[i] = dsnv[i + 1];
        }
        dsnv = Arrays.copyOf(dsnv, dsnv.length - 1);
        n--;
        System.out.println("Đã xóa nhân viên có mã " + xoama);
        ghiFile();
    }

    public void sua() {
        System.out.print("Nhập mã nhân viên cần sửa: ");
        String ma = sc.nextLine();
        boolean timma = false;

        for (int i = 0; i < n; i++) {
            if (dsnv[i].getManv().equalsIgnoreCase(ma)) {
                timma = true;
                System.out.println("Tìm thấy mã của nhân viên cần sửa");
                System.out.println("1. Sửa mã nhân viên");
                System.out.println("2. Sửa họ nhân viên");
                System.out.println("3. Sửa tên nhân viên");
                System.out.println("4. Sửa chức vụ nhân viên");
                System.out.println("5. Sửa số điện thoại");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.println("Nhập mã mới: ");
                        dsnv[i].setManv(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        ghiFile();
                        break;
                    }
                    case 2: {
                        System.out.println("Nhập họ mới: ");
                        dsnv[i].setHo(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        ghiFile();
                        break;
                    }
                    case 3: {
                        System.out.println("Nhập tên mới: ");
                        dsnv[i].setTen(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        ghiFile();
                        break;
                    }
                    case 4: {
                        System.out.println("Nhập chức vụ mới: ");
                        dsnv[i].setChucvu(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        ghiFile();
                        break;
                    }
                }
            }
        }
        if (timma) {
            return;
        }
        System.out.println("Không tìm thấy nhân viên có mã: " + ma);

    }

    // thêm không tham số
    public void them() {
        if (dsnv == null) {
            dsnv = new Nhanvien[0];
            n = 0;
        }
        System.out.println("\n--- Thêm nhân viên mới ---");
        Nhanvien nvmoi = new Nhanvien();

        String ma;
        while (true) {
            System.out.print("Nhập mã nhân viên: ");
            ma = sc.nextLine();
            if (maNhanVienTrung(ma, dsnv, n)) {
                System.out.println("Mã đã tồn tại, vui lòng nhập lại!");
            } else {
                nvmoi.nhap();
                nvmoi.setManv(ma);
                break;
            }
        }
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = nvmoi;
        n++;
        System.out.println("Đã thêm nhân viên thành công");
        ghiFile();
    }

    // thêm có tham số
    public void themTS(Nhanvien nv) {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new Nhanvien(nv);
        n++;

        if (!isReading) {
            System.out.println("Đã thêm thành công");
        }
    }

    // Thống kê theo họ
    public void thongKeTheoHo() {
        System.out.println("\n===== BẢNG THỐNG KÊ NHÂN VIÊN THEO HỌ =====");
        System.out.println("+-----------------+--------------------+");
        System.out.printf("| %-15s | %-18s |\n", "HỌ", "SỐ LƯỢNG NHÂN VIÊN");
        System.out.println("+-----------------+--------------------+");

        for (int i = 0; i < n; i++) {
            String hoHienTai = dsnv[i].getHo();

            // Kiểm tra họ đã in chưa
            boolean daIn = false;
            for (int j = 0; j < i; j++) {
                if (dsnv[j].getHo().equalsIgnoreCase(hoHienTai)) {
                    daIn = true;
                    break;
                }
            }
            if (daIn) {
                continue;
            }

            // Đếm số lượng nhân viên có cùng họ
            int dem = 0;
            for (int j = 0; j < n; j++) {
                if (dsnv[j].getHo().equalsIgnoreCase(hoHienTai)) {
                    dem++;
                }
            }

            // In từng dòng của bảng
            System.out.printf("| %-15s | %-18d |\n", hoHienTai, dem);
        }

        System.out.println("+-----------------+--------------------+");
    }

}
