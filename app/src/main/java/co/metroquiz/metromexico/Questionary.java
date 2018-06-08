package co.metroquiz.metromexico;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Questionary {

    public static Questionary questionary = null;
    private Question[] mQuestions;
    private int mCurrentQuestionIndex = 0;
    private int mNumberCorrectAnswers = 0;

    private Questionary(Context context) {
        mQuestions = new Question[10];

        String[] questionOneChoices = context.getResources().getStringArray(R.array.question1_choices);
        String[] questionOneAnswers = context.getResources().getStringArray(R.array.question1_answers);
        Drawable questionOneImage = context.getResources().getDrawable(R.drawable.eugenia);

        Question questionOne = new Question(context.getString(R.string.question1_question), questionOneImage, questionOneChoices, questionOneAnswers);
        mQuestions[0] = questionOne;

        String[] questionTwoChoices = {};
        String[] questionTwoAnswers = {context.getString(R.string.question2_answer)};
        Drawable questionTwoImage = context.getResources().getDrawable(R.drawable.atzcapozalco);
        Question questionTwo = new Question(context.getString(R.string.question2_question), questionTwoImage, questionTwoChoices, questionTwoAnswers);
        mQuestions[1] = questionTwo;

        String[] questionThreeChoices = context.getResources().getStringArray(R.array.question3_choices);
        String[] questionThreeAnswers = {context.getString(R.string.question3_answer)};
        Drawable questionThreeImage = context.getResources().getDrawable(R.drawable.pinosuarez);
        Question questionThree = new Question(context.getString(R.string.question3_question), questionThreeImage, questionThreeChoices, questionThreeAnswers);
        mQuestions[2] = questionThree;

        String[] questionFourChoices = context.getResources().getStringArray(R.array.question4_choices);
        String[] questionFourAnswers = {context.getString(R.string.question4_answer)};
        Drawable questionFourImage = context.getResources().getDrawable(R.drawable.balbuena);
        Question questionFour = new Question(context.getString(R.string.question4_question), questionFourImage, questionFourChoices, questionFourAnswers);
        mQuestions[3] = questionFour;

        String[] questionFiveChoices = context.getResources().getStringArray(R.array.question5_choices);
        String[] questionFiveAnswers = {context.getString(R.string.question5_answer)};
        Drawable questionFivaImage = context.getResources().getDrawable(R.drawable.cuitlahuac);
        Question questionFive = new Question(context.getString(R.string.question5_question), questionFivaImage, questionFiveChoices, questionFiveAnswers);
        mQuestions[4] = questionFive;

        String[] questionSixChoices = {};
        String[] questionSixAnswers = {context.getString(R.string.question6_answer)};
        Drawable questionSixImage = context.getResources().getDrawable(R.drawable.copilco);
        Question questionSix = new Question(context.getString(R.string.question6_question), questionSixImage, questionSixChoices, questionSixAnswers);
        mQuestions[5] = questionSix;

        String[] questionSevenChoices = context.getResources().getStringArray(R.array.question7_choices);
        String[] questionSevenAnswers = {context.getString(R.string.question7_answer)};
        Drawable questionSevenImage = context.getResources().getDrawable(R.drawable.tezonco);
        Question questionSeven = new Question(context.getString(R.string.question7_question), questionSevenImage, questionSevenChoices, questionSevenAnswers);
        mQuestions[6] = questionSeven;

        String[] questionEightChoices = context.getResources().getStringArray(R.array.question8_choices);
        String[] questionEightAnswers = {context.getString(R.string.question8_answer)};
        Drawable questionEightImage = context.getResources().getDrawable(R.drawable.tezozomoc);
        Question questionEight = new Question(context.getString(R.string.question8_question), questionEightImage, questionEightChoices, questionEightAnswers);
        mQuestions[7] = questionEight;

        String[] questionNineChoices = context.getResources().getStringArray(R.array.question9_choices);
        String[] questionNineAnswers = {context.getString(R.string.question9_answer)};
        Drawable questionNineImage = context.getResources().getDrawable(R.drawable.guelatao);
        Question questionNine = new Question(context.getString(R.string.question9_question), questionNineImage, questionNineChoices, questionNineAnswers);
        mQuestions[8] = questionNine;

        String[] questionTenChoices = context.getResources().getStringArray(R.array.question10_choices);
        String[] questionTenAnswers = {context.getString(R.string.question10_answer)};
        Drawable questionTenImage = context.getResources().getDrawable(R.drawable.puebla);
        Question questionTen = new Question(context.getString(R.string.question10_question), questionTenImage, questionTenChoices, questionTenAnswers);
        mQuestions[9] = questionTen;

    }

    public static Questionary getQuestionary(Context context) {
        if (questionary == null) {
            questionary = new Questionary(context);
        }
        return questionary;
    }

    public Question getCurrentQuestion() {
        return mQuestions[mCurrentQuestionIndex];
    }

    public Question getNextQuestion() {
        if (mCurrentQuestionIndex == mQuestions.length - 1) {
            return null;
        } else {
            mCurrentQuestionIndex += 1;
            return mQuestions[mCurrentQuestionIndex];
        }
    }

    public void checkAnswer(String[] answers) {
        Question question = getCurrentQuestion();
        if (question.isCorrect(answers)) {
            mNumberCorrectAnswers += 1;
        }
    }

    public void checkAnswer(String answer) {
        Question question = getCurrentQuestion();
        if (question.isCorrect(answer)) {
            mNumberCorrectAnswers += 1;
        }
    }

    public int getProgress() {
        int totalQuestions = mQuestions.length;
        int progress = (mCurrentQuestionIndex + 1) * 100 / totalQuestions;
        return progress;
    }

    public int getNumberCurrentQuestion() {
        int currentQuestion = (mCurrentQuestionIndex + 1);
        return currentQuestion;
    }

    public int getTotalQuestions() {
        return mQuestions.length;
    }

    public int getTotalCorrectAnswers() {
        return mNumberCorrectAnswers;
    }

    public boolean hasPassed() {
        int totalQuestions = mQuestions.length;
        int totalCorrectAnswers = mNumberCorrectAnswers;
        int wrongAnswers = totalQuestions - totalCorrectAnswers;
        return wrongAnswers <= 2;
    }

    public void reset() {
        mNumberCorrectAnswers = 0;
        mCurrentQuestionIndex = 0;
    }


}
