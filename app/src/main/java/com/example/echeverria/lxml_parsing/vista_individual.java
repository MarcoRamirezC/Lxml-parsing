package com.example.marc.lxml_parsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class vista_individual  extends Activity {

    // XML node keys
    static final String KEY_ID_A = "id_A";
    static final String KEY_NOMBRE = "nombre";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous intent
        String name = in.getStringExtra(KEY_ID_A);
        String cost = in.getStringExtra(KEY_NOMBRE);

        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.txtid);
        TextView lblCost = (TextView) findViewById(R.id.txtnombre);

        lblName.setText(name);
        lblCost.setText(cost);
    }
}
