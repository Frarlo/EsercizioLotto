package me.ferlin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final Scanner sc = new Scanner(System.in);

        System.out.println("Inserire il numero di estrazioni da fare: ");
        final int numEstrazioni = sc.nextInt();

        System.out.println("Inserire il primo numero dell'ambo: ");
        final int primoNum = getNumWithinBounds(sc);

        System.out.println("Inserire il secondo numero dell'ambo: ");
        final int secondoNum = getNumWithinBounds(sc);

        final DatiCondivisi dati = new DatiCondivisi(numEstrazioni);

        final Thread estraiTh = new Thread(new EstraiRunnable(dati));
        final Thread controlla1Th = new Thread(new ControllaRunnable(dati, primoNum, 1));
        final Thread controlla2Th = new Thread(new ControllaRunnable(dati, secondoNum, 2));

        estraiTh.start();
        controlla1Th.start();
        controlla2Th.start();

        dati.getTerminazioneSemaphore().acquireUninterruptibly();
        System.out.println();
        System.out.println("---------------------");

        int vinte = 0;
        for (Estrazione estrazione : dati.getEstrazioni()) {
            estrazione.printSummary(System.out);
            if(estrazione.isVinta())
                vinte++;
            System.out.println("---------------------");
        }

        switch (vinte) {
            case 0:
                System.out.println("Non è stata vinta alcuna estrazione");
                break;
            case 1:
                System.out.println("E' stata vinta un'estrazione");
                break;
            default:
                System.out.println("Sono state vinte " + vinte + " estrazioni");
        }
    }

    private static int getNumWithinBounds(Scanner sc) {
        int temp = sc.nextInt();
        while (temp < DatiCondivisi.MIN_NUMERO || temp > DatiCondivisi.MAX_NUMERO) {
            System.out.printf("Numero non valido (Min: %s; Max: %s). Reinserirlo: \n",
                    DatiCondivisi.MIN_NUMERO, DatiCondivisi.MAX_NUMERO);
            temp = sc.nextInt();
        }
        return temp;
    }

}
