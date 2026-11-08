package danhsach;

import doituong.HDThue;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class DSHDThue {

    private HDThue[] ds;       // Mảng lưu danh sách hợp đồng thuê
    private int n;
    private boolean isReading = false;
    private static int MAX = 100;

    public DSHDThue() {
        ds = new HDThue[0]; // Khởi tạo mảng rỗng
        n = 0;
        try {
            docFile(); // Tự động đọc file
        } catch (FileNotFoundException e) {
            System.out.println(" File hopdongthue.txt chưa tồn tại. Tạo danh sách trống.");
        }
    }

    // ======= GETTER / SETTER =======
    public int getN() {
        return n;
    }

    // ✅ Thêm getter để lấy mảng hợp đồng (phục vụ cho HoaDon)
    public HDThue[] getDsHopDong() {
        return ds;
    }

    // ======= THÊM =======
    public void them(HDThue hd) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new HDThue(hd);
        n++;
        if (!isReading) {
            System.out.println(" Đã thêm hợp đồng!");
            try {
                ghiFile();
            } catch (IOException e) {
                System.out.println("ERROR ghi file: " + e.getMessage());
            }
        }
    }

    // ======= XÓA =======
    public boolean xoa(String mahd) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getmahd().equalsIgnoreCase(mahd)) {
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                return true;
            }
        }
        return false;
    }

    // ======= TÌM KIẾM =======
    public HDThue timkiem(String mahd) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getmahd().equalsIgnoreCase(mahd)) {
                return ds[i];
            }
        }
        return null;
    }

    // ======= SỬA =======
    public void sua(String mahd, HDThue hdmoi) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getmahd().equalsIgnoreCase(mahd)) {
                ds[i] = hdmoi;
                return;
            }
        }
    }

    // ======= TÍNH TỔNG DOANH THU =======
    public double tongDoanhThu() {
        double tong = 0.0;
        for (int i = 0; i < n; i++) {
            tong += ds[i].gettongtien();
        }
        return tong;
    }

    // ======= HIỂN THỊ DANH SÁCH =======
    public void hienthiDS() {
        try {
            docFile(); // Tải dữ liệu từ file trước khi xem
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Không tìm thấy file hopdongthue.txt. " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("⚠️ Danh sách hợp đồng thuê rỗng!");
            return;
        }

        // Tiêu đề chính
        System.out.println("\n╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                             DANH SÁCH HỢP ĐỒNG THUÊ                                                             ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột
        System.out.printf("║ %-10s | %-12s | %-12s | %-12s | %-10s | %-15s | %-12s ║\n",
                "Mã HĐ", "Mã KH Đại diện", "Check-in", "Check-out", "Mã NV", "Tổng Tiền", "Tổng Phòng");
        System.out.println("╠═══════════|═══════════════|═══════════════|═══════════════|═════════════|═════════════════|═══════════════╣");

        // In từng hợp đồng
        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            ds[i].xuat();
            System.out.println(" ║");
        }
        System.out.println("╚═══════════┴═══════════════┴═══════════════┴═══════════════┴═════════════┴═════════════════┴═══════════════╝");
    }

    public void ghiFile() throws IOException {
        try (FileWriter fw = new FileWriter("hopdongthue.txt")) {
            for (int i = 0; i < n; i++) {
                HDThue hdt = ds[i];
                fw.write(hdt.getmahd() + "|" + hdt.getmakhdaidien() + "|"
                        + new SimpleDateFormat("dd-MM-yyyy").format(hdt.getcheckin()) + "|"
                        + new SimpleDateFormat("dd-MM-yyyy").format(hdt.getcheckout()) + "|"
                        + hdt.gettongtien() + "|" + hdt.getmanv() + "|" + hdt.gettongphongthue() + "\n");
            }
            if (!isReading) {
                System.out.println("[SUCCESS] Đã lưu danh sách hợp đồng vào file hopdongthue.txt");
            }
        } catch (IOException e) {
            System.out.println("ERROR ghi file: " + e.getMessage());
        }
    }

    // ======= GHI FILE =======
    public void docFile() throws FileNotFoundException {
        File file = new File("hopdongthue.txt");
        if (!file.exists()) {
            System.out.println("File hopdongthue.txt chua ton tai!");
            return;
        }

        isReading = true;
        Scanner scfile = new Scanner(file);
        n = 0;
        ds = new HDThue[0];

        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] tach = line.split("\\|");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (tach.length == 7) {
                try {
                    HDThue hdt = new HDThue(
                            tach[0].trim(),
                            tach[1].trim(),
                            sdf.parse(tach[2].trim()),
                            sdf.parse(tach[3].trim()),
                            Double.parseDouble(tach[4].trim()),
                            tach[5].trim(),
                            Integer.parseInt(tach[6].trim())
                    );
                    them(hdt);
                } catch (ParseException | NumberFormatException e) {
                    System.out.println("Lỗi định dạng dữ liệu ở dòng: " + line);
                }
            }
        }
        scfile.close();
        isReading = false;

    }
}
