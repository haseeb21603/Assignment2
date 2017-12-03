package com.example.haseeb.assignment;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovoi7.assignment.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    LocalDate localDate = LocalDate.now();
    DBHandler myDb;
    DayOfWeek dayOfWeek = localDate.getDayOfWeek();
    String day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    Button btnviewAll;
    Button btnviewtoday;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHandler(this);

      //  editName = (EditText)findViewById(R.id.editText_Name);
      //  editStart = (EditText)findViewById(R.id.editText_Start);
      //  editDay = (EditText)findViewById(R.id.editText_Day);
      //  editEnds = (EditText)findViewById(R.id.editText_End);
     //   btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewtoday = (Button)findViewById(R.id.button_viewtoday);
     //   btnviewUpdate= (Button)findViewById(R.id.button_update);
     //   btnDelete= (Button)findViewById(R.id.button_delete);
     //   AddData();
        viewAll();
        viewToday();

     //   UpdateData();
     //   DeleteData();
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Day :"+ res.getString(1)+"\n");
                            buffer.append("Starts :"+ res.getString(2)+"\n");
                            buffer.append("Ends :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void viewToday() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :"+ res.getString(0)+"\n");
                            buffer.append("Day :"+ res.getString(1)+"\n");
                            buffer.append("Starts :"+ res.getString(2)+"\n");
                            buffer.append("Ends :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}