package Core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Poligono;
import Geometry.Ponto;

/**
 * Classe que representa uma comida em forma de quadrado.
 * 
 * @version Versão 1.0 10/05/2024
 * @author Luís Rosa, José Lima, Pedro Ferreira e Pedro Ferreira
 */
public class FoodSquare extends AbstractFood<Poligono> {
    private int sideLength; // Comprimento do lado do quadrado
    private Poligono p; // Polígono representando o quadrado

    /**
     * Construtor para criar uma instância de comida em forma de quadrado.
     * 
     * @param color      A cor da comida.
     * @param type       O tipo de comida.
     * @param arena      A arena em que a comida será gerada.
     * @param sideLength O comprimento do lado do quadrado.
     */
    public FoodSquare(Color color, Core.FoodType type, Arena arena, int sideLength) {
        super(color, type, arena);
        this.sideLength = sideLength;
        spawnFood(arena);
    }

    @Override
    public void spawnFood(Arena arena) {
        int maxTries = 10000;
        int tries = 0;
        int movementIncrement = arena.getHeadDimensions();
        while (tries < maxTries) {
            int x = (int) (Math.random() * (arena.getArenaDimensions()[0] - sideLength));
            int y = (int) (Math.random() * (arena.getArenaDimensions()[1] - sideLength));
            boolean reachableBySnake = (x % movementIncrement == 0) && (y % movementIncrement == 0);
            boolean intersectsObstacle = false; // Flag para verificar interseção com obstáculo
            if (reachableBySnake) {
                List<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(x, y));
                pontos.add(new Ponto(x + sideLength, y));
                pontos.add(new Ponto(x + sideLength, y + sideLength));
                pontos.add(new Ponto(x, y + sideLength));
                p = new Poligono(pontos);
                // Verificar se a comida não intersecta com nenhum obstáculo
                for (Obstacle obstacle : arena.getObstacles()) {
                    if (p.intersect(obstacle.getObstacle())) {
                        intersectsObstacle = true;
                        break;
                    }
                }
                if (!intersectsObstacle) {
                    return;
                }
            }
            tries++;
        }
        // Se não encontrar uma posição válida, gera uma posição aleatória fora dos obstáculos
        this.p = generatePosition(arena.getHeadDimensions());
    }
    
    @Override
    public Poligono getShape() {
        return p;
    }

    @Override
    public List<Ponto> SquareVertices() {
        return p.getPontos();
    }

    /**
     * Gera uma posição aleatória para a comida dentro dos limites da arena.
     * 
     * @param headSize O tamanho da cabeça da cobra.
     * @return         A posição gerada.
     */
    private Poligono generatePosition(int headSize) {
        int[] arenaDimensions = arena.getArenaDimensions();
        int minX = headSize;
        int maxX = arenaDimensions[0] - headSize;
        int minY = headSize;
        int maxY = arenaDimensions[1] - headSize;
        int x = (int) (Math.random() * (maxX - minX + 1)) + minX;
        int y = (int) (Math.random() * (maxY - minY + 1)) + minY;
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(x, y));
        pontos.add(new Ponto(x + sideLength, y));
        pontos.add(new Ponto(x + sideLength, y + sideLength));
        pontos.add(new Ponto(x, y + sideLength));
        return new Poligono(pontos);
    }
}
