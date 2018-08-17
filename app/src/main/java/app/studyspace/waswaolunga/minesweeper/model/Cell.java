package app.studyspace.waswaolunga.minesweeper.model;

/**
 * The Cell class describes a single cell in the game.
 *
 * @author Safari & Waswa Olunga
 */

public class Cell {

    private State cellState;
    private int row;
    private int col;

    public Cell(int row, int col) {

        this.row = row;
        this.col = col;
        this.cellState = State.NO_MINE_UNCLICKED;
    }

    public State getCellState() {
        return cellState;
    }

    public void setCellState(State cellState) {
        this.cellState = cellState;
    }
}