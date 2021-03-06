package com.gamecodeschool.formatrupiah;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Author: Roni Reynal Fitri  on $ {DATE} $ {HOUR}: $ {MINUTE}
 * <p>
 * Email: biruprinting@gmail.com
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvNumber;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber = findViewById(R.id.tvNumber);
        etNumber = findViewById(R.id.etNumber);

        etNumber.addTextChangedListener(new TextWatcher() {
            private String setEditText = etNumber.getText().toString().trim();
                        private String setTextView;

            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().equals(setEditText)) {
                    etNumber.removeTextChangedListener(this);
                    String replace = s.toString().replaceAll("[Rp]", "");
                    if (!replace.isEmpty()) {
                        setEditText = formatrupiah(Double.parseDouble(replace));
                        setTextView = setEditText;
                    } else {

                        setEditText = "";
                        setTextView = "Hasil Input";
                    }
                    etNumber.setText(setEditText);
                    tvNumber.setText(setTextView);
                    etNumber.setSelection(setEditText.length());
                    etNumber.addTextChangedListener(this);
                }

        }

            @Override
            public void afterTextChanged(Editable s) {

            }

            });
    }



    private String formatrupiah(Double number){
        Locale localeID = new Locale("IND","ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String formatrupiah = numberFormat.format((number));
        String[] split= formatrupiah.split(",");
                int length = split[0].length();
        return split[0].substring(0,2)+". "+split[0].substring(2,length);
    }
}
