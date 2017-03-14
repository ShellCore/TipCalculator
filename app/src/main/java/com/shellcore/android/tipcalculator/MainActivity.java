package com.shellcore.android.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    // Constantes
    public static final float REGULAR_SERVICE_PERCENTAGE = 5;
    public static final float GOOD_SERVICE_PERCENTAGE = 10;
    public static final float EXCELLENT_SERVICE_PERCENTAGE = 15;

    // Variables
    float totalBillAmount = 0;
    float percentage = 0;
    float tipTotal = 0;
    float finalBillAmount = 0;

    // Componentes
    @BindView(R.id.edtBill)
    EditText edtBill;
    @BindView(R.id.txtTipPercent)
    TextView txtTipPercent;
    @BindView(R.id.txtTipTotal)
    TextView txtTipTotal;
    @BindView(R.id.txtBillTotalResult)
    TextView txtBillTotalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    @OnTextChanged(R.id.edtBill)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    @OnClick({R.id.btnRegularService, R.id.btnGoodService, R.id.btnExcelentSerice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegularService:
                percentage = REGULAR_SERVICE_PERCENTAGE;
                break;
            case R.id.btnGoodService:
                percentage = GOOD_SERVICE_PERCENTAGE;
                break;
            case R.id.btnExcelentSerice:
                percentage = EXCELLENT_SERVICE_PERCENTAGE;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill() {
        if (percentage == 0) {
            percentage = GOOD_SERVICE_PERCENTAGE;
        }

        if (!edtBill.getText().toString().isEmpty()
                && !edtBill.getText().toString().equals(".")) {
            totalBillAmount = Float.valueOf(edtBill.getText().toString());
        } else {
            totalBillAmount = 0;
        }

        tipTotal = (totalBillAmount * percentage) / 100;
        finalBillAmount = totalBillAmount + tipTotal;
    }

    private void setTipValues() {
        txtTipPercent.setText(getString(R.string.main_message_tipPercent, percentage));
        txtTipTotal.setText(getString(R.string.main_message_tipTotal, tipTotal));
        txtBillTotalResult.setText(getString(R.string.main_message_billTotal_result, finalBillAmount));
    }
}
