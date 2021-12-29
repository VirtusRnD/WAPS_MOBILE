package WildfireAnalysisAndPredictionSystem.test2;

import com.google.firebase.Timestamp;

import java.sql.Time;
import java.util.Date;

public class Fire {
private String countyName;
private Date date;

    public Fire() {
    }

    public Fire(String countyName, Date date) {
        this.countyName = countyName;
        this.date = date;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
