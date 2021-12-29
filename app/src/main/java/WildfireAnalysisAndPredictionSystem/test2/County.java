package WildfireAnalysisAndPredictionSystem.test2;

public class County {
    private String countyName;
    private Boolean isFav;

    public County(String countyName, Boolean isFav) {
        this.countyName = countyName;
        this.isFav = isFav;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }
}
