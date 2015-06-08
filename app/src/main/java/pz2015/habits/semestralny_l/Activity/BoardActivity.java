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

    long startTime;
    long difference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(getApplicationContext());

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_board);

        NUM_ROWS = sessionManager.getLevel();
        NUM_COLS = sessionManager.getLevel();

        myButtons = new myButton[NUM_ROWS][NUM_COLS];

        // populate my buttons
        populatemyButtons();

        // randomize colors :)
        //randomize();

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

                //myButton setText
                myButton.setText("" + row + "," + col);
                //padding text
                myButton.setPadding(0, 0, 0 ,0);
                //myButton setBackground
                myButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.greenbutton));
                //myButton onclick listener
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridMyButtonClicked(FINAL_COL, FINAL_ROW, myButtons);
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

    private void gridMyButtonClicked(int col, int row, myButton[][] array) {
        myButton button = array[row][col];
        button.checkColor();

        if (col == 0 && row == 0) {
            myButton myButton0 = array[row + 1][col];
            myButton myButton1 = array[row][col + 1];

            myButton0.checkColor();
            myButton1.checkColor();
        }
        else if (col == NUM_COLS - 1 && row == 0) {
            myButton myButton0 = array[row + 1][col];
            myButton myButton1 = array[row][col - 1];

            myButton0.checkColor();
            myButton1.checkColor();
        }
        else if (col == 0 && row == NUM_ROWS - 1) {
            myButton myButton0 = array[row - 1][col];
            myButton myButton1 = array[row][col + 1];

            myButton0.checkColor();
            myButton1.checkColor();
        }
        else  if (col == NUM_COLS - 1 && row == NUM_ROWS - 1) {
            myButton myButton0 = array[row - 1][col];
            myButton myButton1 = array[row][col - 1];

            myButton0.checkColor();
            myButton1.checkColor();
        }
        else if (col == 0 && row > 0) {
            myButton myButton0 = array[row - 1][col];
            myButton myButton1 = array[row][col + 1];
            myButton myButton2 = array[row + 1][col];

            myButton0.checkColor();
            myButton1.checkColor();
            myButton2.checkColor();
        }
        else if (col > 0 && row == 0) {
            myButton myButton0 = array[row][col - 1];
            myButton myButton1 = array[row + 1][col];
            myButton myButton2 = array[row][col + 1];

            myButton0.checkColor();
            myButton1.checkColor();
            myButton2.checkColor();
        }
        else if (col == NUM_COLS - 1 && row > 0) {
            myButton myButton0 = array[row - 1][col];
            myButton myButton1 = array[row][col - 1];
            myButton myButton2 = array[row + 1][col];

            myButton0.checkColor();
            myButton1.checkColor();
            myButton2.checkColor();
        }
        else if (col > 0 && row == NUM_ROWS - 1) {
            myButton myButton0 = array[row][col - 1];
            myButton myButton1 = array[row - 1][col];
            myButton myButton2 = array[row][col + 1];

            myButton0.checkColor();
            myButton1.checkColor();
            myButton2.checkColor();
        }
        else {
            myButton myButton0 = array[row - 1][col];
            myButton myButton1 = array[row + 1][col];
            myButton myButton2 = array[row][col - 1];
            myButton myButton3 = array[row][col + 1];

            myButton0.checkColor();
            myButton1.checkColor();
            myButton2.checkColor();
            myButton3.checkColor();
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

    private void randomize() {
        for (int i = 0; i < myButtons.length; i++) {
            for (int j = 0; j < myButtons[i].length; j++) {
                Random r = new Random();
                myButtons[i][j].setColor(r.nextBoolean());
                myButtons[i][j].checkColor();
            }
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
}
