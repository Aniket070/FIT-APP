package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registerpage extends AppCompatActivity {

    int problem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        final EditText etNameR = (EditText) findViewById(R.id.etNameR);
        final EditText etPasswordR = (EditText) findViewById(R.id.etPasswordR);
        final EditText etPasswordR2 = (EditText) findViewById(R.id.etPasswordR2);
        final TextView HelpR = (TextView) findViewById(R.id.HelpR);

        final Button btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                check(etPasswordR.getText().toString());
                check1(etPasswordR.getText().toString(),etPasswordR2.getText().toString());
                check2(etNameR.getText().toString(),etPasswordR.getText().toString(),etPasswordR2.getText().toString());

                if(problem == 5){

                    Intent LoginIntent = new Intent(Registerpage.this,MainActivity.class);
                    Registerpage.this.startActivity(LoginIntent);
                }
                else{
                    switch(problem){
                        case 1:
                            HelpR.setText("Password too small! Enter different Password.");
                            break;
                        case 2:
                            HelpR.setText("Password dont Match!! enter Password again!");
                            break;
                        case 3:
                            HelpR.setText("Username Already Exist ");
                            break;
                    }
                }


            }
        });


    }
        private void check(String p1){
            int p1length;
            p1length = p1.length();

            if((p1length) < 5 ){
                problem = 1;
            }else{
                problem = 5;
            }
        }
        private void check1(String p1, String p2){
            if(p1.equals(p2)){
                problem=5;
                check(p1);
            }else{
                problem=2;
            }
        }
        private void check2(String name, String p1, String p2){
            int lengthname = name.length();
            if(name.equals("Admin")){
                problem = 3;
            }else{
                problem=5;
                check1(p1,p2);
            }
        }
}
