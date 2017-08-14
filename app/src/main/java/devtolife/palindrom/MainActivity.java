package devtolife.palindrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int maxNum = 99999;
    private int minNum = 10000;

    private int divNumMax = 0;
    private int palind;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);

        Button btnStart = (Button) findViewById(R.id.button);
        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        divNumMax = (int) Math.sqrt(maxNum);

        int fPM;
        int sPM;
        boolean isNotPalind;

        fPM = findMaxPrimeNumber(maxNum);
        sPM = findMaxPrimeNumber(fPM - 2);
        isNotPalind = findPalindrome(fPM, sPM);

        while (isNotPalind) {

            if (sPM <= fPM && sPM > minNum) {
                sPM = findMaxPrimeNumber(sPM - 2);
                isNotPalind = findPalindrome(fPM, sPM);

            } else if (sPM <= minNum) {
                fPM = findMaxPrimeNumber(fPM - 2);
                sPM = fPM;
            }

            tv2.setText("1-st primary number: " + fPM);
            tv3.setText("2-nd primary number: " + sPM);
            tv4.setText("1-st * 2-nd = " + palind);

        }
    }


    private int findMaxPrimeNumber(int maxNumPre) {
        int i;
        int j;
        int z;
        int maxNumNew;

        for (i = maxNumPre; i >= minNum; i = i - 2) {

            for (j = 3; j <= divNumMax; j++) {

                z = i % j;

                if (z == 0 && j <= divNumMax) {
                    break;

                } else if (z != 0 && j == divNumMax) {
                    maxNumNew = i;
                    return maxNumNew;

                }
            }

        }
        return 10000;
    }

    private boolean findPalindrome(int firstPrime, int secondPrime) {

        int resultOfMath = firstPrime * secondPrime;

        String ltrResult = Integer.toString(resultOfMath);
        String rtlResult = new StringBuffer(ltrResult).reverse().toString();

        if (ltrResult.equals(rtlResult)) {

            palind = resultOfMath;
            return false;

        } else {
            return true;
        }
    }
}

