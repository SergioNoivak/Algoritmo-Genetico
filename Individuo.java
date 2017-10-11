
package AlgoritmoGenetico;

public class Individuo {

    public StringBuilder cromossomo;

    public void exibe() {

        System.out.printf("\nCROMOSSOMO %s", this.cromossomo.toString());
        System.out.printf("\naptidao %d", this.getAptidao());

    }

    public Individuo cruzar(Individuo pai, int tamanhoCromossomo) {

        double percentual = Math.random();
        int pontoDeCorte = (int) Math.round((tamanhoCromossomo - 1) * percentual);
        Individuo filho = new Individuo(false, tamanhoCromossomo);
        for (int i = 0; i < pontoDeCorte; i++) {
            filho.cromossomo.setCharAt(i, this.cromossomo.charAt(i));
        }
        for (int i = pontoDeCorte; i < tamanhoCromossomo; i++) {
            filho.cromossomo.setCharAt(i, pai.cromossomo.charAt(i));
        }

        return filho;

    }

    public boolean equals(Individuo analizado) {

        boolean igual = true;
        for (int i = 0; i < this.cromossomo.length(); i++) {
            if (this.cromossomo.charAt(i) != analizado.cromossomo.charAt(i)) {
                igual = false;
                break;
            }
        }
        return igual;
    }

    public Individuo(Individuo individuo) {
        this.cromossomo = individuo.cromossomo;
    }

    int getAptidao() {
        return Algoritmos.ocorrenciasDe(this.cromossomo.toString(), '1');
    }

    public Individuo(String cromossomo) {
        this.cromossomo = new StringBuilder(cromossomo);
    }

    public Individuo(boolean sorteio, int tamanhoCromossomo) {

        this.cromossomo = new StringBuilder();

        if (sorteio) {

            for (int i = 0; i < tamanhoCromossomo; i++) {

                if (Math.random() > 0.8) {

                    this.cromossomo.append('1');

                } else {
                    this.cromossomo.append('0');
                }
            }
        } else {

            for (int i = 0; i < tamanhoCromossomo; i++) {
                this.cromossomo.append('0');
            }

        }

    }

    public void mutar() {

        if (Math.random() < 0.05) {
            for (int i = 0; i < this.cromossomo.length(); i++) {
                if (this.cromossomo.charAt(i) == '1') {
                    this.cromossomo.setCharAt(i, '0');
                } else {
                    this.cromossomo.setCharAt(i, '1');
                }
            }
        }
    }

}
