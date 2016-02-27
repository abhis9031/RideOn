package com.example.abhishek.rideon.utils;

/**
 * Created by Abhishek on 10-02-2016.
 */
import com.backendless.BackendlessUser;

public class RideOnUser  extends BackendlessUser{

        public String getEmail()
        {
            return super.getEmail();
        }

        public void setEmail( String email )
        {
            super.setEmail( email );
        }

        public String getPassword()
        {
            return super.getPassword();
        }

        public String getName()
        {
            return (String) super.getProperty( "name" );
        }

        public void setName( String name )
        {
            super.setProperty( "name", name );
        }
    }

