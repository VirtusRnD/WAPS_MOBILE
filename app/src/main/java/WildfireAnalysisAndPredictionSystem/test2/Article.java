package WildfireAnalysisAndPredictionSystem.test2;

public class Article {
    private String authors;
    private String title;
    private String link;
    private int year;


    public Article(String authors, String title, String link, int year) {
        this.authors = authors;
        this.title = title;
        this.link = link;
        this.year = year;

    }

    public String getYear() {
        return this.year + "";
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
