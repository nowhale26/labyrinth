package backend.academy;

public class Edge {
    Cell currentCell;
    Cell nextCell;

    Edge(Cell currentCell, Cell nextCell) {
        this.currentCell = currentCell;
        this.nextCell = nextCell;
    }
}
