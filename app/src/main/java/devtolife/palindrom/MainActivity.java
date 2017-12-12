package devtolife.palindrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private long maxNum;
    private long minNum;


    private long firstPrimeNum;
    private long secondPrimeNum;

    private long dividerNumMax = 0;

    private long biggestPalindrom;
    boolean isNotMaxPalind;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;


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

        isNotMaxPalind = true;

        firstPrimeNum = maxNum;
        secondPrimeNum = maxNum;

        if (biggestPalindrom <= 100000000) {
            biggestPalindrom = 100000000;
        }

        findMinNum();
        findMaxNum();



    }

    @Override
    public void onClick(View v) {

        multipleOfPrime();


    }

    private long findNextPrimeNum(long previousNum) {
        long i;
        long j;
        long z;
        long maxNumNew;

        findDividerNumMax(previousNum);

        for (i = previousNum; i >= minNum; i = i - 2) {

            for (j = 3; j <= dividerNumMax; j++) {

                z = i % j;

                if (z == 0 && j < dividerNumMax) {
                    break;

                } else if (z != 0 && j >= dividerNumMax) {
                    maxNumNew = i;
                    return maxNumNew;
                }
            }
        }
        return 1;
    }

    public void findDividerNumMax(long maxNumPreD) {

        dividerNumMax = (long) Math.sqrt(maxNumPreD);
    }

    private void findPalindrome(long firstPrime, long secondPrime) {

        long resultOfMath = firstPrime * secondPrime;

        String ltrResult = Long.toString(resultOfMath);
        String rtlResult = new StringBuilder(ltrResult).reverse().toString();

        if (ltrResult.equals(rtlResult) && resultOfMath > biggestPalindrom) {
            biggestPalindrom = resultOfMath;
            firstPrimeNum = firstPrime;
            secondPrimeNum = secondPrime;
        }
    }


    public void findMinNum() {
        minNum = 10000;
    }

    public void findMaxNum() {
        maxNum = 99999;
    }

    public void multipleOfPrime() {

        while (isNotMaxPalind) {
            if (firstPrimeNum == maxNum && secondPrimeNum == maxNum) {
                firstPrimeNum = findNextPrimeNum(maxNum);
                secondPrimeNum = findNextPrimeNum(firstPrimeNum);
                findPalindrome(firstPrimeNum, secondPrimeNum);
                isNotMaxPalind = true;

            } else if (secondPrimeNum <= firstPrimeNum && secondPrimeNum > minNum) {
                secondPrimeNum = findNextPrimeNum(secondPrimeNum - 2);
                findPalindrome(firstPrimeNum, secondPrimeNum);
                isNotMaxPalind = true;

            } else if (secondPrimeNum <= minNum) {
                firstPrimeNum = findNextPrimeNum(firstPrimeNum - 2);
                secondPrimeNum = firstPrimeNum;
                isNotMaxPalind = true;

            } else if (firstPrimeNum <= minNum) {
                isNotMaxPalind = false;
                fillLayout();
            }

        }

    }

    public void fillLayout() {
        tv2.setText("1-st primary number: " + firstPrimeNum);
        tv3.setText("2-nd primary number: " + secondPrimeNum);
        tv4.setText("1-st * 2-nd = " + biggestPalindrom);
    }
}

