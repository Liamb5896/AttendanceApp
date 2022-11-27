package com.example.testapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

// Class to manage and initialize SQL lite database
public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "student";
    private static final String COLUMN_ID = "studentID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_COURSE = "course";

    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Student Table
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_COURSE + " TEXT);";

        db.execSQL(query);


        //Teacher Table
        String query2 = "CREATE TABLE " + "Teacher" + " (" + "teacher_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name" + " TEXT, " +
                "email" + " TEXT);";

        db.execSQL(query2);


        //Session Table
        String query3 = "CREATE TABLE " + "Session" + " (" + "session_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "teacher_id" + " INTEGER, " +
                "unit" + " TEXT, " +
                "session_type" + " TEXT, " +
                "start_time" + " TEXT, " +
                "end_time" + " TEXT, " +
                "room_id" + " TEXT);";

        db.execSQL(query3);

        // Session enrollment table
        String query4 = "CREATE TABLE " + "Session_Enrollment" + " (" + "session_enrollment_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "session_id" + " INTEGER, " +
                "student_id" + " TEXT);";

        db.execSQL(query4);

        //Attendance Table
        String query5= "CREATE TABLE " + "Attendance" + " (" + "attendance_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "session_id" + " INTEGER, " +
                "student_id" + " TEXT," +
                "arrival_time" + " TEXT);";

        db.execSQL(query5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS Teacher");
        db.execSQL("DROP TABLE IF EXISTS Session");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        onCreate(db);
    }


    // Create and initialize database with dummy data
    void initDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete all tables before creating them fresh
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS Teacher");
        db.execSQL("DROP TABLE IF EXISTS Session");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        db.execSQL("DROP TABLE IF EXISTS Session_Enrollment");
        onCreate(db);

        addStudent("100568964", "Liam Bradley", "100568964@student.swin.edu.au", "Software Engineering");
        addStudent("100111222", "Sean Bradley", "100111222@student.swin.edu.au", "Electrical Engineering");
        addStudent("100222333", "James Baker", "100222333@student.swin.edu.au", "Software Engineering");
        addStudent("100333444", "Lilly Smith", "100333444@student.swin.edu.au", "Software Engineering");
        addStudent("100444555", "Katherine James", "100444555@student.swin.edu.au", "Electrical Engineering");
        addStudent("100555666", "Jimmy May", "100555666@student.swin.edu.au", "Software Engineering");
        addStudent("100666777", "James Clarke", "100666777@student.swin.edu.au", "Software Engineering");
        addStudent("100777888", "Sarah Mitchell", "100777888@student.swin.edu.au", "Mechanical Engineering");
        addStudent("100888999", "Lily Johns", "100888999@student.swin.edu.au", "Mechanical Engineering");
        addStudent("100999999", "Liam Baker", "100999999@student.swin.edu.au", "Software Engineering");

        addTeacher("Ronald Bartels", "ronald@teacher.swin.edu.au");
        addTeacher("John Smith", "john@teacher.swin.edu.au");

        addSession(2, "EEE30005", "Workshop", "2022-10-29 16:30", "2022-11-29 18:30", "BA201");
        addSession(2, "EEE30005", "Lecture", "2022-10-25 12:30", "2022-10-25 13:30", "ATC101");
        addSession(1, "EEE30005", "Workshop", "2022-10-27 16:30", "2022-11-27 18:30", "BA203");
        addSession(1, "EEE30005", "Tutorial", "2022-10-31 12:30", "2022-11-31 22:30", "BA201");

        addSessionEnrollment("100568964", 4);
        addSessionEnrollment("100111222", 4);
        addSessionEnrollment("100222333", 4);
        addSessionEnrollment("100333444", 4);
        addSessionEnrollment("100444555", 4);
        addSessionEnrollment("100555666", 4);
        addSessionEnrollment("100666777", 4);
        addSessionEnrollment("100777888", 4);
        addSessionEnrollment("100888999", 4);
        addSessionEnrollment("100999999", 4);

        addSessionEnrollment("100568964", 3);
        addSessionEnrollment("100111222", 3);
        addSessionEnrollment("100222333", 3);
        addSessionEnrollment("100333444", 3);
        addSessionEnrollment("100444555", 3);
        addSessionEnrollment("100555666", 3);
    }

    // Check student login
    String loginStudent(String studentID)
    {
        String query = "SELECT * FROM Student WHERE studentID = '" + studentID + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToNext())
        {
            return cursor.getString(1);
        }
        else
        {
            return "None";
        }
    }

    // Add a student to the database
    void addStudent(String studentID, String name, String email, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("studentID", studentID);
        cv.put("name", name);
        cv.put("email", email);
        cv.put("course", course);
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    // Add a session enrollment to the database
    void addSessionEnrollment(String studentID, int session_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("session_id", session_id);
        cv.put("student_id", studentID);

        long result = db.insert("Session_Enrollment", null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    // add an attendance to database
    void addAttendance(int session_id, String student_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("session_id", session_id);
        cv.put("student_id", student_id);

        long result = db.insert("Attendance", null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    // Add session to the database
    void addSession(int teacher_id, String unit_id, String sessionType, String start_time, String end_time, String roomID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("teacher_id", teacher_id);
        cv.put("unit", unit_id);
        cv.put("session_type", sessionType);
        cv.put("start_time", start_time);
        cv.put("end_time", end_time);
        cv.put("room_id", roomID);

        db.insert("Session", null, cv);
    }

    // Add teacher to the database
    void addTeacher(String name, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("email", email);

        db.insert("Teacher", null, cv);
    }

    // Check student into a session / adds to database
    boolean MakeAttendance(String roomID, String studentID)
    {
        String query = "SELECT session_id FROM Session WHERE room_id = '" + roomID + "' AND (start_time < datetime(CURRENT_TIMESTAMP, 'localtime')) AND (end_time > datetime(CURRENT_TIMESTAMP, 'localtime'))";
        SQLiteDatabase db = this.getWritableDatabase();
        int session_id = -1;
        Cursor cursor = null;

        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this.context, "Couldn't find session", Toast.LENGTH_SHORT);
        }
        else
        {
            while (cursor.moveToNext())
            {
                session_id = cursor.getInt(0);
            }
        }

        if (session_id != -1)
        {
            SQLiteDatabase db2 = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            Date time = new java.util.Date(System.currentTimeMillis());
            String time2 = new SimpleDateFormat("HH:mm").format(time);

            cv.put("session_id", session_id);
            cv.put("student_id", studentID);
            cv.put("arrival_time", time2);
            Toast.makeText(this.context, "Added attendance", Toast.LENGTH_SHORT);
            db.insert("Attendance", null, cv);
            return true;

        }
        else
        {
            return false;
        }

    }

    // Check which session is currently in the room and returns its name
    String getSessionTitle(String roomID)
    {
        String query = "SELECT unit, session_type, start_time, end_time FROM Session WHERE room_id = '" + roomID + "' AND (start_time < datetime(CURRENT_TIMESTAMP, 'localtime')) AND (end_time > datetime(CURRENT_TIMESTAMP, 'localtime'))";
        SQLiteDatabase db = this.getWritableDatabase();
        int session_id = -1;
        Cursor cursor = null;
        String title = "";

        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this.context, "Couldn't find session", Toast.LENGTH_SHORT);

        }
        else
        {
            while (cursor.moveToNext())
            {
                title += cursor.getString(0) + " " + cursor.getString(1) + " (" + cursor.getString(2) + ")";
                return title;
            }
        }
        return title;
    }

    // Get all students
    Cursor getStudents()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    // Get all attendances
    Cursor getAttendance()
    {
        String query = "SELECT * FROM Attendance";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    // Get all students enrolled in a session
    Cursor GetSessionEnrollment(int session_id)
    {
        String query = "SELECT * FROM Session_Enrollment WHERE session_id = " + session_id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    // Check if a student has checked into class and return their check in time, otherwise return absent
    String CheckStudentAttendance(String studentID, int session_id)
    {
        String query = "SELECT arrival_time FROM Attendance WHERE session_id = " + session_id + " AND student_id = '" + studentID + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToNext())
        {
            return cursor.getString(0);
        }
        else
        {
            return "Absent";
        }
    }

    // Get a students name from their student ID
    String GetStudentName(String studentID)
    {
        String query = "SELECT name FROM Student WHERE studentID = '" + studentID + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToNext())
        {
            return cursor.getString(0);
        }
        else
        {
            return "Unknown Student";
        }
    }

}
