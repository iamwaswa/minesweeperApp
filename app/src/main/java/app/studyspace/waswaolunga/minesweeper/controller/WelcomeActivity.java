package app.studyspace.waswaolunga.minesweeper.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.studyspace.waswaolunga.minesweeper.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ToolbarSetup.setupScreenToolbar(WelcomeActivity.this, R.id.welcome_activity_toolbar, R.string.welcome_activity_subtitle);

        setupStartGameBtn();
    }

    private void setupStartGameBtn() {

        Button startGameBtn = findViewById(R.id.start_game_btn);

        startGameBtn.setOnClickListener((View view) ->
            startActivity(GameActivity.makeIntent(getApplicationContext()))
        );
    }

}