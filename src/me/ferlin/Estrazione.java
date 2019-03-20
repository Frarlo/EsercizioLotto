package me.ferlin;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class Estrazione {

    // Attributes

    private final String nome;
    private final int[] numeriEstratti;

    private boolean amboUno;
    private boolean amboDue;

    public Estrazione(String nome) {
        this.nome = nome;
        numeriEstratti = new int[DatiCondivisi.NUM_DA_ESTRARRE];
    }

    public void estrai(final Random rn) {
        for (int i = 0; i < numeriEstratti.length; i++)
            numeriEstratti[i] = DatiCondivisi.MIN_NUMERO + rn.nextInt(DatiCondivisi.MAX_NUMERO + 1);
    }

    public boolean contains(int toContain) {
        for (int n : numeriEstratti)
            if(n == toContain)
                return true;
        return false;
    }

    public void setAmboUno(boolean amboUno) {
        this.amboUno = amboUno;
    }

    public void setAmboDue(boolean amboDue) {
        this.amboDue = amboDue;
    }

    public boolean isVinta() {
        return amboUno && amboDue;
    }

    public void printSummary(PrintStream ps) {
        ps.println("Estrazione " + nome);
        ps.println("Numeri: " + Arrays.toString(numeriEstratti));
        ps.println("Primo numero: " + amboUno);
        ps.println("Secondo numero: " + amboDue);
        if(isVinta())
            ps.println("Vinta");
        else
            ps.println("Persa");
    }
}
