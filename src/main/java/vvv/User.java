package vvv;

import java.util.ArrayList;

public class User {

    private String name;
    private boolean administrator = false;
    private AuthenticationOfUser authenticationOfUser;
    private ArrayList<DocumentRights> arrayListDocumentRights = new ArrayList<DocumentRights>();

    public AuthenticationOfUser getAuthenticationOfUser() {
        return authenticationOfUser;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public ArrayList<DocumentRights> getArrayListDocumentRights() {
        return arrayListDocumentRights;
    }

    public User(String name, String password){
        this.name = name;
        this.authenticationOfUser = new AuthenticationOfUser(password);
    }

    public User(String name, String password, boolean administrator){
        this.name = name;
        this.authenticationOfUser = new AuthenticationOfUser(password);
        this.administrator = administrator;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
