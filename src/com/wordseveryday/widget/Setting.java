package com.wordseveryday.widget;

import com.wordseveryday.util.FileHelper;
import com.wordseveryday.util.NetHelper;
import com.wordseveryday.util.ParseHelper;
import com.wordseveryday.util.UpdateService;
import com.wordseveryday.util.WordsConstant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Setting extends Activity{
    private static final int FINISHED = 0;
    private static final int BAD_NET = 1;
    String webPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });
    }
    public Runnable runnable = new Runnable() {
        
        @Override
        public void run() {
            webPage = NetHelper.getWebPage(WordsConstant.SOURSE_URL);
            if (webPage == null) {
                mHandler.sendEmptyMessage(BAD_NET);
            }else {
                mHandler.sendEmptyMessage(FINISHED);
            }
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
                    Toast.makeText(Setting.this, R.string.app_name, Toast.LENGTH_LONG).show();
                }
                startService(new Intent(Setting.this, UpdateService.class));
                break;
            case BAD_NET:
                Toast.makeText(Setting.this, R.string.bad_net, Toast.LENGTH_LONG).show();
            default:
                break;
            }
            super.handleMessage(msg);
        }
        
    };
}
