package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText gpaInput, infoInput;
    RadioGroup scholarshipRadio, genderRadio;
    CheckBox infoCheck;
    Spinner facultySpinner, departmentSpinner;
    ArrayAdapter<String> fAdapter, dAdapter;
    String[] facutlies = {"", "Faculty of Applied Sciences", "Faculty of Architecture", "Faculty of Business",
            "Faculty of Communication", "Faculty of Engineering and Natural Sciences", "Faculty of Health Sciences",
            "Faculty of Law", "Faculty of Social Sciences and Humanities"};
    String[][] departmentList = {{""}, {"", "Aviation Management", "Banking and Finance", "Gastronomy and Culinary Arts",
            "International Retail Management", "Logistics Management", "Sports Management", "Textile and Fashion Design",
            "Tourism Management"}, {"", "Architecture", "Industrial Design", "Interior Design"}, {"", "Business Administration",
            "Economics"}, {"", "Arts and Cultural Management", "Communication Design and Management", "Digital Game Design",
            "Film", "Media"}, {"", "Civil Engineering", "Computer Engineering", "Electrical and Electronics Engineering",
            "Energy Systems Engineering", "Genetics and Bioengineering", "Industrial Engineering",
            "Mathematics", "Mechanical Engineering", "Mechatronics Engineering"}, {"", "Child Development",
            "Health Management", "Nursing", "Nutrition and Dietetics", "Occupational Therapy",
            "Perfusion", "Physiotherapy and Rehabilitation"}, {"Law"}, {"", "Comparative Literature",
            "English Language and Literature", "English Language Teacher Education", "History",
            "International Relations", "Music", "Psychology", "Sociology"}
    };
    String[] departments = {"example", "test"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // gender component
        genderRadio = findViewById(R.id.genderRadio);

        // faculty spinner
        facultySpinner = findViewById(R.id.facultySpinner);
        fAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, facutlies);
        facultySpinner.setAdapter(fAdapter);
        facultySpinner.setOnItemSelectedListener(this);

        // department spinner
        departmentSpinner = findViewById(R.id.departmentSpinner);
        dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        departmentSpinner.setAdapter(dAdapter);

        // gpa and scholarship initialization
        gpaInput = findViewById(R.id.gpaInput);
        scholarshipRadio = findViewById(R.id.scholarshipRadio);

        // additional info edit text
        infoCheck = findViewById(R.id.infoCheck);
        infoInput = findViewById(R.id.infoInput);
    }



    public void getDate(View view){

    }
    
    // when check box is checked the edit text component will be active
    public void enableInput (View view){
        if (infoCheck.isChecked()){
            infoInput.setEnabled(true);
        }
        else {
            infoInput.setEnabled(false);
            infoInput.setText("");
        }

    }

    // reset button - clears all fields
    public void reset (View view) {
        // id
        // names
        // birth date
        // birth place

        genderRadio.clearCheck();
        facultySpinner.setSelection(0);
        gpaInput.setText("");
        scholarshipRadio.clearCheck();
        infoCheck.setChecked(false);
        infoInput.setEnabled(false);
        infoInput.setText("");
    }


    // spinner listener - faculty spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departmentList[i]);
        departmentSpinner.setAdapter(dAdapter);
//        departments = departmentList[i];
//        departmentSpinner.setClickable(true);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}