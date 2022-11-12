package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;

    // hashtable for city/code pairs
    Hashtable<String, String> cityList
            = new Hashtable<String, String>();
    TextView pob;

    EditText gpaInput, infoInput,
    idInput, firstNameInput, lastNameInput;
    RadioGroup scholarshipRadio, genderRadio,
    maleRadio, femaleRadio;
    CheckBox infoCheck;
    private Button datePickerButton;
    Spinner facultySpinner, departmentSpinner, placeOfBirthSpinner;
    ArrayAdapter<String> fAdapter, dAdapter , pobAdapter;
    String[] cityCodesList = {"", "212", "216", "312", "242", "232", "252", "242", "264", "224"};
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


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id input:
        idInput = findViewById(R.id.inputStdId);
        // first name input:
        firstNameInput = findViewById(R.id.inputFirstName);
        // last name input:
        lastNameInput = findViewById(R.id.inputLastName);

        // date picker component:
        initializeDatePicker();
        datePickerButton = findViewById(R.id.datePickerButton);
        datePickerButton.setText(getTodayDate());

        // place of birth picker spinner:
        pob = findViewById(R.id.txtPob);
        pob.setText("city of birth");
        placeOfBirthSpinner = findViewById(R.id.placeOfBirthSpinner);
        pobAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityCodesList);
        initializeCityList();
        placeOfBirthSpinner.setAdapter(pobAdapter);
        placeOfBirthSpinner.setOnItemSelectedListener(this);


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



// PLACE OF BIRTH SECTION
// using date picker


// method to get today's date:
    private String getTodayDate(){
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month +1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return dateToString(day, month, year);
    }

// method to initialize the date picker
    public void initializeDatePicker(){
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            // january starts as 0
            // changing it to 1
            month = month + 1;
            // new date
            String date = dateToString(day, month, year);
            datePickerButton.setText(date);
        }
    };
    // getting today's date
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

         // creating a new date picker dialog
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, dateSetListener
                , year,month,day);
        // limits the max date to the current date
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String dateToString(int day, int month, int year) {
        return  day + " "+ month + " " + " " + year;
    }

    public void openDateSelector(View view) {
        // show the initialized date picker
        datePickerDialog.show();
    }


    // city code list:


    // place of birth city list picker list:
    public void initializeCityList(){
        cityList.put("212", "Istanbul Europe");
        cityList.put("216", "Istanbul Asia");
        cityList.put("312", "Ankara");
        cityList.put("242", "Antalya");
        cityList.put("326", "Hatay");
        cityList.put("232", "Izmir");
        cityList.put("252", "Mugla");
        cityList.put("462", "Trabzon");
        cityList.put("442", "Erzurum");
        cityList.put("224", "Bursa");
    }

    // returns the country from the code
    public void matchCodeToCity(int i){
        String citySelected = "";
        citySelected = cityList.get(cityCodesList[i]);
        System.out.println("in function");
        pob.setText(citySelected);
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
        // calling function to match the city code to city name
        matchCodeToCity(i);
//        departments = departmentList[i];
//        departmentSpinner.setClickable(true);
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}