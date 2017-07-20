package com.example.android.habittrack;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    HabitContract myDb;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new HabitContract(this);

        btnviewAll = (Button) findViewById(R.id.buttonViewAll);
        viewAll();
        GenerateInformationToDatabase();

    }


    public void GenerateInformationToDatabase() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            boolean isInserted1 = myDb.insertData("Monday", (i * 10), (1.2 * 4));
            boolean isInserted2 = myDb.insertData("Tuesday", (i * 10), (1.2 * 4));
            boolean isInserted3 = myDb.insertData("Thursday", (i * 10), (1.2 * 4));
            boolean isInserted4 = myDb.insertData("Wensday", (i * 10), (1.2 * 4));
            boolean isInserted5 = myDb.insertData("Friday", (i * 10), (1.2 * 4));
            boolean isInserted6 = myDb.insertData("Saturday", (i * 10), (1.2 * 4));
            boolean isInserted7 = myDb.insertData("Sunday", (i * 10), (1.2 * 4));
        }

    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Miles :" + res.getString(2) + "\n");
                            buffer.append("Calories :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}