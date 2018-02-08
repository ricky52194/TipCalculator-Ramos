package com.example.ricky52194.tipcalculator_ramos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView subtotalOutput, numPeopleOutput, percentAppliedOutput, tipAmountOutput, totalOutput, splitOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        subtotalOutput = findViewById(R.id.subtotalOutput);
        numPeopleOutput = findViewById(R.id.numPeopleOutput);
        percentAppliedOutput = findViewById(R.id.percentAppliedOutput);
        tipAmountOutput = findViewById(R.id.tipAmountOutput);
        totalOutput = findViewById(R.id.totalOutput);
        splitOutput = findViewById(R.id.splitOutput);

        Bundle bundle = getIntent().getExtras();

        subtotalOutput.setText(getString(R.string.dollarSign) + String.format(getString(R.string.formatDecimal), bundle.getDouble(getString(R.string.subtotalKey))));
        numPeopleOutput.setText(Integer.toString(bundle.getInt(getString(R.string.numPeopleKey))));
        percentAppliedOutput.setText(String.format(getString(R.string.formatDecimal), bundle.getDouble(getString(R.string.tipValueKey))) + getString(R.string.percentSign));
        tipAmountOutput.setText(getString(R.string.dollarSign) + String.format(getString(R.string.formatDecimal), bundle.getDouble(getString(R.string.tipCostKey))));
        totalOutput.setText(getString(R.string.dollarSign) + String.format(getString(R.string.formatDecimal), bundle.getDouble(getString(R.string.totalKey))));
        splitOutput.setText(getString(R.string.dollarSign) + String.format(getString(R.string.formatDecimal), bundle.getDouble(getString(R.string.paySplitKey))));

        Button newButton = findViewById(R.id.newButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.finish();
    }
}
