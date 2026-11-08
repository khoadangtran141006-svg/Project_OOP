package danhsach;

import doituong.DichVu;
import java.io.*;
import java.util.*;

public class DSDichVu {

    private int n;
    private DichVu[] dsdv;
    private boolean isReading = false;
    private final Scanner sc = doituong.Input.get();

    public DSDichVu() {
        n = 0;
        dsdv = new DichVu[0];
        try {
            docFile(); // Tự động đọc file
        } catch (IOException e) {
            System.out.println("Lỗi đọc file dichvu.txt: " + e.getMessage());
        }
    }

    public int getN() {
        return n;
    }

    public DichVu[] getDSDichVu() {
        return dsdv;
    }

    public void xemDS() {
        if (n == 0) {
            System.out.println(" Danh sách dịch vụ rỗng!");
            return;
        }

        // Tiêu đề chính
        System.out.println("\n╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       DANH SÁCH DỊCH VỤ                         ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột
        System.out.printf("║ %-10s | %-20s | %-10s | %-10s ║\n", "Mã DV", "Tên DV", "Đơn giá", "Số lượng");
        System.out.println("╠═══════════|══════════════════════|════════════|═════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dsdv[i].xuat(); // Cần đảm bảo DichVu.xuat() in không có \n
            System.out.println(" ║");
        }
        System.out.println("╚═══════════┴══════════════════════┴════════════┴═════════════╝");
    }

    // Thêm dịch vụ
    public void them(DichVu dv) {
        dsdv = Arrays.copyOf(dsdv, n + 1);
        dsdv[n] = new DichVu(dv);
        n++;
        if (!isReading) {
            System.out.println("Đã thêm dịch vụ thành công!");
        }
    }

    // Xóa theo mã
    public void xoa(String ma) {
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (dsdv[i].getMaDichVu().equalsIgnoreCase(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsdv[j] = dsdv[j + 1];
                }
                n--;
                dsdv = Arrays.copyOf(dsdv, n);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Đã xóa dịch vụ có mã " + ma);
        } else {
            System.out.println("Không tìm thấy mã dịch vụ!");
        }
    }

    // Tìm dịch vụ theo mã (trả về đối tượng)
    public DichVu timDichVu(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsdv[i].getMaDichVu().equalsIgnoreCase(ma)) {
                return dsdv[i];
            }
        }
        return null;
    }

    // Tìm và in ra theo mã
    public void timTheoMa(String ma) {
        DichVu dv = timDichVu(ma);
        if (dv == null) {
            System.out.println("Không tìm thấy dịch vụ có mã: " + ma);
        } else {
            System.out.printf("%-10s %-20s %-10s %-10s\n", "Mã DV", "Tên DV", "Đơn giá", "Số lượng");
            dv.xuat();
        }
    }

    // Tìm theo tên
    public void timTheoTen(String ten) {
        boolean found = false;
        System.out.printf("%-10s %-20s %-10s %-10s\n", "Mã DV", "Tên DV", "Đơn giá", "Số lượng");
        for (int i = 0; i < n; i++) {
            if (dsdv[i].getTenDichVu().toLowerCase().contains(ten.toLowerCase())) {
                dsdv[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy dịch vụ có tên chứa: " + ten);
        }
    }

    // Sửa thông tin dịch vụ
    public void sua(String ma) {
        DichVu dv = timDichVu(ma);
        if (dv == null) {
            System.out.println("Không tìm thấy dịch vụ!");
            return;
        }

        int chon;
        do {
            System.out.println("\n=== SỬA DỊCH VỤ " + ma + " ===");
            System.out.println("1. Sửa tên");
            System.out.println("2. Sửa đơn giá");
            System.out.println("3. Sửa số lượng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                chon = -1;
            }

            switch (chon) {
                case 1 -> {
                    System.out.print("Nhập tên mới: ");
                    dv.setTenDichVu(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Nhập đơn giá mới: ");
                    dv.setDonGia(Double.parseDouble(sc.nextLine()));
                }
                case 3 -> {
                    System.out.print("Nhập số lượng mới: ");
                    dv.setSoLuong(Integer.parseInt(sc.nextLine()));
                }
                case 0 ->
                    System.out.println("Đã thoát sửa!");
                default ->
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }

    // Thống kê theo khoảng giá
    public void thongKeTheoKhoangGia(double min, double max) {
        boolean found = false;
        System.out.printf("%-10s %-20s %-10s %-10s\n", "Mã DV", "Tên DV", "Đơn giá", "Số lượng");
        for (int i = 0; i < n; i++) {
            double gia = dsdv[i].getDonGia();
            if (gia >= min && gia <= max) {
                dsdv[i].xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có dịch vụ nào trong khoảng giá này!");
        }
    }

    // ===== GHI FILE =====
    public void ghiFile() throws IOException {
        try (FileWriter fw = new FileWriter("dichvu.txt")) {
            for (int i = 0; i < n; i++) {
                DichVu dv = dsdv[i];
                fw.write(dv.getMaDichVu() + "|" + dv.getTenDichVu() + "|" + dv.getDonGia() + "|" + dv.getSoLuong() + "\n");
            }
            System.out.println("[SUCCESS] Da luu danh sach phong vao file dichvu.txt");
        }
    }

    // ===== ĐỌC FILE =====
    public void docFile() throws IOException {
        File f = new File("dichvu.txt");
        if (!f.exists()) {
            System.out.println("File chưa tồn tại!");
            return;
        }
        isReading = true;
        Scanner scf = new Scanner(f);
        dsdv = new DichVu[0];
        n = 0;
        while (scf.hasNextLine()) {
            String line = scf.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] t = line.split("\\|");
            if (t.length == 4) {
                try {
                    DichVu dv = new DichVu(
                            t[0].trim(),
                            t[1].trim(),
                            Double.parseDouble(t[2].trim()),
                            Integer.parseInt(t[3].trim())
                    );
                    them(dv);
                } catch (Exception e) {
                    System.out.println("Lỗi đọc dòng: " + line);
                }
            }
        }
        scf.close();
        isReading = false;
        System.out.println("Đã đọc danh sách dịch vụ thành công!");
    }
}
