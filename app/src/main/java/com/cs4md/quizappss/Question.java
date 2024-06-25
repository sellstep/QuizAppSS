package com.cs4md.quizappss;

public class Question {
    private String qText;
    private boolean correctAnswer;
    // add image to question class
    private int imageNum;

    public Question() {
        qText = "";
        correctAnswer = false;
    }

    public Question(String qText, boolean correctAnswer, int iN) {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
        this.imageNum = iN;
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

    public int getImageNum() {
        return imageNum;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qText='" + qText + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
