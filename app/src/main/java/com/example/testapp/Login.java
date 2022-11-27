package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TODO Make proper secure login system


public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        TextView studentID = (TextView) findViewById(R.id.studentID);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.loginbtn);
        Button resetBtn = findViewById(R.id.resetBtn);

        // Log in button pressed
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Login.this);
                //myDB.initDatabase();

                // Get student name from database helper class
                String student = myDB.loginStudent(studentID.getText().toString());
                if (student != "None")
                {
                    Intent i = new Intent(Login.this, MainActivity.class);
                    i.putExtra("studentName", student);
                    i.putExtra("studentID", studentID.getText().toString());
                    startActivity(i);
                } else if (studentID.getText().toString().equals("Ronald") && password.getText().toString().equals("1234")) // Teacher instead
                {
                    startActivity(new Intent(Login.this, Teacher.class));
                } else
                {
                    // No student or teacher with that login
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Reset database button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Login.this);
                myDB.initDatabase();
            }
        });
    }
}
