package san_francisco_app;

import java.util.List;

public class Film {

    private String title;
    private String director;
    private String directorURI;

    public List<String> getBirthPlace() {
        return birthPlace;
    }

    private List<String> birthPlace;

    public void setBirthPlace(List<String> birthPlace) {
        this.birthPlace = birthPlace;
    }

    private String writer;
    private String location;

    public void setFilmUri(String filmUri) {
        this.filmUri = filmUri;
    }

    public String getFilmUri() {
        return filmUri;
    }

    private String filmUri;

    public Film() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirectorURI() {
        return directorURI;
    }

    public void setDirectorURI(String directorURI) {
        this.directorURI = directorURI;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
