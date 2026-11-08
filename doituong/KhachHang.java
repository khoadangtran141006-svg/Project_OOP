package doituong;
import java.util.*;

public class KhachHang{
    private String mahd;
    private String makhach;
    private String maphong;
    Scanner sc = doituong.Input.get();

    // Hàm thiết lập không tham số
    public KhachHang(){}

    //Hàm thiết lập có tham số
    public KhachHang(String mahd, String makhach, String maphong){
        this.mahd=mahd;
        this.makhach=makhach;
        this.maphong=maphong;
    }
    //Hàm thiết lập sao chép
    public KhachHang(KhachHang kh){
        this.mahd=kh.mahd;
        this.makhach=kh.makhach;    
        this.maphong=kh.maphong;
    }
    //Getters and Setters
    public String getmaHD(){
        return mahd;
    }
    public void setmaHD(String maHD){
        this.mahd=maHD;
    }
    public String getmakhach(){
        return makhach;
    }
    public void setmakhach(String makhach){
        this.makhach=makhach;
    }
    public String getmaphong(){
        return maphong;
    }
    public void setmaphong(String maphong){
        this.maphong=maphong;
    }
    public void nhap(){
        System.out.print("Nhập mã hợp đồng:");
        mahd=sc.nextLine();
        System.out.print("Nhập mã khách: ");
        makhach=sc.nextLine();
        System.out.print("Nhập mã phòng: ");
        maphong=sc.nextLine();
    }
    public void xoa(){
        makhach=maphong=mahd=null;
    }
     @Override
    public String toString(){
        return "CTKhachThue{maHD='"+mahd+"',maKhach='"+makhach+"',maPhong='"+maphong+"'}";
    }
    public void sua(){
        System.out.print("1.Mã khách\n2.Mã phòng");
        int chon=sc.nextInt();
        sc.nextLine();
        if(chon==1){
            System.out.print("Nhập mã khách mới: ");
            makhach=sc.nextLine();
        }
        else if(chon==2){
            System.out.print("Nhập mã phòng mới: ");
            maphong=sc.nextLine();
        }
    }
     
}
