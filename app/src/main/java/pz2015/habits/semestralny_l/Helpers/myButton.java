package pz2015.habits.semestralny_l.Helpers;

import android.content.Context;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import pz2015.habits.semestralny_l.R;

/**
 * Created by ASUS on 2015-06-08.
 */
public class myButton extends Button {

    private boolean whichColorIs = true;

    public myButton(Context context) {
        super(context);

        //set myButton layout
        this.setLayoutParams(new TableRow.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
        ));

        //myButton setBackground
        setBackgroundDrawable(getResources().getDrawable(R.drawable.greenbutton));
    }

    public boolean getWhichColorIs() { return this.whichColorIs; }

    public void checkColor() {
        if (whichColorIs == true) {
            this.setBackgroundDrawable(getResources().getDrawable(R.drawable.redbutton));
            whichColorIs = false;
        }
        else {
            this.setBackgroundDrawable(getResources().getDrawable(R.drawable.greenbutton));
            whichColorIs = true;
        }
    }

}
