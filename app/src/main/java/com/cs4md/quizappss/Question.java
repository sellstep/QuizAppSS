package com.cs4md.quizappss;

public class Question {
    private String qText;
    private boolean correctAnswer;
    // add image to question class
    private int imageNum;
    private int questionSound;

    public Question() {
        qText = "";
        correctAnswer = false;
        this.imageNum = 0;
        this.questionSound = 0;
    }

    public Question(String qText, boolean correctAnswer, int iN, int qSound) {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
        this.imageNum = iN;
        this.questionSound = qSound;
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

    public int getQuestionSound() {
        return questionSound;
    }

    public void setQuestionSound(int questionSound) {
        this.questionSound = questionSound;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qText='" + qText + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
