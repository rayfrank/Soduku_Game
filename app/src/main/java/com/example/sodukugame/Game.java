package com.example.sodukugame;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private SudokuGame game;
    private EditText[][] cells = new EditText[9][9]; // To store the references to the EditTexts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize the game logic
        game = new SudokuGame();

        // Dynamically create the Sudoku grid
        generateSudokuGrid();

        // Set up the Validate button
        findViewById(R.id.validate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readInput(); // Get the user's input
                if (game.isGameComplete()) {
                    Toast.makeText(Game.this, "Congratulations! You've completed the puzzle.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Game.this, "There are still empty cells or mistakes.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Method to dynamically generate the Sudoku grid with prefilled numbers and editable cells
    private void generateSudokuGrid() {
        TableLayout table = findViewById(R.id.sudoku_grid);

        for (int row = 0; row < 9; row++) {
            TableRow tableRow = new TableRow(this);
            for (int col = 0; col < 9; col++) {
                if (game.getBoard()[row][col] == 0) {
                    // Empty cell, create an editable EditText
                    EditText editText = new EditText(this);
                    editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                    editText.setMaxLines(1);
                    editText.setGravity(android.view.Gravity.CENTER);
                    editText.setTextSize(18);
                    editText.setLayoutParams(new TableRow.LayoutParams(100, 100));
                    editText.setBackgroundResource(android.R.drawable.editbox_background);
                    cells[row][col] = editText; // Store the reference for later use
                    tableRow.addView(editText);
                } else {
                    // Prefilled cell, create a non-editable TextView
                    TextView textView = new TextView(this);
                    textView.setText(String.valueOf(game.getBoard()[row][col]));
                    textView.setGravity(android.view.Gravity.CENTER);
                    textView.setTextSize(18);
                    textView.setLayoutParams(new TableRow.LayoutParams(100, 100));
                    tableRow.addView(textView);
                }
            }
            table.addView(tableRow);
        }
    }

    // Method to read user input from the editable cells and update the game board
    private void readInput() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (cells[row][col] != null) {
                    String input = cells[row][col].getText().toString();
                    if (!input.isEmpty()) {
                        int value = Integer.parseInt(input);
                        game.updateBoard(row, col, value);  // Update the game logic with the user's input
                    }
                }
            }
        }
    }
}
