package co.yosola.metromexico;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);

        final Questionary questionary = Questionary.getQuestionary(this);
        if(questionary.hasPassed()){
            TextView finalText = (TextView)findViewById(R.id.final_text_view);
            finalText.setText(R.string.final_text_won);
        } else {
            TextView finalText = (TextView)findViewById(R.id.final_text_view);
            finalText.setText(R.string.final_text_lost);
        }

        //Set up the number of question and the current question
        TextView finalProgress=(TextView) findViewById(R.id.score_text_view);
        int totalQuestions = questionary.getTotalQuestions();
        int correctAnswers = questionary.getTotalCorrectAnswers();
        finalProgress.setText("Tuviste " + correctAnswers + " respuestas acertadas de un total de " + totalQuestions + " preguntas.");

        Button startAgainButton = (Button) findViewById(R.id.button_start_again);
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Reset the Questionary and send the user to the main activity
                questionary.reset();
                Intent quizIntent = new Intent(FinalActivity.this, MainActivity.class);
                startActivity(quizIntent);

                //Finish View once the Question is called.
                finish();
            }
        });
    }
}
