package com.example.ambulancefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverRegisterActivity extends AppCompatActivity {
    TextView btn;

    private EditText inputUsername,inputPassword,inputEmail,inputConfirmPassword;
    Button btnregister;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);
        btn = findViewById(R.id.alreadyhaveacc);

        inputUsername = findViewById(R.id.driUsername);
        inputEmail = findViewById(R.id.driEmail);
        inputPassword = findViewById(R.id.driPassword);
        inputConfirmPassword = findViewById(R.id.driConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(DriverRegisterActivity.this);
        btnregister = findViewById(R.id.dribtnRegister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkCredentials();
            }

        });

        checkCredentials();

        btn.setOnClickListener(v -> startActivity(new Intent(DriverRegisterActivity.this,DriverSignInActivity.class)));
    }
    private void checkCredentials() {
        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpassword = inputConfirmPassword.getText().toString();

        if(username.isEmpty() || username.length()<7)
        {
            showError(inputUsername,"Your Username is not valid!");
        }
        else if(email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Email is not valid!");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(inputPassword,"Password not valid.Enter 7 Charachetrs");
        }
        else if(confirmpassword.isEmpty() || !confirmpassword.equals(password))
        {
            showError(inputConfirmPassword,"Not valid!");
        }
        else
        {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(DriverRegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(DriverRegisterActivity.this,DriverHomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(DriverRegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();

                    }
                }
            });
        }
    }

    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();

    }
}