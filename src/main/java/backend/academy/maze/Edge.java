package backend.academy.maze;

public class Edge {
    public Cell currentCell;
    public Cell nextCell;

    public Edge(Cell currentCell, Cell nextCell) {
        this.currentCell = currentCell;
        this.nextCell = nextCell;
    }
}
