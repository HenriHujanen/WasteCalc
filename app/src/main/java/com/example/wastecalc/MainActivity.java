package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private TextView eRegister;

    /* Login attempts left */
    private int counter = 5;

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.editTextUsername);
        ePassword = findViewById(R.id.editTextPassword);
        eAttemptsInfo = findViewById(R.id.textViewAttempts);
        eLogin = findViewById(R.id.buttonLogin);
        eRegister = findViewById(R.id.textViewRegister);

        /* Create a new account */
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
            }
        });

        /* Log in button */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = eName.getText().toString();
                String userPassword = ePassword.getText().toString();

                /* Check if fields are empty */
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter username and password.", Toast.LENGTH_LONG).show();

                }else {

                    isValid = validate(userName, userPassword);

                    /* If wrong username or password was entered */
                    if (!isValid) {

                        counter--;

                        eAttemptsInfo.setText("Login attempts Remaining: " + String.valueOf(counter));

                        /* Disable the login button when no attempts left*/
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(MainActivity.this, "All login attempts used, please try again later.", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect username or password.", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If the credentials were correct, go to the main menu */
                    else {

                        startActivity(new Intent(MainActivity.this, MainMenu.class));
                    }

                }
            }
        });
    }

    private boolean validate(String username, String userPassword){

        if(CreateAccount.credentials != null){
            if(username.equals(CreateAccount.credentials.getUsername()) && userPassword.equals(CreateAccount.credentials.getPassword())){
                return true;
            }
        }

        return false;
    }
}