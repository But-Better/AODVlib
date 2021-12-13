package lora;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoraCommandoTest {

    @Test
    void loraCommando() {
        for (int i = 0; i < 2; i++) {
            byte[] value = LoraCommando.changeAddress("FF12")[i].getBytes();
            Assertions.assertEquals(10, value[value.length - 1]);
            Assertions.assertEquals(13, value[value.length - 2]);
        }
    }

    @Test
    void loraCommandoSendMsg() {
        for (int i = 0; i < 2; i++) {
            byte[] value = LoraCommando.sendMsg("hallo wie gehts dir")[i].getBytes();
            Assertions.assertEquals(10, value[value.length - 1]);
            Assertions.assertEquals(13, value[value.length - 2]);
        }
    }
}
