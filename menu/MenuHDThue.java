package menu;

import danhsach.DSHDThue;
import doituong.HDThue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuHDThue {

    private Scanner sc;
    private DSHDThue ds;

    public MenuHDThue() {
        this.sc = new Scanner(System.in);
        this.ds = new DSHDThue();
    }

    public MenuHDThue(DSHDThue dshdt) {
        this.sc = new Scanner(System.in);
        this.ds = dshdt;
    }

    private Date parseDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr.trim());
    }

    public void chay() {

        int chon;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║----------MENU HỢP ĐỒNG THUÊ--------║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ 1. Thêm hợp đồng thuê              ║");
            System.out.println("║ 2. Xóa hợp đồng thuê               ║");
            System.out.println("║ 3. Tìm kiếm hợp đồng thuê          ║");
            System.out.println("║ 4. Sửa hợp đồng thuê               ║");
            System.out.println("║ 5. Hiển thị                        ║");
            System.out.println("║ 6. Thống kê doanh thu              ║");
            System.out.println("║ 7. Ghi file                        ║");
            System.out.println("║ 8. Đọc file                        ║");
            System.out.println("║ 0. Thoát                           ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("0.Thoát");
            System.out.print("Chọn: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> {
                    try {
                        System.out.print("Mã hợp đồng: ");
                        String mahd = sc.nextLine();
                        System.out.print("Mã khách đại diện: ");
                        String makhdaidien = sc.nextLine();
                        System.out.print("Ngày checkin (dd/MM/yyyy): ");
                        Date checkin = sdf.parse(sc.nextLine().trim());
                        System.out.print("Ngày checkout (dd/MM/yyyy): ");
                        Date checkout = sdf.parse(sc.nextLine().trim());
                        System.out.print("Tổng tiền: ");
                        double tongtien = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Mã nhân viên: ");
                        String manv = sc.nextLine();
                        System.out.print("Tổng số phòng thuê: ");
                        int tpt = Integer.parseInt(sc.nextLine());
                        ds.them(new HDThue(mahd, makhdaidien, checkin, checkout, tongtien, manv, tpt));
                    } catch (ParseException e) {
                        System.out.println("Lỗi định dạng ngày tháng: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Nhập mã hợp đồng cần xoá: ");
                    String mahd = sc.nextLine();
                    if (ds.xoa(mahd)) {
                        System.out.println("Đã xoá!");
                    } else {
                        System.out.println("Không tìm thấy");
                    }
                }
                case 3 -> {
                    System.out.print("Nhập mã hợp đồng cần tìm: ");
                    String mahd = sc.nextLine();

                    HDThue hd = ds.timkiem(mahd);

                    if (hd != null) {
                      System.out.println(hd);
                    } else {
                    System.out.println("Không tìm thấy hợp đồng!");
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Nhập mã hợp đồng cần sửa: ");
                        String mahd = sc.nextLine();
                        System.out.print("Nhập mã khách đại diện mới: ");
                        String makhmoi = sc.nextLine();
                        System.out.print("Nhập ngày checkin mới (dd/MM/yyyy): ");
                        Date checkinnew = sdf.parse(sc.nextLine().trim());
                        System.out.print("Nhập ngày checkout mới (dd/MM/yyyy): ");
                        Date checkoutnew = sdf.parse(sc.nextLine().trim());
                        System.out.print("Nhập tổng tiền mới: ");
                        double tongtienmoi = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Nhập mã nhân viên mới: ");
                        String manvmoi = sc.nextLine();
                        System.out.print("Nhập tổng số phòng thuê mới: ");
                        int tpt = Integer.parseInt(sc.nextLine());
                        ds.sua(mahd, new HDThue(mahd, makhmoi, checkinnew, checkoutnew, tongtienmoi, manvmoi, tpt));
                    } catch (ParseException e) {
                        System.out.println("Lỗi định dạng ngày tháng: " + e.getMessage());
                    }
                }
                case 5 ->
                    ds.hienthiDS();
                case 6 -> {
                    double tong = ds.tongDoanhThu();
                    System.out.println("Tổng doanh thu: " + tong);
                }
                case 7 -> {
                    try {
                        ds.ghiFile();
                    } catch (Exception e) {
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }

                }
                case 8 -> {
                    try {
                        ds.docFile();
                    } catch (Exception e) {
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }
                }
            }

        } while (chon != 0);

        sc.close();
    }
}
