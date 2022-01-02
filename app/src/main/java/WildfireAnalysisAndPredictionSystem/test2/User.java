package WildfireAnalysisAndPredictionSystem.test2;


import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;
/**
 * @author hasanaliozkan
 * **/
public class User {
    private String username;
    private String password;
    private String email;
    private ArrayList<DocumentReference> friends;
    private List<String> counties;

    public User() {
    }
/*
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
*/
    public User(String username, String password, String email, ArrayList<DocumentReference>  friends, ArrayList<String> counties) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.friends = friends;
        this.counties = counties;
    }

    public List<String> getCounties() {
        return counties;
    }

    public void setCounties(List<String> counties) {
        this.counties = counties;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<DocumentReference>  getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<DocumentReference>  friends) {
        this.friends = friends;
    }
}
