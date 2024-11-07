package com.example.cuu_ho_tech.Utils;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Method4UI {
    private Map<TextInputEditText, String> fieldsWithErrors = new HashMap<>();

    public Method4UI() {
    }

    public void addField(TextInputEditText editText, String errorText) {
        fieldsWithErrors.put(editText, errorText);
    }

    public boolean validate() {
        boolean isValid = true;

        for (Map.Entry<TextInputEditText, String> entry : fieldsWithErrors.entrySet()) {
            TextInputEditText editText = entry.getKey();
            String errorText = entry.getValue();

            if (editText.getText().toString().trim().isEmpty()) {
                editText.setError(errorText);
                isValid = false;
            } else {
                editText.setError(null);
            }
        }
        return isValid;
    }

    public static void hidehint(TextInputEditText editText, TextInputLayout inputLayout, String hint) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Ẩn hint khi EditText được focus
                    inputLayout.setHint("");
                } else {
                    if (editText.getText().length() > 0) {
                        inputLayout.setHint("");
                    } else {
                        // Hiện lại hint khi mất focus
                        inputLayout.setHint(hint);
                    }

                }
            }
        });
    }
}
