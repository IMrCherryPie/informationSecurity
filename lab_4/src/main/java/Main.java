import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;

import javax.crypto.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, NoSuchPaddingException {
        String text = readFile();
        encoding(text);
        decoding(arrayListToByteArray(readFileInArrayListByte()));

    }

    private static byte[] arrayListToByteArray(ArrayList<Byte> arrayList){
        byte[] x = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            x[i] = arrayList.get(i);
        }
        return x;
    }

    private static void encoding(String text) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair kp = keyGen.genKeyPair();

        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();


        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] x = cipher.doFinal(text.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] y = cipher.doFinal(x);

        fileWrite(new String(x),"notes3Encoded.txt");
        fileWrite(new String(y),"notes3Decoded.txt");
        System.out.println("\nText is:\n" + text);
        System.out.println("\nEncrypt text:\n" + new String(x));
        System.out.println("\nDecrypt text:\n" + new String(y));
    }

    private static void decoding(byte[] x) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair kp = keyGen.genKeyPair();

        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] y = cipher.doFinal(x);

//        fileWrite(new String(y),"notes3Decoded.txt");
        System.out.println("\nDecrypt text:\n" + new String(y));
    }

    private static String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader("D:\\ДГТУ\\8 семестр\\Компьютерная безопасность\\ЛР1_2\\projects\\lab_4\\src\\main\\java\\notes3.txt")) {
            // читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return stringBuilder.toString();
    }

    private static ArrayList<Byte> readFileInArrayListByte() {
        List<Byte> arrayList =new ArrayList<Byte>();
        try (FileReader reader = new FileReader("D:\\ДГТУ\\8 семестр\\Компьютерная безопасность\\ЛР1_2\\projects\\lab_4\\src\\main\\java\\notes3.txt")) {
            // читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {
                arrayList.add((byte)c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return (ArrayList<Byte>) arrayList;
    }

    private static void fileWrite(String string, String name) {
        try (FileWriter writer = new FileWriter(name, false)) {
            writer.append(string);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}