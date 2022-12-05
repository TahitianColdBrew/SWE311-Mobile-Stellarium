package org.xmum.stellarium;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
;
import org.xmum.stellarium.utils.ApodLoadTask;

import java.util.Calendar;


public class ApodActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private WebView apodPic;
    private TextView apodTitle, apodExplanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);
//        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        apodPic = findViewById(R.id.apod_pic);
        apodTitle = findViewById(R.id.apod_title);
        apodExplanation = findViewById(R.id.apod_explanation);
//        apodPic.loadUrl("https://apod.nasa.gov/apod/image/2211/LastRingPortrait_Cassini_1080.jpg");


        initDatePicker();

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

                String uri = "https://api.nasa.gov/planetary/apod?api_key=KvM2oNxndpA4edEMRd45wb6tcAkBMWlPqSe7gcwu";
                uri += "&date=" + year + "-" + month + "-" + day;
                Log.e("APOD", uri);//打印结果
                System.out.println(uri);
                ApodLoadTask task = new ApodLoadTask(apodPic, apodTitle, apodExplanation);
                task.execute(uri);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        String uri = "https://api.nasa.gov/planetary/apod?api_key=KvM2oNxndpA4edEMRd45wb6tcAkBMWlPqSe7gcwu";
        uri += "&date=" + year + "-" + month + "-" + day;
        Log.e("APOD", uri);//打印结果
        ApodLoadTask task = new ApodLoadTask(apodPic, apodTitle, apodExplanation);
        task.execute(uri);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

}