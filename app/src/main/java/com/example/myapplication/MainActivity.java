package com.example.myapplication;

import android.view.View;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etCity;
    Button btnDate, btnGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etCity = findViewById(R.id.etCity);

        btnDate = findViewById(R.id.btnDate);
        btnGender = findViewById(R.id.btnGender);

        // בחירת תאריך לידה
        btnDate.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker =
                    new DatePickerDialog(
                            MainActivity.this,
                            (view, selectedYear, selectedMonth, selectedDay) -> {

                                String date = selectedDay + "/" +
                                        (selectedMonth + 1) + "/" +
                                        selectedYear;

                                btnDate.setText(date);
                            },
                            year, month, day
                    );

            datePicker.show();
        });

        // בחירת מין באמצעות Dialog
        btnGender.setOnClickListener(v -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("בחר מין");

            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOrientation(RadioGroup.VERTICAL);

            RadioButton male = new RadioButton(this);
            male.setText("זכר");
            male.setId(View.generateViewId());

            RadioButton female = new RadioButton(this);
            female.setText("נקבה");
            female.setId(View.generateViewId());

            radioGroup.addView(male);
            radioGroup.addView(female);

            LinearLayout layout = new LinearLayout(this);
            layout.setPadding(40, 20, 40, 20);
            layout.addView(radioGroup);

            builder.setView(layout);

            builder.setPositiveButton("אישור", (dialog, which) -> {

                int selectedId =
                        radioGroup.getCheckedRadioButtonId();

                if (selectedId == male.getId()) {
                    btnGender.setText("זכר");
                } else if (selectedId == female.getId()) {
                    btnGender.setText("נקבה");
                }
            });

            builder.setNegativeButton("ביטול", (dialog, which) -> {
                dialog.dismiss();
            });

            builder.create().show();
        });
    }
}
