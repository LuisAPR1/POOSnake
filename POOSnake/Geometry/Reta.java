package Geometry;

/**
 * Classe que representa uma reta definida por dois pontos.
 * @version 1.0 03/04/2024
 * @author Luís Rosa
 */
public class Reta {
    private Ponto ponto1; // Primeiro ponto da reta.
    private Ponto ponto2; // Segundo ponto da reta.

    /**
     * Construtor da classe Reta.
     * @param ponto1 primeiro ponto da reta.
     * @param ponto2 segundo ponto da reta.
     */
    public Reta(Ponto ponto1, Ponto ponto2) {
        // check(ponto1,ponto2);
        this.ponto1 = ponto1;
        this.ponto2 = ponto2;
    }

    /**
     * Verifica se os pontos fornecidos para criar a reta são iguais.
     * @param ponto1 primeiro ponto.
     * @param ponto2 segundo ponto.
     */
    // private void check(Ponto ponto1, Ponto ponto2){
    //     if (ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY() && ponto1.getxDouble() == ponto2.getxDouble() && ponto1.getyDouble() == ponto2.getyDouble()){
    //         System.out.println("Reta:vi");
    //         System.exit(0);
    //     }
    // }

    /**
     * Obtém o primeiro ponto da reta.
     * @return primeiro ponto.
     */
    public Ponto getPonto1() {
        return ponto1;
    }

    /**
     * Define o primeiro ponto da reta.
     * @param ponto1 primeiro ponto.
     */
    public void setPonto1(Ponto ponto1) {
        this.ponto1 = ponto1;
    }

    /**
     * Obtém o segundo ponto da reta.
     * @return segundo ponto.
     */
    public Ponto getPonto2() {
        return ponto2;
    }

    /**
     * Define o segundo ponto da reta.
     * @param ponto2 segundo ponto.
     */
    public void setPonto2(Ponto ponto2) {
        this.ponto2 = ponto2;
    }

    /**
     * Verifica se um ponto está na mesma linha da reta.
     * @param ponto ponto a ser verificado.
     * @return true se o ponto estiver na mesma linha da reta, false caso contrário.
     */
    public boolean colineares (Ponto ponto){
        double diferença_y = ponto2.getY() - ponto1.getY();
        double diferença_x = ponto2.getX() - ponto1.getX();
        double m = diferença_y/diferença_x;
        double b = ponto1.getY() - m*ponto1.getX();
        return ponto.getY() == ponto.getX()*m + b;
    }
}
