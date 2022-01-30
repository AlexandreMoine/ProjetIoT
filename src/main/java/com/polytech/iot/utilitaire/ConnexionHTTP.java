package com.polytech.iot.utilitaire;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
public class ConnexionHTTP {

//    public String urlTest = "http://192.168.2.41";    //Linksys RM
    public String urlTest = "http://192.168.43.20";   //Huawei de Quentin

    public Object getSeuil() throws Exception {
        JSONObject json;
        json = readJsonFromUrl(urlTest+"/seuil");
        return json.get("value");
    }

    public Object getTauxGaz() throws Exception {
        JSONObject json;
        json = readJsonFromUrl(urlTest+"/tauxGaz");

        return json.get("value");
    }

    public void setSeuil(int newSeuil) throws IOException, InterruptedException {
        String url = urlTest+"/modifierSeuil";

        String body = "{\"seuil\":"+newSeuil+"}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    public void setVenti(boolean venti) throws IOException, InterruptedException {
        String url = urlTest+"/ventilation";

        String body = "{\n" +
                "\"ventilateur\":"+venti+"\n" +
                "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}