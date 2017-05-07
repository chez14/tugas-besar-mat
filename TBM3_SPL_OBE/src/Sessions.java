
import CramerAlgo.CramerMatrix;
import CramerAlgo.InfiniteSolutionException;
import CramerAlgo.UnsolveableEquationsException;
import MatrixUtil.InvalidMoveException;
import MatrixUtil.Matrix;
import OBEUtil.OBEMatrix;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class Sessions {

    Matrix ma;
    OBEMatrix om;
    CramerMatrix cm;
    double[] constants;

    String[] menu = {
        "1. Tukar baris",
        "2. Jumlah kelipatan baris lain",
        "3. Kali Skalar",
        "4. Keluar dan berhenti"
    };

    /**
     * Memulai sesi untuk soal ini.
     */
    public void doStart() {
        // tanya ukutan matrikx
        System.out.print("Banyak persamaan dan variabel? ");
        int size = Main.in.nextInt();
        fillMatrix(size);
        System.out.println(om);

        // Crammer detection:
        if (!doCramer()) {
            return;
        }

        while (true) {
            try {
                double[] hasil = om.getSolution();
                System.out.println("Solusi tunggal: ");
                int x = 1;
                for (double h : hasil) {
                    System.out.printf("    x%d = %.4f", x++, h);
                }
                return;
            } catch (Exception e) {
                int solu = om.detectSolution();
                if (solu == 0) {
                    System.out.println("Tidak terdapat solusi.");
                    return;
                } else if (solu == -1) {
                    System.out.println("Solusi tak hingga.");
                    return;
                }
            }
            try {
                pilihMenu();
            } catch (Exception e) {
                System.out.println("Ups! terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    /**
     * Lakukan pengecekan dengan camer.
     *
     * @return
     */
    public boolean doCramer() {
        try {
            double[] solusi;
            solusi = cm.getSolution();
            System.out.println("Solusi tunggal: ");
            int x = 1;
            for (double h : solusi) {
                System.out.printf("    x%d = %.4f", x++, h);
            }
        } catch (UnsolveableEquationsException e) {
            System.out.println("Persamaan tidak memiliki solusi.");
        } catch (InfiniteSolutionException e) {
            System.out.println("Persamaan memiliki solusi banyak tak hingga.");
        } catch (InvalidMoveException ex) {
            System.out.println(ex);
        }

        System.out.println("\n\nApakah anda tetap ingin melanjutkan proses OBE? (Ya=1/y; Tidak:0/n)");
        char confirm = Main.in.next().toLowerCase().charAt(0);
        return confirm == 'y' || confirm == '1';
    }

    /**
     * Meminta user untuk mengisi matrix
     *
     * @param size
     */
    public void fillMatrix(int size) {
        ma = new Matrix(size, size);
        cm = new CramerMatrix(size);

        constants = new double[size];
        String request = "", vars = "";
        for (int i = 0; i < size; i++) {
            request += "a" + i + "x" + i;
            vars += "a" + i + ",";
        }

        for (int i = 0; i < size; i++) {
            System.out.printf("Massukan nilai %s dari persamaan %s = a%d: ", vars, request, size + 1);
            for (int j = 0; j < size; j++) {
                ma.setValue(i, j, Main.in.nextDouble());
                cm.setValue(i, j, ma.getValue(i, j));
            }
            constants[i] = Main.in.nextDouble();
        }
        cm.setConstants(constants);
        om = new OBEMatrix(ma, constants);
    }

    /**
     * melakukan perintah ke-2
     */
    public void doAdd() {
        System.out.print("Pilih baris: ");
        int baris = Main.in.nextInt();
        System.out.print("Besar kelipatan: ");
        double skalar = Main.in.nextDouble();
        System.out.print("Jumlah dengan kelipatan baris ke: ");
        int subtractor = Main.in.nextInt();

        om.add(baris - 1, subtractor - 1, skalar);
    }

    /**
     * melakukan perintah ke-3
     */
    public void doMultiply() {
        System.out.print("Pilih baris: ");
        int baris = Main.in.nextInt();
        System.out.print("Besar kelipatan: ");
        double skalar = Main.in.nextDouble();

        om.multiply(baris - 1, skalar);
    }

    /**
     * MElakukan perintah ke-1
     */
    public void doSwap() {
        System.out.print("Pilih baris A: ");
        int barisA = Main.in.nextInt();
        System.out.print("Pilih baris B: ");
        int barisB = Main.in.nextInt();

        om.swap(barisA - 1, barisB - 1);
    }

    /**
     * Menampilkan dan meminta menu.
     */
    public void pilihMenu() {
        System.out.println("Menu:");
        for (String menu : this.menu) {
            System.out.println(menu);
        }
        System.out.print("Pilihan anda: ");
        switch (Main.in.nextInt()) {
            case 2:
                doAdd();
                break;
            case 3:
                doMultiply();
                break;
            case 1:
                doSwap();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Permintaan Anda tidak dapat kami mengerti.\n"
                        + "Mohon maaf coba kembali.");
                break;
        }
        System.out.println(om);
        System.out.println("\n");
    }
}
