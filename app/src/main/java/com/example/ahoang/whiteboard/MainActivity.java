package com.example.ahoang.whiteboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends Activity {

    private EditText editText;
    private TextView outputLabel;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText.setText("hackdartmouth 2015!", TextView.BufferType.NORMAL);
        editText.requestFocus();

        outputLabel = (TextView) findViewById(R.id.textView3);
        outputLabel.setText("don't you wish this was a real terminal?");

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://whiteboardapp.firebaseio.com/");
        myFirebaseRef.child("Output").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editText.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                editText.setText(firebaseError.toString());
            }
        });

        Button compileButton = (Button) findViewById(R.id.compileButton);
        compileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                System.out.println("Clicked");
                String output = editText.getText().toString();
                writeToFile(output);
                System.out.print(output);
            }
        });
    }

    private void writeToFile(String data) {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("output.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
            myFirebaseRef.child("Output").setValue(data);
            myFirebaseRef.child("compiled").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    outputLabel.setText(dataSnapshot.getValue().toString());
                    System.out.print("success");
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println(firebaseError.toString());
                }
            });
    }

}
