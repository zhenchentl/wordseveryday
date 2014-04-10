package com.wordseveryday.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.http.util.EncodingUtils;

import android.os.Environment;
import android.util.Log;

public class FileHelper {
    
    public static String getWordsPath(){
        return Environment.getExternalStorageDirectory()
                + File.separator + WordsConstant.WORDS_PATH;
    }
    
    public static boolean writeToFile(String content){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return false;
        }
        File fileDir = new File(getWordsPath());
        if(!fileDir.mkdirs()){
            Log.i("abc", "mkdir failed");
        }
        try {
            File file = new File(fileDir, WordsConstant.WORDS_FILE);
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static String readFromFile(){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return null;
        }
        try {
            String fileUrl = getWordsPath() + WordsConstant.WORDS_FILE;
            FileInputStream inputStream = new FileInputStream(fileUrl);
            int length = inputStream.available();
            byte [] buffer = new byte[length];
            inputStream.read(buffer);
            String content = EncodingUtils.getString(buffer, "UTF-8");
            inputStream.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
