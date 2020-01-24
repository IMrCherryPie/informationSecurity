package vvv;

public class OpportunitiesOfUser {

    private boolean onlyRead;
    private boolean readWrite;
    private boolean transferOfRights;
    private boolean fullRights;

    OpportunitiesOfUser (boolean onlyRead, boolean readWrite, boolean transferOfRights, boolean fullRights){
        this.onlyRead = onlyRead;
        this.readWrite = readWrite;
        this.transferOfRights = transferOfRights;
        this.fullRights = fullRights;
    }

    public String getTrueRights(){
        StringBuilder trueRights = new StringBuilder();
        if (onlyRead)
            trueRights.append("Чтение; ");
        if (readWrite)
            trueRights.append("Запись; ");
        if (transferOfRights)
            trueRights.append("Передача прав; ");
        if (fullRights)
            trueRights.append("Господь БОГ");
        return (trueRights.length() > 3) ? trueRights.toString() : trueRights.append("У вас нет прав").toString() ;
    }

    public boolean isOnlyRead() {
        return onlyRead;
    }

    public void setOnlyRead(boolean onlyRead) {
        this.onlyRead = onlyRead;
    }

    public boolean isReadWrite() {
        return readWrite;
    }

    public void setReadWrite(boolean readWrite) {
        this.readWrite = readWrite;
    }

    public boolean isTransferOfRights() {
        return transferOfRights;
    }

    public void setTransferOfRights(boolean transferOfRights) {
        this.transferOfRights = transferOfRights;
    }

    public boolean isFullRights() {
        return fullRights;
    }

    public void setFullRights(boolean fullRights) {
        this.fullRights = fullRights;
    }
}
