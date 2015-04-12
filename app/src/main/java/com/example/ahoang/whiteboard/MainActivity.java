package com.example.ahoang.whiteboard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText.setText("hackdartmouth 2015!", TextView.BufferType.NORMAL);
        editText.requestFocus();
    }

}
