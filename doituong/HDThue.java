package doituong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HDThue {

    private String mahd;
    private String makhdaidien;
    private Date checkin;
    private Date checkout;
    private double tongtien;           // primitive, tránh null
    private String manv;
    private int tongphongthue;

    private static final Scanner sc = doituong.Input.get();

    // Hàm thiết lập
    public HDThue() {
        this.tongtien = 0.0;
        this.tongphongthue = 0;
    }

    public HDThue(String mahd, String makhdaidien, Date checkin, Date checkout,
            double tongtien, String manv, int tongphongthue) {
        this.mahd = mahd;
        this.makhdaidien = makhdaidien;
        this.checkin = checkin;
        this.checkout = checkout;
        this.tongtien = tongtien;
        this.manv = manv;
        this.tongphongthue = tongphongthue;
    }

    // Hàm sao chép
    public HDThue(HDThue hd1) {
        this.mahd = hd1.mahd;
        this.makhdaidien = hd1.makhdaidien;
        this.checkin = hd1.checkin;
        this.checkout = hd1.checkout;
        this.tongtien = hd1.tongtien;
        this.manv = hd1.manv;
        this.tongphongthue = hd1.tongphongthue;
    }

    // Getter and Setter
    public String getmahd() {
        return mahd;
    }

    public void setmahd(String mahd) {
        this.mahd = mahd;
    }

    public String getmakhdaidien() {
        return makhdaidien;
    }

    public void setmakhdaidien(String makhdaidien) {
        this.makhdaidien = makhdaidien;
    }

    public Date getcheckin() {
        return checkin;
    }

    public void setcheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getcheckout() {
        return checkout;
    }

    public void setcheckout(Date checkout) {
        this.checkout = checkout;
    }

    public double gettongtien() {
        return tongtien;
    }

    public void settongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getmanv() {
        return manv;
    }

    public void setmanv(String manv) {
        this.manv = manv;
    }

    public int gettongphongthue() {
        return tongphongthue;
    }

    public void settongphongthue(int tongphongthue) {
        this.tongphongthue = tongphongthue;
    }

    // Hàm nhập
    public void nhap() {
        try {
            System.out.print("Nhập mã hợp đồng: ");
            mahd = sc.nextLine().trim();

            System.out.print("Nhập mã khách hàng đại diện: ");
            makhdaidien = sc.nextLine().trim();

            System.out.print("Nhập ngày checkin (dd/MM/yyyy): ");
            String checkinStr = sc.nextLine().trim();
            if (!checkinStr.isEmpty()) {
                checkin = new SimpleDateFormat("dd/MM/yyyy").parse(checkinStr);
            } else {
                checkin = null;
            }

            System.out.print("Nhập ngày checkout (dd/MM/yyyy): ");
            String checkoutStr = sc.nextLine().trim();
            if (!checkoutStr.isEmpty()) {
                checkout = new SimpleDateFormat("dd/MM/yyyy").parse(checkoutStr);
            } else {
                checkout = null;
            }

            // Nhập tổng tiền — dùng parse từ dòng để tránh "newline" problem
            System.out.print("Nhập tổng tiền: ");
            String tongStr = sc.nextLine().trim();
            if (!tongStr.isEmpty()) {
                try {
                    tongtien = Double.parseDouble(tongStr);
                } catch (NumberFormatException ex) {
                    System.out.println("Giá trị tổng tiền không hợp lệ, đặt về 0.0");
                    tongtien = 0.0;
                }
            } else {
                tongtien = 0.0;
            }

            System.out.print("Nhập mã nhân viên: ");
            manv = sc.nextLine().trim();

            System.out.print("Nhập tổng số phòng thuê: ");
            String phongStr = sc.nextLine().trim();
            if (!phongStr.isEmpty()) {
                try {
                    tongphongthue = Integer.parseInt(phongStr);
                } catch (NumberFormatException ex) {
                    System.out.println("Giá trị tổng phòng không hợp lệ, đặt về 0");
                    tongphongthue = 0;
                }
            } else {
                tongphongthue = 0;
            }

        } catch (ParseException e) {
            System.out.println("Lỗi: Sai định dạng ngày (đúng dạng dd/MM/yyyy)");
        } catch (Exception e) {
            System.out.println("Lỗi nhập dữ liệu: " + e.getMessage());
        }
    }

    // Hàm xóa (reset)
    public void xoa() {
        mahd = makhdaidien = manv = null;
        checkin = checkout = null;
        tongphongthue = 0;
        tongtien = 0.0;
    }

    // Hàm xuất
    public void xuat() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Mã hợp đồng: " + (mahd != null ? mahd : ""));
        System.out.println("Mã khách hàng đại diện: " + (makhdaidien != null ? makhdaidien : ""));
        System.out.println("Ngày checkin: " + (checkin != null ? sdf.format(checkin) : ""));
        System.out.println("Ngày checkout: " + (checkout != null ? sdf.format(checkout) : ""));
        System.out.println("Tổng tiền: " + tongtien);
        System.out.println("Mã nhân viên: " + (manv != null ? manv : ""));
        System.out.println("Tổng phòng thuê: " + tongphongthue);
    }

    // Hàm sửa
    public void sua() {
        System.out.println("----- CHỌN MỤC CẦN SỬA -----");
        System.out.println("1. Mã khách đại diện");
        System.out.println("2. Checkin");
        System.out.println("3. Checkout");
        System.out.println("4. Tổng tiền");
        System.out.println("5. Mã nhân viên");
        System.out.println("6. Tổng phòng thuê");
        System.out.print("Chọn: ");
        int chon;
        try {
            chon = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException ex) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        try {
            switch (chon) {
                case 1 -> {
                    System.out.print("Nhập mã khách hàng mới: ");
                    makhdaidien = sc.nextLine().trim();
                }
                case 2 -> {
                    System.out.print("Nhập ngày checkin mới (dd/MM/yyyy): ");
                    String s = sc.nextLine().trim();
                    if (!s.isEmpty()) {
                        checkin = new SimpleDateFormat("dd/MM/yyyy").parse(s); 
                    }else {
                        checkin = null;
                    }
                }
                case 3 -> {
                    System.out.print("Nhập ngày checkout mới (dd/MM/yyyy): ");
                    String s = sc.nextLine().trim();
                    if (!s.isEmpty()) {
                        checkout = new SimpleDateFormat("dd/MM/yyyy").parse(s); 
                    }else {
                        checkout = null;
                    }
                }
                case 4 -> {
                    System.out.print("Nhập tổng tiền mới: ");
                    String s = sc.nextLine().trim();
                    try {
                        tongtien = Double.parseDouble(s);
                    } catch (NumberFormatException ex) {
                        System.out.println("Giá trị không hợp lệ, không thay đổi tổng tiền.");
                    }
                }
                case 5 -> {
                    System.out.print("Nhập mã nhân viên mới: ");
                    manv = sc.nextLine().trim();
                }
                case 6 -> {
                    System.out.print("Nhập tổng phòng thuê mới: ");
                    String s = sc.nextLine().trim();
                    try {
                        tongphongthue = Integer.parseInt(s);
                    } catch (NumberFormatException ex) {
                        System.out.println("Giá trị không hợp lệ, không thay đổi.");
                    }
                }
                default ->
                    System.out.println("Không hợp lệ!");
            }
        } catch (ParseException e) {
            System.out.println("Lỗi định dạng ngày!");
        } catch (Exception e) {
            System.out.println("Lỗi khi sửa: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Hợp đồng: " + (mahd != null ? mahd : "")
                + " | Khách đại diện: " + (makhdaidien != null ? makhdaidien : "")
                + " | Checkin: " + (checkin != null ? sdf.format(checkin) : "")
                + " | Checkout: " + (checkout != null ? sdf.format(checkout) : "")
                + " | Nhân viên: " + (manv != null ? manv : "")
                + " | Tổng tiền: " + tongtien
                + " | Tổng phòng: " + tongphongthue;
    }
}
