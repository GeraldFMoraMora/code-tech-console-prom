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
        
        JSONObject resJson = new JSONObject(content.toString());
        //if (resJson.get("error").toString().equals("true")){
        System.out.println(resJson.get("message"));

    }

    public void delClient(String id) throws Exception{

        URL url = new URL(URL_API+"delClient/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");  

        BufferedReader responce = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = responce.readLine()) != null) {
            content.append(inputLine);
        }
        responce.close();
        connection.disconnect();
        
        JSONObject resJson = new JSONObject(content.toString()); 
        System.out.println(resJson.get("message"));

    }

    public void updateClient( Customer customer) throws Exception{

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        URL url = new URL(URL_API+"updateClient");
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
        
        JSONObject resJson = new JSONObject(content.toString()); 
        System.out.println(resJson.get("message"));

    }

    public String getClientById(String id) throws Exception{

        URL url = new URL(URL_API+"getClientById/"+id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");  

        BufferedReader responce = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = responce.readLine()) != null) {
            content.append(inputLine);
        }
        responce.close();
        connection.disconnect();

        String res = "";
        JSONObject resJson = new JSONObject(content.toString());
        if (resJson.get("error").toString().equals("true")){
            System.out.println(resJson.get("message"));
        }else{
            res = resJson.get("user").toString();
        }

        return res;
    }

    public void getClientsByFilters(int optionFilter)  throws Exception{

        URL url;

        switch (optionFilter) {
            case 1:
                url = new URL(URL_API+"listClientByBirthdate");
                break;
            case 2:
                url = new URL(URL_API+"listClientById");
                break;
            case 3:
                url = new URL(URL_API+"listClientByName");
                break;
            default:
                url = new URL(URL_API+"listClientById");
                break;
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");  

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
