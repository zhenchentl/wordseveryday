package com.wordseveryday.util;


import com.wordseveryday.widget.R;
import com.wordseveryday.widget.Setting;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class GetNewContentService extends Service{

    private static final int FINISHED = 0;
    private static final int BAD_NET = 1;
    String webPage;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("abc", "service create");
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("abc", "service start");
        new Thread(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Runnable runnable = new Runnable() {
        
        @Override
        public void run() {
            webPage = NetHelper.getWebPage(WordsConstant.SOURSE_URL);
            if (webPage == null) {
                mHandler.sendEmptyMessage(BAD_NET);
            }
            mHandler.sendEmptyMessage(FINISHED);
        }
    };
    
    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case FINISHED:
                String content = ParseHelper.getContentFromHtml(webPage);
                Log.i("abc", "content:" + content);
                if(FileHelper.writeToFile(content)){
                    Toast.makeText(GetNewContentService.this, R.string.app_name, Toast.LENGTH_LONG).show();
                }
                startService(new Intent(GetNewContentService.this, UpdateService.class));
                break;
            case BAD_NET:
                Toast.makeText(GetNewContentService .this, R.string.bad_net, Toast.LENGTH_LONG).show();
            default:
                break;
            }
            super.handleMessage(msg);
        }
        
    };
}
