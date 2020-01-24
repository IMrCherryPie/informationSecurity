package vvv;

import java.util.HashMap;

public class DocumentRights {

    private int idDataPacket;
    private String name;
    private OpportunitiesOfUser opportunitiesOfUser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    DocumentRights (int idDataPacket, String name, boolean onlyRead, boolean readWrite, boolean transferOfRights, boolean fullRights){
        this.idDataPacket = idDataPacket;
        this.name = name;
        opportunitiesOfUser = new OpportunitiesOfUser(onlyRead, readWrite, transferOfRights, fullRights);
    }

    public OpportunitiesOfUser getOpportunitiesOfUser() {
        return opportunitiesOfUser;
    }

    public int getIdDataPacket() {
        return idDataPacket;
    }

    public void setIdDataPacket(int idDataPacket) {
        this.idDataPacket = idDataPacket;
    }

}
