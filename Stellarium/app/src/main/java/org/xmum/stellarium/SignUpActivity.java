package org.xmum.stellarium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.xmum.stellarium.utils.DbQuery;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText username, email, password, password2;
    private TextView btnLogin;
    private AppCompatButton btnSignUp;

    private String usernameStr, emailStr, pwdStr,confirmPwdStr;

    private Dialog progressDialog;
    private TextView dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);

        progressDialog = new Dialog(SignUpActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = progressDialog.findViewById(R.id.dialog_text);
        dialogText.setText("Registering user...");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateData()){
                    signUp();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean validateData() {
        usernameStr = username.getText().toString().trim();
        emailStr = email.getText().toString().trim();
        pwdStr = password.getText().toString().trim();
        confirmPwdStr = password2.getText().toString().trim();

        if(usernameStr.isEmpty()){
            username.setError("Please enter your username!");
            return false;
        }
        if(emailStr.isEmpty()){
            email.setError("Please enter your email!");
            return false;
        }
        if(pwdStr.isEmpty()){
            password.setError("Please enter your password!");
            return false;
        }
        if(!confirmPwdStr.equals(pwdStr)){
            Toast.makeText(SignUpActivity.this, "Please enter the same password!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signUp() {
        mAuth.createUserWithEmailAndPassword(emailStr, pwdStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGNUP", "createUserWithEmail:success");

                            // update database
                            DbQuery.createUserData(emailStr, usernameStr, new MyCompleteListener(){

                                @Override
                                public void onSuccess() {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignUpActivity.this, "Successfully signed up!", Toast.LENGTH_SHORT).show();
                                    onSuccessCreateNewAccount();
                                }

                                @Override
                                public void onFailure() {
                                    progressDialog.dismiss();
                                    Toast.makeText(SignUpActivity.this, "Something went wrong... Try later...", Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void onSuccessCreateNewAccount(){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        SignUpActivity.this.finish();
    }
}