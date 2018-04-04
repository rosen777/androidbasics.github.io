package com.example.android.unlearnquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button resetButton = findViewById(R.id.reset);
        resetButton.setVisibility(View.GONE);
    }


    /**
     * Function called upon clicking the submit button
     */

    public int answersMethod() {
        /**
         * Checkboxes
         */
        // Is checkbox one now checked?
        CheckBox checkBoxOne = findViewById(R.id.checkbox_one);
        boolean checkBoxOneText = checkBoxOne.isChecked();

        // Is checkbox two now checked?
        CheckBox checkBoxTwo = findViewById(R.id.checkbox_two);
        boolean checkBoxTwoText = checkBoxTwo.isChecked();

        // Is checkbox three now checked?
        CheckBox checkBoxThree = findViewById(R.id.checkbox_three);
        boolean checkBoxThreeText = checkBoxThree.isChecked();

        // Is checkbox four now checked?
        CheckBox checkBoxFour = findViewById(R.id.checkbox_four);
        boolean checkBoxFourText = checkBoxFour.isChecked();

        // Is checkbox five now checked?
        CheckBox checkBoxFive = findViewById(R.id.checkbox_five);
        boolean checkBoxFiveText = checkBoxFive.isChecked();

        // Is checkbox six now checked?
        CheckBox checkBoxSix = findViewById(R.id.checkbox_six);
        boolean checkBoxSixText = checkBoxSix.isChecked();

        // Is checkbox seven now checked?
        CheckBox checkBoxSeven = findViewById(R.id.checkbox_seven);
        boolean checkBoxSevenText = checkBoxSeven.isChecked();

        // Is checkbox eight now checked?
        CheckBox checkBoxEight = findViewById(R.id.checkbox_eight);
        boolean checkBoxEightText = checkBoxEight.isChecked();

        /**
         * Radio Buttons
         */
        // Is the answer two radio button now checked?
        RadioButton answerTwoButton = findViewById(R.id.answer_two);
        boolean answerTwo = answerTwoButton.isChecked();

        //Is the answer five radio button now checked?
        RadioButton answerFiveButton = findViewById(R.id.answer_five);
        boolean answerFive = answerFiveButton.isChecked();

        //Is the answer nine radio button now checked?
        RadioButton answerNineButton = findViewById(R.id.answer_nine);
        boolean answerNine = answerNineButton.isChecked();

        //Is the answer sixteen radio button now checked?
        RadioButton answerSixteenButton = findViewById(R.id.answer_sixteen);
        boolean answerSixteen = answerSixteenButton.isChecked();


        /**
         * Edit Texts
         */
        // What is the user input?
        EditText answerNineEdit = findViewById(R.id.edit_text_one);
        String userInput = answerNineEdit.getText().toString();

        /**
         * Strings in Edit Texts
         */
        String seventeen = "seventeen";
        String seventeenNumber = "17";

        // Check which radio button was clicked
        if (answerTwoButton.isChecked()) {
            //When the second answer is checked, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (answerFiveButton.isChecked()) {
            //When the fifth answer is checked, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (answerNineButton.isChecked()) {
            //When the ninth answer is checked, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (answerSixteenButton.isChecked()) {
            //When the sixteenth answer is checked, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (checkBoxOne.isChecked() && checkBoxFour.isChecked() && !checkBoxTwo.isChecked() && !checkBoxThree.isChecked()) {
            //When the first checkbox and the fourth checkbox are checked, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (checkBoxFive.isChecked() && checkBoxSeven.isChecked() && !checkBoxSix.isChecked() && !checkBoxEight.isChecked()) {
            //When the fifth checkbox and the seventh checkbox are checked, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (seventeenNumber.equalsIgnoreCase(userInput)) {
            //When the user types in 17, the number of correct answers increases by 1.
            correctAnswers += 1;
        }
        if (seventeen.equalsIgnoreCase(userInput)) {
            //When the user types in seventeen, the number of correct answers increases by 1.
            correctAnswers += 1;
        }

        return correctAnswers;
    }

    /**
     * Your score is calculated with these functions.
     */
    public String calculateScore() {
        correctAnswers = answersMethod();
        String scoreMessage = getString(R.string.correct_answers_summary);
        return scoreMessage;
    }

    public String calculateScore2() {
        correctAnswers = answersMethod();
        String scoreMessage2 = getString(R.string.max_answers_summary);
        return scoreMessage2;
    }

    /**
     * Your score is calculated with these functions.
     */

    public void submitAnswers(View view) {

        if (correctAnswers < 7) {
            String scoreMessage = calculateScore();
            displayMessage(scoreMessage);

            displayScoreA(correctAnswers);

            ImageView handsUpImage = findViewById(R.id.handsup);
            handsUpImage.setVisibility(View.GONE);

            Toast.makeText(this, "Successful submission! You can start unlearning now.", Toast.LENGTH_SHORT).show();
        }

        if (correctAnswers == 7) {
            String scoreMessage2 = calculateScore2();
            displayScoreMax(scoreMessage2);
            ImageView handsUpImage = findViewById(R.id.handsup);
            handsUpImage.setVisibility(View.VISIBLE);
            handsUpImage.setImageResource(R.drawable.handsupinair);


            Toast.makeText(this, "7 of 7! You have nothing to unlearn! Keep learning forward.", Toast.LENGTH_SHORT).show();
            return;
        }


        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setVisibility(View.GONE);

        Button resetButton = findViewById(R.id.reset);
        resetButton.setVisibility(View.VISIBLE);

    }

    public void resetButton(View v) {
        correctAnswers = 0;
        displayScoreA(correctAnswers);
        Toast.makeText(this, "You have reset your score. Try Again!", Toast.LENGTH_SHORT).show();


        //Restore the Submit Quiz Button once clicked
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setVisibility(View.VISIBLE);

        Button resetButton = findViewById(R.id.reset);
        resetButton.setVisibility(View.GONE);

        String scoreMessage3 = getString(R.string.zero_summary);
        displayMax(scoreMessage3);
        String scoreMessage4 = getString(R.string.zero_summary);
        displayScoreB(scoreMessage4);
    }


    /**
     * Display the final score of the user
     */

    public void displayScoreA(int correctAnswers) {
        TextView quizScoreTextView = findViewById(R.id.quiz_score);
        quizScoreTextView.setText("" + correctAnswers + " of 7");
    }

    public void displayScoreMax(String message2) {
        TextView scoreMaxTextView = findViewById(R.id.quiz_score_text);
        scoreMaxTextView.setText(message2);
    }

    private void displayMessage(String message) {
        TextView calculateScoreTextView = findViewById(R.id.quiz_score_text);
        calculateScoreTextView.setText(message);
    }

    public void displayMax(String message3) {
        TextView quizMessageTextView = findViewById(R.id.quiz_score_text);
        quizMessageTextView.setText(message3);
    }

    public void displayScoreB(String message4) {
        TextView quizScoreTextView = findViewById(R.id.quiz_score);
        quizScoreTextView.setText(message4);
    }

}


