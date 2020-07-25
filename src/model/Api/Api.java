package model.Api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Api{

    private static String url;
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;

    public Api(){
        Api.url = null;
        this.client = null;
        this.request = null;
    }

    public Api(String url){
        Api.url = url;
        this.client =  HttpClient.newHttpClient();
        this.request =  HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();
    }

    public void initialize() throws IOException, InterruptedException, IllegalArgumentException{
        if(url == null){
           throw new IllegalArgumentException("Empty url.");
        }
        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void setUrl(String url){
        Api.url = url;
    }

    public String getResponseBody() {
        return this.response.body();
    }

    public int getStatusCode(){
        return this.response.statusCode();
    }

    public <T> List<T> convertResponse(Class<T> anyClass) {
        return new Gson().fromJson(response.body(), TypeToken.
                getParameterized(List.class, anyClass).getType());
    }
}
