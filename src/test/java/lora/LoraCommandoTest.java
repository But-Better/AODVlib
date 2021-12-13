package lora;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoraCommandoTest {

    @Test
    void loraCommando() {
        byte[] value = LoraCommando.changeAddress("FF12")[0].getBytes();
        Assertions.assertEquals(10, value[value.length-1]);
        Assertions.assertEquals(13, value[value.length-2]);
    }
}
