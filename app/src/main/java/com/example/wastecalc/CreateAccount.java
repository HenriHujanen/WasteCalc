package com.example.wastecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    private EditText eRegName;
    private EditText eRegPassword;
    private EditText eRegEmail;
    private Button eRegister;
    private TextView eRegCancel;

    public static Credentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        eRegName = findViewById(R.id.editTextNewUsername);
        eRegPassword = findViewById(R.id.editTextNewPassword);
        eRegEmail = findViewById(R.id.editTextEmailAddress);
        eRegister = findViewById(R.id.buttonRegister);

        /* Cancel button takes the user back to the login screen */
        eRegCancel = findViewById(R.id.textViewCancelRegisteration);
        eRegCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAccount.this, MainActivity.class));
            }
        });

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String registeredName = eRegName.getText().toString();
                String registeredPassword = eRegPassword.getText().toString();
                String registeredEmail = eRegEmail.getText().toString();

                if(validate(registeredName, registeredEmail, registeredPassword))
                {
                    credentials = new Credentials(registeredName, registeredPassword);
                    Toast.makeText(CreateAccount.this, "Successfully created an account.", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(CreateAccount.this, MainActivity.class));
                }
            }
        });
    }

    /* For checking password requirements*/
    private static boolean checkString(String input) {
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;
        boolean specialCharacterPresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCasePresent = true;
            } else if (specialChars.contains(String.valueOf(currentCharacter))) {
                specialCharacterPresent = true;
            }
        }

        return
                numberPresent && upperCasePresent && lowerCasePresent && specialCharacterPresent;
    }

    /* Check that an username and email was entered, and that the password is OK */
    boolean validate(String name, String email, String password)
    {

        if(name.isEmpty() || email.isEmpty() || (password.length() < 12 || !checkString(password)))
        {
            Toast.makeText(this, "Please enter an username and an email address. Also make sure that your password meets the requirements.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}