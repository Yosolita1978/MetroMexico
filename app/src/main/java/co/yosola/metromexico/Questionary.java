package co.yosola.metromexico;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Questionary {

    public static Questionary questionary = null;
    private Question[] mQuestions;
    private int mCurrentQuestionIndex = 0;
    private int mNumberCorrectAnswers = 0;

    public static Questionary getQuestionary(Context context){
        if(questionary == null){
            questionary = new Questionary(context);
        }
        return questionary;
    }

    private Questionary(Context context) {
        mQuestions = new Question[1];

        String[] questionOneChoices = context.getResources().getStringArray(R.array.question1_choices);
        String[] questionOneAnswers = context.getResources().getStringArray(R.array.question1_answers);
        Drawable questionOneImage = context.getResources().getDrawable(R.drawable.eugenia);

        Question questionOne = new Question(context.getString(R.string.question1_question), questionOneImage, questionOneChoices, questionOneAnswers);
        mQuestions[0] = questionOne;

    }

    public Question getCurrentQuestion(){
        return mQuestions[mCurrentQuestionIndex];
    }

    public  Question getNextQuestion(){
        if(mCurrentQuestionIndex == mQuestions.length - 1){
            return null;
        } else {
            mCurrentQuestionIndex += 1;
            return mQuestions[mCurrentQuestionIndex];
        }
    }

    public void checkAnswer(String[] answers){
        Question question = getCurrentQuestion();
        if(question.isCorrect(answers)){
            mNumberCorrectAnswers += 1;
        }
    }

    public void checkAnswer(String answer){
        Question question = getCurrentQuestion();
        if(question.isCorrect(answer)){
            mNumberCorrectAnswers += 1;
        }
    }

    public int getProgress(){
        int totalQuestions = mQuestions.length;
        int progress = (mCurrentQuestionIndex + 1) * 100 / totalQuestions;
        return  progress;
    }

    public int getNumberCurrentQuestion(){
        int currentQuestion = (mCurrentQuestionIndex + 1);
        return currentQuestion;
    }

    public int getTotalQuestions(){
        return mQuestions.length;
    }

    public int getTotalCorrectAnswers (){
        return mNumberCorrectAnswers;
    }

    public boolean hasPassed(){
        int totalQuestions = mQuestions.length;
        int totalCorrectAnswers = mNumberCorrectAnswers;
        int wrongAnswers = totalQuestions - totalCorrectAnswers;
        return wrongAnswers <= 2;
    }

    public void reset(){
        mNumberCorrectAnswers = 0;
        mCurrentQuestionIndex = 0;
    }




}
