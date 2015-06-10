package pz2015.habits.semestralny_l.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

import pz2015.habits.semestralny_l.R;
import pz2015.habits.semestralny_l.Helpers.SessionManager;
import pz2015.habits.semestralny_l.Helpers.myButton;


public class BoardActivity extends Activity {

    private static int NUM_ROWS;
    private static int NUM_COLS;

    myButton myButtons[][];

    private SessionManager sessionManager;

    int test[][] = new int[10][2];

    long startTime;
    long difference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(getApplicationContext());

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_board);

        NUM_ROWS = sessionManager.getLevelI();
        NUM_COLS = sessionManager.getLevelI();

        myButtons = new myButton[NUM_ROWS][NUM_COLS];

        // populate my buttons
        populatemyButtons();

        // randomize colors :)
        randomize();

        startTime = System.currentTimeMillis();
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

                myButton.setText(row + " , " + col);

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
        myButton button = myButtons[row][col];
        button.checkColor();

        if (col - 1 >= 0) {
            myButton button1 = myButtons[row][col - 1];
            button1.checkColor();
        }
        if (col + 1 < NUM_COLS) {
            myButton button1 = myButtons[row][col + 1];
            button1.checkColor();
        }
        if (row - 1 >= 0) {
            myButton button1 = myButtons[row - 1][col];
            button1.checkColor();
        }
        if (row + 1 < NUM_ROWS) {
            myButton button1 = myButtons[row + 1][col];
            button1.checkColor();
        }
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
        for (int i = 0; i < 5; i++) {
            int x = generateRandomWithRange(NUM_ROWS - 1, 0);
            int y = generateRandomWithRange(NUM_COLS - 1, 0);

            test[i][0] = x;
            test[i][1] = y;

            gridMyButtonClicked(y, x);
        }
//        for (int i = 0; i < myButtons.length; i++) {
//            for (int j = 0; j < myButtons[i].length; j++) {
//                Random r = new Random();
//                myButtons[i][j].setColor(r.nextBoolean());
//                myButtons[i][j].checkColor();
//            }
//        }
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
}
