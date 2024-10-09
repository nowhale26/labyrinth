package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int x;
    private int y;
    boolean isVisited = false;
    boolean[] walls = {true, true, true, true};

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean hasWall(Direction dir) {
        return walls[dir.ordinal()];
    }

    public void removeWall(Cell neighbour) {
        //Север
        if (neighbour.x == x && neighbour.y == y - 1) {
            walls[Direction.NORTH.ordinal()] = false;
            neighbour.walls[Direction.SOUTH.ordinal()] = false;
        }//Восток
        else if (neighbour.x == x + 1 && neighbour.y == y) {
            walls[Direction.EAST.ordinal()] = false;
            neighbour.walls[Direction.WEST.ordinal()] = false;
        }//Юг
        else if (neighbour.x == x && neighbour.y == y + 1) {
            walls[Direction.SOUTH.ordinal()] = false;
            neighbour.walls[Direction.NORTH.ordinal()] = false;
        }//Запад
        else if (neighbour.x == x - 1 && neighbour.y == y) {
            walls[Direction.WEST.ordinal()] = false;
            neighbour.walls[Direction.EAST.ordinal()] = false;
        }
    }

}
