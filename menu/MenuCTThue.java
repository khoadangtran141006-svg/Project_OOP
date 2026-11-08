package menu;
import danhsach.DSCTThue;
import doituong.CTThue;
import java.util.Scanner;
public class MenuCTThue {
    private Scanner sc;
    private DSCTThue dsctthue;
    public MenuCTThue(){
        this.sc=new Scanner(System.in);
        this.dsctthue=new DSCTThue();
    }
    public MenuCTThue(DSCTThue dsctthue){
        this.sc= new Scanner(System.in);
        this.dsctthue=dsctthue;
    }
    public void chay(){
        
        int chon;

        do{
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║-----MENU QUẢN LÝ CHI TIẾT THUÊ-----║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ 1. Thêm chi tiết thuê              ║");
            System.out.println("║ 2. Xóa chi tiết thuê               ║");
            System.out.println("║ 3. Tìm kiếm chi tiết thuê          ║");
            System.out.println("║ 4. Hiển thị chi tiết thuê          ║");
            System.out.println("║ 5. Ghi file                        ║");
            System.out.println("║ 6. Đọc file                        ║");
            System.out.println("║ 0. Thoát                           ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.print("Chọn: ");
            chon=sc.nextInt();
            sc.nextLine();

            switch(chon){
                case 1 ->{
                    CTThue ctthue= new CTThue();
                    ctthue.nhap();
                    dsctthue.them(ctthue);
                    System.out.println("Đã thêm chi tiết thuê thành công!");
                }
                case 2 ->{
                    System.out.print("Nhập mã hợp đồng cần xoá: ");
                    String mahd=sc.nextLine();
                    if(dsctthue.xoa(mahd)){
                        System.out.println("Đã xoá!");
                    }
                    else{
                        System.out.println("Không tìm thấy");
                    }
                }
                case 3 ->{
                    System.out.print("Nhập mã hợp đồng cần tìm kiếm: ");
                    String mahd=sc.nextLine();
                    CTThue ctthue=dsctthue.timkiem(mahd);
                    if(ctthue!=null){
                        System.out.println(ctthue);
                        System.out.println("Đã tìm thấy!");
                    }
                    else{
                        System.out.println("Không tìm thấy");
                    }
                }
                case 4 ->{
                    System.out.print("-----Danh sách chi tiết thuê-----\n");
                    dsctthue.hienthiDS();
                }
                case 5 -> {
                    try{
                        dsctthue.ghiFile();
                    } catch(Exception e){
                        System.out.println("Lỗi khi ghi file");
                    }
                }
                
                case 6 -> {
                    try{
                        dsctthue.docFile();
                    } catch(Exception e){
                        System.out.println("Lỗi khi đọc file");
                    }
                }
            } 
        } while(chon!=0); 
        sc.close();        
    }
}
