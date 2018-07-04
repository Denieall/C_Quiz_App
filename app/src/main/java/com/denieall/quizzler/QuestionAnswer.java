package com.denieall.quizzler;

public class QuestionAnswer {
    private int questionID;
    private boolean answer;

    public QuestionAnswer(int id, boolean bool) {
        questionID = id;
        answer = bool;
    }

    public int getQuestionID() {
        return questionID;
    }

    public boolean getAnswer() {
        return answer;
    }
}
