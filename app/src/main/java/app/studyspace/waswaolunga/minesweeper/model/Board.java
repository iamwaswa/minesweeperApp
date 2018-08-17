package app.studyspace.waswaolunga.minesweeper.model;

import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * The Board class stores the data for the game board.
 *
 * @author Safari & Waswa Olunga
 */

public class Board {

    private int numRows;
    private int numCols;
    private Cell[][] gameBoard;
    private int numMinesLeftToFind;

    public Board(int numRows, int numCols) {

        this.numRows = numRows;
        this.numCols = numCols;
        this.gameBoard = new Cell[numRows][numCols];

        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){

                gameBoard[i][j] = new Cell(i, j);
            }
        }
    }

    public int getNumMinesLeftToFind() {
        return numMinesLeftToFind;
    }

    public void fillMines(int totalNumMines){

        numMinesLeftToFind = totalNumMines;

        do {

            int randomRow = (int) (Math.random() * numRows);
            int randomCol = (int) (Math.random() * numCols);

            if (gameBoard[randomRow][randomCol].getCellState() == State.NO_MINE_UNCLICKED) {

                gameBoard[randomRow][randomCol].setCellState(State.MINE_UNCLICKED);
                totalNumMines--;
            }

        } while(totalNumMines != 0);
    }

    public int countNumMines(int currentRow, int currentCol){

        int numMinesFound = 0;

        numMinesFound = checkAlongRow(currentRow, currentCol, numMinesFound);
        numMinesFound = checkAlongColumn(currentRow, currentCol, numMinesFound);

        return numMinesFound;
    }

    private int checkAlongRow(int currentRow, int currentCol, int numMinesFound) {

        for(int i = 0; i < numCols; i++){

            if(i != currentCol && gameBoard[currentRow][i].getCellState() == State.MINE_UNCLICKED){
                numMinesFound++;
            }
        }

        return numMinesFound;
    }

    private int checkAlongColumn(int currentRow, int currentCol, int numMinesFound) {

        for(int i = 0; i < numRows; i++){

            if(i != currentRow && gameBoard[i][currentCol].getCellState() == State.MINE_UNCLICKED){
                numMinesFound++;
            }
        }

        return numMinesFound;
    }

    public void updateNumMinesInRowAndCol(TableLayout gameTable, int currentRow, int currentCol){

        numMinesLeftToFind--;
        updateAlongRow(gameTable, currentRow, currentCol);
        updateAlongColumn(gameTable, currentRow, currentCol);
    }

    private void updateAlongRow(TableLayout gameTable, int currentRow, int currentCol) {

        TableRow currentTableRow = (TableRow) gameTable.getChildAt(currentRow);

        for(int i = 0; i < numCols; i++){

            if(i != currentCol && gameBoard[currentRow][i].getCellState() == State.NUMBER_SHOWS_CLICKED){

                Button gameBtn = (Button) currentTableRow.getChildAt(i);

                String numMinesDisplayed = gameBtn.getText().toString();

                int updatedNumMines = Integer.parseInt(numMinesDisplayed) - 1;
                gameBtn.setText("" + updatedNumMines);
            }
        }
    }

    private void updateAlongColumn(TableLayout gameTable, int currentRow, int currentCol) {

        for(int i = 0; i < numRows; i++){

            TableRow currentTableRow = (TableRow) gameTable.getChildAt(i);

            if(i != currentRow && gameBoard[i][currentCol].getCellState() == State.NUMBER_SHOWS_CLICKED){

                Button gameBtn = (Button) currentTableRow.getChildAt(currentCol);

                String numMinesDisplayed = gameBtn.getText().toString();

                int updatedNumMines = Integer.parseInt(numMinesDisplayed) - 1;
                gameBtn.setText("" + updatedNumMines);
            }
        }
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

}