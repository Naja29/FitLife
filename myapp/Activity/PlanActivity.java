package com.project.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;
import android.widget.EditText;

import com.project.myapp.R;
import com.project.myapp.Database.TaskDatabase;

public class PlanActivity extends AppCompatActivity {

    TaskDatabase taskDatabase;
    EditText id, taskName, taskDuration, taskStatus;
    Button addTask, viewTask ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        taskDatabase = new TaskDatabase(this);
        taskName = findViewById(R.id.taskName);
        taskDuration = findViewById(R.id.taskDuration);
        taskStatus = findViewById(R.id.taskStatus);

        addTask = findViewById(R.id.addTask);
        viewTask = findViewById(R.id.viewTask);

        addTask();
        viewTask();
    }

    // Method to handle the addition of a task
    public void addTask() {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = taskDatabase.insertData(taskName.getText().toString(),
                        taskDuration.getText().toString(), taskStatus.getText().toString());
                if (isInserted == true)
                    Toast.makeText(PlanActivity.this,
                            "Task has been successfully added",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PlanActivity.this,
                            "Task has not been successfully added",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to handle the viewing of tasks
    public void viewTask() {
        viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = taskDatabase.viewData();
                if (result.getCount() == 0) {
                    showMessage("No task found", "Error");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("Task Id : " + result.getString(0) + "\n");
                    buffer.append("Task Name : " + result.getString(1) + "\n");
                    buffer.append("Task Status : " + result.getString(2) + "\n");
                    buffer.append("Task Duration : " + result.getString(3) + "\n\n\n");
                }
                showMessage("List of Tasks : ", buffer.toString());
            }
        });
    }

    // Method to display an alert dialog with a message
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}