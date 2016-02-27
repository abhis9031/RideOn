package com.example.abhishek.rideon.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.example.abhishek.rideon.R;
import com.example.abhishek.rideon.activities.LoginSuccessActivity;
import com.example.abhishek.rideon.activities.MainActivity;
import com.example.abhishek.rideon.callback.DefaultCallback;
import com.example.abhishek.rideon.utils.Defaults;

import java.util.zip.Inflater;

/**
 * Created by Abhishek on 13-02-2016.
 */
public class LoginView extends BaseView {

    private EditText emaillogin, passwordlogin;
    private Button loginButton;
    private CheckBox rememberLoginBox;
    public LoginView(Context context) {

        super(context);
        LayoutInflater  mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.logindetails, this, true);
       /* mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.logindetails, (ViewGroup) getRootView(),false);*/
        method();

    }

    @Override
    protected View getView(int layOutId) {
        return super.getView(layOutId);
    }

    public void method()
{
    initUI();

    Backendless.setUrl(Defaults.SERVER_URL);
    Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.SECRET_KEY, Defaults.VERSION);

    Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(mContext) {
        @Override
        public void handleResponse(Boolean isValidLogin) {
            if (isValidLogin && Backendless.UserService.CurrentUser() == null) {
                String currentUserId = Backendless.UserService.loggedInUser();

                if (!currentUserId.equals("")) {
                    Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(mContext, "Logging in...") {
                        @Override
                        public void handleResponse(BackendlessUser currentUser) {
                            super.handleResponse(currentUser);
                            Backendless.UserService.setCurrentUser(currentUser);
                            mContext.startActivity(new Intent(mContext, LoginSuccessActivity.class));
                        }
                    });
                }
            }

            super.handleResponse(isValidLogin);
        }
    });
}

    @Override
    public View getPopulatedView(View mView, ViewGroup parent) {
        return super.getPopulatedView(mView, parent);
    }

    @Override
    public View getPopulatedView(View mView, ViewGroup parent, boolean showHeaderText) {
        return super.getPopulatedView(mView, parent, showHeaderText);
    }

    @Override
    public View getPopulatedView(View mView, ViewGroup parent, boolean showHeaderText, Boolean isScrolling) {
        return super.getPopulatedView(mView, parent, showHeaderText, isScrolling);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
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

        Backendless.UserService.login(identity, password, new DefaultCallback<BackendlessUser>(mContext) {
            public void handleResponse(BackendlessUser backendlessUser) {
                super.handleResponse(backendlessUser);
                mContext.startActivity(new Intent(mContext, LoginSuccessActivity.class));

            }
        }, rememberLogin);
    }





}
