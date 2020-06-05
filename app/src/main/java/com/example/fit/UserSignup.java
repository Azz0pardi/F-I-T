package com.example.fit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class UserSignup extends AppCompatActivity {


//    Initializers
    DbHelper dbHelper;
    EditText username, password,confirmpass;
    Button signup;
    List<user>  allusers;
    boolean checker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_user_signup);
         username = findViewById(R.id.usernamecreate);
         password= findViewById(R.id.passwordcreate);
         confirmpass = findViewById(R.id.passwordconfirm);
         signup = findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              On click the username, password, confirm password are retrieved from the EditText
                 String Username = username.getText().toString();
                 String Password = password.getText().toString();
                String Confirm =  confirmpass.getText().toString();

                user User = new user(); // A new user object is created

                DbHelper Dbhelper = new DbHelper(v.getContext());
                allusers =  Dbhelper.getAllUsers();

                // The Dbhelper is initialized and a list of all users is retrieved


                if(allusers.size() != 0) {                                        // If the list isn't empty then it's searched for any username which
                    for (int i = 0; i < allusers.size(); i++) {                   // is the same as the one the user just entered. If found then the user already exists
                        if (allusers.get(i).getUsername().equals(Username)) {
                            checker = false;
                        }else{
                            checker = true;
                        }
                    }
                }else{
                    checker = true;
                }

                Log.i("this :",Username+Password+Confirm);
                if(!Username.isEmpty() && !Password.isEmpty() && checker == true &&  Confirm.equals(Password)) { //If all the fields have been entered and the password
                    User = new user(-1, Username, Password);                                                 // is equal to the confirmpassword then a new user is created
                    Dbhelper.InsertUser(User);
                    Toast.makeText(v.getContext(), "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserSignup.this, MainActivity.class);             // After successfully creating a new user the user is taken
                    startActivity(intent);                                                                      // to the Main Activity
                }else{
                    Toast.makeText(v.getContext(), "Error Creating User ", Toast.LENGTH_SHORT).show();    // If Unsuccessful the user is prompted that an error has occured
                }
            }
        });

        final Button button = findViewById(R.id.skip_login); // If the skip button is pressed then the user is taken to the Main Activity
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // this mine
                openBaseActivity();
            }
        });

    }

    private void openBaseActivity() {
        Intent intent = new Intent(UserSignup.this, MainActivity.class);  // Function which takes the user from this Activity to Main
        startActivity(intent);
    }

}
