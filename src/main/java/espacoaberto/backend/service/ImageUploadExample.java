package espacoaberto.backend.service;
/*import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ImageUploadExample {

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
}*/