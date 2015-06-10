package pz2015.habits.semestralny_l.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;

import pz2015.habits.semestralny_l.R;

public class StatisticsActivity extends MY_Activity {

    TextView txtStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        txtStatistics = (TextView) findViewById(R.id.txtStatistics);

        long array1[] = new long[sessionManager.getStatisticsSize()];
        int array2[] = new int[sessionManager.getStatisticsSize()];

        for (int i = 0; i < sessionManager.getStatisticsSize(); i++) {
            array1[i] = sessionManager.getStatisticsTime(i);
            array2[i] = sessionManager.getStatisticsLevel(i);
        }

        txtStatistics.setText(Arrays.toString(array1) + " " + Arrays.toString(array2));
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
