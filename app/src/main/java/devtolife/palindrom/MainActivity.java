package devtolife.palindrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private long maxNum = 99999;
    private long minNum = 10000;


    public long firstPalindrom = maxNum;
    public long secondPalindrom = maxNum;

    private long dividerNumMax = 0;
    private long firstPrimeNum;
    private long secondPrimeNum;
    private long biggestPalindrom;

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

        findMinNum();
        findMaxNum();

        findFirstPrimeNum();
        findSecondPrimeNum();

        multipleOfPrime();

    }

    @Override
    public void onClick(View v) {

        multipleOfPrime();
        fillLayout();

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


    public void findFirstPrimeNum() {
    }

    public void findSecondPrimeNum() {
    }

    public void multipleOfPrime() {

        boolean isNotMaxPalind = true;


        while (isNotMaxPalind) {
            if (firstPalindrom == maxNum && secondPalindrom == maxNum) {
                firstPalindrom = findNextPrimeNum(maxNum);
                secondPalindrom = findNextPrimeNum(firstPalindrom - 2);
                findPalindrome(firstPalindrom, secondPalindrom);

            } else if (secondPalindrom <= firstPalindrom && secondPalindrom > minNum) {
                secondPalindrom = findNextPrimeNum(secondPalindrom - 2);
                findPalindrome(firstPalindrom, secondPalindrom);

            } else if (secondPalindrom <= minNum) {
                firstPalindrom = findNextPrimeNum(firstPalindrom - 2);
                secondPalindrom = firstPalindrom;

            } else if (firstPalindrom <= minNum) {
                isNotMaxPalind = false;
            }

        }

    }

    public void fillLayout() {
        tv2.setText("1-st primary number: " + firstPrimeNum);
        tv3.setText("2-nd primary number: " + secondPrimeNum);
        tv4.setText("1-st * 2-nd = " + biggestPalindrom);
    }
}

