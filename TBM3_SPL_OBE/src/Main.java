
import java.util.Scanner;
/**
 * Kelompok: Skywrath_Mage
 * Program : SPL OBE
 *
 * Nama01: Irvan Hardyanto
 * NPM01: 2016730070
 * Kelas01: A
 *
 * Nama02: Gunawan Christianto
 * NPM02: 2016730011
 * Kelas02: A
 */
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            Sessions s = new Sessions();
            s.doStart();
            
            /// deteksi apakah mau repeat atau tidak.
            System.out.println("\nRepeat? (Yes=y,1; No=n,0)");
            char command = in.next().toLowerCase().charAt(0);
            flag = command == '1' || command == 'y';
        }
        System.out.println("Selesai, terima kasih.");
    }
    
}
