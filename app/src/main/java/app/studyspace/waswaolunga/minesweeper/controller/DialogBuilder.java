package app.studyspace.waswaolunga.minesweeper.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import app.studyspace.waswaolunga.minesweeper.R;

/**
 * The DialogBuilder class handles displaying
 * the game over dialog.
 *
 * @author Safari & Waswa Olunga
 */

public class DialogBuilder {

    public static AlertDialog displayGameOver(Activity activity){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(R.string.game_over_title);
        builder.setMessage(R.string.game_over_message);

        builder.setPositiveButton(R.string.ok_positive_btn_content, (DialogInterface dialog, int id) -> activity.finish());

        builder.setCancelable(false);

        return builder.create();
    }

}