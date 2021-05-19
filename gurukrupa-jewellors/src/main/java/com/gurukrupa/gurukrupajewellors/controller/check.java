package com.gurukrupa.gurukrupajewellors.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class check {
    private final String IS_SITE_UP="Sit is UP";
    private final String IS_SITE_DOWN="Sit is DOWN";
    private final String INCORRECT_URL="URL is Incorrect";
    @GetMapping("/check")
    public String getUrlMessage(@RequestParam String url)
    {
        String returnMessage="";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection=(HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCodeCategory = connection.getResponseCode()/100;
            if( responseCodeCategory!=2 || responseCodeCategory!=3){
                returnMessage = IS_SITE_DOWN+responseCodeCategory;
            }
            else
            {
                returnMessage = IS_SITE_UP;
            }
        } catch (MalformedURLException e) {
           
            returnMessage=INCORRECT_URL;
        } catch (IOException e) {
            
            returnMessage=IS_SITE_DOWN;
        }
        return returnMessage;
    }
}
