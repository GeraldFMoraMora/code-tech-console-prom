package model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import model.entity.Customer;

import org.json.*;

public class ConnectionService {

    private final String URL_API = "http://localhost:8081/client/";

    public void addClient( Customer customer) throws Exception{

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        URL url = new URL(URL_API+"addClient");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");        
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

        JSONObject param = new JSONObject();
        param.put("id", customer.getId());
        param.put("firstName", customer.getFirstname());
        param.put("lastName", customer.getLastname());
        param.put("phone", customer.getPhone());
        param.put("birthdate", outputDateFormat.format(customer.getBirthdate()));

        writer.write(param.toString());
        writer.flush();
        writer.close();

        BufferedReader responce = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = responce.readLine()) != null) {
            content.append(inputLine);
        }
        responce.close();
        connection.disconnect();
        
        System.out.println(content.toString()); 

    }
    
}
