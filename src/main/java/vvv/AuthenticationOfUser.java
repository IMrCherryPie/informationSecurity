package vvv;

public class AuthenticationOfUser {

    private String password;

    AuthenticationOfUser(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
