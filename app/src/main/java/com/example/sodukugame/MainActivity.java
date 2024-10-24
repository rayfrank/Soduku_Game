package com.example.sodukugame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up click listeners for all buttons
        View continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);

        View newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);

        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);

        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.about_button) {
            Intent i = new Intent(this, About.class);
            startActivity(i);
        } else if (id == R.id.continue_button)
        {
            Intent j   =  new Intent (this, Game.class );
            startActivity(j);
            // Handle continue button click
        } else if (id == R.id.new_button) {
            // Handle new game button click
        } else if (id == R.id.exit_button) {
            // Handle exit button click
        }
    }
}
