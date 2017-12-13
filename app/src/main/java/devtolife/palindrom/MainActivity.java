package devtolife.palindrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int maxNum;
    private int minNum;


    private int dividerNumMax = 0;

    private long biggestPalindrom;
    boolean isNotMaxPalind;

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

        isNotMaxPalind = true;
//        findMinNum();
//        findMaxNum();
//
//        firstPrimeNum = maxNum;
//        secondPrimeNum = maxNum;

        if (biggestPalindrom <= 100000000) {
            biggestPalindrom = 100000000;
        }

    }

    @Override
    public void onClick(View v) {

        multipleOfPrime();


    }

    private int findNextPrimeNum(int previousNum) {
        int i;
        int j;
        int z;
        int maxNumNew;

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


    public void multipleOfPrime() {

        int firstPrimeNum = 99999;
        int secondPrimeNum = 10000;

        while (isNotMaxPalind) {
            if (firstPrimeNum == maxNum && secondPrimeNum == maxNum) {

                firstPrimeNum = findNextPrimeNum(maxNum);
                secondPrimeNum = firstPrimeNum;
                findPalindrome(firstPrimeNum, secondPrimeNum);
                secondPrimeNum = secondPrimeNum - 2;

            } else if (secondPrimeNum <= firstPrimeNum && secondPrimeNum > minNum) {
                secondPrimeNum = findNextPrimeNum(secondPrimeNum);
                findPalindrome(firstPrimeNum, secondPrimeNum);
                secondPrimeNum = secondPrimeNum - 2;

            } else if (secondPrimeNum <= minNum) {
                firstPrimeNum = findNextPrimeNum(firstPrimeNum - 2);
                secondPrimeNum = firstPrimeNum;

            } else if (firstPrimeNum <= minNum) {

                fillLayout();
            }

        }

    }
//
//    public void findMinNum() {
//        minNum = 10000;
//    }
//
//    public void findMaxNum() {
//        maxNum = 99999;
//    }

    public void findDividerNumMax(long maxNumPreD) {

        dividerNumMax = (int) Math.sqrt(maxNumPreD);
    }

    private void findPalindrome(int firstPrimePal, int secondPrimePal) {

        long resultOfMath = firstPrimePal * secondPrimePal;

        String ltrResult = Long.toString(resultOfMath);
        String rtlResult = new StringBuilder(ltrResult).reverse().toString();

        if (ltrResult.equals(rtlResult) && resultOfMath > biggestPalindrom) {
            biggestPalindrom = resultOfMath;
            firstPrimeNumPal = firstPrimePal;
            secondPrimeNumPal = secondPrimePal;
            ltrResult = null;
            rtlResult = null;
            resultOfMath = 0;
        }
    }

    public void fillLayout() {
        tv2.setText("1-st primary number: " + firstPrimeNumPal);
        tv3.setText("2-nd primary number: " + secondPrimeNumPal);
        tv4.setText("1-st * 2-nd = " + biggestPalindrom);
    }
}

