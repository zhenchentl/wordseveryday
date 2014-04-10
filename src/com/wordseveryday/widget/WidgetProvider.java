package com.wordseveryday.widget;

import com.wordseveryday.util.UpdateService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;

public class WidgetProvider extends AppWidgetProvider{

    private boolean doubleClike = false;
    private long clickTime;
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Time time = new Time();
        time.setToNow();
        Intent intent = new Intent(context, UpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC, time.toMillis(true), 86400000, pendingIntent);
        Log.i("abc", "update start");
        doubleClike = false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("click")) {
            context.startActivity(new Intent(context, Setting.class));
        }
        Log.i("abc", "on Receive");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        doubleClike = false;
        clickTime = 0;
        Log.i("abc", "on enabled");
    }


}