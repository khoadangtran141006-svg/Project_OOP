package danhsach;

import doituong.Phong;
import doituong.PhongThuong;
import doituong.PhongVIP;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSPhong {

    Scanner sc = doituong.Input.get();
    private int n;
    private Phong[] ds;

    public DSPhong() {
        n = 0;
        ds = new Phong[0];
    }

    public DSPhong(int n) {
        this.n = n;
        this.ds = new Phong[n];
        for (int i = 0; i < n; i++) {
            this.ds[i] = new Phong();
        }
    }

    public DSPhong(DSPhong listphong) {
        this.n = listphong.n;
        this.ds = new Phong[listphong.ds.length];
        for (int i = 0; i < n; i++) {
            if (listphong.ds[i] instanceof PhongThuong) {
                this.ds[i] = new PhongThuong((PhongThuong) listphong.ds[i]);
            } else if (listphong.ds[i] instanceof PhongVIP) {
                this.ds[i] = new PhongVIP((PhongVIP) listphong.ds[i]);
            } else {
                this.ds[i] = new Phong(listphong.ds[i]);
            }
        }
    }

    public int getn() {
        return n;
    }

    public Phong[] getds() {
        return ds;
    }

    public void setn(int n) {
        this.n = n;
    }

    public void setds(Phong[] ds) {
        this.ds = ds;
    }

    public void nhap() {
        System.out.println("Nhap so luong phong: ");
        n = sc.nextInt();
        sc.nextLine();
        ds = new Phong[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phong thu " + (i + 1) + " (chon 1: Phong thuong - 2: Phong VIP): ");
            int chon = sc.nextInt();
            sc.nextLine();
            if (chon == 1) {
                ds[i] = new PhongThuong();
            } else {
                ds[i] = new PhongVIP();
            }
            ds[i].nhap();
        }
    }

    // Thêm phòng thường
    public void themPhongThuTS(PhongThuong pt) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new PhongThuong(pt);
        n++;
    }

    // Thêm phòng VIP
    public void themPhongVipTS(PhongVIP pv) {
        ds = Arrays.copyOf(ds, n + 1);
        ds[n] = new PhongVIP(pv);
        n++;
    }

    // Tìm theo mã phòng
    public void timTheoMaPhong() {
        System.out.print("Nhap ma phong can tim: ");
        String ma = sc.nextLine().trim();
        Phong p = timTheoMaPhong(ma);
        if (p != null) {
            System.out.println("\n=== THONG TIN PHONG ===");
            p.xuat();
        } else {
            System.out.println("Khong tim thay phong co ma: " + ma);
        }
    }

    public Phong timTheoMaPhong(String ma) {
        for (int i = 0; i < n; i++) {
            if (ds[i].getmaphong().equals(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    // Thống kê theo loại phòng
    public void thongKeTheoLoai() {
        int thuong = 0, vip = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof PhongThuong) {
                thuong++;
            } else if (ds[i] instanceof PhongVIP) {
                vip++;
            }
        }
        System.out.println("\n===== THONG KE PHONG =====");
        System.out.println("So phong thuong: " + thuong);
        System.out.println("So phong VIP: " + vip);
        System.out.println("Tong cong: " + n);
    }

    // Sửa thông tin phòng
    public void suaPhong() {
        System.out.print("Nhap ma phong can sua: ");
        String ma = sc.nextLine();
        boolean tim = false;
        for (int i = 0; i < n; i++) {
            if (ds[i].getmaphong().equals(ma)) {
                tim = true;
                System.out.println("Da tim thay phong can sua");
                System.out.println("1. Sua ma phong");
                System.out.println("2. Sua ten phong");
                System.out.println("3. Sua day");
                System.out.println("4. Sua so nguoi toi da");
                System.out.println("5. Sua so giuong");
                System.out.println("6. Sua loai phong");
                System.out.println("7. Sua don gia");
                System.out.println("8. Sua trang thai");
                if (ds[i] instanceof PhongThuong) {
                    System.out.println("9. Sua phi dich vu ngoai gio");
                    System.out.println("10. Sua dich vu boi");
                }
                int chon = sc.nextInt();
                sc.nextLine();
                switch (chon) {
                    case 1:
                        System.out.print("Nhap ma phong moi: ");
                        ds[i].setmaphong(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhap ten phong moi: ");
                        ds[i].settenphong(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Nhap day moi: ");
                        ds[i].setday(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Nhap so nguoi toi da moi: ");
                        ds[i].setsonguoitoida(sc.nextInt());
                        sc.nextLine();
                        break;
                    case 5:
                        System.out.print("Nhap so giuong moi: ");
                        ds[i].setsogiuong(sc.nextInt());
                        sc.nextLine();
                        break;
                    case 6:
                        System.out.print("Nhap loai phong moi: ");
                        ds[i].setloaiphong(sc.nextLine());
                        break;
                    case 7:
                        System.out.print("Nhap don gia moi: ");
                        ds[i].setdongia(sc.nextDouble());
                        sc.nextLine();
                        break;
                    case 8:
                        System.out.print("Nhap trang thai moi: ");
                        ds[i].settrangthai(sc.nextLine());
                        break;
                    case 9:
                        if (ds[i] instanceof PhongThuong) {
                            System.out.print("Nhap phi dich vu ngoai gio moi: ");
                            ((PhongThuong) ds[i]).setphisdvngoigio(sc.nextDouble());
                            sc.nextLine();
                        }
                        break;
                    case 10:
                        if (ds[i] instanceof PhongThuong) {
                            System.out.print("Nhap dich vu boi (Co/Khong): ");
                            ((PhongThuong) ds[i]).setdvuboi(sc.nextLine());
                        }
                        break;
                    default:
                        System.out.println("Lua chon khong hop le!");
                        break;
                }
                System.out.println("Da sua thong tin phong thanh cong!");
            }
        }
        if (!tim) {
            System.out.println("Khong tim thay phong co ma: " + ma);
        }
    }

    // Xóa phòng theo mã
    public void xoaMaPhong() {
        System.out.print("Nhap ma phong muon xoa: ");
        String ma = sc.nextLine();
        boolean tim = false;
        for (int i = 0; i < n; i++) {
            if (ds[i].getmaphong().equals(ma)) {
                tim = true;
                for (int j = i; j < n - 1; j++) {
                    ds[j] = ds[j + 1];
                }
                n--;
                ds = Arrays.copyOf(ds, n);
                System.out.println("Da xoa phong co ma: " + ma);
                break;
            }
        }
        if (!tim) {
            System.out.println("Khong tim thay phong co ma: " + ma);
        }
    }

    // Xem danh sách phòng
    public void xemDS() {
        if (n == 0) {
            System.out.println("Danh sach phong trong!");
            return;
        }

        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                        DANH SACH PHONG                                                    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        int demThuong = 0, demVip = 0;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof PhongThuong) {
                demThuong++;
            } else if (ds[i] instanceof PhongVIP) {
                demVip++;
            }
        }

        if (demThuong > 0) {
            System.out.println("\n  ┌───────────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("  │                                       PHONG THUONG                                                    │");
            System.out.println("  ├───────────────────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-12s %-12s %-12s\n",
                    "Ma phong", "Ten phong", "Day", "So nguoi", "So giuong", "Loai", "Don gia", "Trang thai", "DV Ngoai gio / Boi");
            System.out.println("─".repeat(120));
            for (int i = 0; i < n; i++) {
                if (ds[i] instanceof PhongThuong) {
                    ds[i].xuat();
                }
            }
        }

        if (demVip > 0) {
            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│                                           PHONG VIP                                                   │");
            System.out.println("├─────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("%-10s %-15s %-10s %-10s %-10s %-10s %-12s %-12s %-10s %-10s\n",
                    "Ma phong", "Ten phong", "Day", "So nguoi", "So giuong", "Loai", "Don gia", "Trang thai", "Free DV", "Bon tam");
            System.out.println("─".repeat(120));
            for (int i = 0; i < n; i++) {
                if (ds[i] instanceof PhongVIP) {
                    ds[i].xuat();
                }
            }
        }

        System.out.println("\n╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Tong: " + n + " phong (Thuong: " + demThuong + " | VIP: " + demVip + ")");
    }

    // Ghi file
    public void ghiFile() throws IOException {
        File file = new File("dulieuphong.txt");
        FileWriter fw = new FileWriter(file);

        for (Phong p : ds) {
            if (p instanceof PhongThuong) {
                PhongThuong pt = (PhongThuong) p;
                fw.write(pt.getmaphong() + "|" + pt.gettenphong() + "|" + pt.getday() + "|" + pt.getsonguoitoida() + "|"
                        + pt.getsogiuong() + "|" + "PhongThuong" + "|" + pt.getloaiphong() + "|" + pt.getdongia() + "|"
                        + pt.gettrangthai() + "|" + pt.getphisdvngoigio() + "|" + pt.getdvuboi() + "\n");
            } else if (p instanceof PhongVIP) {
                PhongVIP pv = (PhongVIP) p;
                fw.write(pv.getmaphong() + "|" + pv.gettenphong() + "|" + pv.getday() + "|" + pv.getsonguoitoida() + "|"
                        + pv.getsogiuong() + "|" + "PhongVip" + "|" + pv.getloaiphong() + "|" + pv.getdongia() + "|"
                        + pv.gettrangthai() + "|" + pv.getfreedv() + "|" + pv.getbontamcaocap() + "\n");
            } else {
                fw.write(p.getmaphong() + "|" + p.gettenphong() + "|" + p.getday() + "|" + p.getsonguoitoida() + "|"
                        + p.getsogiuong() + "|" + "Phong" + "|" + p.getloaiphong() + "|" + p.getdongia() + "|"
                        + p.gettrangthai() + "\n");
            }
        }
        fw.close();
        System.out.println("[SUCCESS] Da luu danh sach phong vao file dulieuphong.txt");
    }

    // Đọc file
    public void docFile() throws FileNotFoundException {
        File file = new File("dulieuphong.txt");
        if (!file.exists()) {
            System.out.println("File dulieuphong.txt chua ton tai!");
            return;
        }

        Scanner scfile = new Scanner(file);
        n = 0;
        ds = new Phong[0];

        while (scfile.hasNextLine()) {
            String dong = scfile.nextLine().trim();
            if (dong.isEmpty()) {
                continue;
            }

            String[] tach = dong.split("\\|");

            if (tach.length == 11 && tach[5].equals("PhongThuong")) {
                String ma = tach[0], ten = tach[1], day = tach[2], loai = tach[6], trangthai = tach[8];
                int songuoi = Integer.parseInt(tach[3]);
                int sogiuong = Integer.parseInt(tach[4]);
                double dongia = Double.parseDouble(tach[7]);
                double phisdv = Double.parseDouble(tach[9]);
                String dvuboi = tach[10];
                PhongThuong pt = new PhongThuong(ma, ten, day, songuoi, sogiuong, loai, dongia, trangthai, phisdv, dvuboi);
                themPhongThuTS(pt);
            } else if (tach.length == 11 && tach[5].equals("PhongVip")) {
                String ma = tach[0], ten = tach[1], day = tach[2], loai = tach[6], trangthai = tach[8];
                int songuoi = Integer.parseInt(tach[3]);
                int sogiuong = Integer.parseInt(tach[4]);
                double dongia = Double.parseDouble(tach[7]);
                String freedv = tach[9], bontam = tach[10];
                PhongVIP pv = new PhongVIP(ma, ten, day, songuoi, sogiuong, loai, dongia, trangthai, freedv, bontam);
                themPhongVipTS(pv);
            } else if (tach.length == 9 && tach[5].equals("Phong")) {
                String ma = tach[0], ten = tach[1], day = tach[2], loai = tach[6], trangthai = tach[8];
                int songuoi = Integer.parseInt(tach[3]);
                int sogiuong = Integer.parseInt(tach[4]);
                double dongia = Double.parseDouble(tach[7]);
                Phong p = new Phong(ma, ten, day, songuoi, sogiuong, loai, dongia, trangthai);
                ds = Arrays.copyOf(ds, n + 1);
                ds[n++] = p;
            }
        }

        scfile.close();
        System.out.println("[SUCCESS] Da doc danh sach phong!");
    }

}
