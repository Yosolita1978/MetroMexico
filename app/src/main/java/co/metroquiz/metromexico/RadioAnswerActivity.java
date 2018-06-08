package co.metroquiz.metromexico;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RadioAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the radio_answers_activity.xml layout file
        setContentView(R.layout.radio_answer_activity);

        final Questionary questionary = Questionary.getQuestionary(this);
        Question currentQuestion = questionary.getCurrentQuestion();
        displayQuestion(currentQuestion.getQuestion());
        displayChoices(currentQuestion.getChoices());
        displayImage(currentQuestion.getImageResource());

        //Set up the progress Bar progress
        ProgressBar quizProgressBar = (ProgressBar) findViewById(R.id.determinateBar);
        quizProgressBar.setProgress(questionary.getProgress());

        //Set up the number of question and the current question
        TextView quizProgress = (TextView) findViewById(R.id.currentquestion_view);
        int totalQuestions = questionary.getTotalQuestions();
        int currentNumberQuestion = questionary.getNumberCurrentQuestion();
        quizProgress.setText(currentNumberQuestion + "/" + totalQuestions);

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Get the Answers from the user */
                int[] radioButtonsIds = {R.id.choices1_textview, R.id.choices2_textview, R.id.choices3_textview, R.id.choices4_textview};
                ArrayList<String> userAnswer = new ArrayList<>();
                for (int i = 0; i < radioButtonsIds.length; i++) {
                    RadioButton answerText = (RadioButton) findViewById(radioButtonsIds[i]);
                    if (answerText.isChecked()) {
                        userAnswer.add(answerText.getText().toString());
                    }
                }
                //Check the Answer before going to the next Question
                questionary.checkAnswer(userAnswer.toArray(new String[0]));
                Question nextQuestion = questionary.getNextQuestion();
                Intent nextIntent;
                if (nextQuestion == null) {
                    nextIntent = new Intent(RadioAnswerActivity.this, FinalActivity.class);
                } else {
                    nextIntent = new Intent(RadioAnswerActivity.this, nextQuestion.getActivity());
                }
                startActivity(nextIntent);
                finish();
            }
        });
    }

    /**
     * Displays the given Question.
     */
    public void displayQuestion(String question) {
        TextView questionView = (TextView) findViewById(R.id.questionTextView);
        questionView.setText(question);
    }

    /**
     * Displays the given Choices.
     */
    public void displayChoices(String[] choices) {
        int[] answerIds = {R.id.choices1_textview, R.id.choices2_textview, R.id.choices3_textview, R.id.choices4_textview};
        for (int i = 0; i < choices.length; i++) {
            TextView answerView = (TextView) findViewById(answerIds[i]);
            answerView.setText(choices[i]);
        }
    }

    /**
     * Displays the given Img.
     */
    public void displayImage(Drawable imageResource) {
        ImageView imgResource = (ImageView) findViewById(R.id.radio_img);
        imgResource.setImageDrawable(imageResource);

    }
}
