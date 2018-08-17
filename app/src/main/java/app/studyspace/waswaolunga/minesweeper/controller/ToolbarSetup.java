package app.studyspace.waswaolunga.minesweeper.controller;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * The ToolbarSetup class handles setting up
 * the toolbar for a screen.
 *
 * @author Safari & Waswa Olunga
 */

public class ToolbarSetup {

    public static void setupScreenToolbar(AppCompatActivity activity, int toolbarID, int subtitleResourceID){

        Toolbar toolbar = activity.findViewById(toolbarID);
        activity.setSupportActionBar(toolbar);

        toolbar.setSubtitle(subtitleResourceID);
    }

}