package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SemesterAttendance  extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semester_attendance);

        Button backToTeacherBtn = findViewById(R.id.backToTeacherBtn);

        backToTeacherBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(SemesterAttendance.this, Teacher.class);
                i.putExtra("sessionID", 3);
                startActivity(i);
            }
        });

    }
}
