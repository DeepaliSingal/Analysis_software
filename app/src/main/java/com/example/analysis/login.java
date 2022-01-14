package com.example.analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText email,password;
    TextView enter, signinhere, forgotpassword;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText) findViewById(R.id.EmailAddress1);
        password=(EditText) findViewById(R.id.Password1);
        enter=(TextView) findViewById(R.id.login);
        forgotpassword=(TextView) findViewById(R.id.forgotpass);
        signinhere=(TextView) findViewById(R.id.signinhere);
        DB=new DBHelper(this);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=email.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(login.this, "PLEASE FILL ALL THE FIELDS", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true)
                    {
                        Toast.makeText(login.this, "LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(login.this, "INVALID CREDENTIALS", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=email.getText().toString();
                Boolean newcred=DB.deletedata(user);
                if(newcred==true)
                {
                    Toast.makeText(login.this,"NOW YOU CAN SIGNUP WITH NEW CREDENTIALS",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(login.this,"PLEASE TRY AGAIN LATER!!",Toast.LENGTH_SHORT).show();
            }
        });
        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}