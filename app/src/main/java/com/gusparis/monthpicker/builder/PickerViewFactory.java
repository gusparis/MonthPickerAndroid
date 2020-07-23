package com.gusparis.monthpicker.builder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import com.gusparis.monthpicker.MonthPickerDialog;
import com.gusparis.monthpicker.R;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

public class PickerViewFactory {

  private MonthPickerDialog monthPickerDialog;

  public PickerViewFactory(MonthPickerDialog monthPickerDialog) {
    this.monthPickerDialog = monthPickerDialog;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public AlertDialog build() {
    final FragmentActivity fragmentActivity = monthPickerDialog.getActivity();
    assert fragmentActivity != null;

    final int uiMode = fragmentActivity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
    int dialogStyle = R.style.LightStyle;
    int contentViewStyle = R.layout.picker_list_view;

    if (uiMode == Configuration.UI_MODE_NIGHT_YES) {
      dialogStyle = R.style.DarkStyle;
      contentViewStyle = R.layout.picker_list_view_dark;
    }

    AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity, dialogStyle);
    LayoutInflater inflater = fragmentActivity.getLayoutInflater();
    View contentView = inflater.inflate(contentViewStyle, null);

    MonthYearScrollListener scrollListener = new MonthYearScrollListener();

    final MonthYearNumberPicker monthPicker = new MonthNumberPicker()
        .view(contentView)
        .onScrollListener(scrollListener)
        .build();
    final MonthYearNumberPicker yearPicker = new YearNumberPicker()
        .view(contentView)
        .onScrollListener(scrollListener)
        .build();

    scrollListener.addObserver(monthPicker);
    scrollListener.addObserver(yearPicker);

    builder.setView(contentView)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            monthPickerDialog.getDialog().dismiss();
          }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            monthPickerDialog.getDialog().dismiss();
          }
        });

    return builder.create();
  }
}
