package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button myButton,myButton2;
    TextView myTextView,textViewScore,textViewPlayerName,myTextView2,textViewScore2,textViewPlayerName2, textViewTotalScore1,textViewTotalScore2;
    ImageView myImageView,myImageView2;
    Random myRandom=new Random();
    EditText editPlayerName,editPlayerName2;
    int r1,r2;
    int totalscore1=0;
    int totalscore2=0;
    int myImageArray[]=new int[7];
    boolean player1_authorized=true;

    MediaPlayer dice_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Casting de Player 1
        myButton=findViewById(R.id.button1);
        myTextView=findViewById(R.id.textView1);
        myImageView=findViewById(R.id.imageView1);
        textViewScore=findViewById(R.id.textView);
        textViewPlayerName=findViewById(R.id.textViewPlayerName);

        //Casting de Player 2
        myButton2=findViewById(R.id.button12);
        myTextView2=findViewById(R.id.textView12);
        myImageView2=findViewById(R.id.imageView12);
        textViewScore2=findViewById(R.id.textView2);
        textViewPlayerName2=findViewById(R.id.textViewPlayerName2);
        textViewTotalScore1=findViewById(R.id.textViewTotalScore1);
        textViewTotalScore2=findViewById(R.id.textViewTotalScore2);


        dice_sound= MediaPlayer.create(MainActivity.this,R.raw.dice_throw);




        myImageArray[1]=R.drawable.one;
        myImageArray[2]=R.drawable.two;
        myImageArray[3]=R.drawable.three;
        myImageArray[4]=R.drawable.four;
        myImageArray[5]=R.drawable.five;
        myImageArray[6]=R.drawable.six;

        Intent myIntent=getIntent();
        textViewPlayerName.setText(myIntent.getStringExtra("name_player1"));
        textViewPlayerName2.setText(myIntent.getStringExtra("name_player2"));


        SharedPreferences mySharedPreferences =getSharedPreferences("TotalScore1SP", MODE_PRIVATE);

        totalscore1=mySharedPreferences.getInt("TotalScore1Save",0);
        textViewTotalScore1.setText(Integer.toString(totalscore1));

        totalscore2=mySharedPreferences.getInt("TotalScore2Save",0);
        textViewTotalScore2.setText(Integer.toString(totalscore2));


        myButton.setOnClickListener(new View.OnClickListener() {
            int p1_clickcount=0;
            @Override
            public void onClick(View v) {


                //myButton.setVisibility(View.INVISIBLE);
                //myButton2.setVisibility(View.VISIBLE);

                /*myButton.setEnabled(false);
                myButton2.setEnabled(true);*/

                if (player1_authorized){
                    dice_sound.start();
                    r1=myRandom.nextInt(6)+1;

                    totalscore1=totalscore1+r1;
                    textViewTotalScore1.setText(Integer.toString(totalscore1));

                    SharedPreferences mySharedPreferences =getSharedPreferences("TotalScore1SP", MODE_PRIVATE);
                    SharedPreferences.Editor myeditor=mySharedPreferences.edit();
                    myeditor.putInt("TotalScore1Save",totalscore1);
                    myeditor.apply();

                    myTextView.setText(Integer.toString(r1));
                    myImageView.setImageResource(myImageArray[r1]);
                    player1_authorized=false;

                    p1_clickcount=p1_clickcount+1;
                    if(p1_clickcount==10)
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        if (totalscore1 > totalscore2) {
                            Intent myIntent=getIntent();
                            alertDialog.setTitle(getString(R.string.winner_msg)+myIntent.getStringExtra("name_player1"));
                        }
                        if (totalscore1 < totalscore2) {
                            Intent myIntent=getIntent();
                            alertDialog.setTitle(getString(R.string.winner_msg)+myIntent.getStringExtra("name_player2"));
                        }
                        alertDialog.setMessage(getString(R.string.continue_msg));
                        alertDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Player 1 clicked YES", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"The P1 has a score of "+ totalscore1, Toast.LENGTH_LONG).show();
                            }
                        });
                        alertDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Player 1 clicked NO", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });

                        alertDialog.show();

                        Toast.makeText(getApplicationContext(),"Player 1 clicked 10 time!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,(R.string.wait_p2),Toast.LENGTH_SHORT).show();
                }
            }
        });


        myButton2.setOnClickListener(new View.OnClickListener() {
            int p2_clickcount=0;
            @Override
            public void onClick(View v) {

                //myButton2.setVisibility(View.INVISIBLE);
                //myButton.setVisibility(View.VISIBLE);

                /*myButton2.setEnabled(false);
                myButton.setEnabled(true);*/

                if (!player1_authorized){
                    dice_sound.start();
                    r2=myRandom.nextInt(6)+1;

                    totalscore2=totalscore2+r2;
                    textViewTotalScore2.setText(Integer.toString(totalscore2));

                    SharedPreferences mySharedPreferences =getSharedPreferences("TotalScore2SP", MODE_PRIVATE);
                    SharedPreferences.Editor myeditor=mySharedPreferences.edit();
                    myeditor.putInt("TotalScore2Save",totalscore2);
                    myeditor.apply();

                    myTextView2.setText(Integer.toString(r2));
                    myImageView2.setImageResource(myImageArray[r2]);
                    player1_authorized=true;

                    p2_clickcount=p2_clickcount+1;
                    if(p2_clickcount==10)
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        if (totalscore1 > totalscore2) {
                            Intent myIntent=getIntent();
                            alertDialog.setTitle(getString(R.string.winner_msg)+myIntent.getStringExtra("name_player1"));
                        }
                        if (totalscore1 < totalscore2) {
                            Intent myIntent=getIntent();
                            alertDialog.setTitle(getString(R.string.winner_msg)+myIntent.getStringExtra("name_player2"));
                        }
                        alertDialog.setMessage(getString(R.string.continue_msg));
                        alertDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Player 2 clicked YES", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"The P2 has a score of "+ totalscore1, Toast.LENGTH_LONG).show();
                            }
                        });
                        alertDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Player 2 clicked NO", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });

                        alertDialog.show();

                        Toast.makeText(getApplicationContext(),"Player 1 clicked 10 time!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,(R.string.wait_p1),Toast.LENGTH_SHORT).show();
                }



            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savePlayer1Score",r1);
        outState.putInt("savePlayer2Score",r2);
        outState.putBoolean("authorization",player1_authorized);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        r1=savedInstanceState.getInt("savePlayer1Score");
        myTextView.setText(Integer.toString(r1));
        myImageView.setImageResource(myImageArray[r1]);



        r2=savedInstanceState.getInt("savePlayer2Score");
        myTextView2.setText(Integer.toString(r2));
        myImageView2.setImageResource(myImageArray[r2]);

        player1_authorized=savedInstanceState.getBoolean("authorization");


    }
}
