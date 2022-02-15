package com.example.tempconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
    import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity {
    private EditText fahrenheitIn;
    private TextView celsiusOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get widgits
        fahrenheitIn = (EditText) findViewById(R.id.txtFahrenheitIn);
        celsiusOut = (TextView) findViewById(R.id.lblCelsiusOut);

        // set listener
        fahrenhietIn.setOnEventActionListener(this);
    }

    public boolean onEventAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            celsiusOut.setText(fahrenhietIn.toCelsius().toString());
        }
        return false;
    }
    
    @Override
    public void onPause() {
        Editor editor = savedValues.edit();         
        editor.putString("fahrenheitIn", fahrenheitIn); 
        editor.putFloat("celsiusOut", celsiusOut); 
        editor.commit();         
        
        super.onPause();
    }

    @Override 
    public void onResume() { 
        super.onResume(); 

        // get the instance variables 
        billAmountString = savedValues.getString( 
            "fahrenheitIn", ""); 
        tipPercent = savedValues.getFloat("celsiusOut", 0.2f); 
    }

    public static double toCelsius(double fahrenhiet) {
        return (fahrenhiet - 32) * 5 / 9;
    }
}