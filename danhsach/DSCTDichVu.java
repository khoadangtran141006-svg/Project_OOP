package danhsach;

import doituong.CTDichVu;
import java.io.*;
import java.util.*;

public class DSCTDichVu {

    private CTDichVu[] ds;
    private int n;
    private boolean isReading = false;
    Scanner sc = new Scanner(System.in);

    public DSCTDichVu() {
        ds = new CTDichVu[0];
        n = 0;
    }

    // ===== GETTER / SETTER =====
    public int getN() {
        return n;
    }

    public CTDichVu[] getDSCTDichVu() {
        return ds;
    }

    public void setDSCTDichVu(CTDichVu[] ds) {
        this.ds = ds;
        this.n = (ds != null) ? ds.length : 0;
    }

    // ===== NHẬP =====
    public void nhap() {
        System.out.print("Nhập số lượng chi tiết DV: ");
        n = sc.nextInt();
        sc.nextLine();

        ds = new CTDichVu[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Chi tiết DV thứ " + (i + 1) + " ---");
            ds[i] = new CTDichVu();

            // Nhập mã hóa đơn cho từng chi tiết (bổ sung để liên kết với Hóa đơn)
            System.out.print("Nhập mã hóa đơn: ");
            ds[i].setMaHoaDon(sc.nextLine().trim());

            ds[i].nhap(); // nhập các thông tin khác (mã DV, SL, đơn giá...)
        }
    }

    // ===== XUẤT =====
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sách rỗng!");
            return;
        }

        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n",
                "Mã CTDV", "Mã HD", "Mã DV", "Số lượng", "Đơn giá", "Thành tiền");

        for (int i = 0; i < n; i++) {
            CTDichVu ct = ds[i];
            System.out.printf("%-10s %-10s %-10s %-10d %-10.2f %-10.2f\n",
                    ct.getMaCTDichVu(),
                    ct.getMaHoaDon(),
                    ct.getMaDichVu(),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getThanhTien());
        }
    }

    // ===== THÊM =====
    public void them(CTDichVu ct) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new CTDichVu(ct);
        n++;
        if (!isReading) {
            System.out.println("✅ Đã thêm chi tiết dịch vụ thành công!");
        }
    }

    // ===== XÓA =====
    public void xoa(String ma) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaCTDichVu().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("✅ Đã xóa mã CTDV " + ma); 
        }else {
            System.out.println("❌ Không tìm thấy mã cần xóa!");
        }
    }

    // ===== TÌM KIẾM =====
    public CTDichVu tim(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getMaCTDichVu().equalsIgnoreCase(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    // ===== GHI FILE =====
    public void ghiFile() throws IOException {
        FileWriter fw = new FileWriter("ctdichvu.txt");
        for (CTDichVu ctdv : ds) {
            fw.write(ctdv.getMaCTDichVu() + "|"
                    + ctdv.getMaHoaDon() + "|"
                    + ctdv.getMaDichVu() + "|"
                    + ctdv.getSoLuong() + "|"
                    + ctdv.getDonGia() + "|"
                    + ctdv.getThanhTien() + "\n");
        }

        fw.close();
        System.out.println("[SUCCESS] Da luu danh sach phong vao file ctdichvu.txt");
    }

    // ===== ĐỌC FILE =====
    public void docFile() throws IOException {
        File f = new File("ctdichvu.txt");
        if (!f.exists()) {
            System.out.println("⚠️ File ctdichvu.txt chưa tồn tại!");
            ds = new CTDichVu[0];
            n = 0;
            return;
        }

        isReading = true;
        Scanner scfile = new Scanner(f);
        ds = new CTDichVu[0];
        n = 0;

        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] t = line.split("\\|");
            if (t.length == 6) {
                try {
                    String maCTDichVu = t[0].trim();
                    String maHoaDon = t[1].trim();
                    String maDichVu = t[2].trim();
                    int soLuong = Integer.parseInt(t[3].trim());
                    double donGia = Double.parseDouble(t[4].trim());
                    double thanhTien = Double.parseDouble(t[5].trim());

                    CTDichVu ct = new CTDichVu(maCTDichVu, maHoaDon, maDichVu, soLuong, donGia, thanhTien);

                    ds = Arrays.copyOf(ds, n + 1);
                    ds[n] = ct;
                    n++;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Dữ liệu lỗi: " + line);
                }
            }
        }
        scfile.close();
        isReading = false;
        System.out.println("✅ Đọc file thành công!");
    }
}
