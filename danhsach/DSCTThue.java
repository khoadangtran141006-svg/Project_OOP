package danhsach;

import doituong.CTThue;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DSCTThue {

    private CTThue[] dsctthue;
    private int n;
    private static final int MAX = 100;
    private boolean isReading = false;

    public DSCTThue() {
        dsctthue = new CTThue[0]; // Mảng động
        n = 0;
        try {
            docFile(); // Tự động đọc file
        } catch (IOException e) {
            System.out.println("Lỗi đọc file ctthue.txt: " + e.getMessage());
        }
    }

    public void them(CTThue ctthue) {
        dsctthue = Arrays.copyOf(dsctthue, n + 1);
        dsctthue[n] = new CTThue(ctthue); // Khởi tạo copy
        n++;
        if (!isReading) {
            System.out.println(" Đã thêm chi tiết thuê!");
            try {
                ghiFile();
            } catch (IOException e) {
                System.out.println("ERROR ghi file: " + e.getMessage());
            }
        }
    }

    public boolean xoa(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dsctthue[i].getmahd().equalsIgnoreCase(mahd)) {
                for (int j = i; j < n - 1; j++) {
                    dsctthue[j] = dsctthue[j + 1];
                }
                n--;
                return true;
            }
        }

        return false;
    }

    public CTThue timkiem(String maphong) {
        for (int i = 0; i < n; i++) {
            if (dsctthue[i].getmaphong().equalsIgnoreCase(maphong)) {
                return dsctthue[i];
            }
        }
        return null;
    }

    public void hienthiDS() {
        try {
            docFile();
        } catch (IOException e) {
            System.out.println("ERROR đọc file: " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("⚠️ Danh sách chi tiết thuê rỗng!");
            return;
        }

        // Tiêu đề chính
        System.out.println("\n╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   DANH SÁCH CHI TIẾT THUÊ                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột
        System.out.printf("║ %-10s | %-10s | %-10s ║\n", "Mã HĐ", "Mã Phòng", "Số người");
        System.out.println("╠═══════════|════════════|════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dsctthue[i].xuat();
            System.out.println(" ║");
        }
        System.out.println("╚═══════════┴════════════┴════════════╝");
    }

    public void ghiFile() throws IOException {
        try (FileWriter fw = new FileWriter("ctthue.txt")) {
            for (int i = 0; i < n; i++) {
                CTThue ctthue = dsctthue[i];
                fw.write(ctthue.getmahd() + "|" + ctthue.getmaphong() + "|" + ctthue.getsonguoi() + "\n");
            }
            if (!isReading) {
                System.out.println("[SUCCESS] Đã lưu danh sách chi tiết thuê vào file ctthue.txt");
            }
        } catch (IOException e) {
            System.out.println("ERROR ghi file: " + e.getMessage());
        }
    }

    // ======= ĐỌC FILE =======
    public void docFile() throws IOException {
        File file = new File("ctthue.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File ctthue.txt chua ton tai!");
        }
        isReading = true;
        Scanner scf = new Scanner(file);
        n = 0;
        dsctthue = new CTThue[0];
        while (scf.hasNextLine()) {
            String line = scf.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] tach = line.split("\\|");
            if (tach.length == 3) {
                try {
                    String mahd = tach[0].trim(), maphong = tach[1].trim();
                    int songuoi = Integer.parseInt(tach[2].trim());
                    CTThue ctthue = new CTThue(mahd, maphong, songuoi);
                    them(ctthue);
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi định dạng số ở dòng: " + line);
                }
            }
        }
        scf.close();
        isReading = false;
        // System.out.println("[SUCCESS] Đã đọc danh sách chi tiết thuê!"); // Bỏ in ra ở đây
    }
}
