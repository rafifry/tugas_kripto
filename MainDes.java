package DES;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Naufal A on 08/10/2016.
 */
public class MainDes {
    public static void main(String[] argv) throws UnsupportedEncodingException {
        String teks = "COMPUTER";
        DesEncrypter encrypter = new DesEncrypter(teks);
        List<String> aa = new ArrayList<String>();
        String bb;
        aa = encrypter.encrypt("COMPUTER","133457799BBCDFF1");
        String x = "";
        for (String a:aa) {
            x += a;
        }
        BigInteger temp = new BigInteger(x, 2);
        System.out.println(temp.toString(16));
    }
}