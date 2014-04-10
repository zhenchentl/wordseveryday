package com.wordseveryday.util;

import com.wordseveryday.widget.R;
import com.wordseveryday.widget.Setting;
import com.wordseveryday.widget.WidgetProvider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ViewController {

    public static boolean updateWidGet(Context context){
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), 
                R.layout.mywidget);
        String [] words = ParseHelper.getWordsFromLocal();
        String one;
        if (words == null) {
            one = context.getResources().getString(R.string.app_name);
        }else {
            one = words[(int)(Math.random() * words.length)];
        }
        remoteViews.setTextViewText(R.id.content, one);

        Intent clickIntent = new Intent(context, Setting.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, clickIntent, 
                PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.content, pIntent);
        widgetManager.updateAppWidget(new ComponentName(context, WidgetProvider.class), remoteViews);
        return true;
    }
}
