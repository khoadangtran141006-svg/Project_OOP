package danhsach;

import doituong.HoaDon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class DSHoaDon {

    private Scanner sc = doituong.Input.get();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int n;
    private HoaDon[] dshd;
    private DSCTHoaDon dsCTHD; // thêm dòng này vào phần khai báo thuộc tính

    public void setDSCTHD(DSCTHoaDon dsCTHD) {
        this.dsCTHD = dsCTHD;
    }

    public DSCTHoaDon getDSCTHD() {
        return dsCTHD;
    }

    // ===== CONSTRUCTOR =====
    public DSHoaDon() {
        n = 0;
        dshd = new HoaDon[0];
    }

    public DSHoaDon(int n, HoaDon[] dshd) {
        this.n = n;
        this.dshd = new HoaDon[n];
        for (int i = 0; i < n; i++) {
            this.dshd[i] = new HoaDon(dshd[i]);
        }
    }

    public DSHoaDon(DSHoaDon listHD) {
        this.n = listHD.n;
        this.dshd = new HoaDon[listHD.dshd.length];
        for (int i = 0; i < n; i++) {
            this.dshd[i] = new HoaDon(listHD.dshd[i]);
        }
    }

    // ===== GETTER / SETTER VIẾT THƯỜNG =====
    public int getn() {
        return n;
    }

    public HoaDon[] getDSHD() {
        return dshd;
    }

    public void setn(int n) {
        this.n = n;
    }

    public void setDSHD(HoaDon[] dshd) {
        this.dshd = dshd;
    }

    // ===== NHẬP DANH SÁCH =====
    public void nhap() {
        System.out.print("Nhập số lượng hóa đơn: ");
        n = sc.nextInt();
        sc.nextLine();
        dshd = new HoaDon[n];
        for (int i = 0; i < n; i++) {
            System.out.println("=== Hóa đơn thứ " + (i + 1) + " ===");
            dshd[i] = new HoaDon();
            dshd[i].nhap();
        }
    }

    // ===== XUẤT DANH SÁCH =====
    public void xuat() {
        // Sửa lại tiêu đề và định dạng bảng cho chuẩn
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                       DANH SÁCH HÓA ĐƠN                                                               ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        // Tiêu đề cột
        System.out.printf("║ %-8s | %-8s | %-8s | %-8s | %-8s | %-10s | %-12s | %-12s | %-8s | %-12s ║\n",
                "Mã HD", "Mã DV", "Mã HĐong", "Mã KH", "Mã NV", "Ngày Lập",
                "Tiền Phòng", "Tiền DV", "Thuế", "Tổng Cộng");
        System.out.println("╠═════════|═════════|═════════|═════════|═════════|═══════════|═════════════|═════════════|═════════|═════════════╣");

        for (int i = 0; i < n; i++) {
            System.out.print("║ ");
            dshd[i].xuat(); // dùng hàm xuat() của HoaDon
            System.out.println(" ║");
        }
        System.out.println("╚═════════┴═════════┴═════════┴═════════┴═════════┴═══════════┴═════════════┴═════════════┴═════════┴═════════════╝");
    }

    // ===== THÊM HÓA ĐƠN =====
    public void themHoaDon(HoaDon hd) {
        dshd = Arrays.copyOf(dshd, n + 1);
        dshd[n] = new HoaDon(hd);
        n++;
        System.out.println("✅ Đã thêm hóa đơn!");
    }

    // ===== KIỂM TRA MÃ HÓA ĐƠN =====
    public boolean tonTaiMaHoaDon(String maHoaDon) {
        for (HoaDon hd : dshd) {
            if (hd.getmahoadon().equalsIgnoreCase(maHoaDon)) {
                return true;
            }
        }
        return false;
    }

    // ===== TÌM HÓA ĐƠN =====
    public void timHoaDon(String maHoaDon) {
        boolean tim = false;
        for (HoaDon hd : dshd) {
            if (hd.getmahoadon().equalsIgnoreCase(maHoaDon)) {
                System.out.println("=== THÔNG TIN HÓA ĐƠN TÌM THẤY ===");
                hd.xuat();
                tim = true;
            }
        }
        if (!tim) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + maHoaDon);
        }
    }

    // ===== SỬA HÓA ĐƠN =====
    public void suaHoaDon(String maHoaDon) {
        boolean tim = false;
        for (HoaDon hd : dshd) {
            if (hd.getmahoadon().equalsIgnoreCase(maHoaDon)) {
                tim = true;
                int chon;
                do {
                    System.out.println("Chọn thông tin cần sửa:");
                    System.out.println("1. Mã hóa đơn");
                    System.out.println("2. Mã dịch vụ");
                    System.out.println("3. Mã hợp đồng");
                    System.out.println("4. Mã khách hàng");
                    System.out.println("5. Mã nhân viên");
                    System.out.println("6. Ngày lập");
                    System.out.println("7. Tổng tiền phòng");
                    System.out.println("8. Tổng tiền dịch vụ");
                    System.out.println("9. Thuế");
                    System.out.println("0. Thoát");
                    System.out.print("Chọn: ");
                    chon = sc.nextInt();
                    sc.nextLine();
                    switch (chon) {
                        case 1 -> {
                            System.out.print("Nhập mã hóa đơn mới: ");
                            hd.setmahoadon(sc.nextLine().trim());
                        }
                        case 2 -> {
                            System.out.print("Nhập mã dịch vụ mới: ");
                            hd.setmadichvu(sc.nextLine().trim());
                        }
                        case 3 -> {
                            System.out.print("Nhập mã hợp đồng mới: ");
                            hd.setmahopdong(sc.nextLine().trim());
                        }
                        case 4 -> {
                            System.out.print("Nhập mã khách hàng mới: ");
                            hd.setmakhachhang(sc.nextLine().trim());
                        }
                        case 5 -> {
                            System.out.print("Nhập mã nhân viên mới: ");
                            hd.setmanhanvien(sc.nextLine().trim());
                        }
                        case 6 -> {
                            System.out.print("Nhập ngày lập mới (dd/MM/yyyy): ");
                            String ngay = sc.nextLine().trim();
                            hd.setngaylap(LocalDate.parse(ngay, formatter));
                        }
                        case 7 -> {
                            System.out.print("Nhập tổng tiền phòng mới: ");
                            hd.settongtienphong(sc.nextDouble());
                            sc.nextLine();
                        }
                        case 8 -> {
                            System.out.print("Nhập tổng tiền dịch vụ mới: ");
                            hd.settongtiendichvu(sc.nextDouble());
                            sc.nextLine();
                        }
                        case 9 -> {
                            System.out.print("Nhập thuế mới (%): ");
                            hd.setThue(sc.nextDouble());
                            sc.nextLine();
                        }
                        case 0 ->
                            System.out.println("✅ Thoát sửa hóa đơn");
                        default ->
                            System.out.println("❌ Lựa chọn không hợp lệ!");
                    }
                    hd.tinhtongcong();
                } while (chon != 0);
                break;
            }
        }
        if (!tim) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + maHoaDon);
        }
    }

    // ===== XÓA HÓA ĐƠN =====
    public void xoaHoaDon(String maHoaDon) {
        boolean tim = false;
        for (int i = 0; i < n; i++) {
            if (dshd[i].getmahoadon().equalsIgnoreCase(maHoaDon)) {
                for (int j = i; j < n - 1; j++) {
                    dshd[j] = dshd[j + 1];
                }
                n--;
                dshd = Arrays.copyOf(dshd, n);
                tim = true;
                System.out.println("✅ Đã xóa hóa đơn có mã: " + maHoaDon);
                break;
            }
        }
        if (!tim) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + maHoaDon);
        }
    }

    // Kiểm tra mã hóa đơn có tồn tại trong danh sách hay không
    public boolean tonTaiMaHD(String maHoaDon) {
        for (int i = 0; i < n; i++) {
            if (dshd[i].getmahoadon().equalsIgnoreCase(maHoaDon)) {
                return true; // tìm thấy
            }
        }
        return false; // không tìm thấy
    }

// ===== XEM DANH SÁCH HÓA ĐƠN =====
    public void xemDS() {
        try {
            docFile(); // Tải dữ liệu từ file
        } catch (IOException e) {
            System.out.println("ERROR đọc file: " + e.getMessage());
            return;
        }

        if (n == 0) {
            System.out.println("❌ Danh sách hóa đơn trống!");
            return;
        }

        xuat(); // Gọi hàm xem() đã định dạng ở trên
    }

// ===== THỐNG KÊ HÓA ĐƠN THEO KHOẢNG TIỀN =====
    public void thongKeTheoKhoangTien() {
        if (n == 0) {
            System.out.println("❌ Danh sách hóa đơn trống!");
            return;
        }

        System.out.print("Nhập số tiền tối thiểu: ");
        double min = sc.nextDouble();
        System.out.print("Nhập số tiền tối đa: ");
        double max = sc.nextDouble();
        sc.nextLine();

        boolean found = false;
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          DANH SÁCH HÓA ĐƠN TRONG KHOẢNG TIỀN                                         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-12s %-12s %-12s %-8s %-12s\n",
                "MãHD", "MãDV", "MãHDong", "MãKH", "MãNV", "NgàyLập",
                "TiềnPhòng", "TiềnDV", "Thuế", "TổngCộng");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (HoaDon hd : dshd) {
            if (hd.gettongcong() >= min && hd.gettongcong() <= max) {
                hd.xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ Không có hóa đơn nào nằm trong khoảng tiền này!");
        }

        System.out.println("--------------------------------------------------------------------------------------------------------");
    }

    // ===== GHI FILE =====
    public void ghiFile() throws IOException {
        FileWriter fw = new FileWriter("hoadon.txt");
        for (HoaDon hd : dshd) {
            fw.write(hd.getmahoadon() + "|" + hd.getmadichvu() + "|" + hd.getmahopdong() + "|"
                    + hd.getmakhachhang() + "|" + hd.getmanhanvien() + "|"
                    + hd.getngaylap().format(formatter) + "|" + hd.gettongtienphong() + "|"
                    + hd.gettongtiendichvu() + "|" + hd.getThue() + "|" + hd.gettongcong() + "\n");
        }
        fw.close();
        System.out.println(" Đã ghi danh sách hóa đơn vào file!");
    }

    // Thống kê các hóa đơn của khách hàng theo mã
    public void thongKeTheoKH(String maKhachHang) {
        boolean tim = false;
        System.out.println("\nHÓA ĐƠN CỦA KHÁCH HÀNG " + maKhachHang + ":");
        System.out.printf("%-10s %-10s %-12s %-12s %-12s\n",
                "MãHD", "MãDV", "NgàyLập", "TổngTiền", "Thuế");
        for (HoaDon hd : dshd) {
            if (hd.getmakhachhang().equalsIgnoreCase(maKhachHang)) {
                hd.xuat();
                tim = true;
            }
        }
        if (!tim) {
            System.out.println("Không có hóa đơn cho khách hàng này!");
        }
    }

    // ===== ĐỌC FILE =====
    public void docFile() throws IOException {
        File file = new File("hoadon.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("File hoadon.txt không tồn tại!");
        }
        Scanner scfile = new Scanner(file);
        n = 0;
        dshd = new HoaDon[0];

        while (scfile.hasNextLine()) {
            String line = scfile.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] t = line.split("\\|");
            if (t.length == 10) {
                try {
                    HoaDon hd = new HoaDon(
                            t[0].trim(), t[1].trim(), t[2].trim(), t[3].trim(), t[4].trim(),
                            LocalDate.parse(t[5].trim(), formatter),
                            Double.parseDouble(t[6].trim()),
                            Double.parseDouble(t[7].trim()),
                            Double.parseDouble(t[8].trim())
                    );
                    hd.settongcong(Double.parseDouble(t[9].trim()));
                    dshd = Arrays.copyOf(dshd, n + 1);
                    dshd[n++] = hd;
                } catch (Exception e) {
                    System.out.println("Lỗi định dạng dữ liệu ở dòng: " + line);
                }
            }
        }
        scfile.close();
    }
}
