package com.example.patrickjmartin.android_sprint3;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final int TIMEOUT = 3000;

    public static String httpRequest(String stringUrl, String requestType){
        String result = "";
        InputStream stream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(stringUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.setRequestMethod(requestType);

            if(requestType.equals(GET)){
                connection.connect();
            }

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                stream = connection.getInputStream();
                if(stream != null){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    result = sb.toString();
                }
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
            result = e.getMessage();
        }catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        }finally {
            if(connection != null){
                connection.disconnect();
            }
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }



}