package com.polytech.iot.utilitaire;

import com.polytech.iot.Controller.TauxGazController;
import com.polytech.iot.Domain.TauxGazEntity;
import com.polytech.iot.Service.TauxGazService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class GazTask extends TimerTask {

    @Override
    public void run() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iot","userepul","epul");
            Statement stmt=con.createStatement();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            stmt.executeUpdate("INSERT INTO `mesure`(`quantiteGaz`, `dateMesure`) " +
                    "VALUES ("+returnValue()+",'"+dtf.format(LocalDateTime.now())+"')");

            con.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public int returnValue() throws JSONException, IOException {
        String urlTest = "http://192.168.2.41";
        JSONObject json;
        json = readJsonFromUrl(urlTest+"/tauxGaz");

        return (Integer)json.get("value");
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

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
