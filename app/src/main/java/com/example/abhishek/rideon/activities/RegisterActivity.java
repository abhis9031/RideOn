package com.example.abhishek.rideon.activities;

/**
 * Created by Abhishek on 10-02-2016.
 */
        import android.app.Activity;
        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.*;
        import com.backendless.Backendless;
        import com.backendless.BackendlessUser;
        import com.example.abhishek.rideon.R;
        import com.example.abhishek.rideon.callback.DefaultCallback;
        import com.example.abhishek.rideon.utils.RideOnUser;

public class RegisterActivity extends Activity
{
    private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat( "yyyy/MM/dd" );

    private EditText emailField;
    private EditText nameField;
    private EditText phone;
    private EditText passwordField;

    private Button registerButton;

    private String email;
    private String name;
    private String password;

    private RideOnUser user;

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        initUI();
    }

    private void initUI()
    {
        emailField = (EditText) findViewById( R.id.emailField );
        nameField = (EditText) findViewById( R.id.namefield );
        passwordField = (EditText) findViewById( R.id.passwordField );
        phone=(EditText)findViewById(R.id.phone);
        registerButton = (Button) findViewById( R.id.signupButton );

        registerButton.setOnClickListener( new Button.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                onRegisterButtonClicked();
            }
        } );
    }

    private void onRegisterButtonClicked()
    {
        String emailText = emailField.getText().toString().trim();
        String nameText = nameField.getText().toString().trim();
        String passwordText = passwordField.getText().toString().trim();

        if ( emailText.isEmpty() )
        {
            showToast( "Field 'email' cannot be empty." );
            return;
        }

        if ( passwordText.isEmpty() )
        {
            showToast( "Field 'password' cannot be empty." );
            return;
        }

        if( !emailText.isEmpty() )
        {
            email = emailText;
        }

        if( !nameText.isEmpty() )
        {
            name = nameText;
        }

        if( !passwordText.isEmpty() )
        {
            password = passwordText;
        }

        user = new RideOnUser();

        if( email != null )
        {
            user.setEmail( email );
        }

        if( name != null )
        {
            user.setName( name );
        }

        if( password != null )
        {
            user.setPassword( password );
        }

        Backendless.UserService.register( user, new DefaultCallback<BackendlessUser>( RegisterActivity.this )
        {
            @Override
            public void handleResponse( BackendlessUser response )
            {
                super.handleResponse( response );
                startActivity( new Intent( RegisterActivity.this, RegistrationSuccessActivity.class ) );
                finish();
            }
        } );
    }

    private void showToast( String msg )
    {
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }
}
