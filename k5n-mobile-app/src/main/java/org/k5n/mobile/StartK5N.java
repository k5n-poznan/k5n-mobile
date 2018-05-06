package org.k5n.mobile;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import org.k5n.mobile.admin.BuildConfig;
import org.k5n.mobile.admin.R;

/**
 * StartK5N is a start of IBak Blik the Maven Android
 Plugin. 
 *
 * @author Waldemar Klaczynski
 */
public class StartK5N extends Activity {

    TableLayout table;
    Button redButton;
    Button greenButton;
    Button blueButton;
    Button blackButton;
    Button whiteButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // get all the view components
        table = (TableLayout) findViewById(R.id.Table);
        redButton = (Button) findViewById(R.id.ButtonRed);
        greenButton = (Button) findViewById(R.id.ButtonGreen);
        blueButton = (Button) findViewById(R.id.ButtonBlue);
        blackButton = (Button) findViewById(R.id.ButtonBlack);
        whiteButton = (Button) findViewById(R.id.ButtonWhite);

        // default the full screen to white
        table.setBackgroundColor(Color.WHITE);

        // hook up all the buttons with a table color change on click listener
        redButton.setOnClickListener(OnClickChangeColor(Color.RED));
        greenButton.setOnClickListener(OnClickChangeColor(Color.GREEN));
        blueButton.setOnClickListener(OnClickChangeColor(Color.BLUE));
        blackButton.setOnClickListener(OnClickChangeColor(Color.BLACK));
        whiteButton.setOnClickListener(OnClickChangeColor(Color.WHITE));
        
        TextView tv = (TextView)findViewById(R.id.buildInfo);
        StringBuilder sb = new StringBuilder();
        
        String vn = BuildConfig.VERSION_NAME;
        
        tv.setText(sb.toString());



    }

    /**
     * An OnClickListener that changes the color of the table.
     *
     * @param color
     * @return
     */
    View.OnClickListener OnClickChangeColor(final int color) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table.setBackgroundColor(color);
            }
        };
    }
}
