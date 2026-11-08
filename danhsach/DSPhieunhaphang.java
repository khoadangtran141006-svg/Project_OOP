package danhsach;

import doituong.Phieunhaphang;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class DSPhieunhaphang {

    Scanner sc = doituong.Input.get();
    private int n;
    private Phieunhaphang[] dspnh;

    public DSPhieunhaphang() {
        this.dspnh = new Phieunhaphang[0];
        this.n = 0;
        try {
            docfile();
        } catch (FileNotFoundException e) {
            System.out.println("File nhanvien.txt không tồn tại");
        }
    }

    public DSPhieunhaphang(int n, Phieunhaphang[] dspnh) {
        this.n = n;
        this.dspnh = new Phieunhaphang[n];
        for (int i = 0; i < n; i++) {
            this.dspnh[i] = new Phieunhaphang(dspnh[i]);
        }
    }

    public DSPhieunhaphang(DSPhieunhaphang listpnh) {
        this.n = listpnh.n;
        this.dspnh = new Phieunhaphang[listpnh.dspnh.length];
        for (int i = 0; i < n; i++) {
            this.dspnh[i] = new Phieunhaphang(listpnh.dspnh[i]);
        }
    }

    public void nhap() {
        System.out.println("Nhập số lượng phiếu nhập hàng: ");
        n = sc.nextInt();
        sc.nextLine();
        dspnh = new Phieunhaphang[n];
        for (int i = 0; i < n; i++) {
            dspnh[i] = new Phieunhaphang();
            dspnh[i].nhap();
        }
    }

    public void ghiFile() {
        try (FileWriter fw = new FileWriter("phieunhaphang.txt")) {
            for (int i = 0; i < n; i++) {
                Phieunhaphang pnh = dspnh[i]; // Đổi tên biến từ ncc thành pnh
                fw.write(pnh.getMancc() + "|"
                        + pnh.getMaphieu() + "|"
                        + pnh.getManv() + "|"
                        + pnh.getNgaynhap().format(Phieunhaphang.df) + "|" // Dùng format để ghi ngày
                        + pnh.getTongtien() + "\n");
            }
            System.out.println("SUCCESS");
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    public void docfile() throws FileNotFoundException {
        File file = new File("phieunhaphang.txt");
        if (!file.exists()) {
            System.out.println("File không tồn tại");
            return;
        }
        Scanner scfile = new Scanner(file);
        n = 0;
        dspnh = new Phieunhaphang[0];
        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parth = line.split("\\|");
            if (parth.length == 5) {
                try {
                    String mancc = parth[0].trim();
                    String maphieu = parth[1].trim();
                    String manv = parth[2].trim();
                    LocalDate ngaynhap = LocalDate.parse(parth[3].trim());
                    int tongtien = Integer.parseInt(parth[4].trim());

                    Phieunhaphang pnh = new Phieunhaphang();
                    pnh.setMaphieu(maphieu);
                    pnh.setMancc(mancc);
                    pnh.setManv(manv);
                    pnh.setNgaynhap(ngaynhap);
                    pnh.setTongtien(tongtien);

                    themTS(pnh);
                } catch (Exception e) {
                    System.out.println("Lỗi đọc dữ liệu ở dòng: " + line + ". Chi tiết: " + e.getMessage());
                }
            }
        }
        scfile.close();
        System.out.println("Đã đọc file thành công");
    }

    public static boolean maPhieutrung(String ma, Phieunhaphang[] danhSach, int n) {
        for (int i = 0; i < n; i++) {
            if (danhSach[i].getMaphieu().equalsIgnoreCase(ma)) {
                return true; // mã đã tồn tại
            }
        }
        return false; // mã chưa tồn tại
    }

    public void xem() {
        try {
            docfile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Không tìm thấy file phieunhaphang.txt. " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("Danh sách phiếu nhập hàng rỗng!");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                   DANH SÁCH PHIẾU NHẬP HÀNG                                  s         ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        System.out.printf("║ %-15s | %-12s | %-15s | %-10s | %-15s ║\n",
                "Mã phiếu nhập", "Ngày nhập", "Mã NCC", "Mã NV", "Tổng tiền");
        System.out.println("╠════════════════|═══════════════|════════════════|════════════|════════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dspnh[i].xuat();
            System.out.println(" ║");
        }

        System.out.println("╚════════════════┴═══════════════┴════════════════┴════════════┴════════════════╝");
    }

    public void tim() {
        System.out.println("Nhập mã nhân viên cần tìm: ");
        String ma = sc.nextLine();
        if (dspnh == null || n == 0) {
            System.out.println("Danh sách rỗng.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMancc().equalsIgnoreCase(ma)) {
                System.out.println("Đã tìm thấy");
                dspnh[i].xuat();
                return;
            }
        }
        System.out.println("Không tìm thấy phiếu có mã: " + ma);
    }

    //tìm có tham số
    public Phieunhaphang timMaphieu(String timma) {
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaphieu().equalsIgnoreCase(timma)) {
                return dspnh[i];
            }
        }
        return null;
    }

    public void xoa() {
        System.out.print("Nhập mã phiếu nhập hàng cần xóa: ");
        String ma = sc.nextLine();

        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaphieu().equalsIgnoreCase(ma)) {
                vitri = i;
                break;
            }
        }

        if (vitri == -1) {
            System.out.println("Không tìm thấy phiếu nhập hàng có mã: " + ma);
            return;
        }
        // Dịch phần tử phía sau lên
        for (int i = vitri; i < n - 1; i++) {
            dspnh[i] = dspnh[i + 1];
        }
        // Rút gọn mảng 1 phần tử
        dspnh = Arrays.copyOf(dspnh, dspnh.length - 1);
        n--;
        System.out.println("Đã xóa phiếu nhập hàng có mã " + ma);
        ghiFile();
    }

    // xóa có tham số
    public void xoaTS(String xoama) {
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMaphieu().equalsIgnoreCase(xoama)) {
                vitri = i;
                break;
            }
        }
        if (vitri == -1) {
            System.out.println("Không tìm thấy");
            return;
        }
        for (int i = vitri; i < n - 1; i++) {
            dspnh[i] = dspnh[i + 1];
        }
        dspnh = Arrays.copyOf(dspnh, dspnh.length - 1);
        n--;
        System.out.println("Đã xóa phiếu nhập hàng có mã " + xoama);
        ghiFile();
    }

    public void sua() {
        System.out.print("Nhập mã phiếu hàng cần sửa: ");
        String ma = sc.nextLine();

        for (int i = 0; i < n; i++) {
            if (dspnh[i].getMancc().equalsIgnoreCase(ma)) {
                System.out.println("Tìm thấy mã phiếu cần sửa");
                System.out.println("1. Sửa mã phiếu");
                System.out.println("2. Sửa ngày nhập");
                System.out.println("3. Sửa mã nhà cung cấp");
                System.out.println("4. Sửa mã nhân viên");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: {
                        System.out.println("Nhập mã mới: ");
                        dspnh[i].setMaphieu(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 2: {
                        System.out.print("Nhập ngày nhập mới (dd/MM/yyyy): ");
                        sc.nextLine();
                        String dateStr = sc.nextLine();
                        LocalDate ngaymoi = LocalDate.parse(dateStr, Phieunhaphang.df);
                        dspnh[i].setNgaynhap(ngaymoi);
                        System.out.println("Đã sửa thành công");
                        break;
                    }

                    case 3: {
                        System.out.println("Nhập mã nhà cung cấp mới: ");
                        dspnh[i].setMancc(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                    case 4: {
                        System.out.println("Nhập mã nhân viên mới: ");
                        dspnh[i].setMancc(sc.nextLine());
                        System.out.println("Đã sửa thành công");
                        break;
                    }
                }
            }
        }
        System.out.println("Không tìm thấy phiếu nhập hàng có mã: " + ma);
        return;
    }

    // thêm không tham số
    public void them() {
        if (dspnh == null) {
            dspnh = new Phieunhaphang[0];
            n = 0;
        }
        System.out.println("\n--- Thêm phiếu nhập hàng mới ---");
        Phieunhaphang phieumoi = new Phieunhaphang();

        String ma;
        while (true) {
            System.out.print("Nhập mã phiếu: ");
            ma = sc.nextLine();
            if (maPhieutrung(ma, dspnh, n)) {
                System.out.println("Mã phiếu đã tồn tại, vui lòng nhập lại!");
            } else {
                phieumoi.nhap();
                phieumoi.setMancc(ma);
                break;
            }
        }

        dspnh = Arrays.copyOf(dspnh, n + 1);
        dspnh[n] = phieumoi;
        n++;
        System.out.println("Đã thêm thành công");
        ghiFile();
    }

    //thêm có tham số
    public void themTS(Phieunhaphang pnh) {
        dspnh = Arrays.copyOf(dspnh, n + 1);
        dspnh[n] = new Phieunhaphang(pnh);
        n++;
        System.out.println("Đã thêm thành công");
        ghiFile();
    }
}
