package me.ferlin;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EstraiRunnable implements Runnable {

    private final DatiCondivisi datiCondivisi;

    public EstraiRunnable(DatiCondivisi datiCondivisi) {
        this.datiCondivisi = datiCondivisi;
    }

    @Override
    public void run() {

        Random rn = ThreadLocalRandom.current();
        for(Estrazione estrazione : datiCondivisi.getEstrazioni()) {
            estrazione.estrai(rn);
            datiCondivisi.getEstrattiUnoSemaphore().release();
            datiCondivisi.getEstrattiDueSemaphore().release();
        }

        datiCondivisi.getTerminazioneSemaphore().release();
    }
}
