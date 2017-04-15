package com.nullpointerbay.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    @BindView(R.id.txtOutput)
    TextView txtOutput;
    @BindView(R.id.txtSummary)
    TextView txtSummary;
    @BindView(R.id.btnEqual)
    Button btnEqual;
    private RpnCalc rpnCalc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rpnCalc = new RpnCalc();

    }

    @OnClick({R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnMinus, R.id.bntFour,
            R.id.btnFive, R.id.btnSix, R.id.btnAdd, R.id.btnOne, R.id.btnTwo, R.id.btwThree,
            R.id.buttonDot, R.id.btnZero, R.id.btnEqual, R.id.btnClear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSeven:
                appendStringToOutput(7);
                break;
            case R.id.btnEight:
                appendStringToOutput(8);
                break;
            case R.id.btnNine:
                appendStringToOutput(9);
                break;
            case R.id.btnMinus:
                if (TextUtils.isEmpty(this.txtOutput.getText())) {
                    appendOperatorToOutput(" -");
                } else {
                    appendOperatorToOutput(" - ");
                }
                break;
            case R.id.bntFour:
                appendStringToOutput(4);
                break;
            case R.id.btnFive:
                appendStringToOutput(5);
                break;
            case R.id.btnSix:
                appendStringToOutput(6);
                break;
            case R.id.btnAdd:
                appendOperatorToOutput(" + ");
                break;
            case R.id.btnOne:
                appendStringToOutput(1);
                break;
            case R.id.btnTwo:
                appendStringToOutput(2);
                break;
            case R.id.btwThree:
                appendStringToOutput(3);
                break;
            case R.id.buttonDot:
                appendOperatorToOutput(".");
                break;
            case R.id.btnZero:
                appendStringToOutput(0);
                break;
            case R.id.btnEqual:
                final CharSequence text = txtOutput.getText();
                if (!TextUtils.isEmpty(text)) {
                    final String postfixEquation = rpnCalc.transformInfixToPostfix(text.toString());
                    final double result = rpnCalc.calculate(postfixEquation);
                    this.txtSummary.setText(String.valueOf(result));
                }
                this.txtOutput.setText("");
                break;
            case R.id.btnClear:
                this.txtOutput.setText("");
                this.txtSummary.setText("");
                this.btnEqual.setEnabled(true);
                break;
        }
    }

    private void appendStringToOutput(int seven) {
        this.btnEqual.setEnabled(true);
        this.txtOutput.setText(String.format("%s%d", this.txtOutput.getText(), seven));
    }

    private void appendOperatorToOutput(String operator) {
        this.btnEqual.setEnabled(false);
        this.txtOutput.setText(String.format("%s%s", this.txtOutput.getText(), operator));
    }
}
