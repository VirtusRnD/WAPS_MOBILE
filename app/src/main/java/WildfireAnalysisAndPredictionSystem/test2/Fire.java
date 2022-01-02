package WildfireAnalysisAndPredictionSystem.test2;



public class Fire {
private String countyName;
private String date;

    public Fire() {
    }

    public Fire(String countyName, String date) {
        this.countyName = countyName;
        this.date = date;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
