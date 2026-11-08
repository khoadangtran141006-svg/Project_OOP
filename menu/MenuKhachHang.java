package menu;
import danhsach.DSKhachHang;
import doituong.KhachHang;
import java.util.Scanner;
public class MenuKhachHang {
    private DSKhachHang dskh;
    private Scanner sc;
    public MenuKhachHang(){
        this.sc= new Scanner(System.in);
        this.dskh= new DSKhachHang();
    }
    public MenuKhachHang(DSKhachHang dskh){
        this.sc=new Scanner(System.in);
        this.dskh=dskh;
    }
    public void chay(){
        
        int chon;

        do{
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║------MENU CHI TIẾT KHÁCH THUÊ------║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ 1. Thêm chi tiết khách thuê        ║");
            System.out.println("║ 2. Xóa chi tiết khách thuê         ║");
            System.out.println("║ 3. Tìm kiếm chi tiết khách thuê    ║");
            System.out.println("║ 4. Hiển thị danh sách khách thuê   ║");
            System.out.println("║ 5. Ghi danh sách                   ║");
            System.out.println("║ 6. Đọc danh sách                   ║");
            System.out.println("║ 0. Thoát                           ║");
            System.out.println("╚════════════════════════════════════╝"); 
            System.out.println("Chọn: ");
            chon=sc.nextInt();
            sc.nextLine();

            switch(chon){
                case 1 ->{
                    KhachHang kt= new KhachHang();
                    dskh.them(kt);
                    System.out.println("Thêm chi tiết khách thuê thành công!");
                }
                case 2 ->{
                    System.out.print("Nhập mã khách thuê: ");
                    String makhach=sc.nextLine();
                    if(dskh.xoa(makhach)){
                        System.out.println("Đã xoá thành công!");
                    }
                    else{
                        System.out.println("Không tìm thấy");
                    }
                }
                case 3 ->{
                    System.out.print("Nhập mã khách cần tìm: \n");
                    String makhach=sc.nextLine();
                    KhachHang kt=dskh.timkiem(makhach);
                    if(kt!=null){
                        System.out.println("Đã tìm thấy");
                        System.out.println(kt);
                    }
                    else{
                        System.out.println("Không tìm thấy");
                    }
                }
                case 4 ->{
                    System.out.println("-----Danh sách chi tiết khách thuê-----");
                    dskh.hienthiDS();
                }
                case 5 ->{
                    try{
                        dskh.ghiFile();
                    }catch( Exception e){
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }
                }
                case 6 ->{
                    try{
                        dskh.docFile();
                    }catch( Exception e){
                        System.out.println("Loi khi luu file: " + e.getMessage());
                    }
                }
            }
        }while(chon!=0);
        sc.close();
    }
}
