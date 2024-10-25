package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int x;
    private int y;
    private boolean isSwamp = false;
    boolean isVisited = false;

    boolean[] walls = {true, true, true, true};

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
        this.isVisited = cell.isVisited;
        this.isSwamp = cell.isSwamp();
        System.arraycopy(cell.walls, 0, this.walls, 0, this.walls.length);
    }

    boolean hasWall(Direction dir) {
        return walls[dir.ordinal()];
    }

    public void removeWall(Cell neighbour) {
        if (neighbour.x == x && neighbour.y == y - 1) {
            //Север
            walls[Direction.NORTH.ordinal()] = false;
            neighbour.walls[Direction.SOUTH.ordinal()] = false;
        } else if (neighbour.x == x + 1 && neighbour.y == y) {
            //Восток
            walls[Direction.EAST.ordinal()] = false;
            neighbour.walls[Direction.WEST.ordinal()] = false;
        } else if (neighbour.x == x && neighbour.y == y + 1) {
            //Юг
            walls[Direction.SOUTH.ordinal()] = false;
            neighbour.walls[Direction.NORTH.ordinal()] = false;
        } else if (neighbour.x == x - 1 && neighbour.y == y) {
            //Запад
            walls[Direction.WEST.ordinal()] = false;
            neighbour.walls[Direction.EAST.ordinal()] = false;
        }
    }

    public void placeWall(Cell neighbour) {

        if (neighbour.x == x && neighbour.y == y - 1) {
            //Север
            walls[Direction.NORTH.ordinal()] = true;
            neighbour.walls[Direction.SOUTH.ordinal()] = true;
        } else if (neighbour.x == x + 1 && neighbour.y == y) {
            //Восток
            walls[Direction.EAST.ordinal()] = true;
            neighbour.walls[Direction.WEST.ordinal()] = true;
        } else if (neighbour.x == x && neighbour.y == y + 1) {
            //Юг
            walls[Direction.SOUTH.ordinal()] = true;
            neighbour.walls[Direction.NORTH.ordinal()] = true;
        } else if (neighbour.x == x - 1 && neighbour.y == y) {
            //Запад
            walls[Direction.WEST.ordinal()] = true;
            neighbour.walls[Direction.EAST.ordinal()] = true;
        }
    }

}
