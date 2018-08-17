package app.studyspace.waswaolunga.minesweeper.model;

/**
 * The Minesweeper class holds the logic for
 * the game.
 *
 * @author Safari & Waswa Olunga
 */

public class MineSweeper {

    private Board board;

    public void setupMines(int totalNumMines, int numRows, int numCols){

        board = new Board(numRows, numCols);
        board.fillMines(totalNumMines);
    }

    public Board getBoard() {
        return board;
    }
}