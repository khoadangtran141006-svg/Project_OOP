package doituong;

import java.util.Scanner;

public class PhongThuong extends Phong {
    Scanner sc = new Scanner(System.in);
    private double phisdvngoigio;
    private String dvuboi;

    public PhongThuong() {
        super();
        phisdvngoigio = 0;
        dvuboi = "";
    }

    public PhongThuong(String maphong, String tenphong, String day, int songuoitoida, int sogiuong,
                       String loaiphong, double dongia, String trangthai,
                       double phisdvngoigio, String dvuboi) {
        super(maphong, tenphong, day, songuoitoida, sogiuong, loaiphong, dongia, trangthai);
        this.phisdvngoigio = phisdvngoigio;
        this.dvuboi = dvuboi;
    }

    public PhongThuong(PhongThuong pt) {
        super(pt);
        this.phisdvngoigio = pt.phisdvngoigio;
        this.dvuboi = pt.dvuboi;
    }

    public double getphisdvngoigio() { return phisdvngoigio; }
    public String getdvuboi() { return dvuboi; }
    public void setphisdvngoigio(double phisdvngoigio) { this.phisdvngoigio = phisdvngoigio; }
    public void setdvuboi(String dvuboi) { this.dvuboi = dvuboi; }

    @Override
    public void nhap() {
        System.out.println("\n--- NHAP THONG TIN PHONG THUONG ---");
        super.nhap();

        while (true) {
            try {
                System.out.print("Phi su dung dich vu ngoai gio: ");
                phisdvngoigio = Double.parseDouble(sc.nextLine());
                if (phisdvngoigio >= 0) break;
                System.out.println("Phi phai >= 0");
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le!");
            }
        }

        while (true) {
            System.out.print("Co su dung dich vu boi khong (Co/Khong): ");
            dvuboi = sc.nextLine().trim();
            if (!dvuboi.isEmpty()) break;
            System.out.println("Khong duoc de trong!");
        }
        System.out.println("Da nhap thong tin phong thuong thanh cong!");
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("%-12.2f %-10s\n", phisdvngoigio, dvuboi);
    }
}
