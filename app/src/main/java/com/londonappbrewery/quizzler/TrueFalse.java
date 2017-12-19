package com.londonappbrewery.quizzler;

/**
 * Created by mabeder on 12/13/17.
 */

public class TrueFalse {
    private int mQuestionID;
    private boolean mAnswer;

    public TrueFalse(int questionResource, boolean trueOrFalse) {
        mQuestionID = questionResource;
        mAnswer = trueOrFalse;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
