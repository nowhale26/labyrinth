package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Coordinate {
    private final int x;
    private final int y;
    private Coordinate previousCoordinate;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }
}
