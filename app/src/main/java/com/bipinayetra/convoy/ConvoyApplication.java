package com.bipinayetra.convoy;

import android.app.Application;
import android.content.Context;

import com.bipinayetra.convoy.db.OffersDatabase;

public class ConvoyApplication extends Application {

    private static Context context;

    public ConvoyApplication(){
        context = this;

    }

    public static Context getInstance(){
        return context;
    }
}
