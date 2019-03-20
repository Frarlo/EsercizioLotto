package me.ferlin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class DatiCondivisi {

    // Constants

    public static final int NUM_DA_ESTRARRE = 5;

    public static final int MIN_NUMERO = 0;
    public static final int MAX_NUMERO = 10;

    // Attributes

    private final List<Estrazione> estrazioni;

    private final Semaphore estrattiUnoSemaphore;
    private final Semaphore estrattiDueSemaphore;

    private final Semaphore terminazioneSemaphore;

    public DatiCondivisi(int numEstrazioni) {

        final Estrazione[] temp = new Estrazione[numEstrazioni];
        for (int i = 0; i < temp.length; i++)
            temp[i] = new Estrazione("n. " + (i + 1));
        estrazioni = Collections.unmodifiableList(Arrays.asList(temp));

        estrattiUnoSemaphore = new Semaphore(0);
        estrattiDueSemaphore = new Semaphore(0);
        terminazioneSemaphore = new Semaphore(-2);
    }

    public List<Estrazione> getEstrazioni() {
        return estrazioni;
    }

    public Semaphore getEstrattiUnoSemaphore() {
        return estrattiUnoSemaphore;
    }

    public Semaphore getEstrattiDueSemaphore() {
        return estrattiDueSemaphore;
    }

    public Semaphore getTerminazioneSemaphore() {
        return terminazioneSemaphore;
    }
}
