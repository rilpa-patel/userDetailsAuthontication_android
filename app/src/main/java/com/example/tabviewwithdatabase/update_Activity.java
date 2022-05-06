package com.example.tabviewwithdatabase;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class update_Activity extends AppCompatActivity {

    EditText name, address, datetxt, income, Achievement;
    CheckBox maths,science,gujarati, english;
    Button btndate;
    RadioButton rmaths, rscience, rgujarati, renglish;
    int dobDate, month, year, totalRows;
    int currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = (EditText)findViewById(R.id.name);
        address = (EditText) findViewById(R.id.Address);
        datetxt = (EditText) findViewById(R.id.txtdate);
        income = (EditText) findViewById(R.id.Income);
        Achievement = (EditText)findViewById(R.id.Achievement);

        btndate = (Button) findViewById(R.id.btndate);

        maths = (CheckBox) findViewById(R.id.maths);
        science = (CheckBox) findViewById(R.id.science);
        gujarati = (CheckBox)findViewById(R.id.gujarati);
        english = (CheckBox) findViewById(R.id.english);

        rmaths = (RadioButton)findViewById(R.id.Rmaths);
        rscience = (RadioButton)findViewById(R.id.Rscience);
        rgujarati = (RadioButton)findViewById(R.id.Rgujarati);
        renglish = (RadioButton) findViewById(R.id.Renglish);

        dobDate = 1;
        month = 1;
        year = 2001;

        Intent i = getIntent();

        currentId = i.getIntExtra("id", 1);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Cursor c = databaseHelper.getDataById(Integer.toString(currentId));

        totalRows = databaseHelper.totalRows();

        while (c.moveToNext()){
            String strname = c.getString(1);
            String straddress = c.getString(2);
            String strdob = c.getString(3);
            Float anualincome = c.getFloat(4);
            String strweaksubject = c.getString(5);
            String strstrongsubject = c.getString(6);
            String strachievements = c.getString(7);

            name.setText(strname);
            address.setText(straddress);
            datetxt.setText(strdob);
            income.setText(Float.toString(anualincome));
            Achievement.setText(strachievements);

            String datearray[] = strdob.split("/");
            dobDate = Integer.parseInt(datearray[0]);
            month = Integer.parseInt(datearray[1]);
            year = Integer.parseInt(datearray[2]);

            switch (strstrongsubject){
                case "Maths":
                    rmaths.setChecked(true);
                    break;
                case "Science":
                    rscience.setChecked(true);
                    break;
                case "English":
                    rgujarati.setChecked(true);
                    break;
                case "Gujarati":
                    renglish.setChecked(true);
                    break;
            }

            if(strweaksubject.contains("M")){
                maths.setChecked(true);
            }
            if(strweaksubject.contains("S")){
                science.setChecked(true);
            }
            if(strweaksubject.contains("E")){
                gujarati.setChecked(true);
            }
            if(strweaksubject.contains("G")){
                english.setChecked(true);
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void Update(View view) {
        if(name.getText().toString().trim().isEmpty()){
            Toast.makeText(view.getContext(), "Please enter student name.", Toast.LENGTH_SHORT).show();
        } else if(address.getText().toString().trim().isEmpty()){
            Toast.makeText(view.getContext(), "Please enter address.", Toast.LENGTH_SHORT).show();
        } else if(datetxt.getText().toString().equals("Date Of Birth")){
            Toast.makeText(view.getContext(), "Please select date of birth.", Toast.LENGTH_SHORT).show();
        } else if(income.getText().toString().trim().isEmpty()){
            Toast.makeText(view.getContext(), "Please enter family annual income.", Toast.LENGTH_SHORT).show();
        } else if(Achievement.getText().toString().trim().isEmpty()){
            Toast.makeText(view.getContext(), "Please enter achievements.", Toast.LENGTH_SHORT).show();
        } else if(!(english.isChecked() || gujarati.isChecked() || maths.isChecked() || science.isChecked())){
            Toast.makeText(view.getContext(), "Please select weak subjects.", Toast.LENGTH_SHORT).show();
        } else {
            String weakSubjects = "";
            String strongSubject;

            if(rmaths.isChecked()){
                strongSubject = "Maths";
            } else if(rscience.isChecked()){
                strongSubject = "Science";
            } else if(renglish.isChecked()){
                strongSubject = "English";
            } else {
                strongSubject = "Gujarati";
            }

            if(maths.isChecked()){
                weakSubjects += "M";
            }
            if(science.isChecked()){
                weakSubjects += "S";
            }
            if(english.isChecked()){
                weakSubjects += "E";
            }
            if(gujarati.isChecked()){
                weakSubjects += "G";
            }

            DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
            if(databaseHelper.update(Integer.toString(currentId), name.getText().toString(), address.getText().toString(), datetxt.getText().toString(), income.getText().toString(), weakSubjects, strongSubject, Achievement.getText().toString())){
                Toast.makeText(view.getContext(), "Data updated successfully.", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(view.getContext(), "Error in data updating.", Toast.LENGTH_SHORT).show();
            }

            Intent i = new Intent(update_Activity.this, update_Activity.class);
            i.putExtra("id", currentId);
            startActivity(i);
            finish();
        }


}

    public void Home(View view) {
        Intent i = new Intent(update_Activity.this, Home.class);
        startActivity(i);
        finishAffinity();
    }

    public void previous(View view) {
        int id;

        if(currentId-1 == 0){
            id = totalRows;
        } else{
            id = currentId - 1;
        }

        Intent i = new Intent(update_Activity.this, update_Activity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }

    public void next(View view) {
        int id;

        if(currentId+1 > totalRows){
            id = 1;
        } else{
            id = currentId + 1;
        }

        Intent i = new Intent(update_Activity.this, update_Activity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }

    public void first(View view) {
        int id = 1;

        Intent i = new Intent(update_Activity.this, update_Activity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }

    public void last(View view) {
        int id = totalRows;

        Intent i = new Intent(update_Activity.this, update_Activity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }

    public void Date(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dobDate = i2;
                month = i1;
                year = i;
                datetxt.setText(i2 + "/" + i1+1 + "/" + i);
            }
        }, year, month, dobDate);
        datePickerDialog.show();
    }
}