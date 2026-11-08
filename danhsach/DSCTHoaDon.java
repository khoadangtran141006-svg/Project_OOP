package danhsach;

import doituong.CTHoaDon;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSCTHoaDon {

    private Scanner sc = new Scanner(System.in);
    private int n;
    private CTHoaDon[] dscthd;
    private DSDichVu dsdv;
    private DSPhong dsPhong;
    private boolean isReading = false;

    public DSCTHoaDon() {
        n = 0;
        dscthd = new CTHoaDon[0];
        dsdv = new DSDichVu();
        try {
            docFile(); // Tự động đọc file
        } catch (IOException e) {
            System.out.println("Lỗi đọc file cthoadon.txt: " + e.getMessage());
        }
    }

    // Getter số lượng chi tiết hóa đơn
    public int getn() {
        return n;
    }

    // Getter mảng chi tiết hóa đơn
    public CTHoaDon[] getDSCTHoaDon() {
        return dscthd;
    }

    // Setter liên kết danh sách dịch vụ
    public void setDSDichVu(DSDichVu dsDV) {
        this.dsdv = dsDV;
    }

    // Copy constructor
    public DSCTHoaDon(DSCTHoaDon other) {
        this.n = other.n;
        this.dscthd = new CTHoaDon[other.dscthd.length];
        for (int i = 0; i < n; i++) {
            this.dscthd[i] = new CTHoaDon(other.dscthd[i]);
        }
        this.dsdv = other.dsdv;
    }

    // ===== NHẬP DANH SÁCH =====
    public void nhap() {
        System.out.print("Nhập số lượng chi tiết hóa đơn: ");
        n = sc.nextInt();
        sc.nextLine();
        dscthd = new CTHoaDon[n];
        for (int i = 0; i < n; i++) {
            dscthd[i] = new CTHoaDon();
            dscthd[i].nhap();
            dscthd[i].tinhThanhTien(dsPhong, dsdv); // sử dụng DSPhong + DSDichVu
        }
    }

    // ===== XUẤT DANH SÁCH =====
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sách chi tiết hóa đơn trống!");
            return;
        }

        // Tiêu đề chính
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                       DANH SÁCH CHI TIẾT HÓA ĐƠN                                         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột
        System.out.printf("║ %-8s | %-8s | %-8s | %-8s | %-8s | %-8s | %-12s | %-12s | %-12s ║\n",
                "Mã CT", "Mã HD", "Mã Phòng", "Số Ngày", "Mã DV", "SL",
                "Tiền Phòng", "Tiền DV", "Tổng Tiền");
        System.out.println("╠═════════|═════════|═════════|═════════|═════════|═════════|═════════════|═════════════|═════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dscthd[i].xuat(); // Cần đảm bảo CTHoaDon.xuat() in không có \n
            System.out.println(" ║");
        }
        System.out.println("╚═════════┴═════════┴═════════┴═════════┴═════════┴═════════┴═════════════┴═════════════┴═════════════╝");
    }

    // ===== THÊM CHI TIẾT =====
    public void themCT(CTHoaDon cthd) {
        dscthd = Arrays.copyOf(dscthd, n + 1);
        dscthd[n] = new CTHoaDon(cthd);         // copy constructor
        dscthd[n].tinhThanhTien(dsPhong, dsdv); // tính lại tiền
        n++;
        if (!isReading) {
            System.out.println("Đã thêm chi tiết hóa đơn thành công!");
        }
    }

    // ===== XÓA THEO MÃ HÓA ĐƠN =====
    public void xoaTheoMaHD(String mahd) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dscthd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                for (int j = i; j < n - 1; j++) {
                    dscthd[j] = dscthd[j + 1];
                }
                n--;
                dscthd = Arrays.copyOf(dscthd, n);
                i--;
                found = true;
            }
        }
        if (found) {
            System.out.println("Đã xóa tất cả chi tiết của hóa đơn: " + mahd);
        } else {
            System.out.println("Không tìm thấy chi tiết của hóa đơn: " + mahd);
        }
    }

    // ===== SỬA CHI TIẾT HÓA ĐƠN =====
    public void suaCTHoaDon() {
        System.out.print("Nhập mã chi tiết hóa đơn cần sửa: ");
        String maCT = sc.nextLine().trim();

        // Tìm chi tiết
        CTHoaDon ct = null;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dscthd[i].getMaCTHoaDon().equalsIgnoreCase(maCT)) {
                ct = dscthd[i];
                index = i;
                break;
            }
        }

        if (ct == null) {
            System.out.println("Không tìm thấy chi tiết hóa đơn với mã: " + maCT);
            return;
        }

        System.out.println("Đang sửa chi tiết: ");
        ct.xuat();

        // Sửa mã phòng
        System.out.print("Nhập mã phòng mới (Enter để giữ nguyên): ");
        String maPhong = sc.nextLine().trim();
        if (!maPhong.isEmpty()) {
            ct.setMaPhong(maPhong);
        }
    }

// ===== XEM DANH SÁCH CHI TIẾT HÓA ĐƠN =====
    public void xemDS() {
        try {
            docFile();
        } catch (IOException e) {
            System.out.println("ERROR đọc file: " + e.getMessage());
            return;
        }
        xuat();
    }

    // ===== GHI FILE =====
    public void ghiFile() throws IOException {
        File file = new File("cthoadon.txt");
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < n; i++) {
            fw.write(dscthd[i].getMaCTHoaDon() + "|"
                    + dscthd[i].getMaHoaDon() + "|"
                    + dscthd[i].getMaPhong() + "|"
                    + dscthd[i].getSoNgayThue() + "|"
                    + dscthd[i].getMaDichVu() + "|"
                    + dscthd[i].getSoLuong() + "|"
                    + dscthd[i].getThanhTien() + "\n");
        }
        fw.close();
        System.out.println("Đã ghi danh sách chi tiết hóa đơn thành công.");
    }

    // ===== ĐỌC FILE =====
    public void docFile() throws IOException {
        File file = new File("cthoadon.txt");
        if (!file.exists()) {
            System.out.println("File dulieucthd.txt không tồn tại.");
            dscthd = new CTHoaDon[0];
            n = 0;
            return;
        }
        isReading = true;
        Scanner scfile = new Scanner(file);
        dscthd = new CTHoaDon[0];
        n = 0;

        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\|");
            if (parts.length == 7) { // mãCT|maHD|maPhong|soNgay|maDV|soLuong|thanhTien
                try {
                    String maCT = parts[0].trim();
                    String maHD = parts[1].trim();
                    String maPhong = parts[2].trim();
                    int soNgay = Integer.parseInt(parts[3].trim());
                    String maDV = parts[4].trim();
                    int soLuong = Integer.parseInt(parts[5].trim());
                    double thanhTien = Double.parseDouble(parts[6].trim());

                    CTHoaDon cthd = new CTHoaDon(maCT, maHD, maPhong, soNgay, maDV, soLuong);
                    themCT(cthd);

                } catch (NumberFormatException e) {
                    System.out.println("Dữ liệu lỗi: " + line);
                }
            }
        }
        scfile.close();
        isReading = false;
        System.out.println("[SUCCESS] Đã đọc danh sách chi tiết hóa đơn.");
    }
}
