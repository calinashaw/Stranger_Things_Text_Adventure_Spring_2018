package com.example;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;


public class DownloadingParse {
    static int okInput = 200;

    public static Layout makeApiRequest(String url) throws UnirestException, MalformedURLException {
        final HttpResponse<String> stringHttpResponse;
        // This will throw MalformedURLException if the url is malformed.
        new URL(url);

        stringHttpResponse = Unirest.get(url).asString();
        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
        if (stringHttpResponse.getCode() == okInput) {
            String json = stringHttpResponse.getBody();
            Gson gson = new Gson();
            Layout layout = gson.fromJson(json, Layout.class);
            return layout;
        }return null;
    }
}
