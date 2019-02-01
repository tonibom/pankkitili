//package Pankkitili;

import java.util.*;

public class PankkitiliMain {

    private static String tilinum, omistaja;
    private static double saldo;
    private static boolean poistu = false;

    private static final Scanner lukija = new Scanner(System.in);

    public boolean getPoistu() {
        return poistu;
    }

    public static void setPoistu(boolean poistu) {
        PankkitiliMain.poistu = poistu;
    }

    public static void main(String[] args) {

        System.out.println("Tervetuloa!\n");
        System.out.printf("Syota tilinumero >");
        tilinum = lukija.nextLine();
        System.out.printf("Syota tilin omistajan koko nimi >");
        omistaja = lukija.nextLine();

        while (!poistu) {
            try {
                System.out.printf("Syota tilin saldo >");
                saldo = lukija.nextDouble();
                lukija.nextLine();

                while (saldo < 0) {
                    System.out.printf("Tilin saldo ei voi olla negatiivinen!\nSyota tilin saldo >");
                    saldo = lukija.nextDouble();
                    lukija.nextLine();
                }
                if (saldo >= 0) {
                    setPoistu(true);
                }
            } catch (Exception e) {
                System.out.println("Virheellinen syote!");
                lukija.nextLine();
            }
        }

        Pankkitili tili = new Pankkitili(tilinum, omistaja, saldo);
        setPoistu(false); //reset

        while (!poistu) {
            System.out.printf("\nLISAA TILI     (\"L\")\nOTTO           (\"O\")\nTALLETUS       (\"T\")\nTULOSTA TIEDOT (\"TT\")\nPOISTU         (\"P\")\n\n>");
            String valinta = lukija.nextLine();
            switch (valinta) {

                case "L":
                    tili.lisaaTili();
                    break;

                case "O":
                    tili.otto();
                    break;

                case "T":
                    tili.talletus();
                    break;

                case "TT":
                    tili.printTiedot();
                    break;

                case "P":
                    setPoistu(true);
                    break;

                default:

                    System.out.println("\nKaskya ei ymmarretty!");
                    break;
            }
        }
        System.out.println("Tervetuloa uudelleen!");
    }
}
