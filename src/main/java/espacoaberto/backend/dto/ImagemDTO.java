package espacoaberto.backend.dto;

public class ImagemDTO {
    private String url;

    public ImagemDTO(String url) {
        this.url = url;
    }

    public ImagemDTO() {}


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
