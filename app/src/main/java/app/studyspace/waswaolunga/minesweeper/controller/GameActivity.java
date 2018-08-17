package app.studyspace.waswaolunga.minesweeper.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import app.studyspace.waswaolunga.minesweeper.R;
import app.studyspace.waswaolunga.minesweeper.model.Board;
import app.studyspace.waswaolunga.minesweeper.model.MineSweeper;
import app.studyspace.waswaolunga.minesweeper.model.State;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ToolbarSetup.setupScreenToolbar(GameActivity.this, R.id.game_activity_toolbar, R.string.game_activity_subtitle);

        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setupMines(4, 4, 5);
        Board gameBoard = mineSweeper.getBoard();

        TextView numMinesFoundTextView = findViewById(R.id.num_mines_found_textView);
        numMinesFoundTextView.setText(getString(R.string.num_mines_found_textView_content, 0));

        TextView numMinesLeftTextView = findViewById(R.id.num_mines_left_textView);
        numMinesLeftTextView.setText(getString(R.string.num_mines_left_textView_content, gameBoard.getNumMinesLeftToFind()));

        setupGameButtonsClickListeners(gameBoard);
    }

    private void setupGameButtonsClickListeners(Board gameBoard) {

        TableLayout tableLayout = findViewById(R.id.game_tableLayout);
        int numRows = tableLayout.getChildCount();

        TableRow tableRow = (TableRow) tableLayout.getChildAt(0);
        int numCols = tableRow.getChildCount();

        for(int i = 0; i < numRows; i++){

            TableRow currentTableRow = (TableRow) tableLayout.getChildAt(i);
            for(int j = 0; j < numCols; j++){

                Button gameButton = (Button) currentTableRow.getChildAt(j);
                final int currentRow = i;
                final int currentCol = j;

                gameButton.setOnClickListener((View view) -> {

                    if(gameBoard.getGameBoard()[currentRow][currentCol].getCellState() == State.MINE_UNCLICKED){

                        TextView numMinesFoundTextView = findViewById(R.id.num_mines_found_textView);

                        String numMinesFoundTextViewContent = numMinesFoundTextView.getText().toString();
                        int colonIndex = numMinesFoundTextViewContent.indexOf(":");
                        int numMinesFound = Integer.parseInt(numMinesFoundTextViewContent.substring(colonIndex + 1).trim()) + 1;

                        numMinesFoundTextView.setText(getString(R.string.num_mines_found_textView_content, numMinesFound));

                        TextView numMinesLeftTextView = findViewById(R.id.num_mines_left_textView);

                        String numMinesLeftTextViewContent = numMinesLeftTextView.getText().toString();
                        colonIndex = numMinesFoundTextViewContent.indexOf(":");
                        int numMinesLeft = Integer.parseInt(numMinesLeftTextViewContent.substring(colonIndex + 1).trim()) - 1;

                        numMinesLeftTextView.setText(getString(R.string.num_mines_left_textView_content, numMinesLeft));

                        if(numMinesLeft == 0){
                            DialogBuilder.displayGameOver(GameActivity.this).show();
                        }

                        gameBoard.getGameBoard()[currentRow][currentCol].setCellState(State.MINE_CLICKED);
                        gameBoard.updateNumMinesInRowAndCol(tableLayout, currentRow, currentCol);

                        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
                        gameButton.setBackground(new BitmapDrawable(getApplicationContext().getResources(), scaledBitmap));
                        gameButton.setPadding(5, 5, 5, 5);

                    } else if(gameBoard.getGameBoard()[currentRow][currentCol].getCellState() == State.NO_MINE_UNCLICKED){

                        gameButton.setText("" + gameBoard.countNumMines(currentRow, currentCol));
                        gameBoard.getGameBoard()[currentRow][currentCol].setCellState(State.NUMBER_SHOWS_CLICKED);

                    }
                });
            }
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, GameActivity.class);
    }

}