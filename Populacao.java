package AlgoritmoGenetico;

import java.util.ArrayList;
import java.util.Iterator;

public class Populacao {

    ArrayList<Individuo> individuos;
    public int tamanhoPopulacao;
    public int tamanhoCromossomo;

    public Populacao(ArrayList<Individuo> individuos, int tamanhoPopulacao, int tamanhoCromossomo) {
        this.individuos = individuos;
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.tamanhoCromossomo = tamanhoCromossomo;
    }

    public Populacao(boolean sorteio, int tamanhoCromossomo, int tamanhoPopulacao) {
        this.individuos = new ArrayList<>();

        for (int i = 0; i < tamanhoPopulacao; i++) {
            this.individuos.add(new Individuo(sorteio, tamanhoCromossomo));

        }
        this.tamanhoPopulacao = tamanhoPopulacao;
        this.tamanhoCromossomo = tamanhoCromossomo;
    }

    public void exibe() {

        int i = 0;

        for (Individuo individuo : this.individuos) {
            System.out.printf("\n Indice: %d", i);
            individuo.exibe();
            i++;
        }

    }
    
    void elitismo(Populacao populacaoAnterior){
        Individuo maisAptoDaGeracaoAnterior = populacaoAnterior.maisApto();
        Individuo menosAptoDessa = this.menosApto();
        if(menosAptoDessa.getAptidao() <  maisAptoDaGeracaoAnterior.getAptidao()){

            menosAptoDessa = maisAptoDaGeracaoAnterior;
        }
    }
    
    

    Individuo maisApto() {

        Individuo melhor = new Individuo(false, this.tamanhoCromossomo);

        for (Individuo ind : this.individuos) {
            if (ind.getAptidao() > melhor.getAptidao()) {
                melhor = ind;
            }
        }
        return melhor;
    }

    Individuo menosApto() {

        Individuo pior = new Individuo(false, this.tamanhoCromossomo);
        for (int i = 0; i < pior.cromossomo.length(); i++) {
            pior.cromossomo.setCharAt(i, '1');
        }

        for (Individuo ind : this.individuos) {
            if (ind.getAptidao() < pior.getAptidao()) {
                pior = ind;
            }
        }
        return pior;
    }

    public int somaAptidoes() {

        int soma = 0;
        for (Individuo ind : this.individuos) {

            soma += ind.getAptidao();
        }
        return soma;
    }

    public Populacao cruzar() {
        int i = 0;
        boolean preenchido = false;
        ArrayList<Individuo> filhos = new ArrayList<>();
        while (i < this.tamanhoPopulacao) {

            if (Math.random() < 0.8) {
                int indicePai = -1, indiceMae = -1;
                while (indiceMae == indicePai) {
                    indiceMae = Algoritmos.selecionaDaRoleta(this);
                    indicePai = Algoritmos.selecionaDaRoleta(this);
                }
                Individuo filhoCorrente = new Individuo(this.individuos.get(indicePai).cruzar(this.individuos.get(indiceMae), this.individuos.get(indiceMae).cromossomo.length()));
                filhos.add(filhoCorrente);
                i++;
            }
        }
        Populacao populacao = new Populacao(filhos, filhos.size(), this.tamanhoCromossomo);
        return populacao;
    }

    boolean estagnou(Populacao populacaoAnterior) {
        boolean estagnou = true;
        for (int i = 0; i < this.tamanhoPopulacao; i++) {
            if (populacaoAnterior.individuos.get(i).equals(populacaoAnterior.individuos.get(i)) == false) {
                estagnou = false;
                break;
            }
        }

        return estagnou;
    }

    public void mutar() {

        for (Individuo individuo : this.individuos) {
            individuo.mutar();
        }
    }
}
