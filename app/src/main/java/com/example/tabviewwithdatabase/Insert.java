package com.example.tabviewwithdatabase;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Insert#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Insert extends Fragment {

    EditText name, address, datetxt, income, Achievement;
    Button btndate, reset, submit;
    CheckBox maths,science,gujarati, english;
    RadioButton rmaths, rscience, rgujarati, renglish;
    int dobDate, month, year;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Insert() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Insert.
     */
    // TODO: Rename and change types and number of parameters
    public static Insert newInstance(String param1, String param2) {
        Insert fragment = new Insert();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        name = (EditText)view.findViewById(R.id.name);
        address = (EditText) view.findViewById(R.id.Address);
        datetxt = (EditText) view.findViewById(R.id.txtdate);
        income = (EditText) view.findViewById(R.id.Income);
        Achievement = (EditText) view.findViewById(R.id.Achievement);

        btndate = (Button) view.findViewById(R.id.btndate);
        reset = (Button) view.findViewById(R.id.reset);
        submit = (Button) view.findViewById(R.id.submit);

        maths = (CheckBox) view.findViewById(R.id.maths);
        science = (CheckBox) view.findViewById(R.id.science);
        gujarati = (CheckBox) view.findViewById(R.id.gujarati);
        english = (CheckBox) view.findViewById(R.id.english);

        rmaths = (RadioButton) view.findViewById(R.id.Rmaths);
        rscience = (RadioButton) view.findViewById(R.id.Rscience);
        rgujarati = (RadioButton) view.findViewById(R.id.Rgujarati);
        renglish = (RadioButton) view.findViewById(R.id.Renglish);

        dobDate = 1;
        month = 1;
        year = 2001;

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                address.setText("");
                income.setText("");
                Achievement.setText("");
                maths.setChecked(false);
                science.setChecked(false);
                gujarati.setChecked(false);
                english.setChecked(false);
                rscience.setChecked(false);
                rgujarati.setChecked(false);
                renglish.setChecked(false);
            }
        });

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dobDate = dayOfMonth;
                        month = month+1;
                        year = year;
                        datetxt.setText(dobDate+"/"+month+"/"+year);
                    }
                } , year, (month-1), dobDate);
                datePickerDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1, address1, income1, Achivement1, txtdate1 ;
                name1 = name.getText().toString();
                address1 = address.getText().toString();
                income1 = income.getText().toString();
                txtdate1 = datetxt.getText().toString();
                Achivement1 = Achievement.getText().toString();

                String weakSubject = " ", strongSubject = "";

                if(rmaths.isChecked()){
                    weakSubject+= (rmaths.getText().toString());
                }
                else if(rscience.isChecked()){
                    weakSubject+= (rscience.getText().toString());
                }else if(rgujarati.isChecked()){
                    weakSubject+= (rgujarati.getText().toString());
                }else if(renglish.isChecked()){
                    weakSubject+= (renglish.getText().toString());
                } else{
                    weakSubject  = null;
                }

                if(maths.isChecked()){
                    weakSubject += "M";
                }
                if(science.isChecked()){
                    weakSubject += "S";
                }
                if(english.isChecked()){
                    weakSubject += "E";
                }
                if(gujarati.isChecked()){
                    weakSubject += "G";
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                databaseHelper.insert(name1, address1, txtdate1, income1 , weakSubject, strongSubject, Achivement1);

                Toast.makeText(getContext(), "insert all data.....", Toast.LENGTH_SHORT).show();
                reset.callOnClick();
            }
        });
    }
}