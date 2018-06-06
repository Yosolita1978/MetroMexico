package co.yosola.metromexico;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.button_start);

        final Context context = this;

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Questionary and Next Question
                Question firstQuestion = Questionary.getQuestionary(context).getCurrentQuestion();
                Intent quizIntent = new Intent(MainActivity.this, firstQuestion.getActivity());
                startActivity(quizIntent);

                //Finish View once the Question is called.
                finish();
            }
        });
    }
}

