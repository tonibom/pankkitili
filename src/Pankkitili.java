//package Pankkitili;

import java.util.Scanner;

public class Pankkitili {

    private String tilinum, omistaja;
    private double saldo;
    private boolean poistu = false;
    private static final Scanner lukija = new Scanner(System.in);

    public Pankkitili(String tnum, String owner, double rahasumma) {
        tilinum = tnum;
        omistaja = owner;
        saldo = rahasumma;
    }

    public String getTilinum() {
        return tilinum;
    }

    public String getOmistaja() {
        return omistaja;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setTilinum(final String tilinum) {
        this.tilinum = tilinum;
    }

    public void setOmistaja(final String omistaja) {
        this.omistaja = omistaja;
    }

    public void setSaldo(final double saldo) {
        this.saldo = saldo;
    }

    public void talletus() {

        System.out.printf("Syota tilille talletettava summa >");
        try {

            double talletus = lukija.nextDouble();
            lukija.nextLine();

            if (talletus < 0) {
                System.out.println("Et voi tallettaa negatiivista summaa!\nTalletusta ei voida suorittaa!");

            } else {
                System.out.printf("Talletus suoritettu!\nTalletit tilille %.2f euroa.\nTilin nykyinen saldo on ", talletus);
                talletus = talletus + getSaldo(); //lasketaan tilin saldo lisäämällä tilin saldoon talletettu summa
                setSaldo(talletus);
                System.out.printf("%.2f euroa.\n", getSaldo());
            }

        } catch (Exception e) {
            System.out.println("Virheellinen syote!\nTalletusta ei voida suorittaa!");
            lukija.nextLine();
        }
    }

    public void otto() {

        System.out.printf("Syota tililta nostettava summa >");
        try {
            double nosto = lukija.nextDouble();
            lukija.nextLine();

            if (nosto < 0) { //NEGATIIVINEN NOSTO
                System.out.println("Et voi nostaa negatiivista summaa!\nNostoa ei voida suorittaa!");
            } else {
                if (nosto > getSaldo()) { //YLITTÄÄ SALDON
                    System.out.println("Tilin saldo ei riita nostamaan niin suurta summaa!\nNostoa ei voida suorittaa!");

                } else { //ONNISTUI
                    System.out.printf("Nosto suoritettu!\nNostit tililta %.2f euroa.\nTilin nykyinen saldo on ", nosto);
                    nosto = getSaldo() - nosto; //lasketaan tilin saldo vähentämällä saldosta nostettu summa
                    setSaldo(nosto);
                    System.out.printf("%.2f euroa.\n", getSaldo());
                }
            }
        } catch (Exception e) {
            System.out.println("Virheellinen syote!\nNostoa ei voida suorittaa!");
            lukija.nextLine();
        }
    }

    public void lisaaTili() {

        System.out.println("Aiemmin syottamasi tili poistettiin!");

        System.out.printf("\nSyota tilinumero >");
        setTilinum(lukija.nextLine());

        System.out.printf("Syota tilin omistajan koko nimi >");
        setOmistaja(lukija.nextLine());

        while (!poistu) {

            try {
                System.out.printf("Syota tilin saldo >");
                double rahasumma = lukija.nextDouble();
                lukija.nextLine();

                while (rahasumma < 0) {
                    System.out.printf("Tilin saldo ei voi olla negatiivinen!\nSyota tilin saldo >");
                    rahasumma = lukija.nextDouble();
                    lukija.nextLine();
                }
                if (rahasumma >= 0) {
                    poistu = true; //Syöte on hyväksyttävä; while-loopista voidaan poistua
                    setSaldo(rahasumma);
                }
            } catch (Exception e) {
                System.out.println("Virheellinen syote!");
                lukija.nextLine();
            }
        }
        poistu = false; //reset
    }

    public void printTiedot() {
        System.out.println("Tilinumero: " + getTilinum());
        System.out.println("Tilin omistaja: " + getOmistaja());
        System.out.printf("Tilin saldo: %.2f euroa.\n", getSaldo());
    }

}
