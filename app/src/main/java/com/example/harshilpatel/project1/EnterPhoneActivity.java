/*
  EnterPhoneActivity.java
  Project-1
  Created by Harshil Patel on 2/08/18.
  Copyright © 2018 UIC. All rights reserved.

  <<Harshil Patel>>
   U. of Illinois, Chicago
   CS 478: Spring 2018
   Project 01
 */

package com.example.harshilpatel.project1;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//start of the main activity
public class EnterPhoneActivity extends AppCompatActivity {


    //Edit text
    private EditText phoneNo;


    //On Create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone);

        //the the edit text id
        phoneNo = findViewById(R.id.editText2);

        //the textView.OnEditorActionListener method
        phoneNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                //if the enter or done is pressed on the soft keyboard.
                if (actionId == KeyEvent.KEYCODE_ENTER ||
                        actionId == EditorInfo.IME_ACTION_DONE) {

                    //check if the phone number is valid
                    //if it is valid then display a toast method
                    // and start a new intent and send the number to the first activity.

                    if (isValidate(phoneNo.getText().toString())) {

                        Toast.makeText(EnterPhoneActivity.this, phoneNo.getText() + " is valid", Toast.LENGTH_SHORT).show();
                        Intent validNumber = new Intent();
                        validNumber.putExtra("message", phoneNo.getText().toString());
                        setResult(Activity.RESULT_OK, validNumber);                         //send RESULT_OK if the number is Valid.
                        finish();                                                       //kill the activity.
                        return true;
                    }

                    //if the phone number is not valid the display that the number is invalid
                    //start a new intent, send the invalid number to the first activity
                    //and then kill the app/
                    if (!isValidate(phoneNo.getText().toString())) {

                        Toast.makeText(EnterPhoneActivity.this, phoneNo.getText() + " is not valid", Toast.LENGTH_SHORT).show();
                        Intent notValidNumber = new Intent();
                        notValidNumber.putExtra("message", phoneNo.getText().toString());
                        setResult(Activity.RESULT_CANCELED, notValidNumber);            //send RESULT_CANCELED if the number is Valid.
                        finish();   //kill the app

                        return true;
                    } // end of if
                }//end of EditorAction
                return false;
            }//end of OnEditorActionListener
        });//end of the on create method
    }

    //check the number if it is of the format[ (XXX) XXX-XXX where
    //X is the integer.
    public boolean isValidate(String number) {

        number = number.trim(); //trim the leading spaces and the trailing spaces

        if (Character.isDigit(number.charAt(1)) && Character.isDigit(number.charAt(2)) && Character.isDigit(number.charAt(3)) &&
                Character.isDigit(number.charAt(6)) && Character.isDigit(number.charAt(7)) && Character.isDigit(number.charAt(8)) &&
                Character.isDigit(number.charAt(10)) && Character.isDigit(number.charAt(11)) && Character.isDigit(number.charAt(12)) &&
                Character.isDigit(number.charAt(13))) {
            if (number.charAt(0) == '(' && number.charAt(4) == ')' && number.charAt(5) == ' ' && number.charAt(9) == '-') {
                //return true.
                return true;
            }
        } else {
            return false;    //else return false
        }

        return true;
    }//end of isValid

}//end of enterPhoneActivity
