package com.example.tip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;

    @BindView(R.id.tvTipPercent)
    TextView tvTipPercent;

    @BindView(R.id.tvTipAmount)
    TextView tvTipAmount;

    @BindView(R.id.tvBillTotalAmount)
    TextView tvBillTotalAmount;

    float percent = 0;

    float tipTotal = 0;

    float finalBillAmount = 0;

    float totalBillAmount = 0;

    float REGULAR_TIP_PERCENT = 10;

    float DEFAULT_TIP_PERCENT = 15;

    float EXCELLENT_TIP_PERCENT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        setTipValues();

    }

    private void setTipValues(){

        tvTipPercent.setText(getString(R.string.main_msg_tippercent, percent));

        tvTipAmount.setText(getString(R.string.main_msg_tiptotal, tipTotal));

        tvBillTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBillAmount));
    }

    @OnClick({R.id.ibRegularService, R.id.ibGoodService, R.id.ibExcellentService})
    public void onClick(View view){

        switch (view.getId()){

            case R.id.ibRegularService:
                percent = REGULAR_TIP_PERCENT;
                break;
            case R.id.ibGoodService:
                percent = DEFAULT_TIP_PERCENT;
                break;
            case R.id.ibExcellentService:
                percent = EXCELLENT_TIP_PERCENT;
                break;

        }

        calculateFinalBill();

        setTipValues();

    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged(){

        calculateFinalBill();

        setTipValues();


    }

    public void calculateFinalBill(){

        if (percent == 0)
            percent = DEFAULT_TIP_PERCENT;

        if (!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals("."))
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());

        else
            totalBillAmount = 0;


        tipTotal = (totalBillAmount * percent) / 100;

        finalBillAmount = totalBillAmount + tipTotal;

    }
}