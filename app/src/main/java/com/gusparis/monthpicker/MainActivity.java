package com.gusparis.monthpicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.month_year_picker);

    Button button = findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        MonthPickerDialog monthPickerDialog = new MonthPickerDialog();
        monthPickerDialog.show(getSupportFragmentManager(), "MonthYearPicker");
      }
    });
  }
}
