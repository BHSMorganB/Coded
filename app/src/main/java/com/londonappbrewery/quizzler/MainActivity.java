package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button mTrueButton;
    Button mFalseButton;
    TextView mTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mIndex;
    int mQuestion;
    int mScore;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, false),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, false),
            new TrueFalse(R.string.question_10, false),
            new TrueFalse(R.string.question_11, true),
            new TrueFalse(R.string.question_12, true),
            new TrueFalse(R.string.question_13,false)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getmQuestionID();
        mTextView.setText(mQuestion);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Quizzer", "Button pressed");
                Toast.makeText(getApplicationContext(), "True pressed!", Toast.LENGTH_SHORT).show();
            }
        };

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                updateQuestion();
            }
        });
    }

    public void updateQuestion() {
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if (mIndex == 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + " points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }

        mQuestion = mQuestionBank[mIndex].getmQuestionID();
        mTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score: " + mScore + " / " + mQuestionBank.length);
    }

    public void checkAnswer(boolean userSelected)
    {
        boolean correctAnswer = mQuestionBank[mIndex].ismAnswer();
                if (userSelected == correctAnswer)
                {
                    Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    mScore++;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                }
    }
}
