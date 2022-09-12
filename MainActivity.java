package com.jblearning.tipcalculatorv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
  private TipCalculator tipCalc;
  public NumberFormat money = NumberFormat.getCurrencyInstance();
  private EditText billEditText;
  private EditText tipEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tipCalc = new TipCalculator(0.17f,100.0f);

    billEditText = (EditText) findViewById(R.id.amount_bill);
    tipEditText = (EditText) findViewById(R.id.amount_tip_percent);

    TextChangeHandler tch = new TextChangeHandler();
    billEditText.addTextChangedListener( tch );
    tipEditText.addTextChangedListener( tch );
  }

  public void calculate(){
    String billString = billEditText.getText().toString();
    String tipString = tipEditText.getText().toString();

    TextView tipTextView =
            (TextView) findViewById(R.id.amount_tip);
    TextView totalTextView =
            (TextView) findViewById(R.id.amount_total);
    try{

      float billAmount = Float.parseFloat(billString);
      int tipPercent = Integer.parseInt( tipString );

      tipCalc.setBill( billAmount );
      tipCalc.setTip( 0.01f * tipPercent );

      float tip = tipCalc.tipAmount();
      float total = tipCalc.totalAmount();

      tipTextView.setText(money.format(tip));
      totalTextView.setText(money.format(total));
    }catch(NumberFormatException nfe){
      // pop up alert view
    }
  }

  private class TextChangeHandler implements TextWatcher{
    @Override
    public void afterTextChanged(Editable e) {
      calculate();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {
    }
  }
}
