package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
// import javax.net.ssl.HttpsURLConnection;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.client.methods.HttpPut;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import src.DTOs.Data;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScoreDataClient {
    private static final String URL_API = "http://localhost:8080/scoreData";
    private HttpURLConnection connection;

    public ScoreDataClient() {
        if (connection == null) {
            connection = getConnection();
        }
    }

    private HttpURLConnection getConnection() {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(URL_API);
            return (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    protected void sendDataToAPI(Data data) {
        try {
            connection.setRequestMethod("PUT");
            // build json package with relevant data

        } catch (Exception e) {
            System.out.println("ProtocolException: " + e.getMessage());
        }
    }

    public List<Data> getDataFromAPI() {
        try {
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {// if get request works
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                JSONArray jsonResponseArray = new JSONArray(response.toString());
                return parseDataFromJSONObject(jsonResponseArray);
            }

        } catch (ProtocolException e) {
            System.out.println("ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            connection.disconnect();
        }
        return null;
    }

    private List<Data> parseDataFromJSONObject(JSONArray jsonDataArray) {
        ArrayList<Data> dataList = new ArrayList<Data>();

        // parse the JSON Obj and create and add data obj to array
        for (Object o : jsonDataArray){
            JSONObject jObj =  (JSONObject) o;
            int score = jObj.getInt("score");
            String gameType = jObj.getString("gameType");
            String username = jObj.getString("name");

            Data data = new Data(username, gameType, score);
            dataList.add(data);
        }
        //TODO: check dataList
        for (Data d : dataList){
            System.out.println(d.toString());
        }
        return dataList;
    }

}