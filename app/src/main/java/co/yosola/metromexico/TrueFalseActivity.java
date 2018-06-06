package co.yosola.metromexico;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TrueFalseActivity extends AppCompatActivity {

    String userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the true_false_activity.xml layout file
        setContentView(R.layout.true_false_activity);

        final Questionary questionary = Questionary.getQuestionary(this);
        Question currentQuestion = questionary.getCurrentQuestion();
        displayQuestion(currentQuestion.getQuestion());
        displayChoices(currentQuestion.getChoices());
        displayImage(currentQuestion.getImageResource());

        //Set up the progress Bar progress
        ProgressBar quizProgressBar=(ProgressBar)findViewById(R.id.determinateBar);
        quizProgressBar.setProgress(questionary.getProgress());

        //Set up the number of question and the current question
        TextView quizProgress=(TextView) findViewById(R.id.currentquestion_view);
        int totalQuestions = questionary.getTotalQuestions();
        int currentNumberQuestion = questionary.getNumberCurrentQuestion();
        quizProgress.setText(currentNumberQuestion + "/" + totalQuestions);


        final Button trueButton = (Button) findViewById(R.id.true_button);
        final Button falseButton = (Button) findViewById(R.id.false_button);

        final int selectedColor = getResources().getColor(R.color.colorAccent);
        final int unselectedColor = getResources().getColor(R.color.gray);

        //Create an event listener for the true_button
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueButton.setBackgroundColor(selectedColor);
                falseButton.setBackgroundColor(unselectedColor);

                userAnswer = trueButton.getText().toString();
            }
        });

        //Create an event listener for the false_button
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                falseButton.setBackgroundColor(selectedColor);
                trueButton.setBackgroundColor(unselectedColor);

                userAnswer = falseButton.getText().toString();
            }
        });



        // Create an event listener for the next button
        Button nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////Check the Answer before going to the next Question
                questionary.checkAnswer(userAnswer);
                Question nextQuestion = questionary.getNextQuestion();
                Intent nextIntent;
                if(nextQuestion == null){
                    nextIntent = new Intent(TrueFalseActivity.this, FinalActivity.class);
                } else {
                    nextIntent = new Intent(TrueFalseActivity.this, nextQuestion.getActivity());
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
    public void displayChoices(String[] choices){
        int[] answerIds = {R.id.true_button, R.id.false_button};
        for(int i = 0; i < choices.length; i++){
            TextView answerView = (TextView) findViewById(answerIds[i]);
            answerView.setText(choices[i]);
        }
    }

    /**
     * Displays the given Img.
     */
    public void displayImage(Drawable imageResource) {
        ImageView imgResource = (ImageView) findViewById(R.id.true_img);
        imgResource.setImageDrawable(imageResource);

    }
}
