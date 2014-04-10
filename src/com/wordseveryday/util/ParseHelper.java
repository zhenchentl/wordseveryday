package com.wordseveryday.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.util.Log;

public class ParseHelper {
    
    public static String getContentFromHtml(String webPageStr){
        try {
            Document doc = Jsoup.parse(webPageStr);
            Element element_content = doc.getElementsByClass(WordsConstant.DIV_BLOG_START)
                    .get(0).nextElementSibling();
            String contentString = element_content.text();
            return contentString;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public static String [] getWordsFromLocal(){
        String content = FileHelper.readFromFile();
        if (content == null) {
            return null;
        }
        return content.split("\\|");
    }
}
