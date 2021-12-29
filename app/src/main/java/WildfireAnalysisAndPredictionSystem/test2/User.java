package WildfireAnalysisAndPredictionSystem.test2;

import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private List<User> friends;
    private List<County> counties;

    public User() {
    }

    public User(String username, String password, String email, List<User> friends, List<County> counties) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.friends = friends;
        this.counties = counties;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
