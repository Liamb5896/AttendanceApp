package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Date;

// Page after a student has checked into a class
public class CheckedIn  extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checked_in);
        Bundle extras = getIntent().getExtras();
        Button backBtn = findViewById(R.id.backbtn);

        String sessionText = "";
        MyDatabaseHelper myDB = new MyDatabaseHelper(CheckedIn.this);


        if (extras != null)
        {
            sessionText = myDB.getSessionTitle(extras.getString("roomID"));
        }

        // Get current time in 24 hour time
        // TODO convert to AM / PM
        Date time = new java.util.Date(System.currentTimeMillis());
        String time2 = new SimpleDateFormat("HH:mm").format(time);

        // Sets etxt to show the time a student checked in to class
        TextView session = findViewById(R.id.sessionName);
        TextView checkInTime = findViewById(R.id.checkInTime);
        session.setText(sessionText);
        checkInTime.setText("Checked in at " + time2);


        // Back buttonto go back to main check in page
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(CheckedIn.this, MainActivity.class);
                i.putExtra("sessionID", 4);
                startActivity(i);
            }
        });
    }
}
