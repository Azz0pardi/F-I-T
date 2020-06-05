package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class UserLogin extends AppCompatActivity {


List<user> allusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Initializers

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button signupbtn = findViewById(R.id.signupbtn);



        // If the login button is pressed then the Username and password are taken from the EditText

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username= username.getText().toString();
                String Password = password.getText().toString();

               user User = new user();       // A new user is created

                DbHelper Dbhelper = new DbHelper(v.getContext());
                allusers =  Dbhelper.getAllUsers();
                if(allusers.size() == 0){
                    Toast.makeText(v.getContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                }else{
                for ( int i = 0;i < allusers.size(); i++){          // The list of users is checked for the username entered
                    if(allusers.get(i).getUsername().equals(Username) && allusers.get(i).getPassword().equals(Password)  ){ // If the username is found in the list then the password is checked
                        Toast.makeText(v.getContext(), "Success!", Toast.LENGTH_SHORT).show();                         //  if passwords match then the user is logged in and taken to the main activity
                        Intent intent = new Intent(UserLogin.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(v.getContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show(); // an error is displayed if conditions arent met

                    }
                }}





            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, UserSignup.class);
                startActivity(intent);
            }
        });
        // If the signup button is clicked the user is taken to the sign up Acitivity





        // If button is clicked then the user has chosen to skip logging in process
        final Button button = findViewById(R.id.skip_login);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openBaseActivity();
            }
        });
    }

        // Function used to take user / open the MainActivity
    private void openBaseActivity() {
        Intent intent = new Intent(UserLogin.this, MainActivity.class);
        startActivity(intent);
    }

}
