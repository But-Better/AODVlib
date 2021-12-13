package aodv.Handle;

import aodv.packet.RreqPacket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testRREQPacketToBase64String(){
        RreqPacket rreqPacket = new RreqPacket(0,10,120,1,80,2,6,100);
        assertEquals("AP8KeAFQAgZk",Util.rreqToBase64String(rreqPacket));
    }

}