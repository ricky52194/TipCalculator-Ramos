package com.example.ricky52194.tipcalculator_ramos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText txtAmount, txtPeople, txtTipCustom;
    private Button calculateButton, resetButton;
    public boolean customTipPressed;
    private double tipValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAmount = findViewById(R.id.txtAmount);
        txtPeople = findViewById(R.id.txtPeople);
        txtTipCustom = findViewById(R.id.txtTipCustom);
        txtTipCustom.setVisibility(View.INVISIBLE);

        txtAmount.setOnKeyListener(mKeyListener);
        txtPeople.setOnKeyListener(mKeyListener);
        txtTipCustom.setOnKeyListener(mKeyListener);

        radioGroup = findViewById(R.id.radioGroup);

        calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setEnabled(false);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double subtotal = Double.parseDouble(txtAmount.getText().toString());
                int numPeople = Integer.parseInt(txtPeople.getText().toString());
                if (customTipPressed) {
                    tipValue = Double.parseDouble(txtTipCustom.getText().toString());
                }
                if (subtotal < Double.parseDouble(getString(R.string.one))) {
                    showErrorAlert(getString(R.string.invalidSubtotal), R.id.txtAmount);
                }
                if (numPeople < Double.parseDouble(getString(R.string.one))) {
                    showErrorAlert(getString(R.string.invalidNumPeople), R.id.txtPeople);
                }
                if (tipValue < Double.parseDouble(getString(R.string.one))) {
                    showErrorAlert(getString(R.string.invalidTip), R.id.txtTipCustom);
                }

                if (subtotal > Double.parseDouble(getString(R.string.one)) & numPeople > Double.parseDouble(getString(R.string.one)) && tipValue > Double.parseDouble(getString(R.string.one))) {
                    Double total = (subtotal + (subtotal * (tipValue / Integer.parseInt(getString(R.string.hundred)))));
                    Double paySplit = total / numPeople;
                    Double tipCost = (subtotal * (tipValue / Integer.parseInt(getString(R.string.hundred))));

                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    Bundle bundle = new Bundle();

                    bundle.putDouble(getString(R.string.subtotalKey), subtotal);
                    bundle.putInt(getString(R.string.numPeopleKey), numPeople);
                    bundle.putDouble(getString(R.string.tipValueKey), tipValue);
                    bundle.putDouble(getString(R.string.totalKey), total);
                    bundle.putDouble(getString(R.string.paySplitKey), paySplit);
                    bundle.putDouble(getString(R.string.tipCostKey), tipCost);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        });
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtAmount.setText(null);
                txtPeople.setText(null);
                radioGroup.clearCheck();
                txtTipCustom.setVisibility(View.INVISIBLE);
                txtTipCustom.setText(null);
                calculateButton.setEnabled(false);
            }
        });

    }


    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.tip15:
                if (checked)
                    txtTipCustom.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, getString(R.string.tip15Toast), Toast.LENGTH_SHORT).show();
                    tipValue = Double.parseDouble(getString(R.string.val15));
                    break;

            case R.id.tip20:
                if (checked)
                    txtTipCustom.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, getString(R.string.tip20Toast), Toast.LENGTH_SHORT).show();
                    tipValue = Double.parseDouble(getString(R.string.val20));
                    break;

            case R.id.tip25:
                if (checked)
                    txtTipCustom.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, getString(R.string.tip25Toast), Toast.LENGTH_SHORT).show();
                    tipValue = Double.parseDouble(getString(R.string.val25));
                    break;

            case R.id.tipCustom:
                if (checked)
                    txtTipCustom.setVisibility(View.VISIBLE);
                    Toast.makeText(this, getString(R.string.tipCustomToast), Toast.LENGTH_SHORT).show();
                    customTipPressed = true;
                    break;
        }
    }


    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            switch (v.getId()) {
                case R.id.txtAmount:
                    calculateButton.setEnabled(txtAmount.getText().length() > Integer.parseInt(getString(R.string.zero)) && txtPeople.getText().length() > Integer.parseInt(getString(R.string.zero)));
                    break;
                case R.id.txtPeople:
                    calculateButton.setEnabled(txtAmount.getText().length() > Integer.parseInt(getString(R.string.zero)) && txtPeople.getText().length() > Integer.parseInt(getString(R.string.zero)));
                    break;
                case R.id.txtTipCustom:
                    calculateButton.setEnabled(txtAmount.getText().length() > Integer.parseInt(getString(R.string.zero)) && txtPeople.getText().length() > Integer.parseInt(getString(R.string.zero)) && txtTipCustom.getText().length() > Integer.parseInt(getString(R.string.zero)));
                    break;

            }
            return false;
        }

    };

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.error))
                .setMessage(errorMessage)
                .setNeutralButton(getString(R.string.close),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }



    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(getString(R.string.txtAmountKey),txtAmount.getText().toString());
        savedInstanceState.putString(getString(R.string.txtPeopleKey),txtPeople.getText().toString());
        savedInstanceState.putString(getString(R.string.txtTipCustomKey),txtTipCustom.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        txtAmount.setText(savedInstanceState.getString(getString(R.string.txtAmountKey)));
        txtPeople.setText(savedInstanceState.getString(getString(R.string.txtPeopleKey)));
        txtTipCustom.setText(savedInstanceState.getString(getString(R.string.txtTipCustomKey)));
    }



}
