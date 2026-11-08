package danhsach;

import doituong.KhachHang;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays; 
import java.util.Scanner;

public class DSKhachHang {

    private Scanner sc = new Scanner(System.in); 
    private KhachHang[] dskhachhang;
    private int n;
    private boolean isReading = false;
 
    public DSKhachHang() {
        this.dskhachhang = new KhachHang[0]; 
        this.n = 0;
        try {
            docFile(); 
        } catch (FileNotFoundException e) {
            System.out.println("File khachhang.txt chưa tồn tại. Tạo danh sách trống.");
        }
    }
    public static boolean maKhachHangTrung(String ma, KhachHang[] danhSach, int n) {
        for (int i = 0; i < n; i++) {
            if (danhSach[i].getmakhach().equalsIgnoreCase(ma)) {
                return true; // mã đã tồn tại
            }
        }
        return false; // mã chưa tồn tại
    }

    public void them() {
        if(dskhachhang==null){
            dskhachhang= new KhachHang[0];
            n=0;
        }
        System.out.println("\n--- Them khach hang moi ---");
        KhachHang khmoi= new KhachHang();

        String ma;
        while(true){
            System.out.println("Nhap ma khach hangL: ");
            ma=sc.nextLine();
            if(maKhachHangTrung(ma, dskhachhang, n)){
                System.out.println("Ma da ton tai, vui long nhap lai!");
            }
            else{
                khachHangMoi.nhap();
                khachHangMoi.setmakhach(ma);
                break;
            }
            dskhachhang=Arrays.copyOf(dskhachhang,n+1);
            dskhachhang[n]=khachHangMoi;
            n++;
            System.out.println("Da them khach hang moi thanh cong");
            ghiFile();
            }
    }

    public boolean xoa(String makhach) {
        int vitri = -1;
        for (int i = 0; i < n; i++) {
            if (dskhachhang[i].getmakhach().equalsIgnoreCase(makhach)) {
                vitri = i;
                break;
            }
        }

        if (vitri == -1) {
            return false;
        }

        // Dịch chuyển phần tử và rút gọn mảng
        for (int i = vitri; i < n - 1; i++) {
            dskhachhang[i] = dskhachhang[i + 1];
        }
        dskhachhang = Arrays.copyOf(dskhachhang, n - 1);
        n--;
        ghiFile(); // Ghi file sau khi xóa
        return true;
    }

    public KhachHang timkiem(String makhach) {
        for (int i = 0; i < n; i++) {
            if (dskhachhang[i].getmakhach().equalsIgnoreCase(makhach)) {
                return dskhachhang[i];
            }
        }
        return null;
    }

    public void hienthiDS() {
        if (n == 0) {
            System.out.println("Danh sách khách hàng rỗng!");
            return;
        }

        // Tiêu đề chính
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       DANH SÁCH KHÁCH HÀNG                            ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột (Giả định KhachHang có các trường: Mã HĐ, Mã Khách, Mã Phòng)
        System.out.printf("║ %-10s | %-15s | %-10s | %-20s ║\n",
                "STT", "Mã HĐ", "Mã Khách", "Mã Phòng");
        System.out.println("╠═══════════|═════════════════|════════════|═════════════════════════╣");

        // In từng khách hàng
        for (int i = 0; i < n; i++) {
            System.out.printf("║ %-10d | ", (i + 1));
            System.out.printf("%-15s | %-10s | %-20s ║\n", 
                    dskhachhang[i].getmaHD(), dskhachhang[i].getmakhach(), dskhachhang[i].getmaphong());
        }
        System.out.println("╚═══════════┴═════════════════┴════════════┴═════════════════════════╝");
    }

    public void ghiFile() {
        try (FileWriter fw = new FileWriter("khachhang.txt")) {
            for (int i = 0; i < n; i++) { // Chỉ ghi n phần tử
                KhachHang kh = dskhachhang[i];
                fw.write(kh.getmaHD() + "|" + kh.getmakhach() + "|" + kh.getmaphong() + "\n");
            }
            if (!isReading) { 
                System.out.println("[SUCCESS] Đã lưu danh sách khách hàng vào file khachhang.txt");
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public void docFile() throws FileNotFoundException {
        File file = new File("khachhang.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File khachhang.txt chưa tồn tại!");
        }

        isReading = true;
        Scanner scfile = new Scanner(file);
        n = 0;
        dskhachhang = new KhachHang[0]; 

        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] tach = line.split("\\|");
            if (tach.length == 3) {
                KhachHang kh = new KhachHang(
                        tach[0].trim(),
                        tach[1].trim(),
                        tach[2].trim()
                );
                this.them(kh);
            }
        }

        scfile.close();
        isReading = false;
    }

    // Getter cần thiết cho MenuThongke
    public int getN() {
        return n;
    }

    public KhachHang[] getDSKH() {
        return dskhachhang;
    }
}