package com.gusparis.monthpicker;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import com.gusparis.monthpicker.builder.PickerViewFactory;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

public class MonthPickerDialog extends DialogFragment {

  @Override
  @RequiresApi(api = Build.VERSION_CODES.O)
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    PickerViewFactory dialog = new PickerViewFactory(this);

    return dialog.build();
  }
}
