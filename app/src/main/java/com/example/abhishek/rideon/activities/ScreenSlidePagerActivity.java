package com.example.abhishek.rideon.activities;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.rideon.R;
import com.example.abhishek.rideon.views.LoginView;

/**
 * Created by Abhishek on 26-01-2016.
 */
public class ScreenSlidePagerActivity extends android.support.v4.app.Fragment {
    int pos;
    Context context;
    ActionBar actionBar;
    ViewGroup rootView;
    public ScreenSlidePagerActivity(int position,Context mcontext) {
        this.pos = position;
        this.context=mcontext;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(pos==0) {
            rootView = (ViewGroup) new LoginView(context).getPopulatedView(null,container,false);
            //rootView = (ViewGroup) inflater.inflate(R.layout.logindetails, container, false);
            /*Intent intent = new Intent("android.intent.action.LoginActivity");
            startActivity(intent);*/
        }
        else if(pos==1)
        {
            rootView = (ViewGroup) new LoginView(context).getPopulatedView(null,container,false);
            //(ViewGroup) inflater.inflate(R.layout.signupdetails, container, false);
           /* Intent intent = new Intent("android.intent.action.RegisterActivity");
            startActivity(intent);*/
        }
        else
        {
            rootView = (ViewGroup) new LoginView(context).getPopulatedView(null, container, false);

            //rootView = (ViewGroup) inflater.inflate(R.layout.logindetails, container, false);
            /*Intent intent = new Intent("android.intent.action.LoginActivity");
            startActivity(intent);**/
        }
        return rootView;
    }
}

