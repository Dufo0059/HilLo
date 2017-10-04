/**
 *  the purpose of this app was to make take user input and compare it to a randomly generated number
 *  we then turned this in to a game making users try and guess the randomly generated number.
 *  This program also counts the number of guess the user takes. If the user takes more the 10
 *  guesses they must reset the random number but if they get it under 9 or 5 they are rewarded
 *  with a winning Toast message.
 *  @author Ben Dufour (dufo0059@algonquinlive.com)
 */

package com.example.bendufour.hillo;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;

import java.util.Random;






public class MainActivity extends AppCompatActivity {

    //here i am initializing variables that I will be using in most of my code

    private static final String ABOUT_DIALOG_TAG = "About Dialog";

    private int theNumber;
    private int numberOfUserGuesses;
    private int guessNumber;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //onCreateOptionsMenu(Menu menu);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button guessButton = (Button) findViewById(R.id.button);
        theNumber = randomGen();


        //Toast.makeText(getApplicationContext() ,Integer.toString(theNumber), Toast.LENGTH_SHORT).show();

        numberOfUserGuesses = 0;

        guessButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                numberOfUserGuesses++;


                EditText guessET = (EditText) findViewById(R.id.editText);

                String theGuessString = guessET.getText().toString();

                //String strNmber = theNumber.Integer.toString();

                try {
                    guessNumber = Integer.parseInt(theGuessString);
                } catch (NumberFormatException e) {
                    guessET.setError( "Guess is not valid" );
                    guessET.requestFocus();
                    Toast.makeText(getApplicationContext() ,"must be a digit", Toast.LENGTH_SHORT).show();
                    return;
                }


                 if(guessNumber >= 1001 || guessNumber <= -1){
                     guessET.setError("Guess is not valid");
                     guessET.requestFocus();
                     Toast.makeText(getApplicationContext() ,"number must be between 1 - 1000", Toast.LENGTH_SHORT).show();

                     return;
                 }



                 if (numberOfUserGuesses >= 11){

                     Toast.makeText(getApplicationContext() ,"Please Reset!", Toast.LENGTH_SHORT).show();

                     return;

                 }else if (theNumber < guessNumber){

                     Toast.makeText(getApplicationContext() ,"too high", Toast.LENGTH_SHORT).show();

                     return;

                 }else if (theNumber > guessNumber) {

                     Toast.makeText(getApplicationContext() ,"too low", Toast.LENGTH_SHORT).show();

                     return;

                 }
                 else if (theNumber == guessNumber) {


                     if (numberOfUserGuesses > 6 && numberOfUserGuesses <= 10) {

                         Toast.makeText(getApplicationContext(), "Excellent win!", Toast.LENGTH_LONG).show();
                         return;

                     } else if (numberOfUserGuesses > 0 && numberOfUserGuesses <= 5) {

                         Toast.makeText(getApplicationContext(), "Superior win!", Toast.LENGTH_LONG).show();

                         return;
                     }
                 }

               // Log.i("info number", Integer.toString(i1));
                //Toast.makeText(getApplicationContext() ,Integer.toString(i1), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext() ,Integer.toString(theNumber), Toast.LENGTH_SHORT).show();




            }
        });

        Button resetButton = (Button) findViewById(R.id.button2);

        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // generate the new random number
                theNumber = randomGen();

                numberOfUserGuesses = 0;

                 //Toast.makeText(getApplicationContext() ,"test2", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext() ,Integer.toString(i1), Toast.LENGTH_SHORT).show();


            }
        });

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                // generate the new random number
                //Toast.makeText(getApplicationContext() ,"test2", Toast.LENGTH_SHORT).show();
                 Toast.makeText(getApplicationContext() ,Integer.toString(theNumber), Toast.LENGTH_LONG).show();

                return true;


            }

        });


    }


    public int randomGen () {

        int min = 0;
        int max = 1000;

        Random theNumber = new Random();
        int i1 = theNumber.nextInt(max - min + 1) + min;

        return (i1);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
