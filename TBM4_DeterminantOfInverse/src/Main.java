
import MatrixUtil.*;
import java.util.Scanner;

/**
 *
 * @author hayashi
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("Ukuran matriks?");
            int size = in.nextInt();
            Matrix korban = new Matrix(size, size);
            System.out.println("Input entri baris 1: ");

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    korban.setValue(i, j, in.nextDouble());
                }
                if(!(size <= 1+i))
                    System.out.println("OK! Baris " + (i + 2) + "?");
            }
            
            
            System.out.println("\nPreview matriks:");
            System.out.println(korban + "\n");
            try {
                Matrix inversed = korban.getInverse();
                System.out.println("Bukan matriks singular");
                System.out.println("Berikut matriks inversenya:");
                System.out.println(inversed);
            } catch (InvalidMoveException e) {
                System.out.println("Matriks singular");
                System.out.println("Matriks tidak memiliki inverse.");
            } finally {
                System.out.println("\nRepeat? (Yes=y,1; No=n,0)");
                char command = in.next().toLowerCase().charAt(0);
                flag = command == '1' || command == 'y';
            }
        }
        System.out.println("Selesai, terima kasih.");
    }

}
