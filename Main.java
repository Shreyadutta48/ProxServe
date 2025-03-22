package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Main{
    public static void main(String[] args)
    {
     HttpURLConnection connection = null;
        //Method1-->java.net.HttpURLconnection
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");  
            connection = (HttpURLConnection) url.openConnection();
            //Request setting
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Response Code:"+status);
            if(status > 200)
            {
             reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
             while((line =reader.readLine()) != null)
             {
                responseContent.append(line);
             }reader.close();
            }
            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null)
                {
                    responseContent.append(line);
                }reader.close();
            }System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
           e.printStackTrace(); 
        }catch (IOException e){
            e.printStackTrace();
        }
        

    }
}