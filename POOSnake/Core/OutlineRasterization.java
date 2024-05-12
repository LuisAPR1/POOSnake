package Core;

import java.util.List;
import java.util.LinkedList;

import Geometry.Poligono;
import Geometry.Ponto;
import Geometry.Square;

/**
 * Classe responsável por realizar a rasterização do contorno dos objetos na arena.
 * 
 * Utiliza uma estratégia de rasterização para desenhar o contorno dos objetos, incluindo a cabeça e a cauda da cobra,
 * obstáculos e a fruta, na grade da arena.
 * 
 * @author Luís Rosa, José Lima, Pedro Ferreira
 * @version Versão 1.0 10/05/2024
 */
public class OutlineRasterization implements RasterizationStrategy {

    // Grid que representa a arena
    Cell[][] grid;
    // Referência para a arena
    Arena arena;

    /**
     * Construtor que inicializa a classe de rasterização do contorno.
     * 
     * @param arena A arena onde a rasterização será realizada.
     */
    public OutlineRasterization(Arena arena) {
        this.grid = new Cell[arena.getArenaDimensions()[0]][arena.getArenaDimensions()[1]];
        this.arena = arena;
        initializeArena();
    }

    @Override
    public void render() {
        initializeArena();

        // Desenha o contorno da cabeça da cobra
        drawObject(arena.getS().getHead(), "HEAD");

        // Desenha o contorno da cauda da cobra
        LinkedList<Square> tail = arena.getS().getTailCoordinates();
        for (Square square : tail) {
            drawObject(square, "TAIL");
        }

        // Desenha o contorno dos obstáculos
        for (Obstacle obstacle : arena.getObstacles()) {
            drawObject(obstacle.getObstacle(), "OBSTACLE");
        }

        // Desenha o contorno da fruta
        if (arena.getFruit() != null) {
            Square a = new Square(arena.getFruit().SquareVertices());
            drawObject(a, "FOOD");
        }
    }

    /**
     * Desenha o contorno de um objeto na arena.
     * 
     * @param object   O objeto cujo contorno será desenhado.
     * @param cellType O tipo de célula que será desenhada para representar o contorno.
     */
    private void drawObject(Poligono object, String cellType) {
        List<Ponto> vertices = object.getPontos();

            // Encontra os limites do objeto
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for (Ponto p : vertices) {
                minX = (int) Math.min(minX, p.getX());
                minY = (int) Math.min(minY, p.getY());
                maxX = (int) Math.max(maxX, p.getX());
                maxY = (int) Math.max(maxY, p.getY());
            }

            // Desenha o contorno do objeto
            for (int x = minX; x < maxX; x++) {
                if (x >= 0 && x < grid.length) {
                    if (minY >= 0 && minY < grid[0].length) {
                        grid[x][minY] = Cell.valueOf(cellType);
                    }
                    if (maxY - 1 >= 0 && maxY - 1 < grid[0].length) {
                        grid[x][maxY - 1] = Cell.valueOf(cellType);
                    }
                }
            }
            for (int y = minY; y < maxY; y++) {
                if (y >= 0 && y < grid[0].length) {
                    if (minX >= 0 && minX < grid.length) {
                        grid[minX][y] = Cell.valueOf(cellType);
                    }
                    if (maxX - 1 >= 0 && maxX - 1 < grid.length) {
                        grid[maxX - 1][y] = Cell.valueOf(cellType);
                    }
                }
            }
        }


    /**
     * Inicializa a arena, preenchendo o grid com células vazias.
     */
    private void initializeArena() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
    }

    @Override
    public Cell[][] getGrid() {
        return grid;
    }
}