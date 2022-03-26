package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfA;
    EditText editTextSubValueOfB;
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String A_KEY = "A_KEY";
    public final static String B_KEY = "B_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String onPauseEditTextAString = mPreferences.getString(A_KEY, "");
        String onPauseEditTextBString = mPreferences.getString(B_KEY, "");



        //TODO 3.5 Get references to the editText widgets
        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfA.setText(onPauseEditTextAString);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);
        editTextSubValueOfB.setText(onPauseEditTextBString);
        //TODO 3.6 Get a reference to the Back To Calculator Button
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        //TODO 3.7 Set up setOnClickListener
        buttonBackToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextA = editTextSubValueOfA.getText().toString();
                String editTextB = editTextSubValueOfB.getText().toString();

                try {
                    Utils.checkInvalidInputs(editTextA);
                    Utils.checkInvalidInputs(editTextB);
                }
                catch(Exception e){
                    editTextA = null;
                    editTextB = null;
                }

                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                intent.putExtra(A_KEY, editTextA);
                intent.putExtra(B_KEY, editTextB);
                startActivity(intent);
            }
        });
        //TODO 3.8 Obtain the values stored in the editTextWidgets
        //TODO 3.9 Check that these values are valid --> See the Utils class
        //TODO 3.10 Set up an explicit intent and pass the exchange rate back to MainActivity
        //TODO 3.11 Decide how you are going to handle a divide-by-zero situation or when negative numbers are entered
        //TODO 3.12 Decide how you are going to handle a situation when the editText widgets are empty

    }

    //TODO 4.10 Don't forget to override onPause()

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(A_KEY, editTextSubValueOfA.getText().toString());
        preferencesEditor.putString(B_KEY, editTextSubValueOfB.getText().toString());
        preferencesEditor.apply();
    }

}

