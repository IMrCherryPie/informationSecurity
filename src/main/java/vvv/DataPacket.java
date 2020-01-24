package vvv;

import java.util.ArrayList;

public class DataPacket {

    private String name;
    private int cash;

    public DataPacket(String name, int cash, ArrayList<User> users, int idDataPacket){
        this.name = name;
        this.cash = cash;
        for (User user : users) {
            if(!user.isAdministrator())
                user.getArrayListDocumentRights().add(new DocumentRights(idDataPacket, name,false,false,false,false));
            else user.getArrayListDocumentRights().add(new DocumentRights(idDataPacket, name,false,false,false,true));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void readObject(int id){
        System.out.println("Операция выполняется");
    }

    public void change(int idDataPacket){
        System.out.println("Выполняю изменение");
    }

    public int transferRights(int idUser, ArrayList<User> users, int idDataPacket){
        try {
            users.get(idUser).getArrayListDocumentRights().get(idDataPacket).getOpportunitiesOfUser(); // Получили права на документа юзера назначения
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public int transferRights (String name, ArrayList<User> users, String nameDataPacket){
        for (User user: users) {
            if (user.getName().equals(name)){
                System.out.println("Положительные права переданы");
                return 0;
            }
        }
        return 1;
    }

}
