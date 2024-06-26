package Geometry;

import java.util.Objects;

/**
 * Classe que representa um ponto no plano cartesiano.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class Ponto {
    private double x, y; // Coordenadas inteiras do ponto.

    /**
     * Construtor padrão que inicializa um ponto na origem.
     */
    public Ponto() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor que recebe coordenadas inteiras para criar um ponto.
     * 
     * @param x coordenada x do ponto.
     * @param y coordenada y do ponto.
     */
    public Ponto(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Calcula a distância entre este ponto e outro ponto.
     * 
     * @param p ponto para o qual deseja calcular a distância.
     * @return distância entre os pontos.
     */
    public double dist(Ponto p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Obtém a coordenada x do ponto.
     * 
     * @return coordenada x.
     */
    public double getX() {
        return x;
    }

    /**
     * Obtém a coordenada y do ponto.
     * 
     * @return coordenada y.
     */
    public double getY() {
        return y;
    }

    /**
     * Define a coordenada x do ponto.
     * 
     * @param x coordenada x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Define a coordenada y do ponto.
     * 
     * @param y coordenada y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Verifica se este ponto é igual a outro objeto.
     * 
     * @param obj objeto a ser comparado.
     * @return true se os pontos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Ponto that = (Ponto) obj;

        return this.x == that.x && this.y == that.y;
    }

    /**
     * Calcula o código de hash para este ponto.
     * 
     * @return código de hash do ponto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    /**
     * Retorna uma representação textual deste ponto.
     * 
     * @return string representando o ponto.
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /**
     * Rotaciona o ponto em torno de um ponto fixo por um determinado ângulo.
     * 
     * @param anguloGraus ângulo de rotação em graus.
     * @param fixo        ponto fixo em torno do qual o ponto será rotacionado.
     * @return novo ponto rotacionado.
     */
    public Ponto rotacionar(int anguloGraus, Ponto fixo) {
        double x = (fixo.getX() + (this.x - fixo.getX()) * Math.cos(Math.toRadians(anguloGraus))
                - (this.y - fixo.getY()) * Math.sin(Math.toRadians(anguloGraus)));
        double y = (fixo.getY() + ((this.x - fixo.getX()) * Math.sin(Math.toRadians(anguloGraus))
                + (this.y - fixo.getY()) * Math.cos(Math.toRadians(anguloGraus))));
        return new Ponto(x, y);
    }

    /**
     * Realiza uma translação no ponto.
     * 
     * @param xmove deslocamento horizontal.
     * @param ymove deslocamento vertical.
     * @return novo ponto transladado.
     */
    public Ponto translacionar(int xmove, int ymove) {
        return new Ponto(this.x + xmove, this.y + ymove);
    }
}
