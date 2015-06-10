package pz2015.habits.semestralny_l.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;

import pz2015.habits.semestralny_l.R;

public class StatisticsActivity extends MY_Activity {

    TextView txtAverageTime;
    TextView txtAverageBoardSize;
    TextView txtAverageMovements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        int arraySize = sessionManager.getStatisticsSize();

        txtAverageTime = (TextView) findViewById(R.id.average_time);
        txtAverageBoardSize = (TextView) findViewById(R.id.average_board_size);
        txtAverageMovements = (TextView) findViewById(R.id.average_movements);

        long array1[] = new long[arraySize];
        int array2[] = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array1[i] = sessionManager.getStatisticsTime(i);
            array2[i] = sessionManager.getStatisticsLevel(i);
        }

        long averageTime = 0;
        int averageBoardSize = 0;
        int averageMovements = sessionManager.getMovements();

        for (int i = 0; i < arraySize; i ++) {
            averageTime += array1[i];
            averageBoardSize += array2[i];
        }

        txtAverageTime.setText( averageTime == 0 ? "0" : String.valueOf((double)(averageTime / (arraySize * 1000)))  );
        txtAverageBoardSize.setText( averageBoardSize == 0 ? "0" : String.valueOf(averageBoardSize / arraySize) + "x" + String.valueOf(averageBoardSize / arraySize) );
        txtAverageMovements.setText(String.valueOf( averageMovements == 0 ? "0" : averageMovements / arraySize) );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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
        Intent intent = new Intent(StatisticsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_statistics;
    }

    @Override
    protected Context getContext() { return this.getApplicationContext(); }
}
