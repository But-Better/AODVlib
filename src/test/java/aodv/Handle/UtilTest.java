package aodv.Handle;

import aodv.packet.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testRREQPacketToBase64String(){
        RreqPacket rreqPacket = new RreqPacket(0,10,120,1,80,2,6,100);
        assertEquals("AP8KeAFQAgZk",Util.rreqToBase64String(rreqPacket));
    }

    @Test
    void testPackageToBase64String(){
        RreqPacket rreqPacket = new RreqPacket(0,10,120,1,80,2,6,100);
        assertEquals("AP8KeAFQAgZk",Util.encodePackage(rreqPacket));

        RrepPacket rrepPacket = new RrepPacket(0,9,7,120,1,80,2,11,6);
        assertEquals("EAkHeAFQAgsG",Util.encodePackage(rrepPacket));

        int[] destAdresses = {20};
        int[] destSequences = {110};
        RerrPacket rerrPacket = new RerrPacket(0,12,1,1,destAdresses,destSequences);
        assertEquals("IAwBARRu",Util.encodePackage(rerrPacket));

        int[] destAdresses2 = {21,18};
        int[] destSequences2 = {51,10};
        RerrPacket rerrPacket2 = new RerrPacket(0,8,3,2,destAdresses2,destSequences2);
        assertEquals("IAgDAhUzEgoA",Util.encodePackage(rerrPacket2));

        MsgPacket msgPacket = new MsgPacket(0,2,15,4,100,4,"AODV");
        assertEquals("MAIPBGQEAODV",Util.encodePackage(msgPacket));

        AckPacket ackPacket = new AckPacket(0,3,67);
        assertEquals("QAND",Util.encodePackage(ackPacket));

    }

}