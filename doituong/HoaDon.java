
package doituong;

import danhsach.DSDichVu;
import danhsach.DSHDThue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException; // Danh sách chi tiết dịch vụ (bạn sẽ tạo sau)
import java.util.Scanner;

public class HoaDon {
    private Scanner sc = doituong.Input.get();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // ===== THUỘC TÍNH =====
    private String mahoadon;
    private String madichvu;
    private String mahopdong;
    private String makhachhang;
    private String manhanvien;
    private LocalDate ngaylap;
    private double tongtienphong;
    private double tongtiendichvu;
    private double thue;
    private double tongcong;

    // ===== CONSTRUCTOR =====
    public HoaDon() {
        this.mahoadon = "";
        this.madichvu = "";
        this.mahopdong = "";
        this.makhachhang = "";
        this.manhanvien = "";
        this.ngaylap = LocalDate.now();
        this.tongtienphong = 0.0;
        this.tongtiendichvu = 0.0;
        this.thue = 0.0;
        this.tongcong
 = 0.0;
    }

    public HoaDon(String mahoadon, String madichvu, String mahopdong, String makhachhang, 
                  String manhanvien, LocalDate ngaylap, double tongtienphong, 
                  double tongtiendichvu, double thue) {
        this.mahoadon = mahoadon;
        this.madichvu = madichvu;
        this.mahopdong = mahopdong;
        this.makhachhang = makhachhang;
        this.manhanvien = manhanvien;
        this.ngaylap = ngaylap;
        this.tongtienphong = tongtienphong;
        this.tongtiendichvu = tongtiendichvu;
        this.thue = thue;

    }

    public HoaDon(HoaDon hd) {
        this.mahoadon = hd.mahoadon;
        this.madichvu = hd.madichvu;
        this.mahopdong = hd.mahopdong;
        this.makhachhang = hd.makhachhang;
        this.manhanvien = hd.manhanvien;
        this.ngaylap = hd.ngaylap;
        this.tongtienphong = hd.tongtienphong;
        this.tongtiendichvu = hd.tongtiendichvu;
        this.thue = hd.thue;
        this.tongcong
 = hd.tongcong
;
    }

    // ===== GETTERS / SETTERS =====
    public String getmahoadon() { return mahoadon; }
    public String getmadichvu() { return madichvu; }
    public String getmahopdong() { return mahopdong; }
    public String getmakhachhang() { return makhachhang; }
    public String getmanhanvien() { return manhanvien; }
    public LocalDate getngaylap() { return ngaylap; }
    public double gettongtienphong() { return tongtienphong; }
    public double gettongtiendichvu() { return tongtiendichvu; }
    public double getThue() { return thue; }
    public double gettongcong() { return tongcong
; }

    public void setmahoadon(String mahoadon) { this.mahoadon = mahoadon; }
    public void setmadichvu(String madichvu) { this.madichvu = madichvu; }
    public void setmahopdong(String mahopdong) { this.mahopdong = mahopdong; }
    public void setmakhachhang(String makhachhang) { this.makhachhang = makhachhang; }
    public void setmanhanvien(String manhanvien) { this.manhanvien = manhanvien; }
    public void setngaylap(LocalDate ngaylap) { this.ngaylap = ngaylap; }
    public void settongtienphong(double tongtienphong) { this.tongtienphong = tongtienphong; }
    public void settongtiendichvu(double tongtiendichvu) { this.tongtiendichvu = tongtiendichvu; }
    public void setThue(double thue) { this.thue = thue; }
    public void settongcong(double tongcong) { this.tongcong
 = tongcong
; }

    // ===== NHẬP =====
    public void nhap() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║          NHẬP THÔNG TIN HÓA ĐƠN     ║");
        System.out.println("╚════════════════════════════════════╝");

        System.out.print("Mã hóa đơn: ");
        mahoadon = sc.nextLine().trim().toUpperCase();

        System.out.print("Mã dịch vụ: ");
        madichvu = sc.nextLine().trim().toUpperCase();

        System.out.print("Mã hợp đồng: ");
        mahopdong = sc.nextLine().trim().toUpperCase();

        System.out.print("Mã khách hàng (đại diện): ");
        makhachhang = sc.nextLine().trim().toUpperCase();

        System.out.print("Mã nhân viên lập hóa đơn: ");
        manhanvien = sc.nextLine().trim().toUpperCase();

        while (true) {
            try {
                System.out.print("Ngày lập (dd/MM/yyyy): ");
                String nk = sc.nextLine();
                ngaylap = LocalDate.parse(nk, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Định dạng ngày không hợp lệ! Nhập lại!");
            }
        }

        System.out.print("Tổng tiền phòng: ");
        tongtienphong = sc.nextDouble();

        System.out.print("Tổng tiền dịch vụ: ");
        tongtiendichvu = sc.nextDouble();

        System.out.print("Thuế (%): ");
        thue = sc.nextDouble();
        sc.nextLine(); // clear buffer

        tinhtongcong
();
    }

    // ===== TÍNH TỔNG =====
    public void tinhTongCong(DSHDThue dshd, DSDichVu dsctdv) {
        // Tính tổng tiền phòng từ danh sách hợp đồng
        tongtienphong = 0;
        if (dshd != null) {
            for (int i = 0; i < dshd.getN(); i++) {
                if (dshd.getDsHopDong()[i].getmahd().equalsIgnoreCase(this.mahoadon)) {
                    tongtienphong += dshd.getDsHopDong()[i].gettongtien();
                }
            }
        }
        tinhtongcong();
    }

    // ===== TÍNH TỔNG CỘNG =====
public void tinhtongcong() {
    tongcong = tongtienphong + tongtiendichvu + (tongtienphong + tongtiendichvu) * (thue / 100);
}

    // ===== XUẤT =====
    public void xuat() {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-12s %-12.2f %-12.2f %-8.2f %-12.2f\n",
                mahoadon, madichvu, mahopdong, makhachhang, manhanvien,
                ngaylap.format(formatter), tongtienphong, tongtiendichvu, thue, tongcong
        );
    }
}
