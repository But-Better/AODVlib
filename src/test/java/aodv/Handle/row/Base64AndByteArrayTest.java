package aodv.Handle.row;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Base64;

public class Base64AndByteArrayTest {

    //https://www.baeldung.com/java-base64-encode-and-decode
    @Disabled
    @Test
    void base64EqualsByteArray() {
        String base64Code = "QAND";
        byte[] bytes = new byte[]{64, 3, 67};

        System.out.println("Base64 Code: " + base64Code);
        System.out.println("Byte[] : " + Arrays.toString(Base64.getDecoder().decode(base64Code)));
        byte[] decodedBytes = Base64.getDecoder().decode(base64Code);
        String decodedString = new String(decodedBytes);
        System.out.println(" " + Arrays.toString(decodedBytes));
    }
}
