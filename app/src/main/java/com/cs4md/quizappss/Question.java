package com.cs4md.quizappss;

public class Question {
    private String qText;
    private boolean correctAnswer;
    // add image to question class

    public Question() {
        qText = "";
        correctAnswer = false;
    }

    public Question(String qText, boolean correctAnswer) {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
    }

    public String getqText() {
        return qText;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qText='" + qText + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
