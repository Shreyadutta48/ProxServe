package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
public class Main{
    public static void main(String[] args){
 //HttpURLConnection connection = null;
        //Method1-->java.net.HttpURLconnection
      /** BufferedReader reader;
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
            }//System.out.println(responseContent.toString());
        } catch (MalformedURLException e) {
           e.printStackTrace(); 
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            connection.disconnect()
        }*/
        
//Method 2:java.net.http.HttpClient
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
.thenApply(HttpResponse::body)
.thenApply(Main::parse)
.join();
    
}
public static String parse(String responseBody){
    JSONArray albums = new JSONArray(responseBody);
    StringBuilder result = new StringBuilder();
    for(int i=0;i < albums.length();i++)
    {
        JSONObject album = albums.getJSONObject(i);
        int id = album.getInt("id");
        int userId = album.getInt("userId");
        String title = album.getString("title");
        String albuminfo = id+" "+userId+" "+title;
        System.out.println(albuminfo);
        result.append(albuminfo).append("\n");
    }
    return result.toString();
}
}