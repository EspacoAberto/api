package espacoaberto.backend.service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Optional;

public class ImageUploadExample {

    public static String uploadImageBase64(String base64Image, String clientId) throws IOException {
        String uploadEndpoint = "https://api.imgur.com/3/image/";

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uploadEndpoint);
        httpPost.setHeader("Authorization", "Client-ID " + clientId);

        byte[] imageData = Base64.getDecoder().decode(base64Image);

        ByteArrayEntity entity = new ByteArrayEntity(imageData, ContentType.IMAGE_JPEG);
        httpPost.setEntity(entity);
        System.out.println("\nEntity content:" + entity.getContent());
        System.out.println("\n\n\n\nEntity content type" + entity.getContentType());
        System.out.println("\nEntity content encoding: " + entity.getContentEncoding());
        System.out.println("\nEntity content length: " + entity.getContentLength());
        System.out.println("\nHttp URL: " + httpPost.getURI());
        System.out.println("\nHttp Entity: " + httpPost.getEntity());
        System.out.println("\nHttp Method: " + httpPost.getMethod());
        System.out.println("\nHttp Headers: " + httpPost.getAllHeaders());

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            // Analisar a resposta JSON
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

            System.out.println(jsonResponse);

            // Obter a URL da imagem
            System.out.println(jsonResponse.getAsJsonObject("data"));
            String imageUrl = jsonResponse.getAsJsonObject("data").get("link").getAsString();

            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    public static String uploadImage(byte[] imageData, String clientId) {
        String uploadEndpoint = "https://api.imgur.com/3/image";

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uploadEndpoint);
        httpPost.setHeader("Authorization", "Client-ID " + clientId);

        ByteArrayEntity entity = new ByteArrayEntity(imageData, ContentType.APPLICATION_OCTET_STREAM);
        httpPost.setEntity(entity);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);
            // Analisar a resposta JSON
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);

            // Obter a URL da imagem
            String imageUrl = jsonResponse.getAsJsonObject("data").get("link").getAsString();

            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    }
