package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstPage extends AppCompatActivity {

    EditText editText1player1,editText1player2;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        editText1player1=findViewById(R.id.editTextPlayer1);
        editText1player2=findViewById(R.id.editTextPlayer2);

        buttonStart=findViewById(R.id.buttonStart);

    }

    public void goToGame(View view) {
        Intent myIntent= new Intent(FirstPage.this,MainActivity.class);
        myIntent.putExtra("name_player1",editText1player1.getText().toString());
        myIntent.putExtra("name_player2",editText1player2.getText().toString());
        startActivity(myIntent);
    }
}
