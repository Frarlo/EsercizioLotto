package me.ferlin;

public class ControllaRunnable implements Runnable {

    private final DatiCondivisi datiCondivisi;

    private final int toCheck;
    private final int n;

    public ControllaRunnable(DatiCondivisi datiCondivisi,
                             int toCheck,
                             int n) {

        if(n != 1 && n != 2)
            throw new IllegalArgumentException("Il numero del thread deve essere o 1 o 2");

        this.datiCondivisi = datiCondivisi;
        this.toCheck = toCheck;
        this.n = n;
    }

    @Override
    public void run() {
        try {
            for (Estrazione estrazione : datiCondivisi.getEstrazioni()) {

                switch (n) {
                    case 1:
                        datiCondivisi.getEstrattiUnoSemaphore().acquire();
                        break;
                    case 2:
                        datiCondivisi.getEstrattiDueSemaphore().acquire();
                }

                if(estrazione.contains(toCheck)) {
                    switch (n) {
                        case 1:
                            estrazione.setAmboUno(true);
                            break;
                        case 2:
                            estrazione.setAmboDue(true);
                    }
                }
            }
        } catch (InterruptedException e) {
            // Ignored
        }

        datiCondivisi.getTerminazioneSemaphore().release();
    }
}
