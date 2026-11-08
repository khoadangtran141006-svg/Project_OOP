package doituong;
import java.util.*;

public class CTThue{
        private String mahd;
        private String maphong;
        private int songuoi;
        Scanner sc= doituong.Input.get();
        // Hàm thiết lập không tham số
        public CTThue(){}

        // Hàm thiết lập có tham số
        public CTThue(String maHD, String maPhong, int soNguoi){
            this.mahd=maHD;
            this.maphong=maPhong;
            this.songuoi=soNguoi;
        }

        // Hàm thiết lập sao chép
        public CTThue(CTThue ct){
            this.mahd=ct.mahd;
            this.maphong=ct.maphong;
            this.songuoi=ct.songuoi;
        }

        // Hàm xoá
        public void xoa(){
            mahd=maphong=null;
            songuoi=0;
        }

        // Getters and setters
        public String getmahd(){
            return mahd;
        }
        public void setmahd(String maHD){
            this.mahd=maHD;
        }
        public String getmaphong(){
            return maphong;
        }
        public void setmaphong(String maPhong){
            this.maphong=maPhong;
        }
        public int getsonguoi(){
            return songuoi;
        }
        public void setsonguoi(int soNguoi){
            this.songuoi=soNguoi;
        }
        @Override
        public String toString(){
            return mahd + " - " + maphong + " - " +songuoi;
        }
        // Hàm nhập và xuất
        public void nhap(){
            System.out.print("Nhập mã hợp đồng: ");
            mahd=sc.nextLine();
            System.out.print("Nhập mã phòng: ");
            maphong=sc.nextLine();
            System.out.print("Nhập số người: ");
            songuoi=sc.nextInt();
        }
        public void xuat(){
            System.out.println("Mã hợp đồng: " +mahd);
            System.out.println("Mã phòng: " +maphong);  
            System.out.println("Số người: " +songuoi);  
        }
    }