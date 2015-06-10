package pz2015.habits.semestralny_l.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

import pz2015.habits.semestralny_l.Helpers.AppConfig;
import pz2015.habits.semestralny_l.R;
import pz2015.habits.semestralny_l.Helpers.SessionManager;
import pz2015.habits.semestralny_l.Helpers.myButton;


public class BoardActivity extends MY_Activity {

    private static int NUM_ROWS;
    private static int NUM_COLS;

    myButton myButtons[][];

    private SessionManager sessionManager;

    private int typeOfGame;

    private long startTime;
    private long difference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(getApplicationContext());
        typeOfGame = sessionManager.getTypeOfGame();

        super.onCreate(savedInstanceState);

        setBoardSize();

        NUM_ROWS = sessionManager.getLevelI();
        NUM_COLS = sessionManager.getLevelI();

        myButtons = new myButton[NUM_ROWS][NUM_COLS];

        // populate my buttons
        populatemyButtons();

        // randomize colors :)
        randomize();

        startTime = System.currentTimeMillis();
    }

    private void setBoardSize() {
        if (typeOfGame == AppConfig.TypeOfGame.TestYourMight.getI()) {

        }
        else if (typeOfGame == AppConfig.TypeOfGame.CustomGame.getI()) {

        }
        else {

        }
    }

    private void populatemyButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            //set row layout
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++) {

                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                myButton myButton = new myButton(this);

                //myButton onclick listener
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridMyButtonClicked(FINAL_COL, FINAL_ROW);
                        //WIN
                        if (checkAllButtons()) {
                            endGame();
                        }
                    }
                });
                tableRow.addView(myButton);
                myButtons[row][col] = myButton;
            }
        }
    }

    private void endGame() {
        difference = System.currentTimeMillis() - startTime;

        sessionManager.setTime(difference);

        Intent intent = new Intent(BoardActivity.this, EndGameActivity.class);
        startActivity(intent);
        finish();
    }

    private void gridMyButtonClicked(int col, int row) {
        myButtons[row][col].checkColor();

        if (col - 1 >= 0)
            myButtons[row][col - 1].checkColor();

        if (col + 1 < NUM_COLS)
            myButtons[row][col + 1].checkColor();

        if (row - 1 >= 0)
            myButtons[row - 1][col].checkColor();

        if (row + 1 < NUM_ROWS)
            myButtons[row + 1][col].checkColor();

    }

    public boolean checkAllButtons() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (myButtons[row][col].getWhichColorIs() == false)
                    return false;
            }
        }
        return true;
    }

    private int generateRandomWithRange(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    private void randomize() {
        for (int i = 0; i < NUM_ROWS * NUM_COLS; i++) {
            int x = generateRandomWithRange(NUM_ROWS - 1, 0);
            int y = generateRandomWithRange(NUM_COLS - 1, 0);

            gridMyButtonClicked(y, x);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BoardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_board;
    }

}
