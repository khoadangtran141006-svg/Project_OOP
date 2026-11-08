package doituong;

import java.util.Scanner;

public class Nhacungcap {

    private String mancc;
    private String tenncc;
    private String sdt;
    private String email;
    private String diachi;

    public Nhacungcap() {
    }

    public Nhacungcap(String mancc, String tenncc, String sdt, String email, String diachi) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
    }

    public Nhacungcap(Nhacungcap ncc) {
        this.mancc = ncc.mancc;
        this.tenncc = ncc.tenncc;
        this.sdt = ncc.sdt;
        this.email = ncc.email;
        this.diachi = ncc.diachi;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ma nha cung cap: ");
        mancc = sc.nextLine();
        System.out.println("ten nha cung cap: ");
        tenncc = sc.nextLine();
        System.out.println("so dien thoai: ");
        sdt = sc.nextLine();
        System.out.println("email ");
        email = sc.nextLine();
        System.out.println("dia chi: ");
        diachi = sc.nextLine();
    }

    public void xuat() {
        System.out.printf("%-10s %-15s %-12s %-20s %-15s\n",
                mancc, tenncc, sdt, email, diachi);
    }

    // getter
    public String getMancc() {
        return mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getDiachi() {
        return diachi;
    }

    // setter
    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
