package com.example.abhishek.rideon.activities;

/**
 * Created by Abhishek on 31-01-2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.example.abhishek.rideon.R;
import com.example.abhishek.rideon.callback.DefaultCallback;
import com.example.abhishek.rideon.utils.Defaults;

public class LoginActivity extends Activity {
    private EditText emaillogin, passwordlogin;
    private Button loginButton;
    private CheckBox rememberLoginBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();

        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.SECRET_KEY, Defaults.VERSION);

        Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(this) {
            @Override
            public void handleResponse(Boolean isValidLogin) {
                if (isValidLogin && Backendless.UserService.CurrentUser() == null) {
                    String currentUserId = Backendless.UserService.loggedInUser();

                    if (!currentUserId.equals("")) {
                        Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(LoginActivity.this, "Logging in...") {
                            @Override
                            public void handleResponse(BackendlessUser currentUser) {
                                super.handleResponse(currentUser);
                                Backendless.UserService.setCurrentUser(currentUser);
                                startActivity(new Intent(getBaseContext(), LoginSuccessActivity.class));
                                finish();
                            }
                        });
                    }
                }

                super.handleResponse(isValidLogin);
            }
        });
    }

    private void initUI() {
        emaillogin = (EditText) findViewById(R.id.emaillogin);
        passwordlogin = (EditText) findViewById(R.id.passwordlogin);
        loginButton = (Button) findViewById(R.id.loginButton);
        rememberLoginBox = (CheckBox) findViewById(R.id.rememberLoginBox);
        loginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });

    }

    public void onLoginButtonClicked() {
        String identity = emaillogin.getText().toString();
        String password = passwordlogin.getText().toString();
        boolean rememberLogin = rememberLoginBox.isChecked();

        Backendless.UserService.login(identity, password, new DefaultCallback<BackendlessUser>(LoginActivity.this) {
            public void handleResponse(BackendlessUser backendlessUser) {
                super.handleResponse(backendlessUser);
                startActivity(new Intent(LoginActivity.this, LoginSuccessActivity.class));
                finish();
            }
        }, rememberLogin);
    }
}