package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public boolean isUser = true;
    public String[] winCombinations = {"012","345","678", "036", "147", "258","048", "246"};
    public int[] arrayOfSteps;
    public int winner = -1;
    public boolean checkIfEnd(){
        boolean flag = false;
        int i = 0;
        while(i<arrayOfSteps.length){
            if(arrayOfSteps[i]==-1) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }
    public void stepOfComp(){
       // Toast.makeText(this, "In stepOfComp", Toast.LENGTH_SHORT).show();
        int randomNumber = 0;
        //checking if all occupied
        boolean flag = checkIfEnd();
        if (!flag) {
            this.winner = 2;
            Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show();
        }
        else {
            do {
                double randomNumberD = (Math.random() * 8); // random for comp step
                randomNumber = (int) randomNumberD;
            } while (this.arrayOfSteps[randomNumber] != -1);
          //  Toast.makeText(this, "random: " + randomNumber, Toast.LENGTH_SHORT).show();
            ImageView nol;
            if (this.arrayOfSteps[randomNumber] == -1) {
                switch (randomNumber) {
                    case 0:
                        nol = (ImageView) findViewById(R.id.imageView11_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 1:
                        nol = (ImageView) findViewById(R.id.imageView12_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 2:
                        nol = (ImageView) findViewById(R.id.imageView13_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 3:
                        nol = (ImageView) findViewById(R.id.imageView21_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 4:
                        nol = (ImageView) findViewById(R.id.imageView22_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 5:
                        nol = (ImageView) findViewById(R.id.imageView23_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 6:
                        nol = (ImageView) findViewById(R.id.imageView31_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 7:
                        nol = (ImageView) findViewById(R.id.imageView32_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    case 8:
                        nol = (ImageView) findViewById(R.id.imageView33_nol);
                        nol.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                        break;
                    default:
                        break;
                }
            }
            arrayOfSteps[randomNumber] = 0;
            isUser = !isUser;
            check();

        }
        if (this.winner != -1) {
            Button playAgain = (Button) findViewById(R.id.button);
            playAgain.animate().alpha(1.0f).setDuration(1000L);
            playAgain.setOnClickListener(myButtonClickListener);
            return;
        }

    }
    public void check(){
        int sum_of_same_nol = 0;
        int sum_of_same_chrest = 0;
        for (String combination : winCombinations){
                for (int place = 0; place < combination.length(); place++){
                    int sign = arrayOfSteps[Integer.parseInt(String.valueOf(combination.charAt(place)))];
                    if (sign == -1) {
                        sum_of_same_nol = 0;
                        sum_of_same_chrest = 0;
                        break;
                    } else if (sign == 0){
                        sum_of_same_nol++;
                    } else sum_of_same_chrest++;
                }
            if(sum_of_same_nol>=3){
                this.winner = 0;
                Toast.makeText(this, "ZERO WIN", Toast.LENGTH_SHORT).show();
                return;
            } else if (sum_of_same_chrest>=3){
                this.winner = 1;
                Toast.makeText(this, "CROSS WIN", Toast.LENGTH_SHORT).show();
                return;
            }
            sum_of_same_chrest = 0;
            sum_of_same_nol = 0;
        }


    }
    public void playAgain(int id){
        ImageView sign = (ImageView) findViewById(id);
        sign.animate().alpha(0.0f).setDuration(1000L);
    }
    View.OnClickListener myButtonClickListener = new View.OnClickListener() { // for button
        @Override
        public void onClick(View v) {
            // disappear all
            playAgain(R.id.imageView11_chrest);
            playAgain(R.id.imageView11_nol);
            playAgain(R.id.imageView12_chrest);
            playAgain(R.id.imageView12_nol);
            playAgain(R.id.imageView13_chrest);
            playAgain(R.id.imageView13_nol);
            playAgain(R.id.imageView21_chrest);
            playAgain(R.id.imageView21_nol);
            playAgain(R.id.imageView22_chrest);
            playAgain(R.id.imageView22_nol);
            playAgain(R.id.imageView23_chrest);
            playAgain(R.id.imageView23_nol);
            playAgain(R.id.imageView31_chrest);
            playAgain(R.id.imageView31_nol);
            playAgain(R.id.imageView32_chrest);
            playAgain(R.id.imageView32_nol);
            playAgain(R.id.imageView33_chrest);
            playAgain(R.id.imageView33_nol);
            winner = -1;
            isUser = true;
            for (int i = 0; i < arrayOfSteps.length; i++){
                arrayOfSteps[i] = -1;
            }
            Button playAgain = (Button) findViewById(R.id.button);
            playAgain.animate().alpha(0.0f).setDuration(1000L);
        }
    };
    public void step(int place, int mean){
        //Log.i("Info", "In step");
        arrayOfSteps[place] = mean;
        isUser = !isUser;
       // Toast.makeText(this, ""+arrayOfSteps[place], Toast.LENGTH_SHORT).show();

        //checking + check if not step! for user
        check();
        if (this.winner != -1) {
            Button playAgain = (Button) findViewById(R.id.button);
            playAgain.animate().alpha(1.0f).setDuration(1000L);
            playAgain.setOnClickListener(myButtonClickListener);
            return;
        }
        stepOfComp();
    }
    public void firstRow1(View view) {
        if (isUser) {
          //  Toast.makeText(this, "in 11", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[0] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView11_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(0, 1);
        }
    }
    public void firstRow2(View view){
            if (isUser) {
              //  Toast.makeText(this, "in 12", Toast.LENGTH_SHORT).show();
                if (this.winner != -1) return;
                if (arrayOfSteps[1] != -1) return;
                ImageView chrest = (ImageView) findViewById(R.id.imageView12_chrest);
                chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
                step(1, 1);
            }
    }
    public void firstRow3(View view){
        if(isUser){
           // Toast.makeText(this, "in 13", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[2] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView13_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(2, 1);
        }
    }
    public void secondRow1(View view){
        if(isUser){
           // Toast.makeText(this, "in 21", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[3] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView21_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(3, 1);
        }
    }
    public void secondRow2(View view){
        if(isUser){
           // Toast.makeText(this, "in 22", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[4] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView22_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step( 4, 1);
        }
    }
    public void secondRow3(View view){
        if(isUser){
           // Toast.makeText(this, "in 23", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[5] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView23_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(5, 1);
        }
    }
    public void thirdRow1(View view){
        if(isUser){
            //Toast.makeText(this, "in 31", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[6] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView31_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(6, 1);
        }
    }
    public void thirdRow2(View view){
        if(isUser){
           // Toast.makeText(this, "in 32", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[7] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView32_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(7, 1);
        }
    }
    public void thirdRow3(View view){
        if(isUser){
           //Toast.makeText(this, "in 33", Toast.LENGTH_SHORT).show();
            if (this.winner != -1) return;
            if (arrayOfSteps[8] != -1) return;
            ImageView chrest = (ImageView) findViewById(R.id.imageView33_chrest);
            chrest.animate().alpha(1.0f).rotation(360f).setDuration(2000L);
            step(8, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.arrayOfSteps = new int[9];
        for (int i = 0; i < this.arrayOfSteps.length; i++){
            this.arrayOfSteps[i] = -1;
        }
    }
}