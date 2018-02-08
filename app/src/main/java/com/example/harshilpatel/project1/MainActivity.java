/*
  MainActivity.java
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
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 8652;
    private Button enterPhoneNo;
    private String number;
    private Button dialerButton;

    //start of the onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get the id of the button1
        enterPhoneNo = findViewById(R.id.button1);

        //action listener for the button
        enterPhoneNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //what you intent to do
                Intent enterNumber = new Intent(MainActivity.this, EnterPhoneActivity.class);

                //start a new activity for result
                startActivityForResult(enterNumber, REQUEST_CODE);
            }
        });//end of listener
    }//end of onCreate

    /* The following inbuilt function checks the request code and the
       resultCode and does the following performance:
        1) if the code is RESULT_OK then the phone number is
           is valid and if you press the Go to Dialer button you will be
           directed to the dialer with the phone displaying on the space.
        2) if the code is RESULT_CANCELED then the phone number is invalid an
           if you press the Go to dialer button you wont be able to go to the dialer
           and there will be a toast message instead that the phone number you entered was
           invalid.
    */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {                  //get the request code
            number = data.getStringExtra("message");        // get the number from the previous activity
            dialerButton = findViewById(R.id.button2);       // get the dialer button activity

            if(resultCode == Activity.RESULT_OK) {         //if the result is OK

                //set the button action to take you to the dialer
                dialerButton.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {

                        //what you intent to do
                        Intent openDialer = new Intent(Intent.ACTION_DIAL);    //open the dial
                        openDialer.setData(Uri.parse("tel:" + number));       // display the number on the dialer

                        Toast.makeText(MainActivity.this, number + " is valid", Toast.LENGTH_SHORT).show();

                        //start a new activity.
                        startActivity(openDialer);
                    }
                });
            }//end of id
            else if(resultCode == Activity.RESULT_CANCELED){                    //if the result is Canceled or invalid number

                //set this listener for the button
                dialerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //display a toast message that the number is invalid
                        Toast.makeText(MainActivity.this, number + " is not valid", Toast.LENGTH_SHORT).show();

                    }// emd of onClick
                }); // end of actionlistner
            }
        }//end of onActivityResult
    } // end of main Activity
}//end
