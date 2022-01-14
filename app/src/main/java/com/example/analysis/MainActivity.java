package com.example.analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,confirmpassword,email;
    TextView register,existingusers;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText) findViewById(R.id.PersonName);
        password=(EditText) findViewById(R.id.Password);
        confirmpassword=(EditText) findViewById(R.id.confirmPassword);
        email=(EditText) findViewById(R.id.EmailAddress);
        register=(TextView) findViewById(R.id.signup);
        existingusers=(TextView) findViewById(R.id.loginhere);
        DB= new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=confirmpassword.getText().toString();
                String em=email.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")||em.equals(""))
                    Toast.makeText(MainActivity.this, "PLEASE FILL ALL THE FIELDS", Toast.LENGTH_SHORT).show();
                else
                {
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,pass,em);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "USER ALREADY EXISTS!! PLEASE LOGIN", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "PASSWORDS DON'T MATCH", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        existingusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
    }
}