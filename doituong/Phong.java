package doituong;

import java.util.Scanner;

public class Phong {
    Scanner sc = doituong.Input.get();
    protected String maphong;
    protected String tenphong;
    protected String day;
    protected int songuoitoida;
    protected int sogiuong;
    protected String loaiphong;
    protected double dongia;
    protected String trangthai;

    public Phong() {
        maphong = "";
        tenphong = "";
        day = "";
        songuoitoida = 0;
        sogiuong = 0;
        loaiphong = "";
        dongia = 0;
        trangthai = "";
    }

    public Phong(String maphong, String tenphong, String day, int songuoitoida, int sogiuong,
                 String loaiphong, double dongia, String trangthai) {
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.day = day;
        this.songuoitoida = songuoitoida;
        this.sogiuong = sogiuong;
        this.loaiphong = loaiphong;
        this.dongia = dongia;
        this.trangthai = trangthai;
    }

    public Phong(Phong p) {
        this.maphong = p.maphong;
        this.tenphong = p.tenphong;
        this.day = p.day;
        this.songuoitoida = p.songuoitoida;
        this.sogiuong = p.sogiuong;
        this.loaiphong = p.loaiphong;
        this.dongia = p.dongia;
        this.trangthai = p.trangthai;
    }

    public String getmaphong() { return maphong; }
    public String gettenphong() { return tenphong; }
    public String getday() { return day; }
    public int getsonguoitoida() { return songuoitoida; }
    public int getsogiuong() { return sogiuong; }
    public String getloaiphong() { return loaiphong; }
    public double getdongia() { return dongia; }
    public String gettrangthai() { return trangthai; }

    public void setmaphong(String maphong) { this.maphong = maphong; }
    public void settenphong(String tenphong) { this.tenphong = tenphong; }
    public void setday(String day) { this.day = day; }
    public void setsonguoitoida(int songuoitoida) { this.songuoitoida = songuoitoida; }
    public void setsogiuong(int sogiuong) { this.sogiuong = sogiuong; }
    public void setloaiphong(String loaiphong) { this.loaiphong = loaiphong; }
    public void setdongia(double dongia) { this.dongia = dongia; }
    public void settrangthai(String trangthai) { this.trangthai = trangthai; }

    public void nhap() {
        while (true) {
            System.out.print("Ma phong (VD: P101): ");
            maphong = sc.nextLine().trim().toUpperCase();
            if (!maphong.isEmpty()) break;
            System.out.println("Ma phong khong duoc de trong!");
        }

        while (true) {
            System.out.print("Ten phong: ");
            tenphong = sc.nextLine().trim();
            if (!tenphong.isEmpty()) break;
            System.out.println("Ten phong khong duoc de trong!");
        }

        while (true) {
            System.out.print("Day: ");
            day = sc.nextLine().trim().toUpperCase();
            if (!day.isEmpty()) break;
            System.out.println("Day khong duoc de trong!");
        }

        while (true) {
            try {
                System.out.print("So nguoi toi da: ");
                songuoitoida = Integer.parseInt(sc.nextLine());
                if (songuoitoida > 0) break;
                System.out.println("So nguoi toi da phai > 0!");
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }

        while (true) {
            try {
                System.out.print("So giuong: ");
                sogiuong = Integer.parseInt(sc.nextLine());
                if (sogiuong > 0) break;
                System.out.println("So giuong phai > 0!");
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }

        while (true) {
            System.out.print("Loai phong: ");
            loaiphong = sc.nextLine().trim();
            if (!loaiphong.isEmpty()) break;
            System.out.println("Loai phong khong duoc de trong!");
        }

        while (true) {
            try {
                System.out.print("Don gia: ");
                dongia = Double.parseDouble(sc.nextLine());
                if (dongia > 0) break;
                System.out.println("Don gia phai > 0!");
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }

        System.out.println("\n--- Chon trang thai phong ---");
        System.out.println("1. Trong");
        System.out.println("2. Da dat");
        System.out.println("3. Dang su dung");
        while (true) {
            try {
                System.out.print("Chon (1-3): ");
                int chon = Integer.parseInt(sc.nextLine());
                if (chon == 1) {
                    trangthai = "Trong";
                    break;
                } else if (chon == 2) {
                    trangthai = "Da dat";
                    break;
                } else if (chon == 3) {
                    trangthai = "Dang su dung";
                    break;
                } else {
                    System.out.println("Vui long chon tu 1-3!");
                }
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }
    }

    public void xuat() {
        System.out.printf("%-10s %-15s %-10s %-10d %-10d %-10s %-12.2f %-12s\n",
                maphong, tenphong, day, songuoitoida, sogiuong, loaiphong, dongia, trangthai);
    }
}
