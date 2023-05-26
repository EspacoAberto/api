package espacoaberto.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImgurApiResponse {

    @JsonProperty("link")
    private String link;

    // Getters e Setters

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
