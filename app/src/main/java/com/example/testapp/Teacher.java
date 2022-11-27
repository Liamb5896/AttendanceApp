package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// Page for teacher login
public class Teacher extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);

        Button teacherButton1 = findViewById(R.id.teacherButton1);
        Button teacherButton2 = findViewById(R.id.teacherButton2);
        Button viewStudentBtn = findViewById(R.id.viewStudentBtn);
        Button teacherLogoutBtn = findViewById(R.id.teacherLogoutBtn);

        teacherButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Teacher.this, SessionAttendance.class);
                i.putExtra("sessionID", 4);
                startActivity(i);
            }
        });

        teacherButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Teacher.this, SessionAttendance.class);
                i.putExtra("sessionID", 3);
                startActivity(i);
            }
        });

        viewStudentBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Teacher.this, SemesterAttendance.class);
                startActivity(i);
            }
        });

        teacherLogoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(Teacher.this, Login.class);
                startActivity(i);
            }
        });

    }
}
