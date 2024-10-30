package backend.academy.maze;

import backend.academy.mazesolvers.BfsMazeSolver;
import backend.academy.mazesolvers.SwampMazeSolver;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class MazeSolverTest {
    private Coordinate start = new Coordinate(0, 0);
    private Coordinate finish = new Coordinate(4, 4);

    private Cell[][] mazeGrid = new Cell[][] {
        {
            new Cell(0, 0).setWalls(new boolean[] {true, true, false, true}),
            new Cell(0, 1).setWalls(new boolean[] {false, false, true, true}),
            new Cell(0, 2).setWalls(new boolean[] {true, false, false, true}),
            new Cell(0, 3).setWalls(new boolean[] {false, true, false, true}),
            new Cell(0, 4).setWalls(new boolean[] {false, false, true, true})
        },
        {
            new Cell(1, 0).setWalls(new boolean[] {true, false, false, true}).setSwamp(true),
            new Cell(1, 1).setWalls(new boolean[] {false, true, false, false}).setSwamp(true),
            new Cell(1, 2).setWalls(new boolean[] {false, true, true, false}),
            new Cell(1, 3).setWalls(new boolean[] {true, false, true, true}),
            new Cell(1, 4).setWalls(new boolean[] {true, false, true, false})
        },
        {
            new Cell(2, 0).setWalls(new boolean[] {true, false, true, false}).setSwamp(true),
            new Cell(2, 1).setWalls(new boolean[] {true, false, false, true}),
            new Cell(2, 2).setWalls(new boolean[] {false, true, false, true}),
            new Cell(2, 3).setWalls(new boolean[] {false, false, false, false}).setSwamp(true),
            new Cell(2, 4).setWalls(new boolean[] {false, false, true, false}).setSwamp(true)
        },
        {
            new Cell(3, 0).setWalls(new boolean[] {true, false, true, false}),
            new Cell(3, 1).setWalls(new boolean[] {true, false, true, false}),
            new Cell(3, 2).setWalls(new boolean[] {true, false, true, true}),
            new Cell(3, 3).setWalls(new boolean[] {true, false, true, false}),
            new Cell(3, 4).setWalls(new boolean[] {true, false, true, false})
        },
        {
            new Cell(4, 0).setWalls(new boolean[] {true, true, true, false}),
            new Cell(4, 1).setWalls(new boolean[] {true, true, true, false}),
            new Cell(4, 2).setWalls(new boolean[] {true, true, false, false}),
            new Cell(4, 3).setWalls(new boolean[] {false, true, true, false}),
            new Cell(4, 4).setWalls(new boolean[] {true, true, true, false})
        }
    };

    private Maze maze = new Maze(5, 5, mazeGrid);

    @Test
    public void checkBfsMazeSolver() {
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(4, 4));
        path.add(new Coordinate(3, 4));
        path.add(new Coordinate(2, 4));
        path.add(new Coordinate(1, 4));
        path.add(new Coordinate(0, 4));
        path.add(new Coordinate(0, 3));
        path.add(new Coordinate(0, 2));
        path.add(new Coordinate(1, 2));
        path.add(new Coordinate(1, 1));
        path.add(new Coordinate(0, 1));
        path.add(new Coordinate(0, 0));
        BfsMazeSolver solver = new BfsMazeSolver(maze, start, finish);
        List<Coordinate> bfspath = solver.solveMaze();
        for (int i = 0; i < path.size(); i++) {
            Coordinate coord = path.get(i);
            Coordinate bfscoord = bfspath.get(i);
            Assertions.assertThat(coord.getX()).isEqualTo(bfscoord.getX());
            Assertions.assertThat(coord.getY()).isEqualTo(bfscoord.getY());
        }
    }

    @Test
    public void checkSwampMazeSolver() {
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(4, 4));
        path.add(new Coordinate(3, 4));
        path.add(new Coordinate(2, 4));
        path.add(new Coordinate(1, 4));
        path.add(new Coordinate(0, 4));
        path.add(new Coordinate(0, 3));
        path.add(new Coordinate(0, 2));
        path.add(new Coordinate(1, 2));
        path.add(new Coordinate(1, 1));
        path.add(new Coordinate(0, 1));
        path.add(new Coordinate(0, 0));
        SwampMazeSolver solver = new SwampMazeSolver(maze, start, finish);
        List<Coordinate> swampPath = solver.solveMaze();
        for (int i = 0; i < path.size(); i++) {
            Coordinate coord = path.get(i);
            Coordinate swampcoord = swampPath.get(i);
            Assertions.assertThat(coord.getX()).isEqualTo(swampcoord.getX());
            Assertions.assertThat(coord.getY()).isEqualTo(swampcoord.getY());
        }
    }
}
