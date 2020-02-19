package is.hi.hbv.confirmpay.ConfirmPay.Hashing;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassEncrypter {

    private String Salt = "S$LT&P#PP#R!$FJ$(#JKD))!KSKSKSKS";
    public PassEncrypter() {

    }

    public String encrypt(String password) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String saltyPassword = password + Salt;
        md.update(saltyPassword.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;


    }
}
