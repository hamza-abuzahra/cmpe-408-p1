package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;

    // hashtable for city/code pairs
    Hashtable<String, String> cityList
            = new Hashtable<String, String>();
    TextView pob, resultTxt;

    EditText gpaInput, infoInput,
    idInput, firstNameInput, lastNameInput;
    RadioGroup scholarshipRadio, genderRadio, maleRadio, femaleRadio;
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
    String[] departments = {};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id input
        idInput = findViewById(R.id.stdIdInput);

        // name component
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);

        // date picker component:
        datePickerButton = findViewById(R.id.datePickerBtn);
        initializeDatePicker();
        datePickerButton = findViewById(R.id.datePickerBtn);

        // place of birth picker spinner:
        pob = findViewById(R.id.cityMsg);
        placeOfBirthSpinner = findViewById(R.id.placeOfBirthSpinner);
        pobAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cityCodesList);
        initializeCityList();
        placeOfBirthSpinner.setAdapter(pobAdapter);
        placeOfBirthSpinner.setOnItemSelectedListener(this);

        // gender component
        genderRadio = findViewById(R.id.genderRadio);

        // faculty spinner
        facultySpinner = findViewById(R.id.facultySpinner);
        fAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, facutlies);
        facultySpinner.setAdapter(fAdapter);
        facultySpinner.setOnItemSelectedListener(this);

        // department spinner
        departmentSpinner = findViewById(R.id.departmentSpinner);
        dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        departmentSpinner.setAdapter(dAdapter);

        // gpa and scholarship initialization
        gpaInput = findViewById(R.id.gpaInput);
        scholarshipRadio = findViewById(R.id.scholarshipRadio);

        // additional info edit text
        infoCheck = findViewById(R.id.infoCheck);
        infoInput = findViewById(R.id.infoInput);

        resultTxt = findViewById(R.id.resultTxt);
    }

    // using date picker
    // helper function for dates
    private String dateToString(int day, int month, int year) {
        return  day + "/"+ month + "/" + year;
    }

    // method to get today's date:
    private int[] getTodayDate(){
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month +1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new int[]{day, month, year};
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
        int[] today = getTodayDate();
        datePickerButton.setText(dateToString(today[0], today[1], today[2]));
        Calendar calendar  = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

         // creating a new date picker dialog
        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, dateSetListener
                , today[2],today[1]-1,today[0]);
    }
    public void openDateSelector(View view) {
        // show the initialized date picker
        datePickerDialog.show();
    }

    // PLACE OF BIRTH SECTION

    // city code list:
    // place of birth city list picker list:
    public void initializeCityList(){
        cityList.put("0", "City of Birth");
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

    // submit button listener
    public void submit (View view) {
        // get all inputs
        String id = idInput.getText().toString();
        String fName = firstNameInput.getText().toString().trim();
        String lName = lastNameInput.getText().toString().trim();
        String bDate = datePickerButton.getText().toString();
        String bCity = pob.getText().toString();
        int genderRadioId = genderRadio.getCheckedRadioButtonId();

        String faculty = facultySpinner.getSelectedItem().toString();
        String department = departmentSpinner.getSelectedItem().toString();
        String gpa = gpaInput.getText().toString();
        int scholarshipRadioId = scholarshipRadio.getCheckedRadioButtonId();

        String info = infoInput.getText().toString();

        boolean flag = id.length() == 11 && fName.length() != 0 && lName.length() != 0 &&
                (gpa.length() != 0) && genderRadioId != -1 && scholarshipRadioId != -1 &&
                (!infoCheck.isChecked() || infoInput.getText().toString().trim().length() != 0) &&
                facultySpinner.getSelectedItemPosition() != 0 && placeOfBirthSpinner.getSelectedItemPosition() != 0 &&
                departmentSpinner.getSelectedItemPosition() != 0;

        // check for conditions
        if (!flag){
            Toast.makeText(this, "input not valid, check again", Toast.LENGTH_SHORT).show();
            return;
        }
        float gpaFLoat = Float.parseFloat(gpa);
        if (gpaFLoat > 4 || gpaFLoat < 0){
            Toast.makeText(this, "input not valid, check again", Toast.LENGTH_SHORT).show();
            return;
        }
        DecimalFormat f = new DecimalFormat("#.00");
        gpa = f.format(gpaFLoat);

        RadioButton genderRadioBtn = findViewById(genderRadio.getCheckedRadioButtonId());
        String gender = genderRadioBtn.getText().toString();

        RadioButton scholarshipRadioBtn = findViewById(scholarshipRadio.getCheckedRadioButtonId());
        String scholarship = scholarshipRadioBtn.getText().toString();

        Spanned textmsg = Html.fromHtml("<b><i>ID :</i></b><font color=\"red\">" + id + "<br></font>" + "<b><i>Name :</i></b> <font color=\"red\">" + fName + "<br></font>" +
                "<b><i>Last Name :</i></b> <font color=\"red\">" + lName + "<br></font>" + "<b><i>Birth Date :</i></b> <font color=\"red\">" + bDate + "<br></font>" + "<b><i>Birth City :</i></b> <font color=\"red\">" + bCity + "<br></font>" +
                "<b><i>Gender :</i></b> <font color=\"red\">" + gender + "<br></font>" + "<b><i>Faculty :</i></b> <font color=\"red\">" + faculty + "<br></font>" + "<b><i>Department :</i></b> <font color=\"red\">" + department + "<br></font>" +
                "<b><i>GPA :</i></b> <font color=\"red\">" + gpa + "<br></font>" + "<b><i>Scholarship :</i></b> <font color=\"red\">" + scholarship + "<br></font>" + "<b><i>Additional Info :</i></b> <font color=\"red\">" + info + "<br></font>");

        resultTxt.setText(textmsg);
    }

    // reset button - clears all fields
    public void reset (View view) {
        idInput.setText("");
        firstNameInput.setText("");
        lastNameInput.setText("");
        initializeDatePicker();
        placeOfBirthSpinner.setSelection(0);
        genderRadio.clearCheck();
        facultySpinner.setSelection(0);
        gpaInput.setText("");
        scholarshipRadio.clearCheck();
        infoCheck.setChecked(false);
        infoInput.setEnabled(false);
        infoInput.setText("");
        resultTxt.setText("");
    }

    // click listener for exit button
    public void exit (View view) {
        finish();
        System.exit(0);
    }


    // spinner listener - faculty spinner & place of birth spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == facultySpinner.getId()){
            dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departmentList[i]);
            departmentSpinner.setAdapter(dAdapter);
        }
        if (adapterView.getId() == placeOfBirthSpinner.getId()){
            matchCodeToCity(i);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}