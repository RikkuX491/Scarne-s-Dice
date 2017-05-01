package com.example.rikkux491.scarnesdice;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // To access Buttons from XML
import android.widget.TextView; // To access TextViews from XML
import android.widget.ImageView; // To access ImageViews from XML

import java.util.Random;

import static android.os.SystemClock.sleep;

/*
   Author: Ricardo Guerra
*/

public class MainActivity extends AppCompatActivity {

    private int PlayerTotalScore = 0; // The player's current total score to be displayed.
    private int PlayerTurnScore = 0; // The player's current turn score to be displayed.
    private int CPUTotalScore = 0; // The computer's current total score to be displayed.
    private int CPUTurnScore = 0; // The computer's current turn score to be displayed.

    private boolean PlayerTurn = true; // Is it the player's turn? If not, this value is set
    // to false, to let the program know that it is now the Computer's turn.

    private boolean GameOver = false; // If the game is over, no more rolls or holds.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView TurnScore = (TextView) findViewById(R.id.TurnScore);
        TurnScore.setText("");
    }

    /** Called when user clicks the Roll button */
    public void clickRoll(View view){
        if((PlayerTurn == false) || (GameOver == true)){
            return;
            // Disable the button while it's the computer's turn.
        }
        // Do something in response to button

        PlayerTurn();

        while(!PlayerTurn){
            ComputerTurn();

            if(GameOver) return;
        }

    }

    /** Called when user clicks the Hold button */
    public void clickHold(View view){
        if((PlayerTurn == false) || (GameOver == true)){
            return;
            // Disable the button while it's the computer's turn.
        }
        // Do something in response to button

        // Update the player's total score, add the player's turn score to the total.
        PlayerTotalScore += PlayerTurnScore;

        // Reset the player's turn score to 0.
        PlayerTurnScore = 0;

        // Update the label.
        TextView TotalScore = (TextView) findViewById(R.id.TotalScore);
        TotalScore.setText("Your score: " + PlayerTotalScore + " Computer score: " + CPUTotalScore);
        TextView TurnScore = (TextView) findViewById(R.id.TurnScore);
        TurnScore.setText("");

        if(PlayerTotalScore >= 100){
            TurnScore.setText("You win! Congratulations!");
            GameOver = true;
            return;
        }

        // Now it is the Computer's turn.
        PlayerTurn = false;

        while(!PlayerTurn){
            ComputerTurn();

            if(GameOver) return;
        }
    }

    /** Called when user clicks the Reset button */
    public void clickReset(View view){
        // Do something in response to button

        // When the Reset button is clicked, reset all scores to 0.
        PlayerTotalScore = 0;
        PlayerTurnScore = 0;
        CPUTotalScore = 0;
        CPUTurnScore = 0;

        // Update the label text.
        TextView TotalScore = (TextView) findViewById(R.id.TotalScore);
        TotalScore.setText("Your score: " + PlayerTotalScore + " Computer score: " + CPUTotalScore);
        TextView TurnScore = (TextView) findViewById(R.id.TurnScore);
        TurnScore.setText("");

        PlayerTurn = true;
        GameOver = false;
    }

    public void PlayerTurn(){
        ImageView Dice1 = (ImageView) findViewById(R.id.Dice1); // Get picture of die

        // When you click ROLL, you will roll a random number.
        Drawable NextDice = getResources().getDrawable(R.drawable.dice1, null);
        int DiceRoll = RollTheDice();

        if(DiceRoll == 1){
            PlayerTurnScore = 0;

            // Now it is the Computer's turn... To be continued.
            PlayerTurn = false;
        }
        if(DiceRoll == 2){
            PlayerTurnScore += DiceRoll;
            NextDice = getResources().getDrawable(R.drawable.dice2, null);
        }
        if(DiceRoll == 3){
            PlayerTurnScore += DiceRoll;
            NextDice = getResources().getDrawable(R.drawable.dice3, null);
        }
        if(DiceRoll == 4){
            PlayerTurnScore += DiceRoll;
            NextDice = getResources().getDrawable(R.drawable.dice4, null);
        }
        if(DiceRoll == 5){
            PlayerTurnScore += DiceRoll;
            NextDice = getResources().getDrawable(R.drawable.dice5, null);
        }
        if(DiceRoll == 6){
            PlayerTurnScore += DiceRoll;
            NextDice = getResources().getDrawable(R.drawable.dice6, null);
        }

        Dice1.setImageDrawable(NextDice);

        // Update the label to "Your turn score: X". Where X is the Player's Turn Score.
        TextView TurnScore = (TextView) findViewById(R.id.TurnScore);
        TurnScore.setText("Your turn score: " + PlayerTurnScore);
    }

    public void ComputerTurn(){
        String ComputerRolls = "";

//        ImageView Dice1 = (ImageView) findViewById(R.id.Dice1); // Get picture of die

        // Computer rolls, so we will use the random number generator again to simulate this.
//        Drawable NextDice = getResources().getDrawable(R.drawable.dice1, null);
        int DiceRoll = RollTheDice();

        if(DiceRoll == 1){
            CPUTurnScore = 0;
            ComputerRolls += "1";

            // Now it is the Player's turn again.
            PlayerTurn = true;
        }
        if(DiceRoll == 2){
            CPUTurnScore += DiceRoll;
            ComputerRolls += "2";
//            NextDice = getResources().getDrawable(R.drawable.dice2, null);
        }
        if(DiceRoll == 3){
            CPUTurnScore += DiceRoll;
            ComputerRolls += "3";
//            NextDice = getResources().getDrawable(R.drawable.dice3, null);
        }
        if(DiceRoll == 4){
            CPUTurnScore += DiceRoll;
            ComputerRolls += "4";
//            NextDice = getResources().getDrawable(R.drawable.dice4, null);
        }
        if(DiceRoll == 5){
            CPUTurnScore += DiceRoll;
            ComputerRolls += "5";
//            NextDice = getResources().getDrawable(R.drawable.dice5, null);
        }
        if(DiceRoll == 6){
            CPUTurnScore += DiceRoll;
            ComputerRolls += "6";
//            NextDice = getResources().getDrawable(R.drawable.dice6, null);
        }

//        Dice1.setImageDrawable(NextDice);

        // Update the label to "Computer turn score: Y". Where Y is the Computer's Turn Score.
        TextView TurnScore = (TextView) findViewById(R.id.TurnScore);
        TurnScore.setText("Computer rolls: " + ComputerRolls);

        // Now to simulate "Hold" for the computer player.
        // Update the computer's total score, add the computer's turn score to the total.
        CPUTotalScore += CPUTurnScore;

        // Reset the computer's turn score to 0.
        CPUTurnScore = 0;

        // Update the label.
        TextView TotalScore = (TextView) findViewById(R.id.TotalScore);
        TotalScore.setText("Your score: " + PlayerTotalScore + " Computer score: " + CPUTotalScore);
//        TurnScore = (TextView) findViewById(R.id.TurnScore);
//        TurnScore.setText("");

        // Now it is the player's turn again.
        PlayerTurn = true;

        if(CPUTotalScore >= 100){
            TurnScore.setText("Game Over. You lose.");
            GameOver = true;
        }
    }


    // Implementation for what happens when you roll the die
    public int RollTheDice(){
        Random RandomRoll = new Random();

        // Simulate a dice roll by picking a random number from 1 to 6.
        int RandomValue = (RandomRoll.nextInt(6) + 1);

        return RandomValue;
    }

}