package espacoaberto.backend.service;
/*
import espacoaberto.backend.dto.ImgurApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ImgurApiClient {
    @Value("${imgur.api.client-id}")
    private String clientId;

    @Value("${imgur.api.client-secret}")
    private String clientSecret;

    private RestTemplate restTemplate;

    public ImgurApiClient() {
        this.restTemplate = new RestTemplate();
    }

    // Implemente os métodos necessários para interagir com a API do Imgur
    // ...
    public String uploadImage(byte[] imageBytes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Client-ID " + clientId);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", imageBytes);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String url = "https://api.imgur.com/3/upload";

        // Enviar a solicitação POST para a API do Imgur
        ImgurApiResponse response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ImgurApiResponse.class).getBody();

        if (response != null ) {
            return response.getLink();
        }

        return null;
    }
}*/
