package com.example.tempconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    // declare fahrenheit & celsuis values
    private EditText fahrenheitIn;
    private TextView celsiusOut;

    // create sharedPreferences obj
    private SharedPreferences savedValues;

    // create a string & float
    private String fahrenheitInString = "";
    private float celsiusOutFloat = .2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get widgets
        fahrenheitIn = (EditText) findViewById(R.id.txtFahrenheitIn);
        celsiusOut = (TextView) findViewById(R.id.lblCelsiusOut);

        // set listener
        fahrenheitIn.setOnEditorActionListener(this);

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    public void setCelsius() {
        fahrenheitInString = fahrenheitIn.getText().toString();
        float fehren;
        fehren = float.parseFloat(fahrenheitInString);
        celsiusOut.setText((fehren - 32) * 5 / 9);
    }

    @Override
    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("fahrenheitIn", fahrenheitInString);
        editor.putFloat("celsiusOut", celsiusOutFloat);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        fahrenheitInString = savedValues.getString("fahrenheitInString", "");
        celsiusOutFloat = savedValues.getFloat("celsiusOutFloat", 0.2f);

        super.onResume();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            setCelsius();
        }
        return false;
    }
}