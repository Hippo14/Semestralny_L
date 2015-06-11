package pz2015.habits.semestralny_l.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import pz2015.habits.semestralny_l.Helpers.AppConfig;
import pz2015.habits.semestralny_l.Helpers.ConnectionManager;
import pz2015.habits.semestralny_l.R;

public class StatisticsActivity extends MY_Activity {

    TextView txtAverageTime;
    TextView txtAverageBoardSize;
    TextView txtAverageMovements;

    Button btnSendStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        final int arraySize = sessionManager.getStatisticsSize();

        txtAverageTime = (TextView) findViewById(R.id.average_time);
        txtAverageBoardSize = (TextView) findViewById(R.id.average_board_size);
        txtAverageMovements = (TextView) findViewById(R.id.average_movements);
        btnSendStatistics = (Button) findViewById(R.id.btnSendStatistics);

        long array1[] = new long[arraySize];
        int array2[] = new int[arraySize];
        int array3[] = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array1[i] = sessionManager.getStatisticsTime(i);
            array2[i] = sessionManager.getStatisticsLevel(i);
            array3[i] = sessionManager.getStatisticsMovements(i);
        }

        long averageTime = 0;
        int averageBoardSize = 0;
        int averageMovements = 0;

        for (int i = 0; i < arraySize; i ++) {
            averageTime += array1[i];
            averageBoardSize += array2[i];
            averageMovements += array3[i];
        }

        txtAverageTime.setText( averageTime == 0 ? "0" : String.valueOf((double)(averageTime / (arraySize * 1000)))  );
        txtAverageBoardSize.setText( averageBoardSize == 0 ? "0" : String.valueOf(averageBoardSize / arraySize) + "x" + String.valueOf(averageBoardSize / arraySize) );
        txtAverageMovements.setText(String.valueOf( averageMovements == 0 ? "0" : averageMovements / arraySize) );

        final long finalAverageTime = averageTime;
        final int finalAverageBoardSize = averageBoardSize;
        final int finalAverageMovements = averageMovements;

        btnSendStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build list params
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("tag", AppConfig.TAG_SYNCHRO.toString()));
                list.add(new BasicNameValuePair("salt", sessionManager.getSalt()));
                list.add(new BasicNameValuePair("average_time", Long.toString(finalAverageTime / arraySize) ));
                list.add(new BasicNameValuePair("average_board_size", Integer.toString(finalAverageBoardSize / arraySize) ));
                list.add(new BasicNameValuePair("average_movements", Integer.toString(finalAverageMovements / arraySize) ));

                ConnectionManager connectionManager = new ConnectionManager(StatisticsActivity.this, list);
                connectionManager.execute();
            }
        });
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
