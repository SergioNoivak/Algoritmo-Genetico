/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmoGenetico;

/**
 *
 * @author sergi
 */
public final class Algoritmos {

    private Algoritmos() {

    }

    public static int ocorrenciasDe(String x, char c) {

        int total = 0;

        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == c) {
                total++;
            }
        }
        return total;
    }

    public static int selecionaDaRoleta(Populacao pop) {

        double percentual = Math.random();
        int valorSorteado = (int) (percentual * pop.somaAptidoes());
        int totalParcial = 0;
        int i = -1;

        while ((i < pop.tamanhoPopulacao) && (totalParcial <= valorSorteado)) {
            i++;
            totalParcial += pop.individuos.get(i).getAptidao();
        }
        return i;
    }

    public static Individuo AlgoritmoGenetico(int numeroDeAlelos, int numeroDeIndividuos, int taxaDePopulacao) {

        int i = 0;
        Populacao pop = new Populacao(true, numeroDeAlelos, numeroDeIndividuos);
        Populacao anterior = new Populacao(false, numeroDeAlelos, numeroDeIndividuos);
        while (i < taxaDePopulacao) {
            if (i > 0) {
                pop.elitismo(anterior);
            }

            anterior = pop;
            pop = pop.cruzar();
            pop.mutar();
            if (pop.estagnou(anterior)) {
                i++;
            }
        }


        return pop.maisApto();

    }

}
