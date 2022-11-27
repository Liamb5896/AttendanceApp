package com.example.testapp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SessionAttendance extends AppCompatActivity
{
    MyDatabaseHelper myDB;

    ArrayList<String> studentID;
    ArrayList<String> studentName;
    ArrayList<String> attendanceTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_attendance);

        myDB = new MyDatabaseHelper(SessionAttendance.this);
        studentID = new ArrayList<>();
        studentName = new ArrayList<>();
        attendanceTime = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        int sessionID = extras.getInt("sessionID");

        displayStudents(sessionID);
        init();
    }

    void displayStudents(int sessionID)
    {
        Cursor cursor = myDB.GetSessionEnrollment(sessionID);

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Students", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                studentID.add(cursor.getString(2));
                attendanceTime.add(myDB.CheckStudentAttendance(cursor.getString(2), sessionID));
                studentName.add(myDB.GetStudentName(cursor.getString(2)));
            }
        }
    }

    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Num ");
        tv0.setTextColor(Color.WHITE);
        tv0.setTextSize(18);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Name ");
        tv1.setTextColor(Color.WHITE);
        tv1.setTextSize(18);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Student Number ");
        tv2.setTextColor(Color.WHITE);
        tv2.setTextSize(18);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Present ");
        tv3.setTextColor(Color.WHITE);
        tv3.setTextSize(18);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i = 0; i < studentID.size(); i++) {
            //Button tempBtn = new Button(this);
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            t1v.setTextSize(18);
            tbrow.addView(t1v);
            //tbrow.addView(tempBtn);
            TextView t2v = new TextView(this);
            t2v.setText(studentName.get(i));
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            t2v.setTextSize(18);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(studentID.get(i));
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            t3v.setTextSize(18);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText(attendanceTime.get(i));
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            t4v.setTextSize(18);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }

}

