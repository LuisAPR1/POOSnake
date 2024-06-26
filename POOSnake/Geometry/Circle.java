package Geometry;

import java.util.ArrayList;
import java.util.List;
import Core.Shape;

/**
 * Classe que representa um círculo com centro e raio.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */

public class Circle implements Shape {
    Ponto centro; // O centro do círculo
    int raio; // O raio do círculo

    /**
     * Construtor para criar um círculo com um centro e raio específicos.
     * 
     * @param centro O ponto que representa o centro do círculo.
     * @param raio   O raio do círculo.
     */
    public Circle(Ponto centro, int raio) {
        setCentro(centro);
        setRaio(raio / 2); // O raio fornecido é dividido por 2, pois o círculo é representado pela metade
                           // do raio
    }

    /**
     * Obtém o centro do círculo.
     * 
     * @return O ponto representando o centro do círculo.
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * Define o centro do círculo.
     * 
     * @param centro O ponto que representa o novo centro do círculo.
     */
    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    /**
     * Obtém todas as coordenadas que compõem o círculo.
     * 
     * @return Uma lista de pontos representando todas as coordenadas do círculo.
     */
    public List<Ponto> getAllCoordinates() {
        int numLados = Math.max(10, (int) (2 * Math.PI * raio / 10)); // Aumenta o número de lados com o aumento do raio
        List<Ponto> pontos = new ArrayList<>();

        for (int i = 0; i < numLados; i++) {
            double angle = 2 * Math.PI * i / numLados;
            double x = centro.getX() + raio * Math.cos(angle);
            double y = centro.getY() + raio * Math.sin(angle);
            pontos.add(new Ponto(x, y));
        }

        return pontos;
    }
    
    

    /**
     * Obtém o raio do círculo.
     * 
     * @return O raio do círculo.
     */
    public int getRaio() {
        return raio;
    }

    /**
     * Define o raio do círculo.
     * 
     * @param raio O novo raio do círculo.
     */
    public void setRaio(int raio) {
        this.raio = raio;
    }

    /**
     * Obtém a posição do círculo (no caso, o centro).
     * 
     * @return O ponto representando a posição do círculo.
     */
    @Override
    public Ponto getPosition() {
        return centro;
    }

    /**
     * Verifica se o círculo intersecta com um polígono.
     * 
     * @param polygon O polígono com o qual verificar a interseção.
     * @return true se houver interseção, caso contrário false.
     */
    public boolean intersect(Poligono polygon) {
        // Obtém todas as coordenadas do polígono fornecido por argumento
        List<Ponto> polygonCoordinates = polygon.getAllCoordinates();

        // Itera sobre todas as coordenadas do polígono
        for (Ponto point : polygonCoordinates) {
            // Calcula a distância entre o centro do círculo e o ponto do polígono
            double distance = Math.sqrt(Math.pow(this.centro.getX() - point.getX(), 2) +
                    Math.pow(this.centro.getY() - point.getY(), 2));

            // Se a distância for menor ou igual ao raio do círculo, há interseção
            if (distance <= this.raio) {
                return true;
            }
        }

        // Se nenhum ponto do polígono estiver dentro do círculo, retorna falso
        return false;
    }

    public boolean intersect(Circle circle) {
        // Obtém todas as coordenadas do polígono fornecido por argumento
        List<Ponto> polygonCoordinates = circle.getAllCoordinates();

        // Itera sobre todas as coordenadas do polígono
        for (Ponto point : polygonCoordinates) {
            // Calcula a distância entre o centro do círculo e o ponto do polígono
            double distance = Math.sqrt(Math.pow(this.centro.getX() - point.getX(), 2) +
                    Math.pow(this.centro.getY() - point.getY(), 2));

            // Se a distância for menor ou igual ao raio do círculo, há interseção
            if (distance <= this.raio) {
                return true;
            }
        }

        // Se nenhum ponto do polígono estiver dentro do círculo, retorna falso
        return false;
    }

    public Poligono toPolygon() {
        int numLados = Math.max(10, (int) (2 * Math.PI * raio / 10)); // Aumenta o número de lados com o aumento do raio
        List<Ponto> pontos = new ArrayList<>();

        for (int i = 0; i < numLados; i++) {
            double angle = 2 * Math.PI * i / numLados;
            double x = centro.getX() + raio * Math.cos(angle);
            double y = centro.getY() + raio * Math.sin(angle);
            pontos.add(new Ponto(x, y));
        }

        return new Poligono(pontos);
    }
}
