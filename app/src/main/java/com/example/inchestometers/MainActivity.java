package com.example.inchestometers;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViews();
        setupOnClickListener();

    }


    private void findViews() {
        editText = findViewById(R.id.editText_inches);
        button = findViewById(R.id.button_calculate);
        textView = findViewById(R.id.textView_meters);
    }

    private void setupOnClickListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter value for inches", Toast.LENGTH_LONG).show();
                }else displayResult(convertToMeters());
            }
        });
    }

    private double convertToMeters() {
        String inchesText = editText.getText().toString();
        double inches = Double.parseDouble(inchesText);
        return inches * 0.0254;
    }

    private void displayResult(double meters) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String metersStringValue = myDecimalFormatter.format(meters);
        textView.setText(editText.getText().toString() + " Inches equals " + metersStringValue + " meters");
    }

}