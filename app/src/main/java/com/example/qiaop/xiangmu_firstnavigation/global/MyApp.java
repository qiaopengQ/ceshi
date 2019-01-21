package com.example.qiaop.xiangmu_firstnavigation.global;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {
    private static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        Context context = getApplicationContext();

        CrashReport.initCrashReport(getApplicationContext(), "7e8f7d0c14", false);

    }

    public static MyApp getApplication(){
        return myApp;
    }
}
