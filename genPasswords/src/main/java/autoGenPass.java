
public class autoGenPass {
    public static void main(String[] args) {
        for (int k = 1; k < 101; k++) {
            System.out.println("[" + k + "]" + "  " + passwordGenerator(6));
        }
    }

    public static String passwordGenerator(int size){
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < size; i++) {
            password.append(passwordGen());
        }
        return password.toString();
    }

    private static char passwordGen(){
        char password;
        double init = Math.random()*100;
        if (init < 50){
            int min = 65;
            int max = 90;
            int random = min + (int) (Math.random() * (max-min));
            password = ((char)random);
        } else {
            int min = 97;
            int max = 122;
            int random = min + (int) (Math.random() * (max-min));
            password = ((char)random);
        }
        return  password;
    }
}
