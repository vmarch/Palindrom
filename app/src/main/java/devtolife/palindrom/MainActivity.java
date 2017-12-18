package devtolife.palindrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int maxNumNew;

    private int dividerNumMax;

    private long biggestPalindrom;

    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private int firstPrimeNumPal;
    private int secondPrimeNumPal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);

        Button btnStart = (Button) findViewById(R.id.button);
        btnStart.setOnClickListener(this);

        biggestPalindrom = 0;
    }

    @Override
    public void onClick(View v) {

        multipleOfPrime();

    }

    private void findNextPrimeNum(int previousNum) {
        int i;
        int j;
        int z;
        maxNumNew = previousNum;

        findDividerNumMax(previousNum);

        for (i = previousNum; i >= 10000; i = i - 2) {
            if (maxNumNew != previousNum && maxNumNew > 10000) {
                break;
            }
            for (j = 3; j <= dividerNumMax; j++) {

                z = i % j;

                if (z == 0 && j < dividerNumMax) {
                    break;

                } else if (z != 0 && j >= dividerNumMax) {
                    maxNumNew = i;
                    break;
                }
            }
        }
    }


    public void multipleOfPrime() {
        boolean isNotMaxPalind = true;
        int firstPrimeNum = 99999;
        int secondPrimeNum = 99999;

        while (isNotMaxPalind) {

            if (secondPrimeNum < firstPrimeNum && secondPrimeNum > 10000) {
                findNextPrimeNum(secondPrimeNum);
                secondPrimeNum = maxNumNew;
                findPalindrome(firstPrimeNum, secondPrimeNum);
                secondPrimeNum = secondPrimeNum - 2;

            } else if (secondPrimeNum < firstPrimeNum && secondPrimeNum <= 10000) {
                secondPrimeNum = firstPrimeNum;

            } else if (firstPrimeNum == secondPrimeNum && secondPrimeNum > 10000) {
                findNextPrimeNum(secondPrimeNum);
                firstPrimeNum = maxNumNew;
                secondPrimeNum = maxNumNew;
                findPalindrome(firstPrimeNum, secondPrimeNum);
                secondPrimeNum = maxNumNew - 2;

            } else if (firstPrimeNum <= 10000) {
                isNotMaxPalind = false;
                fillLayout();

            }
        }
    }

    public void findDividerNumMax(long maxNumPreD) {

        dividerNumMax = (int) Math.sqrt(maxNumPreD);
    }

    private void findPalindrome(int firstPrimePal, int secondPrimePal) {

        long resultOfMath = (long) firstPrimePal * (long) secondPrimePal;

        String ltrResult = Long.toString(resultOfMath);
        String rtlResult = new StringBuilder(ltrResult).reverse().toString();

        if (ltrResult.equals(rtlResult) && resultOfMath > biggestPalindrom) {
            biggestPalindrom = resultOfMath;
            firstPrimeNumPal = firstPrimePal;
            secondPrimeNumPal = secondPrimePal;
        }

        ltrResult = null;
        rtlResult = null;
        resultOfMath = 0;
    }

    public void fillLayout() {
        tv2.setText("1-st primary number: " + firstPrimeNumPal);
        tv3.setText("2-nd primary number: " + secondPrimeNumPal);
        tv4.setText("1-st * 2-nd = " + biggestPalindrom);
    }
}

