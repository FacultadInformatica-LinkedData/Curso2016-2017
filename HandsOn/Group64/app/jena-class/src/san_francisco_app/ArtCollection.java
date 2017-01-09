package san_francisco_app;

public class ArtCollection {

    private String artist;
    private String title;
    private String titleURI;

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    private String location;
    public ArtCollection() {}

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleURI() {
        return titleURI;
    }

    public void setTitleURI(String titleURI) {
        this.titleURI = titleURI;
    }
}
